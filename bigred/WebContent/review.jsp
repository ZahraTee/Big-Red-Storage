<!DOCTYPE html>
<%@page import="java.util.Date"%>
<%@page import="java.text.*" %>

<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>Review Booking</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">

		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="css/style.css">
		<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,700' rel='stylesheet' type='text/css'>

		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
	</head>
	<body>

	    <%@ include file="/nav-bar.jsp" %>
		<% com.bigred.objects.SessionState.assertAccessValidity(request,response,5); %>



		<div class="container">

			<div class = "page-header">
				<div class = "page-heading">
					<a href="extraoptions.html" class="btn btn-default">Back</a>
					<h2>Review Quote</h2>
				</div>
				
				<%@ include file="/stepwizzard.jsp" %>

			</div>

			<div class="row">
				<div class="col-md-12">

					<div class="panel panel-defaut">
				        <div class="panel-heading">
				            <h3 class="panel-title">Selected Options : Review</h3>
				        </div>
						<%
						
						com.bigred.objects.SessionState state = (com.bigred.objects.SessionState)session.getAttribute("State");
						String location="";
						String startDate=null,endDate=null;
						double roomSize=-1;
						if(state.getBooking()!=null && state.getBooking().getLastPage()>=5)
						{
							location = state.getBooking().getBranch().getName();
							startDate = DateFormat.getDateTimeInstance(
						            DateFormat.MEDIUM, DateFormat.SHORT).format(state.getBooking().getStartDate());
							endDate = DateFormat.getDateTimeInstance(
						            DateFormat.MEDIUM, DateFormat.SHORT).format(state.getBooking().getEndDate());
							roomSize = state.getBooking().getRoomType().getSize();
						}
					
						%>
				        <div class="panel-body">
				            <li class = "list-unstyled">
				            	<ul>Location: <b><%out.print(location);%></b></ul>
				            	<ul>Start Date: <b><%out.print(startDate);%></b></ul>
				            	<ul>End Date: <b><%out.print(endDate);%></b></ul>
				            	<ul>Room Size: <b><%out.print(roomSize);%> feet</b></ul>
				            </li>
				        </div>

				        <div class="panel-footer">
				            <span>Save Quote For Later</span>
				            
				            <form class="form-horizontal pull-right">
			                        <div class="control-group">
			                        <% if(!state.getCustomer().isLoggedIn()) 
				            		{%>
			                            <input placeholder="E-mail" name="email" type="email" autofocus="">
			                            <%} %>
			                        <!-- Change this to a button or input when using this as a form -->
			                        <a onclick="alert('Email Sent!')" class="btn btn-default">Send e-mail</a>
			                        </div>
			                </form>
			                
				        </div>
				    </div>

				</div>
			</div>

			<!-- I was thinking we could replace this entire section with a continue to payment button if they're signed in? -->
			
			<%
			if (!customer_logged_in) {
			%>
			<div class="row">
				<div class="col-md-6">
			        <div class="panel panel-defaut">
				        <div class="panel-heading">
				            <h3 class="panel-title">Sign In</h3>
				        </div>
				        <div class="panel-body">
				            <form action="login" method="post" role="form">
				                <fieldset>
				                    <div class="form-group">
				                        <input type="text" class="form-control" name="username" placeholder="Username" autofocus="">
				                    </div>
				                    <div class="form-group">
				                        <input type="password" class="form-control" name="password" placeholder="Password" value="">
				                    </div>
				                    <div class="checkbox">
				                        <label>
				                            <input name="remember" type="checkbox" value="Remember Me">Remember Me
				                        </label>
				                    </div>
				                    <!-- Change this to a button or input when using this as a form -->
				                    <input type="submit" class="btn btn-primary pull-right" name="sign_in" value="Sign in">
				                </fieldset>
				            </form>
				        </div>
				    </div>
		    	</div>

			   <div class="col-md-6">
			        <div class="panel panel-default">
			            <div class="panel-heading">
			                <h3 class="panel-title">Register</h3>
			            </div>
			            <div class="panel-body">
			                <form method="post" action="register" role="form">
			                    <fieldset>
			                    	<div class="form-group">
			                            <input class="form-control" placeholder="first name" name="first_name" type="text" autofocus="">
			                        </div>
			                    	<div class="form-group">
			                            <input class="form-control" placeholder="surname" name="surname" type="text" autofocus="">
			                        </div>
			                    	<div class="form-group">
			                            <input class="form-control" placeholder="username" name="username" type="text" autofocus="">
			                        </div>
			                        <div class="form-group">
			                            <input class="form-control" placeholder="email" name="email" type="email" autofocus="">
			                        </div>
			                        <div class="form-group">
			                            <input class="form-control" placeholder="password" name="password" type="password" value="">
			                        </div>
			                        <!-- Change this to a button or input when using this as a form -->
			                        <input type="submit" class="btn btn-primary pull-right" name="register" value="Create Account">
			                    </fieldset>
			                </form>
			            </div>
			        </div>
			    </div>
			</div>
			<%
			}
			else {
			%>
			
			<a href="payment" class="btn btn-primary pull-right">Proceed to Checkout</a>
			
			<%
			}
			%>
			

		</div>

	<script src="http://code.jquery.com/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
	</body>
	
</html>