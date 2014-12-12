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
@WebServlet("/update_password")
public class UpdatePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionState state = (SessionState)request.getSession().getAttribute("State");
		Customer customer = state.getCustomer();
		String oldPassword = request.getParameter("oldPassword");
		if(Customer.findCustomer(customer.getAccount().getUserName(),oldPassword)!=null)
			{
			String 	password = request.getParameter("newPassword");
			customer.changePassword(password);
			response.sendRedirect("profile.jsp");
			}
		else
		{
			PrintWriter out = response.getWriter();  
			response.setContentType("text/html");  
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Old Password Incorrect');");
			out.println("location='profile.jsp?BooleanArgs=001';");
			out.println("</script>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        	
				doGet(request,response);
	}

}
