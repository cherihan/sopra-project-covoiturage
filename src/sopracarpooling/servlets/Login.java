package sopracarpooling.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controler.*;



/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")



/**
 * This Servlets aims to check that you really are a registered user of site. If you are you will be redirected to 
 * your rides proposition. Otherwise you will be sent back to the login page. 
 * @author Tristan
 *
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//this servlets is not accessible form a the get methode
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Controler controle = new Controler();
		//retreive the parameters 		
		//if the parameters are valide (ask melina if they are good)
			//set the cookies 
			//redirect to the rides pages 
		//else parameters not valide or not a user
			//send to the home page 
		
		String userName = request.getParameter("userName");
		String pwd = request.getParameter("pwd");
		userName = "lala";
		pwd = "12";
		if(userName != null && pwd != null){
			int userID = controle.userIdAndPasswordAregood(userName, pwd);
			boolean isAdmin = controle.isAdmin(userID);; 
			//System.out.println("##DEBUG## (doPost, Login) ="+userID);			
			if(userID > 0){
				//Création de la session
				HttpSession session = request.getSession();
				session.setAttribute("userID", Integer.toString(userID));
				if(isAdmin){session.setAttribute("isAdmin", "1");}						
				response.sendRedirect("test");
			}else {response.sendRedirect("test");}
		}else {response.sendRedirect("test");}
		
	}

}
