<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>Select a Location</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" type="text/css" href="assets/css/bootstrap-example.min.css" />
		<link rel="stylesheet" type="text/css" href="assets/css/storelocator.css" />
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="css/style.css">
		<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,700' rel='stylesheet' type='text/css'>

		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

	</head>
	<body>
	
	<%
	//allow access only if session exists
	String customer_type = null;
	if(session.getAttribute("customer_type") == null) {
	    response.sendRedirect("index.jsp");
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
                <li><a href="#"></a></li>
            </ul>

            <form class="navbar-form navbar-right" role="search">
                <div class="form-group">
                        <input type="text" class="form-control" name="username" placeholder="Username">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="password" placeholder="Password">
                    </div>
                    <button type="submit" class="btn btn-default">Sign In</button>
            </form>

        </div>

    </nav>




<div class="container">



	<div class = "page-header"> 
		<h2>Select a Location</h2>

		<div class="stepwizard">
		    <div class="stepwizard-row">
		        <div class="stepwizard-step">
		            <button type="button" class="btn btn-primary btn-circle">1</button>
		            <p>Select Location</p>
		        </div>
		        <div class="stepwizard-step">
		            <button type="button" class="btn btn-default btn-circle">2</button>
		            <p>Room Options</p>
		        </div>
		        <div class="stepwizard-step">
		            <button type="button" class="btn btn-default btn-circle" disabled="disabled">3</button>
		            <p>Add Extras</p>
		        </div>
		        <div class="stepwizard-step">
		            <button type="button" class="btn btn-default btn-circle" disabled="disabled">4</button>
		            <p>Review Booking</p>
		        </div>
		        <div class="stepwizard-step">
		            <button type="button" class="btn btn-default btn-circle" disabled="disabled">5</button>
		            <p>Payment</p>
		        </div>  
		    </div>
		</div>
	</div>



			<div class="bh-sl-container container-fluid">
			
					<div class="bh-sl-form-container">
						<form id="bh-sl-user-location" class="form-inline" method="post" action="#" role="form">
							<div class="form-input form-group">
								<label for="bh-sl-address">Enter Address or Zip Code:</label>
								<input class="form-control" type="text" id="bh-sl-address" name="bh-sl-address" />
							</div>
	
							<button id="bh-sl-submit" class="btn btn-primary" type="submit">Submit</button>
						</form>
					</div>
		
				<div id="map-container" class="bh-sl-map-container">
					<div class="row">
						<div id="map-results-container" class="container">
							<div id="bh-sl-map" class="bh-sl-map col-md-9"></div>
							<div class="bh-sl-loc-list col-md-3">
								<ul class="list list-unstyled"></ul>
							</div>
						</div>
					</div>
	      		</div>
				
	        </div>



	
					
		<form id="branch" action="branch_submit" method="post">
			<input id="branch_id" type="hidden" name="branch_id">
			<input id="branch_id_submit" type="submit" class="btn btn-primary pull-right" name="branch_id_submit" value="Continue">
		</form>

	


	<script src="http://code.jquery.com/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="assets/js/libs/handlebars.min.js"></script>
    <script src="http://maps.google.com/maps/api/js?sensor=false&region=UK"></script>
    <script src="assets/js/plugins/storeLocator/jquery.storelocator.js"></script>
		<script>
			$(function() {
				$('#map-container').storeLocator({
					'mapSettings' : {
						zoom: 10
						}
				});
				$('#branch').submit(function () {

					var attr_value = $("#branch_id").attr('value');
					//alert(attr_value);
					if (typeof attr_value === typeof undefined || attr_value === false) {
						alert("Please select a branch!");
						return false;
					}
				});
			});
			
			
		</script>
	</body>
	
</html>