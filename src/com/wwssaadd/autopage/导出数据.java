package com.wwssaadd.autopage;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import vdll.data.msql.MySql;
import vdll.debug.DemoUtil;
import vdll.utils.ExcelUtil;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Hocean on 2017/6/5.
 */
public class 导出数据 {

    private static String dbURL = "jdbc:mysql://172.17.21.25:3306/tbi_erp?useUnicode=true&amp;characterEncoding=UTF-8&allowMultiQueries=true";
    private static String username = "tbi";
    private static String password = "desladmin";
    //private String workDir = "src/";


    public 导出数据() {
        输出目录TextField .setText(FileSystemView.getFileSystemView().getHomeDirectory().getPath());
        panel1.setPreferredSize(new Dimension(800, 500));
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MySql mySql = new MySql();
                try {
                    mySql.open(dbURL, username, password);
                    String startDate = textField1.getText();
                    String endDate = textField2.getText();
                    //region String sql
                    String sql = "\n" +
                            "\n" +
                            "\n" +
                            "SELECT t.*\n" +
                            "\n" +
                            "FROM\n" +
                            "(SELECT\n" +
                            "\t'BSP机票' AS  业务类型,\n" +
                            "-- IFNULL(g.group_name,g2.group_name) ,\n" +
                            "IFNULL(g.group_name,g2.group_name) AS 操作组 ,\n" +
                            "\n" +
                            "\t-- su.name,\n" +
                            "\tifnull(bci.company_name, '个人支付') as 子级结算方,\n" +
                            "\tCASE bat.ticket_type\n" +
                            "\tWHEN '2'\n" +
                            "\t\tTHEN '国际机票'\n" +
                            "\tWHEN '4'\n" +
                            "\t\tTHEN '国内机票'\n" +
                            "\tEND as 区分,\n" +
                            "\tsum(CASE batr.ticket_status\n" +
                            "\t\t\tWHEN 'T'\n" +
                            "\t\t\t\tTHEN 1\n" +
                            "\t\t\tELSE 0\n" +
                            "\t\t\tEND) as 件数,\n" +
                            "\tsum(batr.total_price) as 销售额,\n" +
                            "\tsum(ifnull(batr.cash_amount, 0) * ifnull(batr.comm_percent, 0) + ifnull(batr.comm_amount, 0)) as 代理费,\n" +
                            "\tsum(batr.difference_amount) as 差额\n" +
                            "FROM bas_air_trans_records batr\n" +
                            "\tLEFT JOIN sys_user su ON batr.operate_by = su.id\n" +
                            "LEFT JOIN (SELECT u.id AS user_id,\tg.id AS group_id,\tg.group_name,\tu.`name` AS user_name FROM sys_user u LEFT JOIN sys_user_group ug ON u.id = ug.user_id LEFT JOIN sys_group g ON g.id = ug.group_id GROUP BY\tu.id)g2 on g2.user_id = su.id\n" +
                            "\t,\n" +
                            "\tbas_air_tktdata bat\n" +
                            "\tLEFT JOIN bas_company_info bci ON bat.settle_company_id = bci.id AND bci.parent_id IS NOT NULL\n" +
                            "\n" +
                            "LEFT JOIN (SELECT * FROM bas_company_user GROUP BY compay_id) comuser ON comuser.compay_id = bci.id\n" +
                            "LEFT JOIN (SELECT u.id AS user_id,\tg.id AS group_id,\tg.group_name,\tu.`name` AS user_name FROM sys_user u LEFT JOIN sys_user_group ug ON u.id = ug.user_id LEFT JOIN sys_group g ON g.id = ug.group_id GROUP BY\tu.id)g on g.user_id = comuser.user_id\n" +
                            "\n" +
                            "\n" +
                            "\n" +
                            "WHERE batr.tkt_id = bat.id\n" +
                            "\t\t\tAND batr.air_type = '0'\n" +
                            "\t\t\tAND batr.del_flag = '0'\n" +
                            "\t\t\tAND bat.del_flag = '0'\n" +
                            "\t\t\tAND batr.operate_date >= DATE_FORMAT('startDate', '%Y-%m-%d 00:00:00')\n" +
                            "\t\t\tAND batr.operate_date <= DATE_FORMAT('endDate', '%Y-%m-%d 23:59:59')\n" +
                            "GROUP BY su.name, bat.ticket_type,\n" +
                            "\tifnull(bci.company_name, '个人支付')\n" +
                            "\n" +
                            "UNION ALL\n" +
                            "\n" +
                            "SELECT\n" +
                            "\t'外采酒店',\n" +
                            "IFNULL(g.group_name,g2.group_name),\n" +
                            "\n" +
                            "\t-- su.name,\n" +
                            "\tifnull(bci.company_name, '个人支付'),\n" +
                            "\t'',\n" +
                            "\tsum(bhtr.night_num),\n" +
                            "\tsum(bhtr.total_price),\n" +
                            "\t0,\n" +
                            "\tsum(bhtr.difference_amount)\n" +
                            "FROM bas_hotel_trans_records bhtr\n" +
                            "\tLEFT JOIN sys_user su ON bhtr.operate_by = su.id\n" +
                            "LEFT JOIN (SELECT u.id AS user_id,\tg.id AS group_id,\tg.group_name,\tu.`name` AS user_name FROM sys_user u LEFT JOIN sys_user_group ug ON u.id = ug.user_id LEFT JOIN sys_group g ON g.id = ug.group_id GROUP BY\tu.id)g2 on g2.user_id = su.id\n" +
                            "\n" +
                            "\t,\n" +
                            "\tbas_hotel_purchase bhp\n" +
                            "\tLEFT JOIN bas_company_info bci ON bhp.settle_company_id = bci.id AND bci.parent_id IS NOT NULL\n" +
                            "LEFT JOIN (SELECT * FROM bas_company_user GROUP BY compay_id) comuser ON comuser.compay_id = bci.id\n" +
                            "LEFT JOIN (SELECT u.id AS user_id,\tg.id AS group_id,\tg.group_name,\tu.`name` AS user_name FROM sys_user u LEFT JOIN sys_user_group ug ON u.id = ug.user_id LEFT JOIN sys_group g ON g.id = ug.group_id GROUP BY\tu.id)g on g.user_id = comuser.user_id\n" +
                            "\n" +
                            "WHERE bhp.id = bhtr.hotel_purchase_id\n" +
                            "\t\t\tAND bhp.del_flag = '0'\n" +
                            "\t\t\tAND bhtr.del_flag = '0'\n" +
                            "\t\t\tAND bhtr.operate_date >= DATE_FORMAT('startDate', '%Y-%m-%d 00:00:00')\n" +
                            "\t\t\tAND bhtr.operate_date <= DATE_FORMAT('endDate', '%Y-%m-%d 23:59:59')\n" +
                            "GROUP BY su.name,\n" +
                            "\tifnull(bci.company_name, '个人支付')\n" +
                            "\n" +
                            "UNION ALL\n" +
                            "\n" +
                            "SELECT\n" +
                            "\t'保险',\n" +
                            "IFNULL(g.group_name,g2.group_name),\n" +
                            "\n" +
                            "\n" +
                            "\t-- su.name,\n" +
                            "\tifnull(bci.company_name, '个人支付'),\n" +
                            "\t'',\n" +
                            "\tcount(bi.id),\n" +
                            "\tsum(bii.income_amount),\n" +
                            "\t0,\n" +
                            "\tsum(CASE bii.income_amount\n" +
                            "\t\t\tWHEN 20\n" +
                            "\t\t\t\tTHEN 14\n" +
                            "\t\t\tELSE 0\n" +
                            "\t\t\tEND\n" +
                            "\t)\n" +
                            "FROM bas_insurance bi\n" +
                            "\tLEFT JOIN sys_user su ON bi.create_by = su.id\n" +
                            "LEFT JOIN (SELECT u.id AS user_id,\tg.id AS group_id,\tg.group_name,\tu.`name` AS user_name FROM sys_user u LEFT JOIN sys_user_group ug ON u.id = ug.user_id LEFT JOIN sys_group g ON g.id = ug.group_id GROUP BY\tu.id)g2 on g2.user_id = su.id\n" +
                            "\n" +
                            "\t, bas_income_info bii\n" +
                            "\tLEFT JOIN bas_company_info bci ON bii.settle_company_id = bci.id AND bci.parent_id IS NOT NULL\n" +
                            "LEFT JOIN (SELECT * FROM bas_company_user GROUP BY compay_id) comuser ON comuser.compay_id = bci.id\n" +
                            "LEFT JOIN (SELECT u.id AS user_id,\tg.id AS group_id,\tg.group_name,\tu.`name` AS user_name FROM sys_user u LEFT JOIN sys_user_group ug ON u.id = ug.user_id LEFT JOIN sys_group g ON g.id = ug.group_id GROUP BY\tu.id)g on g.user_id = comuser.user_id\n" +
                            "\n" +
                            "WHERE bi.id = bii.business_id\n" +
                            "\t\t\tAND bii.business_type = '1'\n" +
                            "\t\t\tAND bi.del_flag = '0'\n" +
                            "\t\t\tAND bii.del_flag = '0'\n" +
                            "\t\t\tAND bi.create_date >= DATE_FORMAT('startDate', '%Y-%m-%d 00:00:00')\n" +
                            "\t\t\tAND bi.create_date <= DATE_FORMAT('endDate', '%Y-%m-%d 23:59:59')\n" +
                            "GROUP BY su.name,\n" +
                            "\tifnull(bci.company_name, '个人支付')\n" +
                            ")t";
                    //endregion
                    sql = sql.replace("startDate", startDate);
                    sql = sql.replace("endDate", endDate);
                    mySql.exeQ(sql);
                    List<Map<String, Object>> data = mySql.getData();
                    ExcelUtil.create(data, new String[]{"业务类型", "操作组", "子级结算方", "区分", "件数", "销售额", "代理费", "差额"}, "sheet", getWorkDir() + File.separator + startDate + " " + endDate + ".xls");
                    //System.out.println(data.get(0));
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    mySql.close();
                }
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser jfc = new JFileChooser();
                    if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                        File file = jfc.getSelectedFile();
                        Scanner input = new Scanner(file);
                        while (input.hasNext()) {
                            System.out.println(input.nextLine());
                        }
                        input.close();
                    } else
                        System.out.println("No file is selected!");
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JFileChooser fileChooser = new JFileChooser("D:\\");
                JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnVal = fileChooser.showOpenDialog(fileChooser);
                if(returnVal == JFileChooser.APPROVE_OPTION){
                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();//这个就是你选择的文件夹的路径
                    setWorkDir(filePath);
                    //System.out.println(filePath);
                }
            }
        });
        打开输出文件夹Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().open(new File(getWorkDir()));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public String getWorkDir() {
        return 输出目录TextField.getText();
    }

    public void setWorkDir(String workDir) {
        输出目录TextField .setText(workDir);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("导出数据");
        frame.setContentPane(new 导出数据().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                super.windowClosing(e);
            }
        });
    }

    private JPanel panel1;
    private JButton button1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField 输出目录TextField;
    private JButton button2;
    private JButton button3;
    private JButton 打开输出文件夹Button;

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
