package com.demo.guidemo;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class EmployeeDAO {
	public static FileInputStream fis;
	public static Properties p;
	public static Connection con;

	public EmployeeDAO() {
		try {
			fis = new FileInputStream("D:\\WorkSpaces\\JAVA\\JDBC\\JDBCDemo\\externalresources\\demo.properties");
			p = new Properties();
			p.load(fis);
			String url = p.getProperty("url");
			String un = p.getProperty("un");
			String pw = p.getProperty("pw");
			con = DriverManager.getConnection(url, un, pw);
			System.out.println("DB Connection successful to " + url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Employee> getAllEmployees() throws Exception {

		ArrayList<Employee> al = new ArrayList<>();
		Statement stmt;
		ResultSet res;

		stmt = con.createStatement();
		res = stmt.executeQuery("select * from employees");
		while (res.next()) {
			Employee temp = convertRowToEmployees(res);
			al.add(temp);
		}

		return al;
	}

	public ArrayList<Employee> searchEmployees(String str) throws Exception {
		PreparedStatement pstmt;
		ResultSet res;
		ArrayList<Employee> al = new ArrayList<>();
		try {
			str += "%";

			pstmt = con.prepareStatement("select * from employees where last_name like ?");
			pstmt.setString(1, str);
			res = pstmt.executeQuery();
			while (res.next()) {
				Employee temp = convertRowToEmployees(res);
				al.add(temp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return al;
	}

	private Employee convertRowToEmployees(ResultSet res) throws SQLException {

		int id = res.getInt("id");
		String lastname = res.getString("last_name");
		String firstname = res.getString("first_name");
		String email = res.getString("email");
		BigDecimal salary = res.getBigDecimal("salary");

		Employee e = new Employee(id, lastname, firstname, email, salary);
		return e;
	}

	public static void main(String[] args) throws Exception {

		EmployeeDAO employeeDAO = new EmployeeDAO();
		System.out.println(employeeDAO.getAllEmployees());
		System.out.println(employeeDAO.searchEmployees("thom"));
	}
}
