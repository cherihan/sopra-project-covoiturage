<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.*"%>





	<ul>	
		<li><a href="Home">Page d'accueil</a></li>
		<li><a href="RidesUpdate">Profil</a></li>
		<li><a href="faq.jsp">FAQ</a></li>		
		<li><a href="Logout">Déconnexion</a></li>
		
		<%
		HttpSession es = request.getSession();
		User u = (User) es.getAttribute("user");
		if(u instanceof Admin){ %>
		
		<li><a href="Administration">Administation</a></li>
		
		<%} %>
	</ul>
	
	
	