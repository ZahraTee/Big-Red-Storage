<%@page import="com.bigred.objects.SessionState"%>
<%
	SessionState.formalizeSession(request, response);
	out.println("Everything fine here!" + (5+6));
%>