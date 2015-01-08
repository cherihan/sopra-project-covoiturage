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
		

		HttpSession s = request.getSession();
		User user = (User)  s.getAttribute("user");
		
				
		
		//tous les tableaux sont rempli
		//On a donc tous les trajets qui partes de vers chez lui, à son travail, et les gens qui fonts ce trajet. 
		ArrayList <ArrayList <Ride>> weeklyRides = new ArrayList<ArrayList <Ride>>();
		
		
		//demander à alex le rides possible.
		
		
		request.setAttribute("weeklyRides", weeklyRides); //on pass les rides possible à la page jsp! (qui les affiches) 
		RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	}

}
