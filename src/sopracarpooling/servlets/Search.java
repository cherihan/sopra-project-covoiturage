package sopracarpooling.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DataBaseAccess;
import database.RequestDidNotWork;
import model.*;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataBaseAccess dB = new DataBaseAccess();
		HttpSession s = request.getSession();
		User user = (User) s.getAttribute("user");

		try{
			//demander à alex les rides
			ArrayList <Ride> matchingRides = dB.requestMatchingRides(user);
			
			//System.out.println("###DEBUG ### (servlet, Search) : size mR : "+matchingRides.size());
			s.setAttribute("matchingRides", matchingRides); 
			ArrayList <JourDeLaSemaine> jour = new ArrayList<JourDeLaSemaine>();
			jour.add(new JourDeLaSemaine(1,"lundi"));
			s.setAttribute("jours", jour);
			
		}catch (RequestDidNotWork e){
			request.setAttribute("performAction", "error");
		}finally {
			s.setAttribute("search", true);
			
			response.sendRedirect("Home");
			
		}
		
	}

}
