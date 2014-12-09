package com.bigred.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RoomTypeServlet
 */
@WebServlet("/room_type_submit")
public class RoomTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomTypeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String room_type_submit = request.getParameter("room_type_submit");
        String room_type = request.getParameter("room_type");
        String weekly_cost = request.getParameter("weekly_cost");
        
        if (room_type_submit != null){
			HttpSession session = request.getSession();
			session.setAttribute("room_type", room_type);
			session.setAttribute("weekly_cost", weekly_cost);
			session.setMaxInactiveInterval(30*60);
            response.sendRedirect("extraoptions.jsp");
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