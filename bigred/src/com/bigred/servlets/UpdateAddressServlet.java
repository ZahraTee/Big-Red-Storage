package com.bigred.servlets;

import com.bigred.objects.Account;
import com.bigred.objects.Customer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bigred.objects.SessionState;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/update_address")
public class UpdateAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAddressServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionState state = (SessionState)request.getSession().getAttribute("State");
		Customer customer = state.getCustomer();

		String 	address1 = request.getParameter("address1"),
				address2 = request.getParameter("address2"),
				city = request.getParameter("city"),
				country = request.getParameter("country"),
				postcode = request.getParameter("postcode");
		customer.addAddress(address1,address2,city,country,postcode);
		response.sendRedirect("profile.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        	
				doGet(request,response);
	}

}
