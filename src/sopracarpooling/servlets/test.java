package sopracarpooling.servlets;

import java.io.IOException;

import model.*;
import database.*;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class test
 */
@WebServlet("/TEST")
public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public test() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.sendRedirect("testing_tristan.jsp");
		// ça ça marche !

		/*
		 * response.setContentType("text/html"); PrintWriter out =
		 * response.getWriter(); HttpSession s = request.getSession(); String
		 * admin = (String) s.getAttribute("isAdmin"); String user = (String)
		 * s.getAttribute("userID"); boolean newS = s.isNew();
		 * out.println("<html><body><h2>"
		 * +user+"</h2><br><p>"+admin+"</p><br></body></html>");
		 */

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DataBaseAccess dB = new DataBaseAccess();
		HttpSession s = request.getSession();
		int action = Integer.parseInt(request.getParameter("action"));
		try {

			switch (action) {
			case 1:
				// On teste la conncetion
				String sMail = (String) request.getParameter("emailadress");
				EmailAdresse mail = new EmailAdresse(sMail);
				String sPass = (String) request.getParameter("pwd");
				MotDePass pass = new MotDePass(sPass);
				//System.out.println("###DEBUG ### (test, doPost) emailRCV: "+mail+ " pass : "+pass.getClaire());
				User user = dB.requestUserIsRegistered(mail, pass);
				s.setAttribute("user", user);

				break;
			case 2:
				s.invalidate();
				break;

			default:
				break;
			}

		} catch (Exception e) {
			s.setAttribute("error", e);
		}

		RequestDispatcher rd = request
				.getRequestDispatcher("testing_tristan.jsp");
		rd.forward(request, response);

	}
}
