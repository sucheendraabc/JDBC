package com.demo;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StoringFilesInDBIUsingClobsDemo {
	public static String url = "jdbc:mysql://localhost:3306/demo";
	public static String un = "root";
	public static String pw = "system";
	public static Connection con;
	public static PreparedStatement pstmt;
	public static File f;
	public static FileReader fr;
	public static void main(String[] args) {
		try {
			con = DriverManager.getConnection(url, un, pw);
			String sql="update employees set resume=? where email='sucheendra.sachin@gmail.com'";
			pstmt=con.prepareStatement(sql);
			
			System.out.println(sql);
			
			f=new File("E:\\RESUME\\sucheendra-resume.pdf");
			fr=new FileReader(f);
			pstmt.setCharacterStream(1, fr);
			
			System.out.println("File reading from..."+f.getAbsolutePath());
			
			pstmt.executeUpdate();
			System.out.println("Successful");
			
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
}
