<!DOCTYPE html>
<%@page import="com.bigred.objects.BookingOption"%>
<%@page import="com.bigred.objects.BookingOptions"%>
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

	<%

	String customer_type = null;
	String weekly_cost = null;
	if (session.getAttribute("customer_type") == null || session.getAttribute("weekly_cost") == null) {
	    response.sendRedirect("index.jsp");
	}
	else {
		customer_type = (String) session.getAttribute("customer_type");
		weekly_cost = (String) session.getAttribute("weekly_cost");
	}
	%>

    <nav role="navigation" class="navbar navbar-default">

        <!-- Brand and toggle get grouped for better mobile display -->

        <div class="navbar-header">
            <button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="index.html" class="navbar-brand">Big Red Storage Company</a>
        </div>

        <!-- Collection of nav links, forms, and other content for toggling -->

        <div id="navbarCollapse" class="collapse navbar-collapse">

            <ul class="nav navbar-nav">
                <li class="active"><a href="index.html">Home</a></li>
            </ul>

           <form class="navbar-form navbar-right" role="search">
                <div class="form-group">
                        <input type="text" class="form-control" name="username" placeholder="Username">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" name="password" placeholder="Password">
                    </div>
                    <button type="submit" class="btn btn-default">Sign In</button>
            </form>

        </div>

    </nav>


	<div class="container">
		<div class = "page-header">
			<div class = "page-heading">
				<a href="roomsizes.html" class="btn btn-default">Back</a>
				<h2>Select Extra Options</h2>
			</div>

			<div class="stepwizard">
			    <div class="stepwizard-row">
			        <div class="stepwizard-step">
			            <a href="location.html" class="btn btn-default btn-circle">1</a>
			            <p>Select Location</p>
			        </div>
			        <div class="stepwizard-step">
			            <a href="roomdates.html" class="btn btn-default btn-circle">2</a>
			            <p>Select Dates</p>
			        </div>
			        <div class="stepwizard-step">
			            <a href="roomsizes.html" class="btn btn-default btn-circle">3</a>
			            <p>Select Room Sizes</p>
			        </div>
			        <div class="stepwizard-step">
			            <a href="extraoptions.html" class="btn btn-primary btn-circle">4</a>
			            <p>Add Extras</p>
			        </div>
			        <div class="stepwizard-step">
			            <a href="review.html" class="btn btn-default btn-circle">5</a>
			            <p>Review Booking</p>
			        </div>
			        <div class="stepwizard-step">
			            <a href="payment.html" class="btn btn-default btn-circle">6</a>
			            <p>Payment</p>
			        </div>  
			    </div>
			</div>
		</div>
		
		<form action="extra_options_submit" method="get" class="form-horizontal">
		<div class = "col-md-6 col-sm-6">
		<%
		BookingOptions options = new BookingOptions();
       	List<BookingOption> list = new ArrayList<BookingOption>();
        int customer_type_id = Integer.parseInt(customer_type);
       	list = options.getBookingOptions(customer_type_id);
       	for(BookingOption option : list) {
       		int id = option.getId();
       		String name = option.getName();
       		String description = option.getDescription();
       		int price = option.getPrice();
       		String price_type = option.getPriceType();
       	%>
		    <div class="form-group">
		        <div class="col-sm-1 col-md-offset-2">
		          	<div class="checkbox">
		                <input data-price="<%out.print(price);%>" data-pricetype="<%out.print(price_type);%>" type="checkbox" value="<%out.print(id);%>">
			        </div>
			    </div>
		        <label style="text-align:left" class="col-sm-3 control-label" for="check1"><%out.print(name);%></label>
			</div>
		<%
       	}
		%>			  	
			
		</div>


		<div class = "col-md-4 col-md-offset-1 col-sm-5">
			<div class ="quote redbox">
				<span>£</span>
				<span id="weekly_cost"><%out.print(weekly_cost);%></span>
				<span> - weekly cost</span>
			</div>
			<div class ="quote redbox">
				<span>£</span>
				<span id="extra_cost">0</span>
				<span> - extra costs (only payed once)</span>
			</div>
			<input id="extraoptions_submit" type="submit" class="btn btn-primary pull-right" name="extraoptions_submitt" value="Continue">
		</div>
		</form>
	</body>
	
	<script>
		$(document).ready(function() {
		
			$(".checkbox :checkbox").change(function() {
			    var price_type = $(this).data("pricetype");
			    var price = $(this).data("price");
			    var weekly_cost = parseInt($("#weekly_cost").text());
			    var extra_cost = parseInt($("#extra_cost").text());

				if(this.checked) {
					if (price_type == "weekly") {
						$("#weekly_cost").text(price+weekly_cost);
					}
					else {
						if (price_type == "once") {
							$("#extra_cost").text(price+extra_cost);
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