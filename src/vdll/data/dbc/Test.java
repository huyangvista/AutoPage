package vdll.data.dbc;

import vdll.core.Action1;
import vdll.data.msql.MySql;
import vdll.utils.ArrayUtil;
import vdll.utils.io.FileUtil;

import java.io.File;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        File[] files = FileUtil.getFiles("C:\\Users\\Hocean\\Desktop\\books");

        ArrayUtil.fore(files, new Action1<File>() {
            @Override
            public void invoke(File file) {
                System.out.println(file.getName());

            }
        });


        MySql sql = null;
        try {
            sql = new MySql(Sqlite.open());
            sql.exeQ("SELECT * FROM chapter");
            List<Map<String, Object>> data = sql.getData();
            for (Map<String, Object> item : data) {
               // System.out.println(item.get("id"));
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sql.close();
        }
    }
}
