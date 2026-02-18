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

@WebServlet("/WithdrawServlet")
public class WithdrawServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String uname = req.getParameter("uname");
        String amtStr = req.getParameter("amount");

        // 🔹 Validation
        if(uname == null || uname.trim().isEmpty() ||
           amtStr == null || amtStr.trim().isEmpty()) {

            req.setAttribute("msg", "All fields are required");
            req.getRequestDispatcher("Message.jsp").forward(req, res);
            return;
        }

        double amount = Double.parseDouble(amtStr);

        try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","Anu","Anushri")) {

            // 1️⃣ Check balance
            PreparedStatement check = con.prepareStatement(
                "SELECT BALANCE FROM USER_DATA WHERE USERNAME=?");

            check.setString(1, uname);

            ResultSet rs = check.executeQuery();

            if (rs.next()) {

                double balance = rs.getDouble("BALANCE");

                // 2️⃣ Check sufficient balance
                if (balance >= amount) {

                    // 3️⃣ Deduct money
                    PreparedStatement ps = con.prepareStatement(
                        "UPDATE USER_DATA SET BALANCE = BALANCE - ? WHERE USERNAME=?");

                    ps.setDouble(1, amount);
                    ps.setString(2, uname);

                    ps.executeUpdate();

                    req.setAttribute("msg",
                        "₹ " + amount + " withdrawn successfully!");

                } else {
                    req.setAttribute("msg", "Insufficient Balance!");
                }

            } else {
                req.setAttribute("msg", "User not found!");
            }

            // 4️⃣ Show result
            req.getRequestDispatcher("WithdrawMessage.jsp").forward(req, res);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("msg", "Error during withdrawal");
            req.getRequestDispatcher("WithdrawMessage.jsp").forward(req, res);
        }
    }
}
