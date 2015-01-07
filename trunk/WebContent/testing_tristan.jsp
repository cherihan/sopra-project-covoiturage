<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sopra testing jsp</title>
<style type="text/css">
.error{
	color: red;
	font-weight: bold;
}
</style>

</head>
<body>

	<%--This is a jsp comment --%>
	<% HttpSession s = request.getSession(); %>
	<%
		Exception e= (Exception) s.getAttribute("error");
		if (e != null){%>
			<h1 class="error">${e}</h1>
			
		<%}
		
	%>


	<h1>Connection</h1>
	<form action="/SopraCarPooling/test" method="post">
		<input type="hidden" name="action" value="1">
		<input type="text" name="emailadress" value="superman@gmail.com"><br>
		<input type="password" name="pwd" value="superman"><br>
		<input type="submit" value="Submit"><br>
	</form>
	
	<h3>conection</h3>
	<%
		
		User test = (User) s.getAttribute("user");
		if(test != null ){
			out.println("<p>Brother is in the hood !! : " +test+"</p><br>");
		}else{
			out.println("<p>no brother is connected.</p><br>");
		}
		
	%>
	
	<h1>Disconnect</h1>
		<form action="/SopraCarPooling/test" method="post">
			<input type="hidden" name="action" value="2">
			<input type="submit" value="Submit"><br>
		</form>
	
	<!--  
	<h1>Test un</h1>
	
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
	-->
	




</body>
</html>