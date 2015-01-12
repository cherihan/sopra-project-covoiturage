<%@page import="java.util.ArrayList"%>
<%@page import="model.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>


</head>
<body>


<%
HttpSession s = request.getSession();
ArrayList<User> allUsers =(ArrayList<User>) s.getAttribute("allUsers");



%>


<%if(allUsers != null){ %>

<table class="table table-hover">
	<thead>
		<tr>
		<th>ID</th>
		<th>Name</th>
		<th>e-mail</th>
		<th>phone</th>
		<th>action</th>
		</tr>
	</thead>
	<tbody>
	<%for(int p = 0; p< allUsers.size(); p++){ %>
		<tr <%=(allUsers.get(p) instanceof Admin)?"class='success'":"" %>>
		<td><%=allUsers.get(p).getID() %></td>
		<td><%=allUsers.get(p).getFirstName() %> <%=allUsers.get(p).getLastName() %></td>
		<td><%=allUsers.get(p).getEmail() %></td>
		<td><%=allUsers.get(p).getTel() %></td>
		<td>
		<form class="form"  action="/SopraCarPooling/UserManagement" method="post">
			<input type="hidden" name="user" value="<%=allUsers.get(p).getID() %>">
			<button type="submit" name="action" value="rides" class="btn btn-default">Edit Rides</button>
			<button type="submit" name="action" value="profil" class="btn btn-default">Edit Profil</button>
			<%if(allUsers.get(p) instanceof Admin){ %>
			<button type="submit" name="action" value="bane" class="btn btn-primary">Bane Admin</button>
			<%}else {%>
			<button type="submit" name="action" value="admin" class="btn btn-success">Make Admin</button>
			<%} %>
			<button type="submit" name="action" value="del" class="btn btn-danger">Del User</button>
		</form>
		
		</td>
		</tr>
	
	
	
	<%} %>
	</tbody>
</table>



<%}else{ %>

<h1>No users found contact developers of the application. (Something must have been wrong :( )</h1>

<%} %>
</body>
</html>