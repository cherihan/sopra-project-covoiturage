<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sopra testing jsp</title>
</head>
<body>

	<%--This is a jsp comment --%>


	<h1>Test un</h1>
	<%
		HttpSession s = request.getSession();
		User test = (User) s.getAttribute("user");
		out.println("hello brother !! un: " + test);
	%>
	<br>
	<p>tout marche bien</p>
	<br>
	<br>

	<h1>Test deux</h1>
	${header["user-agent"]}
	<br> ${un}
	<br>
	<p>tout marche bien</p>
	<br>
	<br>
	
	




</body>
</html>