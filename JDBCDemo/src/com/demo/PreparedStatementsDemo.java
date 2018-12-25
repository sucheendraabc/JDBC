package com.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PreparedStatementsDemo {

	static String url = "jdbc:mysql://localhost:3306/demo";
	static String un = "root";
	static String pw = "system";
	static Connection con = null;
	static PreparedStatement pstmt = null;
	static ResultSet res = null;

	public static void result(ResultSet rs) {

		try {
			while (rs.next()) {

				System.out.println(rs.getInt(1) + ", " + rs.getString("last_name") + ", " + rs.getString("first_name")
						+ " " + rs.getString("email") + ", " + rs.getString("department") + ", "
						+ rs.getString("salary"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		try {
			con = DriverManager.getConnection(url, un, pw);

			pstmt = con.prepareStatement("select * from employees where salary>? and department=?");
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter the salary");
			double salary1 = scan.nextDouble();
			System.out.println("Enter the department");
			String dept = scan.next();

			pstmt.setDouble(1, salary1);
			pstmt.setString(2, dept);

			res = pstmt.executeQuery();

			result(res);

			// reusing

			

			System.out.println("Enter the salary");
			double salary2 = scan.nextDouble();
			System.out.println("Enter the department");
			String dept2 = scan.next();

			pstmt.setDouble(1, salary2);
			pstmt.setString(2, dept2);
			res = pstmt.executeQuery();

			result(res);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
