package com.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class RetrievingFileFromDBUsingCLOBDemo {
	public static String url = "jdbc:mysql://localhost:3306/demo";
	public static String un = "root";
	public static String pw = "system";
	public static Connection con;
	public static PreparedStatement pstmt;
	public static Statement stmt;
	public static ResultSet res;
	public static File f;
	public static FileOutputStream fos;
	public static void main(String[] args) {
		try {
			con = DriverManager.getConnection(url, un, pw);
			String sql="select resume from employees where email='sucheendra.sachin@gmail.com'";
			stmt=con.createStatement();
			res = stmt.executeQuery(sql);
			f=new File("D:\\MyOutputs\\file.pdf");
			fos=new FileOutputStream(f);
			
			System.out.println("Writing to file "+f.getAbsolutePath());
			System.out.println(sql);
			
			while(res.next())
			{
				Reader chars = res.getCharacterStream("resume");
				int theChar;
				while((theChar=chars.read())>0)
				{
					fos.write(theChar);
				}
			}
			
			
			
			
			
			System.out.println("Writing to file successful ");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
