<%@page import="java.util.ArrayList"%>
<%@page import="model.*"%>



<%
HttpSession sess = request.getSession();
ArrayList<User> allUsers =(ArrayList<User>) sess.getAttribute("allUsers");
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
