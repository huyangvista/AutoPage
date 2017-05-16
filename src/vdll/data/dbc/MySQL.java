package vdll.data.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * JDBC操作的工具类,加载数据库驱动,获取数据库连接
 *
 * Created by Hocean on 2017/4/14.
 */
public class MySQL implements IDB {

    private static String driverName = "com.mysql.jdbc.Driver";
    private static String dbURL = "jdbc:mysql://vives.cc:3306/tbi_erp?useUnicode=true&characterEncoding=utf8";
    private static String username = "root";
    private static String password = "hoceanvista";

    static {
        load();
    }

    public MySQL() {
    }

    public static void load() {
        try {
            Class.forName(driverName);
        } catch (Exception e) {
        }
    }

    /**
     * 获取数据库连接
     *
     * @return Connection conn
     */
    public static Connection open() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbURL, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

}
