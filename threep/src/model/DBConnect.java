package model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	private final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	private final String USER = "SCOTT";
	private final String PASSWORD = "1234";

	Connection conn = null;

	public Connection getConnection(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			return conn;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
//
//	public static void main(String[] args) {
//		DBConnect connect = new DBConnect();
//		Connection conn = connect.getConnection();
//		Statement stmt = null;
//		ResultSet rs = null;
//		String sql = "SELECT * FROM hairshop";
//
//		try {
//
//			stmt = conn.createStatement();
//			rs = stmt.executeQuery(sql);
//
//			while(rs.next()){
//				System.out.println(rs.getNString(""));
//				System.out.println(rs.getNString("email"));
//				System.out.println(rs.getNString("password"));
//				System.out.println(rs.getNString("password"));
//				System.out.println(rs.getNString("password"));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//
//	}

}


