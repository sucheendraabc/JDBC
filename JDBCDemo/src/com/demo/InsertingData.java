package com.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class InsertingData {
	static String url = "jdbc:mysql://localhost:3306/demo";
	static String un = "root";
	static String pw = "system";
	static Connection con;
	static Statement stmt;
	static ResultSet res;
	
	public static void main(String[] args) {

		try {
			con = DriverManager.getConnection(url, un, pw);
			stmt = con.createStatement();
			String query="insert into employees(id,lastname,firstname,email)values(5,'parama','chatura','chatri@gmail.com')";
			stmt.executeUpdate(query);
			System.out.println("insert Successful");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
