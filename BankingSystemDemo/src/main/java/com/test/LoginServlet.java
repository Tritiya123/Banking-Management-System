package com.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private static final String URL =
        "jdbc:oracle:thin:@localhost:1521:ORCL";
    private static final String USER = "Anu";
    private static final String PASS = "Anushri";

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String uname = request.getParameter("uname");
        String pword = request.getParameter("pword");

        String sql =
            "SELECT FNAME FROM USER_DATA WHERE USERNAME=? AND PASSWORD=?";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            try (Connection con = DriverManager.getConnection(URL, USER, PASS);
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, uname);
                ps.setString(2, pword);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    request.setAttribute("msg", "Login Successful");
                    request.setAttribute("fname", rs.getString("FNAME"));

                    request.getRequestDispatcher("LoginSuccess.jsp")
                           .forward(request, response);
                } else {
                    request.setAttribute("msg", "Invalid username or password");
                    request.getRequestDispatcher("Failed.jsp")
                           .forward(request, response);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "Database Error");
            request.getRequestDispatcher("Failed.jsp")
                   .forward(request, response);
        }
    }
}
