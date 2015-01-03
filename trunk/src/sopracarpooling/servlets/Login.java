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

import controler.*;
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
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		
		String loginPage = "login.html";
		String nextPage = "Home";// bien envoyer vers home (servlet)

		
		String userName = request.getParameter("userName");
		String pwd = request.getParameter("pwd");

		// pour les testes
		 userName = "lala";
		 pwd = "12";

		if (userName != null && pwd != null) {

			// try catch ?

			// int userID = BD(userName, pwd);
			// boolean isAdmin = DB(userID);

			// simulation
			User user = new User(userName);
			
			//System.out.println("###DEBUG ### (servlets, Login) = user : "+user);

			// Cr�ation de la session
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			
			//request.setAttribute("user", user);
			// on peux ajouter des informations ici dans le cookie
			if (user.isAdmin()) {
				session.setAttribute("isAdmin", true);
			}
			response.sendRedirect(nextPage);
		} else {
			
			response.sendRedirect(loginPage);//with error
		}

	}

}
