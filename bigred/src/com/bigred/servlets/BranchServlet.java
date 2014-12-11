package com.bigred.servlets;

import com.bigred.objects.Account;
import com.bigred.objects.Booking;
import com.bigred.objects.SessionState;
import com.bigred.objects.Branch;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LocationServlet
 */
@WebServlet("/branch_submit")
public class BranchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BranchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String branch_submit = request.getParameter("branch_id_submit");
        String branch = request.getParameter("branch_id");
        if (branch_submit != null){
        	SessionState state = (SessionState)request.getSession().getAttribute("State");
        	if(state.getBooking()==null)
        		state.setBooking(new Booking());
        	state.getBooking().setBranch(Branch.getBranch(Integer.parseInt(branch)));
			response.sendRedirect("roomdates.jsp");
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
