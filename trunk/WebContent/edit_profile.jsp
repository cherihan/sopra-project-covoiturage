<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="model.*"%>

<%
	HttpSession s = request.getSession();
	User u = (User) s.getAttribute("user");
%>
<html>
<head>

<link rel="stylesheet" href="./css/profile_edit.css">
 <link rel="stylesheet" type="text/css" href="./css/side_panels.css" />
 <link rel="stylesheet" type="text/css" href="./css/main.css" />
 <link rel="stylesheet" type="text/css" href="./css/header.css" />
 
 <script>
  
function checkProfileForm()
{
  if (document.profile_form.pwd.value == document.profile_form.pwd_confirm.value)
  {
	  document.profile_form.submit();
  }
  else
    alert("Passwords do not match !");
}
</script>

</head>

<body>

<title>Profile Edition</title>

<header>
	<%@ include file="header.jsp" %>
</header>

<left_side>
	<%@ include file="user_banner.jsp" %>
</left_side>

<div id="bg">
  <div class="module">
    <ul>
      <li class="tab activeTab"><a href="EditProfile"><img src="./images/user.png" alt="" class="icon"/></a></li>
      <li class="tab sleepyTab" ><a href="RidesUpdate"><img src="./images/milestone.png" alt="abra" class="icon"/></a></li>
    </ul>
    <form class="form" action="/SopraCarPooling/EditProfile" method="post"><br><br>
      <input type="text" name="firstName" value="${user.getFirstName()}" class="textbox" />
      <input type="text" name="lastName" value="${user.getLastName()}" class="firstnamebox" /><br>
      <input type="email" name="email" value="${user.getEmail()}" class="longbox" />
      <input type="text" name="tel" value="${user.getTel()}" class="phonebox" /><br>
      <textarea type="bio" name="bio" value="${user.getBio()}"></textarea><br><br>
      <div class="pwdtxt">New password :
      <input type="password" name="pwd" value="Password" class="pwdbox" /> </div>
      <div class="pwdtxt">Confirm password :
      <input type="password" name="pwd_confirm" value="Password" class="pwdbox" /></div>

      <input type="submit" onClick="checkProfileForm()" value="Save changes" class="button" />     
    </form>
  </div>
</div>


</body>
<html>