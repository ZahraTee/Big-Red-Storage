<!DOCTYPE html>

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
					<a href="location.jsp" class="btn btn-default">Back</a>
					<h2>Select Dates</h2>
				</div>

				<%@ include file="/stepwizzard.jsp" %>

			</div>
			
		 <form class="form-horizontal" action="interval_submit" method="get">
		 
			<div class = "col-md-6 col-sm-7">
		   

		        <div class="form-group">
		            <label for="startDate" class="control-label col-xs-4">Start Date</label>
		            <div class="col-xs-8">
		                <input type="date" class="form-control" id="startDate" name="startDate" placeholder="YYYY-MM-DD">
		            </div>
		        </div>
		        <div class="form-group">

		            <label for="endDate" class="control-label col-xs-4">
		            	<span class = "pull-left"> <input id="radio-endDate" type="radio" name="endDate_type" value="date" checked="checked"></span>
		            	End Date
		            </label>
		            <div id="div_endDate" class="col-xs-8">
		                <input type="date" class="form-control" id="endDate" name="endDate" placeholder="YYYY-MM-DD">
		            </div>
		        </div>
		        <div class="form-group">
		            <label for="duration" class="control-label col-xs-4">
		            	<span class = "pull-left"> <input id="radio-endDays" type="radio" name="endDate_type" value="days"></span>
		            	Duration
		            </label>
		            <div style="display:none" id="div_days" class="col-xs-3">
		                <input type="number" class="form-control" id="endDate" name="days">
		            </div> 
		            <div style="display:none" id="div_days_type" class="col-xs-5">
						<select id="days_type" name="days_type" class="form-control">
							<option value="day">day(s)</option>
							<option value="week">week(s)</option>
							<option value="month">month(s)</option>
						</select> 
				    </div>
						            
		        </div>

		    
			</div>

			<div class = "col-md-4 col-md-offset-1 col-sm-5">
				<input id="interval_submit" type="submit" class="btn btn-primary pull-right" name="interval_submit" value="Continue">
			</div>
		</form>

	</body>
	<script>
		$(function(){
			$('#radio-endDate').click(function(){
				if ($(this).is(':checked'))
				{
					$("#div_endDate").show();
				 	$("#div_days").hide();
				 	$("#div_days_type").hide();
				}
			});
			$('#radio-endDays').click(function(){
				if ($(this).is(':checked'))
				{
					$("#div_endDate").hide();
				 	$("#div_days").show();
				 	$("#div_days_type").show();
				}
			});
		});
	
	
	</script>
	<script src="http://code.jquery.com/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
	
</html>