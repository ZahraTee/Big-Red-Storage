<!DOCTYPE html>

<%@page import="com.bigred.objects.CustomerType"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Welcome to Big Red Storage</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">

		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="css/style.css">
		<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,700' rel='stylesheet' type='text/css'>

		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

	</head>
	
	<body>

		<%@ include file="/nav-bar.jsp" %>

		<div class="container">

			<%
			if(response.getStatus()==302)
				System.out.println("That was the Bug!");
			if (customer_logged_in && response.getStatus()!=302) {
					response.sendRedirect("location.jsp");
			    }
			%>
			
		    <div class="jumbotron">
		
		    	<div class = "layer">
			        <h1>Store your stuff with us!</h1>
			        <p>We have lots and lots and lots of storage rooms. Seriously.</p>
			        <!--<p><a href="#" class="btn btn-primary btn-lg">Learn more</a></p>-->
		    	</div>
		    </div>
			<div class="row">
					
				
				<%
				List<CustomerType> list = CustomerType.getCustomerList();
		       	
		       	for(CustomerType type : list) {
		       		int id = type.getId();
		       		String name = type.getName();;
		       		int discount = type.getDiscount();
		       		String image = type.getImage();
		       		String description = type.getDescription();
				%>
	        	<form action="customer_type_submit" method="get">
		       		<div class="col-md-4">
		       		
						<div class="panel panel-default storagetype">
					        <div class="panel-heading">
					            <h3 class="panel-title"><%out.print(name);%></h3>
					        </div>
					        <div class="panel-body">
					        	<img src="images/customer-types/<%out.println(image);%>" alt="<%out.print(name);%> Storage"/>
						        <ul class="list-unstyled">
									<%out.print(description);%>
								</ul>
								<input type="hidden" name="customer_type_id" value="<%out.print(id);%>">
								<input type="submit" class="continue btn btn-primary" name="customer_type_id_submit" value="Continue">
					        </div>
					    </div>
					</div>
	        	</form>	
	        	<%
	        	}
	        	%>
		    </div>	
		</div>
	</body>
</html>