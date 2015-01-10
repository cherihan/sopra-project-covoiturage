<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<% 
	HttpSession s = request.getSession();
	User user =(User) s.getAttribute("user"); %>

<html>

<head>
 <meta charset="utf-8" />
 <link rel="stylesheet" type="text/css" href="./css/main.css" />
 <link rel="stylesheet" type="text/css" href="./css/side_panels.css" />
 <link rel="stylesheet" type="text/css" href="./css/header.css" />
 <link rel="stylesheet" type="text/css" href="./css/route_table.css" /> 
 <link rel="stylesheet" type="text/css" href="./css/footer.css" />
</head>

<body>

<title>Sopra Carpooling Website</title>

<header>
	<ul>
		<li><a href="Home">Home</a></li>
		<li><a href="edit_profile.jsp">Profile</a></li>
		<li><a href="faq.jsp">FAQ</a></li>		
		<li><a href="Logout">Log out</a></li>
	</ul>
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
                    <tr>
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
                    </tr>
                    
                    <tr>
                        <td >
                            31200 - Toulouse
                        </td>
                        <td>
                            SOPRA
                        </td>
                        <td>
                        	Yes
                        </td>
                        <td>
                            Jean Dupont
                        </td>
                        <td>
                        	Jean Dupont
                        </td>
                        <td>
                        	<i>Lien pop-up<br>maps</i>
                        </td>
                    </tr>
                    
                    <tr>
                        <td >
                            SOPRA
                        </td>
                        <td>
                            81000 - Albi
                        </td>
                        <td>
                            No
                        </td>
                        <td>
                            -
                        </td>
                        <td>
                        	Marie
                        </td>
                        <td>
                        	<i>Lien pop-up<br>maps</i>
                        </td>
                    </tr>
                  
                    
                </table>
            </div>
  </div>
</div>

</body>

</html>
