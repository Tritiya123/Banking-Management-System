package com.test;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/MiniStatementServlet")
public class MiniStatementServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String uname = req.getParameter("uname");

        List<Transaction> list = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:ORCL","Anu","Anushri")) {

            PreparedStatement ps = con.prepareStatement(
                "SELECT TYPE, AMOUNT, TDATE FROM TRANSACTION_HISTORY WHERE USERNAME=? ORDER BY TDATE DESC FETCH FIRST 5 ROWS ONLY");

            ps.setString(1, uname);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                String type = rs.getString("TYPE");
                double amount = rs.getDouble("AMOUNT");
                String date = rs.getString("TDATE");

                list.add(new Transaction(type, amount, date));
            }

            req.setAttribute("list", list);

            req.getRequestDispatcher("StatementMessage.jsp").forward(req, res);

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
