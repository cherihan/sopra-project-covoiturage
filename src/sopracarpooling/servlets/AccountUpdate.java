package sopracarpooling.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.*
;

/**
 * Servlet implementation class AccountUpdate
 */
@WebServlet("/AccountUpdate")
public class AccountUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountUpdate() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String nextPage = "edit_profile.html";
		HttpSession s = request.getSession();
		User user = (User) s.getAttribute("user");
		//protection of the page. 
		if(user == null){response.sendRedirect("login.html");}
		
		//check that the session is well established 
	
		
		//make sure of with parameters are changed here with the team. 
		String name = request.getParameter("name");
		String nickName = request.getParameter("nick-name");
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		String phone = request.getParameter("phone");
		String bio = request.getParameter("bio");

		// check if all the parameters have been seted

		try {
			
			//appeler fonct de Alex pour fair changer les chose dans la base (no change if null)
			
			
			s.setAttribute("actionPerformed", true);
			response.sendRedirect(nextPage);
		} catch (Exception e) {
			s.setAttribute("actionPerformed", false);
			response.sendRedirect(nextPage);// faire passer un message
											// d'erreur
		}

	}

}
