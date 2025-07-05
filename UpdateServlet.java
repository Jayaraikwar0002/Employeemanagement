 package com.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdateServlet  extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse res)throws IOException ,ServletException{
	res.setContentType("text/html");
	PrintWriter out=res.getWriter();
	
	String EName=req.getParameter("ename");
	String ESalary=req.getParameter("esalary");
	
	 try {
		 Class.forName("com.mysql.jdbc.Driver");

		 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","root");
		 PreparedStatement ps=con.prepareStatement("update addrecord  set esalary=? where ename=?");
		 
		 ps.setString(2,EName);
		 ps.setString(1, ESalary);
		
		 int click=ps.executeUpdate();
		 if(click==1) {
			 out.println("<h1>"+click+"Data update data sucessfully...........</h1>");
			 
		 }
		 else {
			 out.println("<h1>"+click+"Data are not update  retry");
		 }
	 }
	
	 catch(Exception e) {
		 out.println(e);
	 }
}}