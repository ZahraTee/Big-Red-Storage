    <%
    com.bigred.objects.SessionState.formalizeSession(request, response);
    com.bigred.objects.Customer customer = ((com.bigred.objects.SessionState)session.getAttribute("State")).getCustomer();
    boolean customer_logged_in;
    String customer_name = null;
    if (customer == null) {
    	customer_logged_in = false;
    }
    else {
    	customer_logged_in = ((com.bigred.objects.SessionState)session.getAttribute("State")).getCustomer().isLoggedIn();
    }
    if (customer_logged_in) {
		customer_name = ((com.bigred.objects.SessionState)session.getAttribute("State")).getCustomer().getName();
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
            <a href="#" class="navbar-brand">Big Red Storage Company</a>
        </div>

        <!-- Collection of nav links, forms, and other content for toggling -->

        <div id="navbarCollapse" class="collapse navbar-collapse">

            <ul class="nav navbar-nav">
                <li class="active"><a href="index">Home</a></li>
                <li><a href="index"></a></li>
            </ul>

			<%
			if (!customer_logged_in) {
			%>

            <form action="login" method="post" class="navbar-form navbar-right" role="form">
                <div class="form-group">
                        <input type="text" class="form-control" name="username" placeholder="Username">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" name="password" placeholder="Password">
                    </div>
                    <input type="submit" class="btn btn-default" name="sign_in" value="Sign in">
            </form>
            
            <%
            }
			else {	
			%>
				<ul class="nav navbar-nav navbar-right">
				  <li><a href="profile.jsp"><%out.print(customer_name);%></a></li>
				  <li><a href="logout">Log Out</a></li>
				</ul>
			<%
			}
            %>

        </div>

    </nav>