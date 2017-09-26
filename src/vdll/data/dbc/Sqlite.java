package vdll.data.dbc;

import vdll.data.msql.MySql;
import vdll.data.msql.MySqlString;

import java.sql.*;
import java.util.List;
import java.util.Map;

/**
 * Created by Hocean on 2017/6/27.
 */
public class Sqlite implements IDB {

    public static void main(String[] args) {
        MySql sql = null;
        try {
            sql = new MySql(open());
            sql.exeQ("SELECT * FROM chapter");
            List<Map<String, Object>> data = sql.getData();
            for (Map<String, Object> item : data) {
                System.out.println(item.get("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sql.close();
        }
    }

    static {
        load();
    }

    public static void load() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection open() {

        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:src/asses/hudong.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static Connection open(String path) {

        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:" + path);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

}