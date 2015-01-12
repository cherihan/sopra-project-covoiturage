<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="model.*" import="java.util.*"%>


<% 
	HttpSession s = request.getSession();
	User user =(User) s.getAttribute("user");
	ArrayList <Ride> rides = user.getWeekRides();
	ArrayList <JourDeLaSemaine> jours = (ArrayList <JourDeLaSemaine>) s.getAttribute("jours");
	ArrayList <Service> sopraOff = (ArrayList <Service>) s.getAttribute("sopraSite");

	
	//dev usage only 
	
	/*jours = new ArrayList<JourDeLaSemaine>();
	jours.add(new JourDeLaSemaine(1,"lundi"));
	jours.add(new JourDeLaSemaine(2,"mardi"));
	jours.add(new JourDeLaSemaine(3,"mercredi"));
	jours.add(new JourDeLaSemaine(4,"jeudi"));
	jours.add(new JourDeLaSemaine(5,"vendredi"));*/
	/*sopraOff = new ArrayList<Service>();
	sopraOff.add(new Service(1,"Colomiers Perget","",null));
	sopraOff.add(new Service(2,"Colomiers Ramacier","",null));
	sopraOff.add(new Service(3,"Ramonville","",null));*/
	/*rides = new ArrayList <Ride> ();
	rides.add(new Ride(999, user, new Adresse(new PostCode(32000),"r","v"),sopraOff.get(0),
			jours.get(0),new Heure("1745"),false,"C est le lundi"));
	rides.add(new Ride(998, user, new Adresse(new PostCode(32000),"r","v"),sopraOff.get(0),
			jours.get(1),new Heure("0815"),true,"C est le mardi"));
	rides.add(new Ride(997, user, new Adresse(new PostCode(32000),"r","v"),sopraOff.get(0),
			jours.get(1),new Heure("1745"),false,"C est le mardi"));
	rides.add(new Ride(996, user, new Adresse(new PostCode(32000),"eglise","paris"),sopraOff.get(0),
			jours.get(3),new Heure("1815"),false,"C est le jeudi"));*/
	int r = 0;
	
%>
<html>
<head>

<link rel="stylesheet" href="./css/profile_edit.css">
 <link rel="stylesheet" type="text/css" href="./css/side_panels.css" />
 <link rel="stylesheet" type="text/css" href="./css/main.css" />
 <link rel="stylesheet" type="text/css" href="./css/header.css" />
 <title>My destinations</title>
</head>

<body>



<header>
	<%@ include file="header.jsp" %>
</header>

<left_side>
	<%@ include file="user_banner.jsp" %>
</left_side> 


  <div class="module">
    <ul>
      <li class="tab sleepyTab"><a href="edit_profile.jsp"><img src="./images/user.png" alt="" class="icon"/></a></li>
      <li class="tab activeTab" ><a href="edit_route.jsp"><img src="./images/milestone.png" alt="abra" class="icon"/></a></li>
    </ul>
    
    	<form class="form"  action="/SopraCarPooling/RidesUpdate" method="post">

			<%for(int i = 0; i < jours.size(); i++){ 
				int j = jours.get(i).getJour();
				
				//ride ?
				Ride aller = null;
				Ride retour= null;
				Ride duJour = null ;
				
				if( r < rides.size() && rides.get(r).getJour().equals(jours.get(i))
						&& rides.get(r).getSens()){
					aller= rides.get(r);
					duJour= rides.get(r);						
					r++;
				}
				if(r < rides.size() && rides.get(r).getJour().equals(jours.get(i)) 
						&& !rides.get(r).getSens()){
					retour= rides.get(r);
					duJour= rides.get(r);
					r++;
				}
				
				
			%>
			
			<h2><%=jours.get(i).toString()%></h2>
		        <table >
                    <tr>
                                    
                        <td>
                            Home<br><br>
                            <input type="text" name="<%=j%>-street" 
                            <%=
                            (duJour != null)? "value='"+duJour.getHome().getRue()+"'": "placeholder='Street'"
                            %> class="rstreetbox" /><br>
                            <input type="text" name="<%=j%>-code-post" 
                            placeholder="Code" 
                            <%=
                            (duJour != null)? "value='"+duJour.getHome().getPostCode()+"'": "placeholder='Street'"
                            %> class="rcodebox" />                            
                            <input type="text" name="<%=j%>-city"
                            <%=
                            (duJour != null)? "value='"+duJour.getHome().getVille()+"'": "placeholder='City'"
                            %> class="rcitybox" />
                        </td>
                        <td >
                            Office<br><br>
					    	<select name="<%=j %>-service">
					    		<%for(int k =0 ; k < sopraOff.size(); k++){ %>
					  				<option value="<%=sopraOff.get(k).getId() %>"
					  						
					  				<%=(duJour != null && duJour.getOffice().equals(sopraOff.get(k)))? "selected" : "" %>>
					  				<%=sopraOff.get(k).getNom() %>
					  				</option>					 			
					 			<%} %>
							</select>
                        </td>
                        <td>
                        
                        <%//Heurs 
                        	String ah = (aller != null)? aller.getAtOfficeAt().getHoursString():"08";
                        	String am = (aller != null)? aller.getAtOfficeAt().getMinutesString():"15";
                        	String rh = (retour != null)? retour.getAtOfficeAt().getHoursString():"18";
                        	String rm = (retour != null)? retour.getAtOfficeAt().getMinutesString():"30";
                        %>
                        
                        
                            <INPUT type="checkbox" <%=(aller != null)? "checked":""%> name="<%=j%>-aller"> Aller : 
                    		<input type="text" name="ah<%=j%>" value="<%=ah %>" class="hourbox" />h
							<input type="text" name="am<%=j%>" value="<%=am %>" class="hourbox" />m<br>
                    		<INPUT type="checkbox" <%=(retour != null)? "checked":""%> name="<%=j%>-retour"> Retour : 
                    		<input type="text" name="rh<%=j%>" value="<%=rh %>" class="hourbox" />h
							<input type="text" name="rm<%=j%>" value="<%=rm %>" class="hourbox" />m
                        </td>                
                    </tr>
                 </table>
                 <textarea name="<%=j%>-com" placeholder='Comment'><%=(duJour != null)? duJour.getComment(): "" %></textarea><br>
                 
              
               <%}%>
                			
 	     <input type="submit" value="Update routes" class="button" />   
   </form>
   
  </div>


</body>
<html>