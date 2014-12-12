<%@page import="com.bigred.objects.SessionState"%>

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

	</head>
	<body>
	
    <%@ include file="/nav-bar.jsp" %>
	<% com.bigred.objects.SessionState.assertAccessValidity(request,response,1); %>
	
<div class="container">

	<div class = "page-header">
		<div class = "page-heading">
			<%
			if (!customer_logged_in) {
			%>
			<a href="index.jsp" class="btn btn-default">Back</a>
			<%}%>
			<h2>Select a Location</h2>
		</div>

		<%@ include file="/stepwizzard.jsp" %>

	</div>

	<div class="row">
		<!--<div class="col-md-6 col-sm-6">
			<div class = "redbox">-->
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

						<div id="map-results-container" class="container">
							<div id="bh-sl-map" class="bh-sl-map col-sm-6 col-xs-12"></div>
							<div class="col-sm-6 col-xs-12">
								<div class="bh-sl-loc-list">
									<ul class="list list-unstyled"></ul>
								</div>
										<form id="branch" action="branch_submit" method="get">
											<input id="branch_id" type="hidden" name="branch_id">
											<input id="branch_id_submit" type="submit" class="btn btn-primary pull-right" name="branch_id_submit" value="Continue">
										</form>
							</div>
						</div>

	      		</div>
				
	        </div>
		</div>


		

	</div>

		<script src="http://code.jquery.com/jquery.min.js"></script>
	    <script src="js/bootstrap.min.js"></script>
	    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
	    <script src="assets/js/libs/handlebars.min.js"></script>
	    <script src="http://maps.google.com/maps/api/js?sensor=false&region=UK"></script>
	    <script src="assets/js/plugins/storeLocator/jquery.storelocator.js"></script>
		<script>
			$(function() {
				$('#map-container').storeLocator({
					'defaultLoc': true,
					'defaultLat': '51.500152',
					'defaultLng' : '-0.126236',
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