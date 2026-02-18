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

@WebServlet("/TransferServlet")
public class TransferServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String fromUser = req.getParameter("fromAccount");
        String toUser = req.getParameter("toAccount");
        double amount = Double.parseDouble(req.getParameter("amount"));

        try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","Anu","Anushri")) {

            con.setAutoCommit(false);

            // Check sender balance
            PreparedStatement check = con.prepareStatement(
                "SELECT BALANCE FROM USER_DATA WHERE USERNAME=?");
            check.setString(1, fromUser);

            ResultSet rs = check.executeQuery();

            if(!rs.next()) {
                req.setAttribute("msg", "Sender not found");
                req.getRequestDispatcher("TransferMessage.jsp").forward(req, res);
                return;
            }

            double balance = rs.getDouble("BALANCE");

            if(balance < amount) {
                req.setAttribute("msg", "Insufficient Balance");
                req.getRequestDispatcher("TransferMessage.jsp").forward(req, res);
                return;
            }

            // Debit sender
            PreparedStatement debit = con.prepareStatement(
                "UPDATE USER_DATA SET BALANCE = BALANCE - ? WHERE USERNAME=?");
            debit.setDouble(1, amount);
            debit.setString(2, fromUser);
            debit.executeUpdate();

            // Credit receiver
            PreparedStatement credit = con.prepareStatement(
                "UPDATE USER_DATA SET BALANCE = BALANCE + ? WHERE USERNAME=?");
            credit.setDouble(1, amount);
            credit.setString(2, toUser);

            int rows = credit.executeUpdate();

            if(rows == 0) {
                con.rollback();
                req.setAttribute("msg", "Receiver not found");
            } else {
                con.commit();
                req.setAttribute("msg", "Transfer Successful");
            }

            req.getRequestDispatcher("Message.jsp").forward(req, res);

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
