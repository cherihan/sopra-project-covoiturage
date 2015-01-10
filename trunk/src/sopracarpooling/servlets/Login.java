package sopracarpooling.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DataBaseAccess;
import model.*;

/**
 * Servlet implementation class Login
 */
// change if necessary
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//si onis user loged in ?
		HttpSession s = request.getSession();
		
		if(s.getAttribute("user")!= null){
			response.sendRedirect("/SopraCarPooling/Home");
		}else{			
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DataBaseAccess dB = new DataBaseAccess();		
		String loginPage = "login.jsp";
		String nextPage = "Home";// bien envoyer vers home (servlet)
		HttpSession s = request.getSession();
		//grabe parameters
		String sMail = (String) request.getParameter("emailadress");
		String sPass = (String) request.getParameter("pwd");		
		
		if (sMail != null && sPass != null) {
			EmailAdresse mail = new EmailAdresse(sMail);
			MotDePass pass = new MotDePass(sPass);
			try{
				User user = dB.requestUserIsRegistered(mail, pass);
				s.setAttribute("user", user);
				System.out.println("###DEBUG ### (servlets, Login) = user : "+user);
				response.sendRedirect(nextPage);
			}catch (Exception e){
				s.setAttribute("actionPerform", "errorNologIN");
				response.sendRedirect(loginPage);
			}
		} else {
			s.setAttribute("actionPerform", "errorNologIN");
			response.sendRedirect(loginPage);
		}

	}
	
}
