package com.bigred.servlets;
import java.io.PrintWriter;

import com.bigred.objects.SessionState;
import com.bigred.objects.Booking;
import com.bigred.objects.Customer;
import com.bigred.objects.CustomerType;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		String customer_type_submit = request.getParameter("customer_type_id_submit");
        String customer_type = request.getParameter("customer_type_id");
        
        if (customer_type_submit != null){
        	SessionState state = (SessionState)request.getSession().getAttribute("State");
			state.setBooking(new Booking());
			state.setCustomer(new Customer(CustomerType.getCustomerType(Integer.parseInt(customer_type))));
			response.sendRedirect("location.jsp");
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
