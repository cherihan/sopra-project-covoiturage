<%@page import="com.sun.org.apache.bcel.internal.classfile.Code"%>
<%@page import="java.util.*"%>
<%@page import="model.*"%>




<%
HttpSession sess = request.getSession();
HashMap<PostCode,HashMap<Service,Integer>> res = (HashMap<PostCode,HashMap<Service,Integer>>) sess.getAttribute("repartission");
ArrayList<Service> services =(ArrayList<Service>) sess.getAttribute("services");

%>

<table class="table table-bordered table-hover" >

<tr>
<%
//System.out.println("###DEBUG ### (jsp, DataManagement) : "+res);
//System.out.println("###DEBUG ### (jsp, DataManagement) : "+services);

 %>
 
 
 <th>Code Postal</th>
<%for(int i = 0; i <services.size() ; i++){ %>
	
	<th></th>
<%} %>
</tr>
<%for(PostCode code : res.keySet()){ %>

<tr>
<td><%=code %> </td>

<%for(Service serv : res.get(code).keySet()){ %>

	<td><%=res.get(code).get(serv)  %></td>
	

<%} %>
</tr>
<%} %>




</table>
