package com.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RetrievingFileFromDB {
	public static String url = "jdbc:mysql://localhost:3306/demo";
	public static String un = "root";
	public static String pw = "system";
	public static Connection con;
	public static PreparedStatement pstmt;
	public static Statement stmt;
	public static ResultSet res;
	public static File f;
	public static FileOutputStream fos;
	public static InputStream is;
	public static void main(String[] args) throws IOException {
		try {
			String sql="SELECT resume from employees where email='sucheendra.sachin@gmail.com'";
			con = DriverManager.getConnection(url, un, pw);
			stmt=con.createStatement();
			res = stmt.executeQuery(sql);
			
		
			f = new File("D:\\MyOutputs\\myResume.pdf");
			fos = new FileOutputStream(f);
			System.out.println("Writing to new File..."+f.getAbsolutePath());
			while(res.next())
			{
				 is = res.getBinaryStream("resume");
				 byte[] buffer=new byte[20000];
				 
				 while(is.read(buffer)>0)
				 {
					 fos.write(buffer);
				 }
			}
			System.out.println("\nWrite completed....");
			
			
			
		} catch (SQLException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
