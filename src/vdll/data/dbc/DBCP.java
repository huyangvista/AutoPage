package vdll.data.dbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.dbcp.*;
//import org.apache.tomcat.dbcp.dbcp.BasicDataSourceFactory;

/**
 * java 数据库连接池
 * tomcat数据库连接池管理类<br>
 * 使用为tomcat部署环境<br>
 * 需要在类路径下准备数据库连接配置文件dbcp.properties
 * Created by Hocean on 2017/4/14.
 */
public class DBCP {
	private static final Log log = LogFactory.getLog(DBCP.class);
	private static final String configFile = "dbcp.properties";
	private static DataSource dataSource;
	static {
		load();
	}
	private DBCP() {

	}
	
	protected static void load(){
		Properties dbProperties = new Properties();
		try {
			dbProperties.load(DBCP.class.getClassLoader().getResourceAsStream(configFile));
			//dbProperties.load(new FileReader("/mnt/sdcard/"+configFile));
			dataSource = BasicDataSourceFactory.createDataSource(dbProperties);
			Connection conn = getConn();
			DatabaseMetaData mdm = conn.getMetaData();
			log.info("Connected to " + mdm.getDatabaseProductName() + " " + mdm.getDatabaseProductVersion());
			conn.close();
		} catch (Exception e) {
			log.error("初始化连接池失败：" + e);
		}
	}
	/**
	 * 获取链接，用完后记得关闭
	 * 
	 * @see {@link DBCP#closeConn(Connection)}
	 * @return
	 */
	public static Connection getConn() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			log.error("获取数据库连接失败：" + e);
		}
		return conn;
	}
	/**
	 * 关闭连接
	 * 
	 * @param conn
	 *            需要关闭的连接
	 */
	public static void closeConn(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.setAutoCommit(true);
				conn.close();
			}
		} catch (SQLException e) {
			log.error("关闭数据库连接失败：" + e);
		}
	}
}
