package com.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see GenericServlet#GenericServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#service(ServletRequest request, ServletResponse response)
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
			try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","Anu","Anushri");
				PreparedStatement psmt = con.prepareStatement("INSERT INTO USER_DATA (FNAME, LNAME, USERNAME, PASSWORD, BALANCE)\r\n"
						+ "VALUES (?, ?, ?, ?, 0)\r\n"
						+ "");
			    
				String fname = req.getParameter("fname");
				String lname = req.getParameter("lname");
				String uname = req.getParameter("uname");
				String pword = req.getParameter("pword");
				
                 psmt.setString(1, fname);
				 psmt.setString(2, lname);
				 psmt.setString(3, uname);
				 psmt.setString(4, pword);
				 int k =  psmt.executeUpdate();
				  if(k > 0)
					 {
	        req.setAttribute("msg", "Registration Success");
						RequestDispatcher rd = req.getRequestDispatcher("RegisterSuccess.jsp");
						rd.forward(req, res);
					 }
					 else {
						 req.setAttribute("msg","Registration Failed");
						 req.getRequestDispatcher("Failed.jsp").forward(req, res);
					 }
				 
				  
				}
			
			catch(Exception e) {
				if(e.getMessage().contains("unique")) {
	                req.setAttribute("msg", "Username already exists. Try another.");
	            } else {
	                req.setAttribute("msg", "Database Error");
	            }

	            req.getRequestDispatcher("Failed.jsp").forward(req, res);
				
				}
				
			}
	}


