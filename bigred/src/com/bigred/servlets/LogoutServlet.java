package com.bigred.servlets;

import com.bigred.objects.Customer;
import com.bigred.objects.Booking;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import com.bigred.objects.SessionState;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        	SessionState state = (SessionState)request.getSession().getAttribute("State");
        	if(state.getCustomer()!=null)
        	{
        		Customer customer =new Customer(state.getCustomer().getCustomerType());
        		state.setCustomer(customer);
        	}
			Cookie c = null;
			Cookie[] cookies = request.getCookies();
			if(cookies!=null)
			{
				for(int i=0;i<cookies.length;i++)
				{
					if(cookies[i].getName().equals("CustomerId"))
					{
						c=cookies[i];
						break;
					}
				}
			}
			if(c!=null)
			{
				c.setMaxAge(0);
				response.addCookie(c);
			}
			Booking booking = state.getBooking();
			if(booking==null)
				response.sendRedirect("index.jsp");
			else
			{
				int last_page = state.getBooking().getLastPage();
				switch (last_page) {
					case (1):
						response.sendRedirect("location.jsp");
						break;
					case (2):
						response.sendRedirect("roomdates.jsp");
						break;
					case (3):
						response.sendRedirect("roomsizes.jsp");
						break;
					case (4):
						response.sendRedirect("extraoptions.jsp");
						break;
					case (5):
					case (6):
						response.sendRedirect("review.jsp");
						break;
					
					}
			}

        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
