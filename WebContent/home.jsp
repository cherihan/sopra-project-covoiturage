<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<% 
	HttpSession s = request.getSession();
	User user =(User) s.getAttribute("user"); 
	ArrayList <Ride> mR	= (ArrayList <Ride>) s.getAttribute("matchingRides");
	ArrayList <JourDeLaSemaine> jours	= (ArrayList <JourDeLaSemaine>) s.getAttribute("jours");
	ArrayList <JourDeLaSemaine> joursPossible	= (ArrayList <JourDeLaSemaine>) s.getAttribute("joursPossible");
	ArrayList <Service> sopraOff = (ArrayList <Service>) s.getAttribute("sopraSite");
%>

<html>

<head>
<title>Sopra Carpooling Website</title>
 <meta charset="utf-8" />
 <link rel="stylesheet" type="text/css" href="./css/main.css" />
 <link rel="stylesheet" type="text/css" href="./css/side_panels.css" />
 <link rel="stylesheet" type="text/css" href="./css/header.css" />
 <link rel="stylesheet" type="text/css" href="./css/route_table.css" />
</head>

<body>



<header>
	<%@ include file="header.jsp" %>
</header>
       
<left_side>

	<%@ include file="user_banner.jsp" %>

</left_side>      

<div id="bg">
  <div class="route_module">
			<form class="form" action="/SopraCarPooling/Search" method="post">
			
				<select name="search-jour">
					<%for(int i = 0; i < joursPossible.size(); i++){ 
          			JourDeLaSemaine jour = joursPossible.get(i); %>
					<option value="<%=jour.getJour() %>">
						<%=jour.toString()%>
					</option>

					<%} %>
				</select> 
				<input type="text" name="address-search" placeholder='Rue' class="textbox">
				<input type="text" name="cp-search" placeholder='Code Postal' class="textbox">
				<input type="text" name="ville-search" placeholder='Ville' class="textbox"> 
				
				<select	name="search-service">
					<%
						for (int k = 0; k < sopraOff.size(); k++) {
					%>
					<option value="<%=sopraOff.get(k).getId()%>">
						<%=sopraOff.get(k).getNom()%>
					</option>
					<%
						}
					%>
				</select>
				<input type="submit" value="Nouvelle recherche" class="button" /> 
			</form>

	   
    <div class="route_table" >
   
             
                    
                    <%for(int i = 0; i < jours.size(); i++){ 
                    	JourDeLaSemaine jour = jours.get(i);
                    	int ji = jour.getJour();
                    	
                    	//choix des rides a afficherpour un jour (oui c'est salle)
                    	ArrayList<Ride> rJ = new ArrayList<Ride>();
 						for (int k = 0; k < mR.size(); k++){ 							
 							if(mR.get(k).getJour().equals(jour)){
 								rJ.add(mR.get(k));
 							} 							
 						}                   	
                    	
 						if(rJ.size()>0){
 							
                    %>
                  
                    	
                    
                   
                    <h2 style="text-transform: capitalize;"><%=jour %></h2>
                    <p><b>Home :</b> <%=rJ.get(0).getHome().getPostCode()%> - 
                    <b>Office :</b> <%=rJ.get(0).getOffice().getNom() %></p>
                    <!--  <tr>
						<td><b>Home :</b> <%=rJ.get(0).getHome().getPostCode()%><br></td>
                    	<td><b>Office :</b> <%=rJ.get(0).getOffice().getNom() %><br></td>     
                    	<td>
                    	</td>
                    	<td></td>
                    	<td></td>             	
                    </tr>-->
                    <table class="table table-hover">
                    <tr>
                    	<th>Name</th>
                    	<th>Contact</th>
                    	<th>Addresse</th>
                    	<th>Heure</th>
                    	<th>Comment</th>
                    </tr>
                    <%
                    	ArrayList<Ride> aller = new ArrayList<Ride>();
                   		ArrayList<Ride> retour = new ArrayList<Ride>();
 						for (int k = 0; k < rJ.size(); k++){ 							
 							if(rJ.get(k).getSens()){
 								aller.add(rJ.get(k));
 							}else{
 								retour.add(rJ.get(k));
 							}
 						}
                    
                    	for(int k = 0 ; k <aller.size(); k++){ %>
                    	
                   		<tr>
                       		 <td >
                          	  <%=aller.get(k).getUser().getLastName() %> <%=aller.get(k).getUser().getFirstName()%> 
                          	  </td>
                          	  <td>
                          	  <%=aller.get(k).getUser().getEmail() %>
                       		 </td>
                       		 <td>
                       		 <%=aller.get(k).getHome().getRue()%><br>
                       		 <%=aller.get(k).getHome().getPostCode()%> - <%=aller.get(k).getHome().getVille() %>
                       		 </td>
                       		 <td>
                         	   <%=aller.get(k).getAtOfficeAt()%>
                       		</td>
                       		 <td>
                        		<%=aller.get(k).getComment() %>
                        	</td>
                        	
                   		 </tr>
                   	 
                    <%
                    	}
                    	for(int k = 0 ; k <retour.size(); k++){%>
                    	
                    	<tr>
                       		 <td >
                          	  <%=retour.get(k).getUser().getLastName() %> <%=retour.get(k).getUser().getFirstName()%> 
                          	  </td>
                          	  <td>
                          	  <%=retour.get(k).getUser().getEmail() %>
                       		 </td>
                       		 <td>
                       		 <%=retour.get(k).getHome().getRue()%><br>
                       		 <%=retour.get(k).getHome().getPostCode()%> - <%=retour.get(k).getHome().getVille() %>
                       		 </td>
                       		 <td>
                         	   <%=retour.get(k).getAtOfficeAt()%>
                       		</td>
                       		 <td>
                        		<%=retour.get(k).getComment() %>
                        	</td>
                       	
                  		 </tr>
                    	
                    	<%
                    	}
                    %>
                    </table>
                    <%
                    
                    
 						}
                    }
                    %>
                    
                
            </div>
  </div>
</div>

</body>

</html>