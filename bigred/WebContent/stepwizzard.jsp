	<div class="stepwizard">
	    <div class="stepwizard-row">
		    <%
			int last_page = ((com.bigred.objects.SessionState)session.getAttribute("State")).getBooking().getLastPage();
			String[] step_names = {"Select location", "Select Dates", "Select Room Sizes", "Add Extras", "Review Booking", "Payment"};
			String[] step_links = {"location.jsp", "roomdates.jsp", "roomsizes.jsp", "extraoptions.jsp", "review.jsp", "payment.html"};
			int steps_number = step_names.length;
			for (int i=0; i< steps_number; i++) {
				%>
			<div class="stepwizard-step">
				<%
				if (i < last_page) {
				%>
	            <a href="<%out.print(step_links[i]);%>" class="btn btn-primary btn-circle"><%out.print(i+1);%></a>
	        	<%
	       		}
				else {
				%>
				<a class="btn disabled btn-primary btn-circle"><%out.print(i+1);%></a>
				<%
				}
				%>
	            <p><%out.print(step_names[i]);%></p>
	        </div>
	        <%
	        }
			%>
			
	    </div>
	</div>