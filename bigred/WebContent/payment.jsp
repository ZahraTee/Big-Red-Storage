<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>Make Payment</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">

		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="css/style.css">
		<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,700' rel='stylesheet' type='text/css'>

		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

	</head>
	<body>

		<%@ include file="/nav-bar.jsp" %>
		<%com.bigred.objects.SessionState.assertAccessValidity(request,response,6); %>

		<div class="container">

			<div class = "page-header">
				<div class = "page-heading">
					<a href="review.jsp" class="btn btn-default">Back</a>
					<h2>Payment</h2>
				</div>

				<%@ include file="/stepwizzard.jsp" %>

			</div>

			<div class = "row">
				<div class = "col-md-12">

			      <form class="form-horizontal">

			     
			          <!-- Name -->
			          <div class="control-group">
			            <label class="control-label col-md-3"  for="username">Cardholder's Name</label>
			            <div class="controls">
			              <input type="text" id="username" name="username" placeholder="" class="input-xlarge">
			            </div>
			          </div>
			     
			          <!-- Card Number -->
			          <div class="control-group">
			            <label class="control-label col-md-3" for="email">Card Number</label>
			            <div class="controls">
			              <input type="text" id="email" name="email" placeholder="" class="input-xlarge">
			            </div>
			          </div>
			     
			          <!-- Expiry-->
			          <div class="control-group col-md-12">
			            <label class="control-label col-md-3" for="expiry_month">Expiry Date</label>
			            <div class="controls">
			              <select name="expiry_month" id="expiry_month">
			                <option></option>
			                <option value="01">Jan (01)</option>
			                <option value="02">Feb (02)</option>
			                <option value="03">Mar (03)</option>
			                <option value="04">Apr (04)</option>
			                <option value="05">May (05)</option>
			                <option value="06">June (06)</option>
			                <option value="07">July (07)</option>
			                <option value="08">Aug (08)</option>
			                <option value="09">Sep (09)</option>
			                <option value="10">Oct (10)</option>1
			                <option value="11">Nov (11)</option>
			                <option value="12">Dec (12)</option>
			              </select>
			              <select name="expiry_year">
			                <option value="13">2013</option>
			                <option value="14">2014</option>
			                <option value="15">2015</option>
			                <option value="16">2016</option>
			                <option value="17">2017</option>
			                <option value="18">2018</option>
			                <option value="19">2019</option>
			                <option value="20">2020</option>
			                <option value="21">2021</option>
			                <option value="22">2022</option>
			                <option value="23">2023</option>
			              </select>
			            </div>
			          </div>
			     
			          <!-- CVV -->
			          <div class="control-group">
			            <label class="control-label col-md-3" for="password_confirm">Card CVV</label>
			            <div class="controls">
			              <input type="password" id="password_confirm" name="password_confirm" placeholder="">
			            </div>
			          </div>
			          <!-- Submit -->
			          <div class="col-md-8">
			              <a href="confirm_booking" class="btn btn-success pull-right">Pay Now</a>
			            </div>
			          </div>
			     

			      </form>

		   
				</div>
			</div>

		</div>

	<script src="http://code.jquery.com/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	</body>
	
</html>