package com.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class EstablishingConnection {
	static String url = "jdbc:mysql://localhost:3306/demo";
	static String un = "root";
	static String pw = "system";
	static Connection con;
	static Statement stmt;
	static ResultSet res;

	public static void main(String[] args) {

		try {
			// establishing connection
			con = DriverManager.getConnection(url, un, pw);

			// executing the query
			String query = "select * from employees";
			stmt = con.createStatement();
			// printing the resultset
			res = stmt.executeQuery(query);
			while (res.next()) {
				System.out.println(res.getString("firstname") + ", " + res.getString("lastname"));
			}
			// closing the connection
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
