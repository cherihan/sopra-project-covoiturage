<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.*,database.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>test alex</title>
</head>
<body>
	
<h2>RequestUserRides</h2>
<% 

	DataBaseAccess db = new DataBaseAccess();
	EmailAdresse email = new EmailAdresse("superman@gmail.com");
	//NumeroTelephone tel = new NumeroTelephone("0");
	//User test = new User(2,"Superman","clark",email,"Superman.",tel);
	MotDePass mdp = new MotDePass("superman");
	User test = db.requestUserIsRegistered(email,mdp);
	out.println("<p>salut</p><br>"+test);
	test.setBio("salut je suis estelle");
	EmailAdresse mail = new EmailAdresse("estelle@estelle.vn");
	test.setEmail(mail);
	test.setFirstName("estelle");
	test.setLastName("nguyen");
	NumeroTelephone tel = new NumeroTelephone("0606060606");
	test.setTel(tel);
	//db.requestServices();
	//ArrayList<Ride> rides = db.requestUserRides(test);
	//ArrayList<Ride> rides = db.requestMatchingRides(test);
	//System.out.println("1111");
	//out.println("<p>"+rides.get(0)+"</p>");
	//out.println("1111");
	//out.println("<p>"+rides.get(1)+"</p>");
	db.editUserProfile(test,mdp);

%>

</body>
</html>