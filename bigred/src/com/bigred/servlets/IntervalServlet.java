package com.bigred.servlets;
import java.io.PrintWriter;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class IntervalServlet
 */
@WebServlet("/interval_submit")
public class IntervalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IntervalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String interval_submit = request.getParameter("interval_submit");
		
		if (interval_submit != null){
	        String start = request.getParameter("startDate");
	        String end_type = request.getParameter("endDate_type");
	        String end = request.getParameter("endDate");
	        String days = request.getParameter("days");
	        String days_type = request.getParameter("days_type");
	        
	        Date start_date = null;
	    	Date end_date = null;
	    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    	
	    	try{
	        	start_date = (Date) df.parse(start);
	        }
	        catch (ParseException e) {
	        	e.printStackTrace();
	        }
	    	
	        if (end_type.equals("days")) {
	
	        	int days_number = Integer.parseInt(days);
	        	
	        	Calendar c = Calendar.getInstance(); 
	        	c.setTime(start_date); 
	        	
	        	switch (days_type){
	        	case("day"):
	        		c.add(Calendar.DATE, days_number);
	        		break;
	        	case("week"):
	        		c.add(Calendar.WEEK_OF_YEAR, days_number);
	        		break;
	        	case("month"):
	        		c.add(Calendar.MONTH, days_number);
	        		break;
	        	}
	        	
	        	end_date = c.getTime();
	        }
	        else {
	        	try{
	            	end_date = (Date) df.parse(end);
	            }
	            catch (ParseException e) {
	            	e.printStackTrace();
	            }
	        }

			HttpSession session = request.getSession();
			session.setAttribute("start_date", start_date);
			session.setAttribute("end_date", end_date);
			session.setMaxInactiveInterval(30*60);
			PrintWriter out= response.getWriter();
			out.println(end_type + " - " + df.format(start_date) + " - " + df.format(end_date));
			out.println(days_type);
            response.sendRedirect("roomsizes.jsp");
        }
		else {
			response.sendRedirect("index.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
