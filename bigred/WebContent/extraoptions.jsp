<!DOCTYPE html>
<%@page import="com.bigred.objects.BookingOption"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Select Extra Options</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">

		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="css/style.css">
		<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,700' rel='stylesheet' type='text/css'>

		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

	</head>
	<body>

    <%@ include file="/nav-bar.jsp" %>
	<% com.bigred.objects.SessionState.assertAccessValidity(request,response,4); %>
	

	<div class="container">
		<div class = "page-header">
			<div class = "page-heading">
				<a href="roomsizes.jsp" class="btn btn-default">Back</a>
				<h2>Select Extra Options</h2>
			</div>

			<%@ include file="/stepwizzard.jsp" %>
		</div>
		
		<form action="extra_options_submit" method="get" class="form-horizontal">
		<div class = "col-md-6 col-sm-6">
		<%
		com.bigred.objects.SessionState state = (com.bigred.objects.SessionState)session.getAttribute("State");
		int discount=-1;
		boolean validPage = state.getBooking()!=null && state.getBooking().getLastPage()>=4;
		if(validPage)
		{
		discount = state.getCustomer().getCustomerType().getDiscount();
       	List<BookingOption> list = BookingOption.getBookingOptions(state.getCustomer().getCustomerType().getId());
       	for(BookingOption option : list) {
       		
       		int id = option.getId();
       		String name = option.getName();
       		String description = option.getDescription();
       		double price = option.getPrice() * (1 - discount/100.0);
       		String price_type = option.getPriceType();
       	%>
		    <div class="form-group">
		        <div class="col-sm-1 col-md-offset-2">
		          	<div class="checkbox">
		                <input data-price="<%out.print(price);%>" data-pricetype="<%out.print(price_type);%>" type="checkbox" name="checkedOptions" value="<%out.print(id);%>">
			        </div>
			    </div>
		        <label style="text-align:left" class="col-sm-3 control-label" for="check1"><%out.print(name);%></label>
			</div>
		<%
       	}
		}
		%>			  	
			
		</div>


		<div class = "col-md-4 col-md-offset-1 col-sm-5">
			<div class ="quote redbox">
				<span>£</span>
<<<<<<< HEAD
				<span id="weekly_cost"><%out.print(!validPage?"":state.getBooking().getRoomType().getPrice() * (1 - discount/100.0));%></span>
=======
				<span id="weekly_cost"><%out.print((int)(((com.bigred.objects.SessionState)session.getAttribute("State")).getBooking().getRoomType().getPrice() * (1 - discount/100.0)));%></span>
>>>>>>> FETCH_HEAD
				<span> - weekly cost</span>
			</div>
			<div class ="quote redbox">
				<span>£</span>
				<span id="extra_cost">0</span>
				<span> - extra costs (only payed once)</span>
			</div>
			<input id="extra_options_submit" type="submit" class="btn btn-primary pull-right" name="extra_options_submit" value="Continue">
		</div>
		</form>
	</body>
	
	<script>
		$(document).ready(function() {
		
			$(".checkbox :checkbox").change(function() {
			    var price_type = ($(this).data("pricetype"));
			    var price = parseFloat($(this).data("price"));
			    var weekly_cost = parseFloat($("#weekly_cost").text());
			    var extra_cost = parseFloat($("#extra_cost").text());

				if(this.checked) {
					if (price_type == "weekly") {
						$("#weekly_cost").text(price + weekly_cost);
					}
					else {
						if (price_type == "once") {
							$("#extra_cost").text(price + extra_cost);
						}
					}
			    }
				else {
					if (price_type == "weekly") {
						$("#weekly_cost").text(weekly_cost - price);
					}
					else {
						if (price_type == "once") {
							$("#extra_cost").text(extra_cost - price);
						}
					}
				}
		    });
		});
	</script>
	
	<script src="http://code.jquery.com/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
	
</html>