package com.demo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StoredProceduresDemo1 {

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
			String department = "HR";
			int increaseAmt = 10000;
			System.out.println("old salary");
			getSalaries(con, department);

			myCall = con.prepareCall("{call increase_salaries_for_department(?,?)}");
			myCall.setString(1, department);
			myCall.setDouble(2, increaseAmt);

			// Call stored procedure
			System.out.println("\n\nCalling stored procedure.  increase_salaries_for_department('" + department
					+ "', " + increaseAmt + ")");
			
			myCall.execute();
			
			System.out.println("stored procedure called successfully");
			System.out.println("updated salary");
			getSalaries(con, department);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void getSalaries(Connection con2, String department) {

		try {
			pstmt = con.prepareStatement("SELECT * FROM EMPLOYEES WHERE DEPARTMENT=?");
			pstmt.setString(1, department);
			res = pstmt.executeQuery();
			while (res.next()) {
				String last_name = res.getString("last_name");
				String first_name = res.getString("first_name");
				String dept = res.getString("department");
				double salary = res.getDouble("salary");

				System.out.printf("%s , %s , %s , %.2f\n", last_name, first_name, dept, salary);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
}
