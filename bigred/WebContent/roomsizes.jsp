<!DOCTYPE html>

<%@page import="com.bigred.objects.RoomTypes"%>
<%@page import="com.bigred.objects.RoomType"%>
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

	<%

	String branch = null;
	if (session.getAttribute("branch") == null) {
	    response.sendRedirect("index.jsp");
	}
	else {
		branch = (String) session.getAttribute("branch");
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
                <li><a href="index.html"></a></li>
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
					<a href="roomdates.html" class="btn btn-default">Back</a>
					<h2>Select Room Size</h2>
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
				            <a href="roomsizes.html" class="btn btn-primary btn-circle">3</a>
				            <p>Select Room Sizes</p>
				        </div>
				        <div class="stepwizard-step">
				            <a href="extraoptions.html" class="btn btn-default btn-circle">4</a>
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

		<div class = "col-md-6 col-sm-7">
		    <form action="room_type_submit" method="get" class="form-horizontal">

		        <div class="form-group">
		            <label for="roomSize" class="control-label col-xs-4">Room Size</label>
		            <div id="div_days_type" class="col-xs-5">
						<select id="room_type" name="room_type" class="form-control">
							<%
							RoomTypes types = new RoomTypes();
					       	List<RoomType> list = new ArrayList<RoomType>();
		            		int branch_id = Integer.parseInt(branch);
					       	list = types.getAvailableBranchRoomTypes(branch_id, "", "");
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