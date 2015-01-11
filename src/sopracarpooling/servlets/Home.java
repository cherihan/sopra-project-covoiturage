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
@WebServlet("/Home")
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
		
		//grab les éléments de la page = id du user 
		DataBaseAccess dB = new DataBaseAccess ();
		

		HttpSession s = request.getSession();
		User user = (User)  s.getAttribute("user");
		
		
		
		//tous les tableaux sont rempli
		//On a donc tous les trajets qui partes de vers chez lui, à son travail, et les gens qui fonts ce trajet. 
		try{
			
			if(user == null){
				user = dB.requestUserIsRegistered(new EmailAdresse("superman@gmail.com"), new MotDePass("superman"));
				s.setAttribute("user", user);
			}
			
			ArrayList <Ride> matchingRides = dB.requestMatchingRides(user);
			
			s.setAttribute("matchingRides", matchingRides); 
			s.setAttribute("jours", dB.requestJours());
			System.out.println("###DEBUG ### (servlet, Home) : size mR : "+matchingRides.size());
			
			
		}catch (RequestDidNotWork e){
			request.setAttribute("performAction", "error");
		}finally {
			RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);
		}
		
		
		
		
		//demander à alex le rides possible.
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	}

}
