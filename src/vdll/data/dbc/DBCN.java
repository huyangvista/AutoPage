package vdll.data.dbc;

import java.sql.*;

/**
 * JDBC操作的工具类,加载数据库驱动,获取数据库连接
 * 弃用的
 * 请使用  MySql
 *
 * @author Hocean
 */
public class DBCN implements IDB {
    private static Connection conn = null;
    private static DBProp dbProp = new DBProp();

    static {
        load();
    }

    private DBCN() {
    }

    public static void load() {
        try {
            dbProp.load();
            Class.forName(dbProp.getDriverClassName());
        } catch (Exception e) {
        }
    }


    /**
     * 获取数据库连接
     *
     * @return Connection conn
     */
    public static Connection open() {
        String url = dbProp.getUrl() +  "?useUnicode=true&characterEncoding=utf8";
        try {
            conn = DriverManager.getConnection(url, dbProp.getUsername(), dbProp.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     *
     */
    public static void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void close(ResultSet rs, PreparedStatement pst, Connection con) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * @param conn 数据库的连接
     * @param sql  要执行的sql语句
     * @param
     * @return PreparedStatement 返回类型
     * @throws
     * @Title: getPstmt
     * @Description: TODO 获取数据库的预处理命令类PreparedStatement
     */
    public static PreparedStatement getPstmt(Connection conn, String sql) {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return pstmt;
    }

    /**
     * @param conn
     * @param sql
     * @return PreparedStatement
     * @Title getPstmt
     * @Discription 获取数据库处理对
     */
    public static PreparedStatement getPstmt(Connection conn, String sql, int autoGenerateKeys) {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql, autoGenerateKeys);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return pstmt;
    }

    /**
     * @param pstmt PreparedStatement对象
     * @param
     * @return ResultSet 返回类型
     * @throws
     * @Title: getRs
     * @Description: TODO 获取结果
     */
    public static ResultSet getRs(PreparedStatement pstmt) {
        ResultSet rs = null;
        try {
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * @param rs 结果
     * @return void 返回类型
     * @throws
     * @Title: close
     * @Description: TODO 关闭结果集的连接
     */
    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            rs = null;
        }
    }

}
