<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    
    <%
	String error = (String) request.getAttribute("actionPerform");
	if(error != null){
	
	%>
		<div class="alert alert-warning alert-dismissible" role="alert">
  			<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
  				<strong>Ooop!</strong> An error occurred, please check your the informations you are sending and try again. 
		</div>

	<%}%>