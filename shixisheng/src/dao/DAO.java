package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ���ݿ⹤���� ����mySQL���ݿ�
 * @author LTY
 * 
 */
public class DAO {
	// ������Ҫ������ ����Щ����ֱ��д�����ܺ�������ļ��У�
	private static String url = "jdbc:mysql://127.0.0.1:3306/shixiseng?characterEncoding=utf-8";
	private static String user = "root";
	private static String password = "1234";
	private static String driverName = "com.mysql.jdbc.Driver";

	// ������̬���Ӷ���
	private static Connection connection = null;

	// ��д��̬�����(�ȹ��캯���ȼ���) ��������������
	static {
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}// end
		// ������Ӷ���

	public static Connection getConnection() {
		// �������Ӷ���
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}