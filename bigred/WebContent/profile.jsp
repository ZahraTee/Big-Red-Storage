<!DOCTYPE html>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.bigred.objects.*"%>
<%@page import="java.util.Date"%>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>My Profile</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">

		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="css/style.css">
		<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,700' rel='stylesheet' type='text/css'>

		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
		
		<style type="text/css">
			.wael 
			{
    			float:right;
    		}
        </style>
    

	</head>
	<body>
	<%  
	boolean detailsB=false,addressB=false,passwordB=false;
	String result = request.getParameter("BooleanArgs");
	if(result!=null)
	{
		detailsB=result.substring(0,1).equals("1");
		addressB=result.substring(1,2).equals("1");
		passwordB=result.substring(2,3).equals("1");
	}
	%>
    <%@ include file="/nav-bar.jsp" %>
    

		<% 
			SessionState state = (SessionState)request.getSession().getAttribute("State");
        	if(state.getCustomer()!=null && state.getCustomer().isLoggedIn())
        	{
		%>

<div class="container">

	<div class = "page-header"> 
		<h2>My Profile</h2>
	</div>

	<div class="row">
		<div class="col-sm-6">
			<div class="panel panel-defaut">
		        <div class="panel-heading">
		            <h3 class="panel-title">My Details</h3>
		        </div>
				<%
				com.bigred.objects.Customer c = ((com.bigred.objects.SessionState)session.getAttribute("State")).getCustomer();
        		String name = c.getName(),email=c.getEmail(),
        				fullName=c.getName(),firstName=c.getFirstName(),lastName=c.getLastName(),phone=c.getPhone();
        		%>
        		<% if(!detailsB)
        			{%>
		        <div class="panel-body">
		            <li class = "list-unstyled">
		            	<ul>Name: <b><%out.print(fullName);%></b></ul>
		            	<ul>Email: <b><%out.print(email);%></b></ul>
		            	<ul>Phone: <b><%out.print(phone);%></b></ul>
		            </li>
		        </div>
		        <div class="panel-footer">
	                <a href="profile.jsp?BooleanArgs=100" class="btn btn-primary col-sm-12">Edit Details</a>
		        </div>
		        <%}else{ %>
		        <div class="panel-body">
		        <form action="update_details" method="get">
		            	<label>First Name:</label>
		            	<input  type="text" size="30" id="firstName" name="firstName" placeholder="<% out.print(firstName);%>">		            	            	
		            	<br>
		            	<label>Last Name:</label>
		            	<input  type="text" size="30" id="lastName" name="lastName" placeholder="<% out.print(lastName);%>">		            
						<br>
		            	<label>Email:</label>
		            	<input  type="text" size="30" id="email" name="email" placeholder="<% out.print(email);%>">		            
						<br>
		            	<label>Phone:</label>
		            	<input  type="text" size="30" id="phone" name="phone" placeholder="<% out.print(phone);%>">		            								                    
						<div class="panel-footer">
	                	<input type="submit" value="Edit Details" class="btn btn-primary col-sm-12">
		        		</div>
					</form>
					</div>
					</div>
		        <%} %>
		        <% if(!passwordB)
        			{%>		        
		        <div class="panel-footer">
	                <a href="profile.jsp?BooleanArgs=001" class="btn btn-primary col-sm-12">Change Password</a>
		        </div>
		        <%}else{ %>
		        <div class="panel-body">
		        <form action="update_password" method="p">
		            	<label>Old Password:</label>
		            	<input  type="password" size="30" id="oldPassword" name="oldPassword" placeholder="***************">		            	            	
		            	<br>
		            	<label>New Password:</label>
		            	<input  type="password" size="30" id="newPassword" name="newPassword" placeholder="***************">		            						            								                    
						<div class="panel-footer">
	                	<input type="submit" value="Change Password" class="btn btn-primary col-sm-12">
		        		</div>
					</form>
					</div>
					</div>
		        <%} %>
		    </div>
		</div>
		
		<div class="col-sm-6">
			<div class="panel panel-defaut">
		        <div class="panel-heading">
		            <h3 class="panel-title">Address</h3>
		        </div>
				<%
				String[] address = customer.getAddress();
        		%>
		        <div class="panel-body">
		        <%if(!addressB){%>
		            <div>
		            	<ul>Address1: <b><%out.print(address[0]);%></b></ul>
		            	<ul>Address2: <b><%out.print(address[1]);%></b></ul>
		            	<ul>Postcode: <b><%out.print(address[2]);%></b></ul>
		            	<ul>City: <b><%out.print(address[3]);%></b></ul>
		            	<ul>Country: <b><%out.print(address[4]);%></b></ul>
		            </div>
		           	         
		            </div>
		        	<div class="panel-footer">
		        	<a href="profile.jsp?BooleanArgs=010" class="btn btn-primary col-sm-12">Edit Address</a>
		        	</div>
		         <%} else { %>
		            <form action="update_address" method="get">
		            	<label>Address1:</label>
		            	<input  type="text" size="30" id="address1" name="address1" placeholder="<% out.print(address[0]);%>">		            	            	
		            	<br>
		            	<label>Address2:</label>
		            	<input  type="text" size="30" id="address2" name="address2" placeholder="<% out.print(address[1]);%>">		            
						<br>
		            	<label>Postcode:</label>
		            	<input  type="text" size="30" id="postcode" name="postcode" placeholder="<% out.print(address[2]);%>">		            
						<br>
		            	<label>City:</label>
		            	<input  type="text" size="30" id="city" name="city" placeholder="<% out.print(address[3]);%>">		            
						<br>
		            	<label>Country:</label>
		            	<input  type="text" size="30" id="country" name="country" placeholder="<% out.print(address[4]);%>">		            
						<div class="panel-footer">
	                	<input type="submit" value="Edit Address" class="btn btn-primary col-sm-12">
		        		</div>
					</form>
					</div>
		        
					<%} %>
					
		        
		    </div>
		    </div>
		    
		  		


	</div>
	
	<div class="row">
		<div class="col-sm-6">
			<div class="panel panel-defaut">
		        <div class="panel-heading">
		            <h3 class="panel-title">My Bookings</h3>
		        </div>      

		        <div class="panel-body">
		        <%
		        List<com.bigred.objects.Booking> bookings = com.bigred.objects.Booking.allBookingsOf(customer);
		        for (Booking booking : bookings) {
		        	int id = booking.getId();
		        	Date startDate = booking.getStartDate();
		        	Date endDate = booking.getEndDate();
		        	double price = booking.totalCost();
		        %>
		            <div class="booking">
		            	<ul class="list-unstyled">
		            	<li>Ref No.: <b><%out.print(id);%></b></li>
		            	<li>Start: <b><%out.print(startDate.toString());%></b></li>
		            	<li>End: <b><%out.print(endDate.toString());%></b></li>
		            	<li>Total price: <b><%out.print(String.format("%.2f£",price));%></b></li>
		            </ul>
		            </div>
		            <%
		            }
		        %>		        
		        </div>
		    </div>
		</div>
		<div class="panel-body">
			<form action="new_booking" method="get">
		    <div class="col-sm-6">
		    <div class="panel panel-defaut">		    
		        <div class="panel-body">
	                <a href="new_booking" class="btn btn-primary col-sm-12">Make A New Booking</a>
		        </div>
		    </div>
			</div>
	        </form>
		</div>
	</div>
	

	<script src="http://code.jquery.com/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
	</body>
	
</html>
<%}
	else if(response.getStatus()!=302)
		response.sendRedirect("index.jsp");
%>