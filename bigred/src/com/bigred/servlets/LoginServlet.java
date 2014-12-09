package com.bigred.servlets;

import com.bigred.objects.Customer;
import com.bigred.objects.Booking;
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
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		String login_submit = request.getParameter("sign_in");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if (login_submit != null){
        	SessionState state = (SessionState)request.getSession().getAttribute("State");
			Customer customer = Customer.findCustomer(username, password);
			if (customer == null) {
				 PrintWriter out= response.getWriter();
		         out.println("Wrong username or password!");
			}
			else {
				state.setCustomer(customer);
				if (state.getBooking() == null) {
					state.setBooking(new Booking());
					response.sendRedirect("profile.jsp");
				}
				else {
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
						response.sendRedirect("review.jsp");
						break;
					case (6):
						response.sendRedirect("payment.html");
						break;
					}
				}
				//response.setIntHeader("Refresh", 5);
			}
        }
        else {
        	response.sendRedirect("index.jsp");
        }
        
		//response.sendRedirect("profile.jsp");
	}

}
