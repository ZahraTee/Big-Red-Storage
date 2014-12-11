<!DOCTYPE html>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.bigred.objects.Booking"%>
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

	</head>
	<body>

    <nav role="navigation" class="navbar navbar-default">

        <!-- Brand and toggle get grouped for better mobile display -->

        <div class="navbar-header">
            <button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="index.jsp" class="navbar-brand">Big Red Storage Company</a>
        </div>

        <!-- Collection of nav links, forms, and other content for toggling -->

        <div id="navbarCollapse" class="collapse navbar-collapse">

            <ul class="nav navbar-nav">
                <li class="active"><a href="index.jsp">Home</a></li>
                <li><a href="index.html"></a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
				 <li><a href="logout">Log Out</a></li>
			</ul>

        </div>

    </nav>




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
				com.bigred.objects.Customer customer = ((com.bigred.objects.SessionState)session.getAttribute("State")).getCustomer();
        		%>
		        <div class="panel-body">
		            <li class = "list-unstyled">
		            	<ul>Name: <b><%out.print(((com.bigred.objects.SessionState)session.getAttribute("State")).getCustomer().getName());%></b></ul>
		            	<ul>Email: <b><%out.print(((com.bigred.objects.SessionState)session.getAttribute("State")).getCustomer().getEmail());%></b></ul>
		            	<ul>Phone: <b><%out.print(((com.bigred.objects.SessionState)session.getAttribute("State")).getCustomer().getPhone());%></b></ul>
		            </li>
		        </div>
		        <div class="panel-footer">
	                <a href="javascript:;" class="btn btn-primary col-sm-12">Edit Details</a>
		        </div>
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
		            <li class = "list-unstyled">
		            	<ul>Address1: <b><%out.print(address[0]);%></b></ul>
		            	<ul>Address2: <b><%out.print(address[1]);%></b></ul>
		            	<ul>Postcode: <b><%out.print(address[2]);%></b></ul>
		            	<ul>City: <b><%out.print(address[3]);%></b></ul>
		            	<ul>Country: <b><%out.print(address[4]);%></b></ul>
		            </li>
		        </div>
		        <div class="panel-footer">
	                <a href="javascript:;" class="btn btn-primary col-sm-12">Edit Address</a>
		        </div>
		    </div>
		</div>

		<div class="col-md-6 col-sm-6">
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
		            	<li>Total price: <b><%out.print(price);%></b></li>
		            </ul>
		            </div>
		            <%
		            }
		        %>
		            <div class="booking past">
		            	<ul class="list-unstyled">
		            	<li>Ref No. #123456</li>
		            	<li>Starts 12-12-14</li>
		            	<li>Detail</li>
		            </ul>
		            </div>
		        </div>
		    </div>
		</div>
		
	</div>
	

	<script src="http://code.jquery.com/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
	</body>
	
</html>