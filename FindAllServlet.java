package com.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class FindAllServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        try {
            // Database connection setup
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "root");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM addrecord");

            // Execute query
            ResultSet rs = ps.executeQuery();
            
            // HTML page content
            out.println("<html>");
            out.println("<head>");
            out.println("<title>All Records</title>");
            out.println("<link rel='stylesheet' type='text/css' href='styles.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<h1>Employee Records</h1>");

            // Loop through the result set and display each record
            out.println("<div class='records'>");
            while (rs.next()) {
                out.println("<div class='record-item'>");
                out.println("<p><strong>Emp No:</strong> " + rs.getString(1) + "</p>");
                out.println("<p><strong>Emp Name:</strong> " + rs.getString(2) + "</p>");
                out.println("<p><strong>Emp Salary:</strong> " + rs.getString(3) + "</p>");
                out.println("</div>");
            }
            out.println("</div>");
            
            out.println("<a href='index.html' class='back-home'>Back to Home</a>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
            
        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}
