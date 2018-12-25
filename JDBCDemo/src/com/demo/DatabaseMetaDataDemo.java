package com.demo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

// TODO: Auto-generated Javadoc
/**
 * The Class DatabaseMetaDataDemo.
 */
public class DatabaseMetaDataDemo
{
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static String url="jdbc:mysql://localhost:3306/demo";
	public static String un="root";
	public static String pw="system";
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		try {
			Connection con = DriverManager.getConnection(url, un, pw);
			
			DatabaseMetaData databaseMetaData = con.getMetaData();
			
			String productName = databaseMetaData.getDatabaseProductName();
			String productVersion = databaseMetaData.getDatabaseProductVersion();
			
			String driverName = databaseMetaData.getDriverName();
			String driverVersion = databaseMetaData.getDriverVersion();
			
			System.out.println("Product name is "+productName);
			System.out.println("Product Version is "+productVersion);
			System.out.println("Driver name is "+driverName);
			System.out.println("Driver version is "+driverVersion);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}
}
