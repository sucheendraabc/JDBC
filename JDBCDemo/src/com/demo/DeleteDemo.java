package com.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DeleteDemo {
	static String url = "jdbc:mysql://localhost:3306/demo";
	static String un = "root";
	static String pw = "system";
	static Connection con = null;
	static Statement stmt;

	public static void main(String[] args) {

		try {
			con = DriverManager.getConnection(url, un, pw);
			stmt = con.createStatement();
			String query = "delete from employees where id=5";
			int rowsAffected = stmt.executeUpdate(query);
			System.out.println("total rows affected= "+rowsAffected);
			System.out.println("delete successful");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
