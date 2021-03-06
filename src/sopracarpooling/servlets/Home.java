package sopracarpooling.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.*;

import com.google.gson.Gson;

import database.DataBaseAccess;
import database.RequestDidNotWork;

import java.util.*;

import model.*;

import java.util.*;

/**
 * Servlet implementation class Home
*/

public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//grab les �l�ments de la page = id du user 
		DataBaseAccess dB = new DataBaseAccess ();
		

		HttpSession s = request.getSession();
		User user = (User)  s.getAttribute("user");
			
		Boolean search = (Boolean) request.getAttribute("search");
		//System.out.println("###DEBUG ### (servlet, Home) searsh : "+search);
		
		
		try {
			//FOR DEBUG ONLY
			/*if(user == null){
				user = dB.requestUserIsRegistered(new EmailAdresse("dd@yopmail.com"), new MotDePass("millieu"));
				s.setAttribute("user", user);
			}*/
			System.out.println("###DEBUG ### (servlet, Home) : search = "+search); 
			if(search == null || search == false){
				
				ArrayList<Ride> matchingRides = dB.requestMatchingRides(user);
				request.setAttribute("matchingRides", matchingRides);
				request.setAttribute("jours", dB.requestJours());
				//System.out.println("###DEBUG ### (servlet, Home) : size mR : "+ matchingRides.size());
				
			}
			
						
			request.setAttribute("joursPossible", dB.requestJours());
			request.setAttribute("sopraSite", dB.requestServices());
		} catch (RequestDidNotWork e) {
			request.setAttribute("performAction", "error");
		} finally {
						
			RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);
		}
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doGet(request, response);
	
	}

}
