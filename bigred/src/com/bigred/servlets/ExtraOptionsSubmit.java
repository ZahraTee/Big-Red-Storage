package com.bigred.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bigred.objects.BookingOption;
import com.bigred.objects.SessionState;

/**
 * Servlet implementation class ExtraOptionsSubmit
 */
@WebServlet("/extra_options_submit")
public class ExtraOptionsSubmit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExtraOptionsSubmit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String extra_options_submit = request.getParameter("extra_options_submit");
		String[] checked_options= request.getParameterValues("checkedOptions");
		
		if (extra_options_submit != null) {
			SessionState state = (SessionState)request.getSession().getAttribute("State");
			
			String option_ids = "(";
			
			if (checked_options != null) 
			{
				for(int i=0;i<checked_options.length;i++)
				{
					if(i!=0) {
						option_ids += ",";
					}
					option_ids += checked_options[i];
				}
			}
			
			option_ids += ")";
			
			state.getBooking().setBookingOptions(BookingOption.getBookingOptionsById(option_ids));
			response.sendRedirect("review.jsp");
		}
		else {
			response.sendRedirect("index.jsp");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
