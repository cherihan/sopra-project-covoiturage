<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<% 
	HttpSession s = request.getSession();
	User user =(User) s.getAttribute("user"); 
	ArrayList <Ride> mR	= (ArrayList <Ride>) s.getAttribute("matchingRides");
	ArrayList <JourDeLaSemaine> jours	= (ArrayList <JourDeLaSemaine>) s.getAttribute("jours");
%>

<html>

<head>
<title>Sopra Carpooling Website</title>
	<%@ include file="header.jsp" %>
</header>

<body>



<header>
	<%@ include file="header.jsp" %>
</header>
       
<left_side>

	<%@ include file="user_banner.jsp" %>

</left_side>      

<div id="bg">
  <div class="route_module">
 Ici : options de recherche
	<input type="button" value="New research" class="button" />    
    <div class="route_table" >
   
    
                <table >
                    <!--<tr>
                        <td>
                            From
                        </td>
                        <td >
                            To
                        </td>
                        <td>
                            Round trip
                        </td>                
                        <td >
                            Driver
                        </td>                        
                        <td>
                            Contact
                        </td>
                        <td>
                        	Maps
                        </td>
                    </tr>  -->
                    
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
                    <tr> 
                    	
                    	<td><h2><%=jour %></h2>
                    	<td> <%=rJ.get(0).getHome().getPostCode()%> - <%=rJ.get(0).getHome().getRue()%> </td>
                    	<td> <%=rJ.get(0).getOffice().getNom() %> </td>
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
                          	  <%=aller.get(k).getUser().getLastName() %> <%=aller.get(k).getUser().getFirstName()  %>
                       		 </td>
                       		 <td>
                         	   <%=aller.get(k).getAtOfficeAt() %>
                       		</td>
                       		 <td>
                        		<%=aller.get(k).getComment() %>
                        	</td>
                        	
                   		 </tr>
                   	 
                    <%
                    	}
                    	for(int k = 0 ; k <aller.size(); k++){%>
                    	
                    	<tr>
                      		 <td >
                         	  <%=retour.get(k).getUser().getLastName() %> <%=aller.get(k).getUser().getFirstName()  %>
                      		 </td>
                      		 <td>
                        	   <%=retour.get(k).getAtOfficeAt() %>
                      		</td>
                      		 <td>
                       			<%=retour.get(k).getComment() %>
                       		</td>
                       	
                  		 </tr>
                    	
                    	<%
                    	}
                    
 						}
                    }
                    %>
                    
                </table>
            </div>
  </div>
</div>

</body>

</html>