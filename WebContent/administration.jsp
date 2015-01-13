<%@page import="com.sun.org.apache.bcel.internal.classfile.Code"%>
<%@page import="java.util.*"%>
<%@page import="model.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administration</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

<%HttpSession s = request.getSession(); %>


<style type="text/css">
body{
width : 90%;
margin-left : 5%;
}

</style>

</head>
<body>
<h1>Administration</h1> <a href="Home">Back to Application</a><br><br>

<!-- Columns are always 50% wide, on mobile and desktop -->
<div class="row">
  <div class="col-xs-6">
  <div class="panel panel-default">
  <div class="panel-heading"><a href="DataManagement">Data Management</a></div>
  <div class="panel-body">
    Get administration information about the application 
  </div>
</div>
</div>
  <div class="col-xs-6">
  <div class="panel panel-default">
  <div class="panel-heading"><a href="UserManagement">User Management</a></div>
  <div class="panel-body">
    Administration users of the application.  
  </div>
</div>
</div>
</div>

<% 
String action = (String) s.getAttribute("action"); 


%>
<%if(action != null){
	if( action.equals("data")){ %>

	<%@include file="DataManagement.jsp" %>
<%}else if (action.equals("user")){ %>
	<%@include file="UserManagement.jsp" %>

<%}
}
s.setAttribute("action", "rien");
%>
</body>
</html>