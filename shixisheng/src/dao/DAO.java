package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库工具类 链接mySQL数据库
 * @author LTY
 * 
 */
public class DAO {
	// 链接需要的数据 （这些数据直接写到加密后的数据文件中）
	private static String url = "jdbc:mysql://127.0.0.1:3306/shixiseng?characterEncoding=utf-8";
	private static String user = "root";
	private static String password = "1234";
	private static String driverName = "com.mysql.jdbc.Driver";

	// 声明静态链接对象
	private static Connection connection = null;

	// 编写静态代码块(比构造函数先加载) 用来加载驱动类
	static {
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}// end
		// 获得链接对象

	public static Connection getConnection() {
		// 创建链接对象
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}