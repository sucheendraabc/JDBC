package com.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StoringFilesInDB {

	public static String url = "jdbc:mysql://localhost:3306/demo";
	public static String un = "root";
	public static String pw = "system";
	public static Connection con;
	public static PreparedStatement pstmt;

	public static void main(String[] args) {
		try {
			con = DriverManager.getConnection(url, un, pw);

			String sql = "update employees set resume=? where email='sucheendra.sachin@gmail.com'";
			pstmt = con.prepareStatement(sql);

			File f = new File("E:\\RESUME\\sucheendra-resume.pdf");
			FileInputStream fis = new FileInputStream(f);
			pstmt.setBinaryStream(1, fis);

			System.out.println("Reading from... " + f.getAbsolutePath());
			pstmt.executeUpdate();
			System.out.println("upload complete..");

		} catch (SQLException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
