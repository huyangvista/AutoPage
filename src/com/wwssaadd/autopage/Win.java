package com.wwssaadd.autopage;

import demo.bas_bi_summary;
import vdll.data.msql.MySqlBuild;
import vdll.tools.CompareUtil;
import vdll.utils.DemoUtil;
import vdll.utils.FileOperate;
import vdll.utils.ParmsUtil;
import vdll.utils.Tools;
import vdll.data.msql.MySql;
import vdll.data.msql.MySqlString;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.*;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * Created by Hocean on 2017/1/25.
 */
public class Win {
    private String path = "src/asses/";
    private JButton button1;
    public JPanel panel1;
    private JButton button2;
    private JButton button3;
    private JTextField textField1;
    private JButton button4;
    private JButton button5;
    private JButton button6;

    MySql mySql = new MySql();

    public Win() {
        mySql.open();
        Main.frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mySql.close();
                super.windowClosing(e);
            }
        });
        button1.addActionListener(e -> {
            mySql.exeQ("SELECT * FROM bas_si_user");
            java.util.List<Map<String, Object>> list = mySql.getParms();
            for (Map<String, Object> map : list) {
                System.out.println(map.get("id"));
            }
            mySql.exeU("INSERT INTO bas_si_user(id,si_id,user_id) VALUES('2','2','2')");
        });
        button2.addActionListener(e -> {
            try {
                String vjsp = FileOperate.readTxt(path + "baseJsp.jsp", ""); //得到模版
                //String sql = "SELECT * FROM `tab`,`tab2` WHERE tab.id = tab2.id";
                String sql = "SELECT * FROM bas_si_user";

                mySql.exeQ(sql);
                String[] cols = mySql.getColumnName();
                String txt = FileOperate.readTxt(path + "fragment.txt", "");
                //txt = "sadfa\r\nsdf[[asdfasdfas]]asdfasdfasdfsdfsadfdfsdf";
                List<String> listItem = Tools.getTagIn(txt, "<item>", "</item>");
               /* java.util.List<Map<String, Object>> list =  mySql.getParms();
                for (Map<String, Object> map: list ) {
                    Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
                    while (it.hasNext()){
                        Map.Entry<String, Object> en = it.next();
                        String k = en.getKey();
                        Object v = en.getValue();
                    }
                }*/
                String searchBodyTxt, searchBody = "[[searchBody]]";
                String listTitleTxt, listTitle = "[[listTitle]]";
                String listBodyTxt, listBody = "[[listBody]]";
                String editBodyTxt, editBody = "[[editBody]]";

                {//搜索体
                    StringBuilder out = new StringBuilder();
                    String start = "                            <tr >";
                    String end = "                            </tr>\r\n";

                    String label = "[[label]]";
                    for (int i = 0; i < cols.length; i++) {
                        if (i == 0) {
                            out.append(start);
                        } else {
                            if (i % 3 == 0) {
                                out.append(end);
                                out.append(start);
                            }
                        }
                        String vs = listItem.get(0).replace(label, cols[i]);
                        out.append(vs);
                        if (i >= cols.length - 1) out.append(end);
                    }
                    searchBodyTxt = out.toString();
                }
                {//列表体头
                    StringBuilder out = new StringBuilder();
                    String item = "                            <th>%s</th>\r\n";
                    for (int i = 0; i < cols.length; i++) {
                        String vs = String.format(item, cols[i]);
                        out.append(vs);
                    }
                    listTitleTxt = out.toString();
                }
                {///列表体
                    StringBuilder out = new StringBuilder();
                    String item = "                            <td>{{item.%s}}</td>\r\n";
                    for (int i = 0; i < cols.length; i++) {
                        String vs = String.format(item, cols[i]);
                        out.append(vs);
                    }
                    listBodyTxt = out.toString();
                }
                {//编辑表单
                    String start = "<div class=\"row\" >\r\n";
                    String end = "</div>\r\n";
                    StringBuilder out = new StringBuilder();
                    String label = "[[label]]";
                    for (int i = 0; i < cols.length; i++) {
                        if (i == 0) {
                            out.append(start);
                        } else {
                            if (i % 3 == 0) {
                                out.append(end);
                                out.append(start);
                            }
                        }
                        String vs = listItem.get(1).replace(label, cols[i]);
                        out.append(vs);
                        if (i >= cols.length - 1) out.append(start);
                    }
                    editBodyTxt = out.toString();
                }
                {
                    //搜索和翻页的链接
                    String searchUrl = "[[searchUrl]]";
                    String searchUrlTxt = "/tbi/basesiuser/basesiuser";
                    vjsp = vjsp.replace(searchUrl, searchUrlTxt);

                    //JS 路径
                    String defaultJS = "[[defaultJS]]";
                    String defaultJSTxt = "erp/bas/basSiUser";
                    vjsp = vjsp.replace(defaultJS, defaultJSTxt);

                    //angulejs Ctr
                    String defaultCtr = "[[defaultCtr]]";
                    String defaultCtrTxt = "basSiUserFromSysUserCtr";
                    vjsp = vjsp.replace(defaultCtr, defaultCtrTxt);


                }

                vjsp = vjsp.replace(searchBody, searchBodyTxt);
                vjsp = vjsp.replace(listTitle, listTitleTxt);
                vjsp = vjsp.replace(listBody, listBodyTxt);
                vjsp = vjsp.replace(editBody, editBodyTxt);

                FileOperate.createFile(path + "out.jsp", vjsp);
            } catch (Exception es) {
                es.printStackTrace();
            }
        });


        button4.addActionListener(e -> {
            mySql.exeQ(MySqlString.showTables());
            java.util.List<Map<String, Object>> list = mySql.getParms();
            String[] tabNames = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = list.get(i);
                Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, Object> next = iterator.next();
                    //System.out.println(next.getValue());
                    tabNames[i] = next.getValue().toString();
                }
            }


            for (int i = 0; i < tabNames.length; i++) {
                StringBuilder sb = new StringBuilder();
                sb.append("package demo;");
                sb.append("\r\n");
                sb.append("\r\n");
                sb.append("import vdll.data.msql.AISql;");
                sb.append("\r\n");
                sb.append("\r\n");
                sb.append("public class " + tabNames[i] + "{");
                sb.append("\r\n");
                sb.append("\r\n");
                mySql.exeQ(MySqlString.showColumn(tabNames[i], mySql.getDatabaseName()));
                list = mySql.getParms();
                for (int j = 0; j < list.size(); j++) {
                    Map<String, Object> map = list.get(j);
                    Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
                    while (iterator.hasNext()) {
                        Map.Entry<String, Object> next = iterator.next();
                        //System.out.println(next.getValue());

                        sb.append("@AISql public String " + next.getValue() + ";");
                        sb.append("\r\n");
                    }
                }
                sb.append("\r\n");
                sb.append("}");
                FileOperate.createFile("src/demo/" + tabNames[i] + ".java", sb.toString());
                //System.out.println(sb.toString());
            }
            System.out.println("build demo finish");
        });

        button5.addActionListener(e -> {
//            MySqlBuild<demo.bas_si_user> msb = new MySqlBuild<>(demo.bas_si_user.class, "bas_si_user", mySql);
//            List<demo.bas_si_user> bas_si_users = msb.get();
//            for(demo.bas_si_user item : bas_si_users){
//                System.out.println(item.id);
//            }

            String s = FileOperate.readTxt("src/sql.txt", "utf-8");
            mySql.exeQ(s);
            java.util.List<Map<String, Object>> list = mySql.getParms();
            ParmsUtil.setListParmsRep(list, new Object[][]{
                    {ParmsUtil.EListParmsRep.clone, "group_name", "group_name_zone"},
                    {ParmsUtil.EListParmsRep.replace, "group_name_zone",
                            "赖金兰组", "李莹", "李莹组", "李莹",
                            "康莹组", "刘晓静", "侯佳音组", "刘晓静",
                            "杜鹃组", "杜鹃", "高燕组", "杜鹃", "北京", "杜鹃"},
                    {}
            });
            s = FileOperate.readTxt("src/sql2.txt", "utf-8");
            mySql.exeQ(s);
            java.util.List<Map<String, Object>> list2 = mySql.getParms();

            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = list.get(i);
                Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();


                //str
                String namespace = "" + map.get("group_name_zone");//区域
                String group_name = "" + map.get("group_name");  //组
                String sales_data = "";          /////////////国内机票经营数据
                //number
                String count = ""; //////////////国内张数
                String sales_amount = "" + map.get("total_price_sum");  //销售额
                String comm_amount = "" + map.get("base_comm_amount_sum"); //代理费
                String difference_amount = "" + map.get("difference_amount_sum"); //差额
                String profit = "";////////////////单张利润
                String profit_percen = "";//////////////利润率

                String ticket_type = "" + map.get("ticket_type"); //4国内2国际


                long couInter = 0;
                long couChina = 0;
                long couIns = 0;
                long couHotel = 0;

                double _sales_amount = Double.parseDouble(sales_amount);
                double _difference_amount = Double.parseDouble(difference_amount);
                profit_percen = "" + _difference_amount / _sales_amount;

                String business_type = "" + map.get("business_type");
                //国内机票经营数据
                switch (business_type) {
                    case "0":  //机票
                        if("4".equals(ticket_type)){
                            couInter = find(list2,"4",group_name);
                            couChina = find(list2,"4",group_name);
                            couIns   = find(list2,"4",group_name);
                            couHotel = find(list2,"4",group_name);

                            count = "" + couChina;
                            profit = "" + _difference_amount / couChina;
                            sales_data = "国内机票经营数据";
                        }
                        if("2".equals(ticket_type)){
                            couInter = find(list2,"3",group_name);
                            couChina = find(list2,"3",group_name);
                            couIns   = find(list2,"3",group_name);
                            couHotel = find(list2,"3",group_name);

                            count = "" + couInter;
                            profit = "" + _difference_amount / couInter;
                            sales_data = "国际机票经营数据";
                        }

                        break;
                    case "1": //保险

                        couInter = find(list2,"5",group_name);
                        couChina = find(list2,"5",group_name);
                        couIns   = find(list2,"5",group_name);
                        couHotel = find(list2,"5",group_name);

                        count = "" + couIns;
                        profit = "" + _difference_amount / couIns;
                        sales_data = "保险";


                        break;
                    case "2": //酒店3

                        couInter = find(list2,"4",group_name);
                        couChina = find(list2,"4",group_name);
                        couIns   = find(list2,"4",group_name);
                        couHotel = find(list2,"4",group_name);

                        count = "" + couHotel;
                        profit = "" + _difference_amount / couHotel;
                        sales_data = "酒店";

                        break;
                    case "3": //录入的机票2

                        break;
                    case "4": //OTHER

                        break;

                    default:
                        break;
                }
                bas_bi_summary bbs = new bas_bi_summary();
                bbs.namespace = namespace;
                bbs.group_name = group_name;
                bbs.sales_data = sales_data;
                bbs.count = count;
                bbs.sales_amount = sales_amount;
                bbs.comm_amount = comm_amount;
                bbs.difference_amount = difference_amount;
                bbs.profit = profit;
                bbs.profit_percen = profit_percen;

                if (bbs.namespace.trim().equals("") || bbs.namespace.trim().equals("null")) {
                    bbs.namespace = null;
                }
                if (bbs.group_name.trim().equals("") || bbs.group_name.trim().equals("null")) {
                    bbs.group_name = null;
                }
                if (bbs.sales_data.trim().equals("") || bbs.sales_data.trim().equals("null")) {
                    bbs.sales_data = null;
                }

                if (bbs.count.trim().equals("")) {
                    bbs.count = null;
                }
                if (bbs.sales_amount.trim().equals("")) {
                    bbs.sales_amount = null;
                }
                if (bbs.comm_amount.trim().equals("")) {
                    bbs.comm_amount = null;
                }
                if (bbs.difference_amount.trim().equals("")) {
                    bbs.difference_amount = null;
                }
                if (bbs.profit.trim().equals("")) {
                    bbs.profit = null;
                }
                if (bbs.profit_percen.trim().equals("") || bbs.profit_percen.trim().equals("NaN")) {
                    bbs.profit_percen = null;
                }

                MySqlBuild<bas_bi_summary> msb = new MySqlBuild<bas_bi_summary>(bas_bi_summary.class, "bas_bi_summary", mySql);
                msb.add(bbs);

                System.out.println(DemoUtil.toString(bbs));

            }
        });

        button6.addActionListener(e -> {

        });
    }


    public static void listSet(List<Map<String, Object>> list) {
        if (list == null) return;
        ParmsUtil.setListParmsRep(list, new Object[][]{
                {ParmsUtil.EListParmsRep.replaceIndex, "business_type", 0, "机票", "保险", "酒店", "外采机票", "账单"},
                {ParmsUtil.EListParmsRep.replaceIndex, "settle_type", 0, "公司月结", "个人支付"},
                {ParmsUtil.EListParmsRep.replace, "status", 0, "新建", 2, "已加入账单", 3, "已确认账单", 4, "已核销"},
                {ParmsUtil.EListParmsRep.replaceIndex, "business_type_ss", 0, "正常", "已调账"},
                {ParmsUtil.EListParmsRep.cloneSelectReplace, "ticket_status", "business_type_ss", "business_type_ss", "正常", "business_type", "机票", new Object[]{"T", "出票", "R", "退票", "V", "废票", "TR", "部分退票", "RI", "退票对账中", "TI", "部分退票对账中"}},
                {ParmsUtil.EListParmsRep.cloneSelectValue, "business_type_ss", "business_type_ss", "business_type", "账单", "其他"},
                {ParmsUtil.EListParmsRep.gather, "business_type_ss", "business_type_item", "正常", "business_type"},
                {ParmsUtil.EListParmsRep.replace, "receivable_price", "", "0.00"},
                {ParmsUtil.EListParmsRep.replace, "total_price", "", "0.00"},
                {ParmsUtil.EListParmsRep.replace, "receivable_price_b", "", "0.00"},
                {ParmsUtil.EListParmsRep.replace, "total_price_b", "", "0.00"},
                {ParmsUtil.EListParmsRep.replace, "difference_amount", "", "0.00"},
                {ParmsUtil.EListParmsRep.replace, "bill_amount", "", "0.00"},
                {ParmsUtil.EListParmsRep.replace, "base_comm_amount", "", "0.00"},
                {ParmsUtil.EListParmsRep.ifnulladd, "receivable_price", "0.00"},
                {ParmsUtil.EListParmsRep.ifnulladd, "total_price", "0.00"},
                {ParmsUtil.EListParmsRep.ifnulladd, "receivable_price_b", "0.00"},
                {ParmsUtil.EListParmsRep.ifnulladd, "total_price_b", "0.00"},
                {ParmsUtil.EListParmsRep.ifnulladd, "difference_amount", "0.00"},
                {ParmsUtil.EListParmsRep.ifnulladd, "bill_amount", "0.00"},
                {ParmsUtil.EListParmsRep.ifnulladd, "base_comm_amount", "0.00"},
                {}
        });

    }

    public static void listSetExcel(List<Map<String, Object>> list) throws IOException {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");

        CompareUtil.sort(list, CompareUtil.ESort.asc, "business_type", "id");


        String business_typeBack = null;
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);

            Row row = sheet.createRow(i);
            row.createCell(0).setCellValue("" + map.get("group_name_zone"));
            row.createCell(1).setCellValue("" + map.get("group_name"));
            row.createCell(2).setCellValue("" + map.get("receivable_price_sum"));
            row.createCell(3).setCellValue("" + map.get("base_comm_amount_sum"));
            row.createCell(4).setCellValue("" + map.get("difference_amount_sum"));
            row.createCell(5).setCellValue("" + map.get("business_type"));

            String business_type = (map.get("business_type") + "").trim();
            if (business_typeBack != null && !business_type.equals(business_typeBack)) {
                sheet.createRow(i);
            }
            business_typeBack = business_type;
            switch (business_type) {
                case "0":

                    break;
                case "1":

                    break;
                case "2":

                    break;
                case "3":

                    break;
                case "4":

                    break;
                default:
                    break;
            }
        }

        FileOutputStream fileOut = new FileOutputStream("src/workbook.xls");


        wb.write(fileOut);
        fileOut.close();
    }

    public static class POITest {
        //使用POI创建excel工作簿
        public static void createWorkBook() throws IOException {
            //创建excel工作簿
            Workbook wb = new HSSFWorkbook();
            //创建第一个sheet（页），命名为 new sheet
            Sheet sheet = wb.createSheet("new sheet");
            //Row 行
            //Cell 方格
            // Row 和 Cell 都是从0开始计数的

            // 创建一行，在页sheet上
            Row row = sheet.createRow(0);
            // 在row行上创建一个方格
            Cell cell = row.createCell(0);
            //设置方格的显示
            cell.setCellValue(1);

            // Or do it on one line.
            row.createCell(1).setCellValue(1.2);
            row.createCell(2).setCellValue("This is a string 速度反馈链接");
            row.createCell(3).setCellValue(true);

            //创建一个文件 命名为workbook.xls
            FileOutputStream fileOut = new FileOutputStream("src/workbook.xls");
            // 把上面创建的工作簿输出到文件中
            wb.write(fileOut);
            //关闭输出流
            fileOut.close();
        }

        //使用POI读入excel工作簿文件
        public static void readWorkBook() throws Exception {
            // poi读取excel
            //创建要读入的文件的输入流
            InputStream inp = new FileInputStream("workbook.xls");

            //根据上述创建的输入流 创建工作簿对象
            Workbook wb = WorkbookFactory.create(inp);
            //得到第一页 sheet
            //页Sheet是从0开始索引的
            Sheet sheet = wb.getSheetAt(0);
            //利用foreach循环 遍历sheet中的所有行
            for (Row row : sheet) {
                //遍历row中的所有方格
                for (Cell cell : row) {
                    //输出方格中的内容，以空格间隔
                    System.out.print(cell.toString() + "  ");
                }
                //每一个行输出之后换行
                System.out.println();
            }
            //关闭输入流
            inp.close();
        }

        public static void main(String[] args) throws Exception {
            POITest.createWorkBook();
            //POITest.readWorkBook();
        }
    }


    public long find(java.util.List<Map<String, Object>> list, String type, String groupName) {
        long l = 0;
        for (Map<String, Object> item : list) {
            if (type.equals(item.get("type")) && groupName.equals(item.get("group_name"))) {
                try {
                    l = Long.parseLong( item.get("cou").toString());
                }catch (Exception e){

                }

                break;
            }
        }
        return l;
    }
}
