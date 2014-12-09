<!DOCTYPE html>
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

	    <nav role="navigation" class="navbar navbar-default">

	        <!-- Brand and toggle get grouped for better mobile display -->

	        <div class="navbar-header">
	            <button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">
	                <span class="sr-only">Toggle navigation</span>
	                <span class="icon-bar"></span>
	                <span class="icon-bar"></span>
	                <span class="icon-bar"></span>
	            </button>
	            <a href="#" class="navbar-brand">Big Red Storage Company</a>
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
				<div class = "page-heading">
					<a href="extraoptions.html" class="btn btn-default">Back</a>
					<h2>Review Quote</h2>
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
				            <a href="extraoptions.html" class="btn btn-default btn-circle">4</a>
				            <p>Add Extras</p>
				        </div>
				        <div class="stepwizard-step">
				            <a href="review.html" class="btn btn-primary btn-circle">5</a>
				            <p>Review Booking</p>
				        </div>
				        <div class="stepwizard-step">
				            <a href="payment.html" class="btn btn-default btn-circle">6</a>
				            <p>Payment</p>
				        </div>  
				    </div>
				</div>

			</div>

			<div class="row">
				<div class="col-md-12">

					<div class="panel panel-defaut">
				        <div class="panel-heading">
				            <h3 class="panel-title">Selected Options : Review</h3>
				        </div>

				        <div class="panel-body">
				            <li class = "list-unstyled">
				            	<ul>Location</ul>
				            	<ul>Start Date</ul>
				            	<ul>End Date</ul>
				            	<ul>Room Size</ul>
				            </li>
				        </div>

				        <div class="panel-footer">
				            <span>Save Quote for Later</span>
				            <form class="form-horizontal pull-right">
			                        <div class="control-group">
			                            <input placeholder="E-mail" name="email" type="email" autofocus="">
			                        <!-- Change this to a button or input when using this as a form -->
			                        <a href="javascript:;" class="btn btn-default">Send e-mail</a>
			                        </div>
			                </form>
				        </div>
				    </div>

				</div>
			</div>

			<!-- I was thinking we could replace this entire section with a continue to payment button if they're signed in? -->
			
			<div class="row">
				<div class="col-md-6">
			        <div class="panel panel-defaut">
				        <div class="panel-heading">
				            <h3 class="panel-title">Sign In</h3>
				        </div>
				        <div class="panel-body">
				            <form role="form">
				                <fieldset>
				                    <div class="form-group">
				                        <input class="form-control" placeholder="E-mail" name="email" type="email" autofocus="">
				                    </div>
				                    <div class="form-group">
				                        <input class="form-control" placeholder="Password" name="password" type="password" value="">
				                    </div>
				                    <div class="checkbox">
				                        <label>
				                            <input name="remember" type="checkbox" value="Remember Me">Remember Me
				                        </label>
				                    </div>
				                    <!-- Change this to a button or input when using this as a form -->
				                    <a href="payment.html" class="btn btn-primary pull-right">Login</a>
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
			                <form role="form">
			                    <fieldset>
			                        <div class="form-group">
			                            <input class="form-control" placeholder="E-mail" name="email" type="email" autofocus="">
			                        </div>
			                        <div class="form-group">
			                            <input class="form-control" placeholder="Password" name="password" type="password" value="">
			                        </div>
			                        <!-- Change this to a button or input when using this as a form -->
			                        <a href="payment.html" class="btn btn-primary pull-right">Create Account</a>
			                    </fieldset>
			                </form>
			            </div>
			        </div>
			    </div>
			</div>

		</div>

	<script src="http://code.jquery.com/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
	</body>
	
</html>