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
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Home");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataBaseAccess dB = new DataBaseAccess();
		HttpSession s = request.getSession();
		User user = (User) s.getAttribute("user");
		
		String jour = request.getParameter("search-jour");
		String street =request.getParameter("address-search");
		String cp = request.getParameter("cp-search");
		String ville =  request.getParameter("ville-search");
		String site = request.getParameter("search-service");
		ArrayList <JourDeLaSemaine> jours = new ArrayList<JourDeLaSemaine>();
		

		try{
			JourDeLaSemaine j = dB.getJour(Integer.parseInt(jour));
			PostCode codePost = new PostCode(Integer.parseInt(cp));
			Adresse addr = new Adresse(codePost, street, ville);
			Service office = new Service(Integer.parseInt(site));
			
			//demander à alex les rides
			
			ArrayList <Ride> matchingRides = dB.requestSearchRides(addr, j, office);
			
			//System.out.println("###DEBUG ### (servlet, Search) : size mR : "+matchingRides.size());
			request.setAttribute("matchingRides", matchingRides); 
			
			jours.add(j);
			
		}catch (Exception e){
			request.setAttribute("actionPerform", "error");
		}finally {
			System.out.println("###DEBUG ### (servlet, Search) : errorATT : "+request.getAttribute("actionPerform"));
			request.setAttribute("jours", jours);
			request.setAttribute("search", true);	
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Home");
			dispatcher.forward(request, response);
			
		}
		
	}

}
