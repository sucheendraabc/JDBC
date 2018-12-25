package com.demo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StoredProceduresResultSetDemo {
	public static Connection con = null;
	public static String url = "jdbc:mysql://localhost:3306/demo";
	public static String user = "root";
	public static String password = "system";
	public static PreparedStatement pstmt = null;
	public static ResultSet res;
	public static CallableStatement myCall;

	public static void main(String[] args) {

		try {
			con = DriverManager.getConnection(url, user, password);

			String dept = "Engineering";

			myCall = con.prepareCall("{call get_employees_for_department(?)}");

			myCall.setString(1, dept);

			System.out.println("calling the stored procedure call get_employees_for_department(" + dept + ")");

			myCall.execute();

			System.out.println("stored procedure executed successfully");

			ResultSet res = myCall.getResultSet();

			showResult(res);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Show result.
	 *
	 * @param rs the rs
	 */
	private static void showResult(ResultSet rs) {

		try {
			while (rs.next()) {
				String last_name = rs.getString("last_name");
				String first_name = rs.getString("first_name");
				String department = rs.getString("department");
				double sal = rs.getDouble("salary");
				System.out.printf("%s,%s,%s,%.2f\n", last_name, first_name, department, sal);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
