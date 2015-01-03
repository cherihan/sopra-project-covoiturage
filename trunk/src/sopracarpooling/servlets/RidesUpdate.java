package sopracarpooling.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

/**
 * Servlet implementation class RidesUpdate
 */
@WebServlet("/RidesUpdate")
public class RidesUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RidesUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nextPage = "edit_profile.html";
		
		

		
		//check that the session is well established 
		HttpSession s =request.getSession();
		User user = (User) s.getAttribute("user");
		
		//filter for session
		
		//make sure of with parameters are changed here with the team. 
		String modayID = request.getParameter("monday-id");
		String modaySource = request.getParameter("monday-src");
		String modaydest = request.getParameter("monday-dest");
		String modaytime = request.getParameter("monday-time");
		String modaySens = request.getParameter("monday-sens");
		//faire ça pour tous les param envoyé. (ou json si a le temps)
			
		// check if all the parameters have been seted

		try {
			
			
			//appeler fonct de Alex pour fair changer les chose dans la base 
			
			
			
			s.setAttribute("action-perfom", true);
			response.sendRedirect(nextPage);//en precisent que tout c'est bien passé
		} catch (Exception e) {
			
			s.setAttribute("action-perfom", false);
			response.sendRedirect(nextPage);// faire passer un message
											// d'erreur
		}

	

	}

}
