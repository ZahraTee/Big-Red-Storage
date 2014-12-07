package com.bigred.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;


/**
 * Servlet implementation class CustomerTypeServlet
 */
@WebServlet("/customer_type_submit")
public class CustomerTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerTypeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get request parameters for userID and password
		String customer_type_submit = request.getParameter("customer_type_id_submit");
        String customer_type = request.getParameter("customer_type_id");
        
        if (customer_type_submit != null){
			HttpSession session = request.getSession();
			session.setAttribute("customer_type", customer_type);
			session.setMaxInactiveInterval(30*60);
			Cookie customer_type_cookie = new Cookie("customer_type", customer_type);
            customer_type_cookie.setMaxAge(30*60);
            response.addCookie(customer_type_cookie);
            response.sendRedirect("location.jsp");
        }
	}

}