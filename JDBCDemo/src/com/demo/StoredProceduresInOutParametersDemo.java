package com.demo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class StoredProceduresInOutParametersDemo {

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
			String dept = "HR";

			myCall = con.prepareCall("{call greet_the_department(?)}");
			myCall.registerOutParameter(1, Types.VARCHAR);
			myCall.setString(1, dept);

			System.out.println("Calling a stored procedure, call greet_the_department(" + dept + ")");
			myCall.execute();
			System.out.println("stored procedure executed");
			
			String res = myCall.getString(1);
			System.out.println("Result= "+res);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
