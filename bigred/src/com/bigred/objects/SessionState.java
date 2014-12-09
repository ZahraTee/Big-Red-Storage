package com.bigred.objects;

import java.io.IOException;

import javax.servlet.http.*;
import javax.servlet.*;

public class SessionState {
	
	private Customer customer;
	private Booking booking;
	
	/**
	 * checks if the HttpSession is New, if so it assigns a SessionState to it and redirects the to the index page.
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public static void formalizeSession(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		HttpSession session = request.getSession();
		if(session.getAttribute("State")==null)
		{
			SessionState state = new SessionState();
			//Assign state.customer from cookies if possible
			Cookie[] cookies = request.getCookies();
			Customer c = null;
			for(int i=0;i<cookies.length;i++)
			{
				if(cookies[i].getName().equals("CustomerId"))
				{
					c=Customer.getCustomerFromCookieValue(cookies[i].getValue());
					break;
				}
			}
			state.customer=c;
			
			session.setAttribute("State",state);
			response.sendRedirect("index.jsp");
		}
	}
	private SessionState()
	{
		customer=null;
		booking=null;
	}
	public Customer getCustomer()
	{
		return customer;
	}
	public Booking getBooking()
	{
		return booking;
	}
	public void setCustomer(Customer customer)
	{
		this.customer=customer;
	}
	public void setBooking(Booking booking)
	{
		this.booking=booking;
	}
}
