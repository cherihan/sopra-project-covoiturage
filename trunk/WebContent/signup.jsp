<html>
<head>

<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.5.0/pure-min.css">
<link rel="stylesheet" type="text/css" href="./css/login_signup.css">

</head>

<body>

<div id="identification">
</div>
<title>Sign up</title>

<div class="container">

  <div id="login-form">


    <h3>
    <img src="./images/logo.png"/><br><br>
    Register</h3>

    <fieldset>

      <form action="/SopraCarPooling/CreatAccount" method="post">

        <input type="lastname" name="lastname" required placeholder="Last name *"> 
       
        <input type="firstname" name="firstname" required placeholder="First name *"> 

        <input type="password" name="pwd" required placeholder="Password *"> 
		
		<input type="email" name="email" required placeholder="Email *"> 

		<input type="phone" name="phone" placeholder="Optional : Phone number"> 
		
		<textarea type="bio" name="bio" placeholder="Optional : About you"></textarea>
		
        <input type="submit" onClick="checkProfileForm()" value="Sign up">

	</form>
	
        <footer class="clearfix">
      
        </footer>

    </fieldset>

  </div> <!-- end login-form -->
</div>


</body>
<html>

