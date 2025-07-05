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


public class Findservlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        // Retrieve the employee name from the request
        String EName = req.getParameter("ename");

        try {
            // Database connection setup
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "root");
            PreparedStatement ps = con.prepareStatement("select * from addrecord where ename=?");

            // Set parameter for prepared statement
            ps.setString(1, EName);

            // Execute query and get results
            ResultSet rs = ps.executeQuery();

            // HTML output
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Find Employee</title>");
            out.println("<link rel='stylesheet' type='text/css' href='styless.css'>"); // Link to external CSS file
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");

            out.println("<h1>Employee Details</h1>");

            // Check if the employee record is found
            boolean recordFound = false;
            while (rs.next()) {
                out.println("<div class='employee-record'>");
                out.println("<p><strong>Emp No:</strong> " + rs.getString(1) + "</p>");
                out.println("<p><strong>Emp Name:</strong> " + rs.getString(2) + "</p>");
                out.println("<p><strong>Emp Salary:</strong> " + rs.getString(3) + "</p>");
                out.println("</div>");
                recordFound = true;
            }

            if (!recordFound) {
                out.println("<p>No record found for the employee: " + EName + "</p>");
            }

            // Back to Home link
            out.println("<a href='index.html' class='back-home'>Back to Home</a>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");

        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}
