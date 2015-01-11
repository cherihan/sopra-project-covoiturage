<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.*"%>

	
<html>
<head>
 <meta charset="utf-8" />
 <link rel="stylesheet" type="text/css" href="./css/main.css" />
 <link rel="stylesheet" type="text/css" href="./css/side_panels.css" />
</head>

<body>

<h1><br>${user.getFirstName()}</h1>
<h1>${user.getLastName()}</h1>
<br>
<br>
<textarea disabled>
${user.getBio()}
</textarea>
<br>

<div id=contact>
${user.getEmail()}<br><br>
${user.getTel()}
</div>

</body>
</html>