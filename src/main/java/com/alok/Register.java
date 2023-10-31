package com.alok;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpServlet;

public class Register extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name=request.getParameter("user_name");
		String password=request.getParameter("user_password");
		String email=request.getParameter("user_email");
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3307/register?useSSL=false";
			String user="root";
			String pass="root";
			Connection con=DriverManager.getConnection(url,user,pass);
			String q="insert into user(user_name,user_password, user_email) values(?,?,?)";
			PreparedStatement pstmt=con.prepareStatement(q);
			pstmt.setString(1, name);
			pstmt.setString(2, password);
			pstmt.setString(3, email);
			
			pstmt.executeUpdate();
			con.close();
			out.println("<h1>Done</h1>");
			
		}catch(Exception e) {
			e.printStackTrace();
			out.println("<h1>Error</h1>");
			
		}
	}
}
