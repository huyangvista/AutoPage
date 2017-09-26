package vdll.utils;//package vdll.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Hocean on 2017/6/5.
 */
public class ExcelUtil {


    /**
     * @param list
     * @param sheetName "sheet"
     * @param Filename  "src/workbook.xls"
     */
    public static void create(List<Map<String, Object>> list,String[] rowTitle, String sheetName, String Filename) {
        try {
            //创建excel工作簿
            Workbook wb = new HSSFWorkbook();
            //创建第一个sheet（页），命名为 new sheet
            Sheet sheet = wb.createSheet(sheetName);
            {
                Row row = sheet.createRow(0);
                for (int j = 0; j < rowTitle.length; j++) {
                    row.createCell(j).setCellValue(rowTitle[j]);
                }
            }
            for (int i = 0; i < list.size(); i++) {
                // 创建一行，在页sheet上
                Row row = sheet.createRow(i + 1);
                for (int j = 0; j < rowTitle.length; j++) {
                    row.createCell(j).setCellValue(list.get(i).get(rowTitle[j]).toString());
                }
            }
            //创建一个文件 命名为workbook.xls
            File f = new File(Filename);
            if(f.exists()){
                boolean delete = f.delete();
                if (!delete){
                    System.out.println("文件删除错误");
                }
            }
            FileOutputStream fileOut = new FileOutputStream(f);
            // 把上面创建的工作簿输出到文件中
            wb.write(fileOut);
            //关闭输出流
            fileOut.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
