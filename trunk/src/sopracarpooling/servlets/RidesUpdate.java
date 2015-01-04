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
		//Monday 
		String mondayID = request.getParameter("monday-id");//will not be changed
		String mondaySource = request.getParameter("monday-src");//home (code postal dans un premier temps)
		String mondaydest = request.getParameter("monday-dest");//le nom d'un service
		String mondaytime = request.getParameter("monday-time");//l'heure du ride
		String mondaySens = request.getParameter("monday-sens");//le send
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
