<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>My Profile</title>
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
            <a href="index.jsp" class="navbar-brand">Big Red Storage Company</a>
        </div>

        <!-- Collection of nav links, forms, and other content for toggling -->

        <div id="navbarCollapse" class="collapse navbar-collapse">

            <ul class="nav navbar-nav">
                <li class="active"><a href="index.jsp">Home</a></li>
                <li><a href="index.html"></a></li>
            </ul>

            <!--<form class="navbar-form navbar-right" role="search">
                <div class="form-group">
                        <input type="text" class="form-control" name="username" placeholder="Username">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="password" placeholder="Password">
                    </div>
                    <button type="submit" class="btn btn-default">Sign In</button>
            </form>-->

        </div>

    </nav>




<div class="container">

	<div class = "page-header"> 
		<h2>My Profile</h2>
	</div>

	<div class="row">
		<div class="col-sm-6">
			<div class="panel panel-defaut">
		        <div class="panel-heading">
		            <h3 class="panel-title">My Details</h3>
		        </div>

		        <div class="panel-body">
		            <li class = "list-unstyled">
		            	<ul>Name</ul>
		            	<ul>Email</ul>
		            	<ul>Something</ul>
		            	<ul>Something else</ul>
		            </li>
		        </div>
		        <div class="panel-footer">
	                <a href="javascript:;" class="btn btn-primary col-sm-12">Edit Details</a>
		        </div>
		    </div>
		</div>
		<div class="col-sm-6">
			<div class="panel panel-defaut">
		        <div class="panel-heading">
		            <h3 class="panel-title">My Bookings</h3>
		        </div>

		        <div class="panel-body">
		            <div class="booking">
		            	<ul class="list-unstyled">
		            	<li>Ref No. #123456</li>
		            	<li>Starts 12-12-14</li>
		            	<li>Detail</li>
		            </ul>
		            </div>
		            <div class="booking past">
		            	<ul class="list-unstyled">
		            	<li>Ref No. #123456</li>
		            	<li>Starts 12-12-14</li>
		            	<li>Detail</li>
		            </ul>
		            </div>
		        </div>
		    </div>
		</div>


		<div class="col-md-6 col-sm-6">
		</div>
	</div>

	<script src="http://code.jquery.com/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
	</body>
	
</html>