package com.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/depositServlet")
public class DepositServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String uname = req.getParameter("uname");
        double amount = Double.parseDouble(req.getParameter("amount"));

        try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","Anu","Anushri")) {

            PreparedStatement ps = con.prepareStatement(
                "UPDATE USER_DATA SET BALANCE = BALANCE + ? WHERE USERNAME=?");

            ps.setDouble(1, amount);
            ps.setString(2, uname);

            int rows = ps.executeUpdate();

            if(rows > 0)
                req.setAttribute("msg", "Deposit Successful");
            else
                req.setAttribute("msg", "User not found");

            req.getRequestDispatcher("DepositMessage.jsp").forward(req, res);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
