<!DOCTYPE html>

<%@page import="com.bigred.objects.RoomType"%>
<%@page import="com.bigred.objects.Branch"%>
<%@page import="java.util.Date"%>
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

    <jsp:include page="nav-bar.jsp" />

		<div class="container">
			<div class = "page-header">
				<div class = "page-heading">
					<a href="roomdates.jsp" class="btn btn-default">Back</a>
					<h2>Select Room Size</h2>
				</div>
 
				<%@ include file="/stepwizzard.jsp" %>
			</div>

		<div class = "col-md-6 col-sm-7">
		    <form action="room_type_submit" method="get" class="form-horizontal">

		        <div class="form-group">
		            <label for="roomSize" class="control-label col-xs-4">Room Size</label>
		            <div id="div_days_type" class="col-xs-5">
						<select id="room_type" name="room_type" class="form-control">
							<%
						
							Date startDate = ((com.bigred.objects.SessionState)session.getAttribute("State")).getBooking().getStartDate();
							Date endDate = ((com.bigred.objects.SessionState)session.getAttribute("State")).getBooking().getEndDate();
							List<RoomType> list = ((com.bigred.objects.SessionState)session.getAttribute("State")).getBooking().getBranch().getAvailableTypes(startDate, endDate);
					       	for(RoomType type : list) {
					       		int id = type.getId();
					       		int size = type.getSize();
					       		int price = type.getPrice();
					       		String image = type.getImageUrl();
					       	%>
								<option value="<%out.print(id);%>" data-prc="<%out.print(price);%>" data-img="images/room-types/<%out.print(image);%>"><%out.print(size);%> sq ft</option>
							<%
					       	}
							%>
						</select> 
				    </div>
		        </div>
		    
	</div>

		<div class = "col-md-5 col-sm-5">
			<div id="roomvisualiser" class ="redbox"></div>

			<div class ="redbox quote">
				<span>Cost: </span>
				<span id="cost"></span>
				<span>/week</span>
			</div>
			<input id="weekly_cost" type="hidden" name="weekly_cost">
			<input id="room_type_submit" type="submit" class="btn btn-primary pull-right" name="room_type_submit" value="Continue">
		</div>
		</form>


	

	</body>
	<script>
	$(document).ready(function() {
		var selected_price = $('#room_type option:first-child').data("prc");
		var img_src = $('#room_type option:first-child').data("img");
		$("#roomvisualiser").html(img_src ? "<img src='" + img_src + "'>" : "");
		$("#cost").text("£" + selected_price);
		$("#weekly_cost").val(selected_price);
	    $("#room_type").change(function() {
	        var img_src = $(this).find(":selected").data("img");
	        var selected_price = $(this).find(":selected").data("prc");
	        $("#roomvisualiser").html(img_src ? "<img src='" + img_src + "'>" : "");
	        $("#cost").text("£" + selected_price);
	        $("#weekly_cost").val(selected_price);
	    });
	});
	</script>

	<script src="http://code.jquery.com/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
	
</html>