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

@WebServlet("/BalanceServlet")
public class BalanceServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String uname = req.getParameter("uname");

        try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","Anu","Anushri")) {

            PreparedStatement ps = con.prepareStatement(
                "SELECT BALANCE FROM USER_DATA WHERE USERNAME=?");

            ps.setString(1, uname);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                double balance = rs.getDouble("BALANCE");
                req.setAttribute("msg", "Balance = ₹ " + balance);
            } else {
                req.setAttribute("msg", "User not found");
            }

            req.getRequestDispatcher("BalanceMessage.jsp").forward(req, res);

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
