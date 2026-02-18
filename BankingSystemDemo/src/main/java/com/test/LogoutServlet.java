package com.test;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        // 🔐 Get current session (if exists)
        HttpSession session = req.getSession(false);

        if(session != null) {
            session.invalidate();   // destroy session
        }

        // redirect to login page
        res.sendRedirect("Login.html");
    }
}
