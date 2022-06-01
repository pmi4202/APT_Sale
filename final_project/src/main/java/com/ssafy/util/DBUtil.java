//package com.ssafy.util;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//
//public class DBUtil {
///*
//	private final String driverName = "com.mysql.cj.jdbc.Driver";
//	private final String url = "jdbc:mysql://127.0.0.1:3306/ssafyweb?serverTimezone=UTC";
//	private final String user = "ssafy";
//	private final String pass = "ssafy";
//
//	private static DBUtil instance = new DBUtil();
//
//	private DBUtil() {
//		try {
//			Class.forName(driverName);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static DBUtil getInstance() {
//		return instance;
//	}
//
//	public Connection getConnection() throws SQLException {
//		return DriverManager.getConnection(url, user, pass);
//	}
//
//	public void close(AutoCloseable... closeables) {
//		for (AutoCloseable c : closeables) {
//			if (c != null) {
//				try {
//					c.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
//*/
//	private static DBUtil instance = new DBUtil();
//
//	private DBUtil() {}
//
//	public static DBUtil getInstance() {
//		return instance;
//	}
//	
//	public Connection getConnection() throws SQLException {
//		try {
//			Context context = new InitialContext();
//			Context rootContext = (Context) context.lookup("java:comp/env");
//			DataSource dataSource = (DataSource) rootContext.lookup("jdbc/ssafy");
//			return dataSource.getConnection();
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	public void close(AutoCloseable... closeables) {
//		for (AutoCloseable c : closeables) {
//			if (c != null) {
//				try {
//					c.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
//}
