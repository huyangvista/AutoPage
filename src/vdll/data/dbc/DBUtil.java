package vdll.data.dbc;

import java.sql.*;

/**
 * Created by Hocean on 2017/4/15.
 */
public class DBUtil {

    public static Statement createStatement(Connection conn) {
        Statement st = null;
        try {
            st = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return st;
    }

    public static Boolean exe(Connection conn, String sql) {
        Boolean r = null;
        try {
            Statement st = conn.createStatement();
            r = st.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }

    public static ResultSet exeQ(Connection conn, String sql) {
        ResultSet r = null;
        try {
            Statement st = conn.createStatement();
            r = st.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }

    public static int exeU(Connection conn, String sql) {
        int r = -1;
        try {
            Statement st = conn.createStatement();
            r = st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }

    public static PreparedStatement pre(Connection conn, String sql) {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pstmt;
    }

    public static PreparedStatement pre(Connection conn, String sql, int autoGenerateKeys) {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql, autoGenerateKeys);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pstmt;
    }

    public static Boolean exe(PreparedStatement pstmt) {
        Boolean r = null;
        try {
            r = pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }

    public static ResultSet exeQ(PreparedStatement pstmt) {
        ResultSet r = null;
        try {
            r = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }

    public static int exeU(PreparedStatement pstmt) {
        int r = -1;
        try {
            r = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }

    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
        }
    }

    public static void close(PreparedStatement pst) {
        try {
            if (pst != null) {
                pst.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Connection conn) {
        try {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(ResultSet rs, PreparedStatement pst, Connection con) {
        close(rs);
        close(pst);
        close(con);
    }
}
