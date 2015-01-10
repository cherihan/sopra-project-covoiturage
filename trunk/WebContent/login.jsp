<html>
<head>

<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.5.0/pure-min.css">
<link rel="stylesheet" type="text/css" href="./css/login_signup.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
</head>

<body>

<script>
/*$(document).ready(function(){
  $("#identification").click(function(){
    $(this).css("background-color","#A52A2A");
  });
}); */

</script>



<div id="identification">
</div>

<title>Connection</title>

<div class="container">

  <div id="login-form">


    <h3>Connection</h3>

<html>

       
</body>

</html>

    <fieldset>

      <form action="/SopraCarPooling/Login" method="post">

        <input type="email" name="emailadress" required value="Email" onBlur="if(this.value=='')this.value='Email'" onFocus="if(this.value=='Email')this.value='' "> <!-- JS because of IE support; better: placeholder="Email" -->

        <input type="password" name="pwd" required value="Password" onBlur="if(this.value=='')this.value='Password'" onFocus="if(this.value=='Password')this.value='' "> <!-- JS because of IE support; better: placeholder="Password" -->

        <input type="submit" value="Login">
	</form>
        <footer class="clearfix">

        <p><span class="info">?</span><a href="#">Forgot Password</a></p>
        <p><span class="info">+</span><a href="signup.jsp"">Sign up</a></p>

        </footer>

      

    </fieldset>

  </div> <!-- end login-form -->
</div>


</body>
<html>

