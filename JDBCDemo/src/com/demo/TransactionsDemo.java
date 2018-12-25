package com.demo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * The Class TransactionsDemo.
 *
 * @author Sucheendra
 * @version 1 build on 22/12/2018
 * <h1>Demonstrates the Transactions concept</h1>
 */
public class TransactionsDemo {
	
	/** The con. */
	public static Connection con = null;
	
	/** The url. */
	public static String url = "jdbc:mysql://localhost:3306/demo";
	
	/** The user. */
	public static String user = "root";
	
	/** The password. */
	public static String password = "system";
	
	/** The pstmt. */
	public static PreparedStatement pstmt = null;
	
	/** The res. */
	public static ResultSet res;
	
	/** The my call. */
	public static CallableStatement myCall;
	
	/** The stmt. */
	public static Statement stmt;

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {

		try {

			con = DriverManager.getConnection(url, user, password);

			con.setAutoCommit(false);

			System.out.println("\n Salaries Before Executing transaction \n");
			showSalaries(con, "HR");
			System.out.println();
			System.out.println();
			showSalaries(con, "Engineering");

			stmt = con.createStatement();
			// first transaction
			stmt.executeUpdate("delete from employees where department='HR'");
			// Second transaction
			stmt.executeUpdate("update employees set salary=300000 where department='Engineering'");

			boolean ok = askUsertoSave();
			if (ok) {
				con.commit();
				System.out.println("\nTransaction committed successfully\n");
			} else {
				con.rollback();
				System.out.println("Transaction rolled back");
			}
			System.out.println("\nsalaries After Transaction execution\n");
			showSalaries(con, "HR");
			System.out.println();
			System.out.println();
			showSalaries(con, "Engineering");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	
	/**
	 * Ask user to save the transaction.
	 *
	 * @return true, if successful
	 * @return false, if unsuccessful
	 */
	private static boolean askUsertoSave() {

		Scanner scan = new Scanner(System.in);
		System.out.println("Save the transaction yes/no");
		String next = scan.next();
		if (next.equalsIgnoreCase("yes")) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Show salaries.
	 *
	 * @param connection the connection
	 * @param s1 the s 1
	 */
	private static void showSalaries(Connection connection, String s1) {
		try {
			pstmt = con.prepareStatement("SELECT * FROM EMPLOYEES WHERE DEPARTMENT=?");
			pstmt.setString(1, s1);
			System.out.println("\nPrinting the salaries of " + s1 + " Department\n");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String fname = rs.getString("first_name");
				String lname = rs.getString("last_name");
				String department = rs.getString("department");
				double sal = rs.getDouble("salary");
				System.out.printf("%s,%s,%s,%.2f\n", fname, lname, department, sal);
				System.out.println();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
