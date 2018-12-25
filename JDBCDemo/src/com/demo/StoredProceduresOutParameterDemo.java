package com.demo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class StoredProceduresOutParameterDemo {
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

			//calling the stored procedure
			myCall = con.prepareCall("{call get_count_for_department(?,?)}");
			
			myCall.setString(1, dept);
			myCall.registerOutParameter(2, Types.INTEGER);
			
			
			System.out.println("get_count_for_department("+dept+",?)");
			
			myCall.execute();
			
			System.out.println("stored procedure executed successfully");
			//to get the count
			int count = myCall.getInt(2);
			System.out.println("The total num of employess for "+dept+" department  is "+count);

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
