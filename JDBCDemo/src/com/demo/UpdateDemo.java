package com.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class UpdateDemo {
	static String url = "jdbc:mysql://localhost:3306/demo";
	static String un = "root";
	static String pw = "system";
	static Connection con = null;
	static Statement stmt;

	public static void main(String[] args) {

		try {
			con = DriverManager.getConnection(url, un, pw);
			stmt = con.createStatement();
			String query = "UPDATE employees set email='chooluchoolu@gmail.com' WHERE id=4";
			stmt.executeUpdate(query);
			System.out.println("update successful");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
