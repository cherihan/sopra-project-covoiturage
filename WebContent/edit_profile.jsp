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
  <div class="module">
    <ul>
      <li class="tab activeTab"><a href="edit_profile.jsp"><img src="./images/user.png" alt="" class="icon"/></a></li>
      <li class="tab sleepyTab" ><a href="RidesUpdate"><img src="./images/milestone.png" alt="abra" class="icon"/></a></li>
    </ul>
    <form class="form" name="profile_form"><br><br>
      <input type="text" value="${user.getFirstName()}" class="textbox" />
      <input type="text" value="${user.getLastName()}" class="firstnamebox" /><br>
      <input type="email" name="${user.getEmail()}" value="Email Address" class="longbox" />
      <input type="text" value="${user.getTel()}" class="phonebox" /><br>
      <input type="text" value="N° and Street" class="longbox" />
      <input type="text" value="Post code" class="codebox" /> 
      <input type="text" value="City" class="citybox" /><br>
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