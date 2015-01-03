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
		
		
		System.out.println(user);
		
		
		//On va dire que le user est bien passer par la.
		//Donc a notre user et on sais que sont
		// on peu donc chercher les rides qui lui conviennes 
		ArrayList <Ride> mondayRides = new ArrayList<Ride>();
		ArrayList <Ride> tuesdayRides = new ArrayList<Ride>();
		ArrayList <Ride> wednesdayRides = new ArrayList<Ride>();
		ArrayList <Ride> thrusdayRides = new ArrayList<Ride>();
		ArrayList <Ride> FridayRides = new ArrayList<Ride>();
		
		
		//Possible rides
		//rechercher avec la base de donnée ou avec une classe special
		
		
		/*
		ArrayList <ArrayList <Ride>> weeklyRides = new ArrayList<ArrayList <Ride>>();
		
		request.setAttribute("weeklyRides", weeklyRides); //on pass les rides possible à la page jsp ! 
		RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
		rd.forward(request, response);
		*/
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("hello");
		Map<String, String> options = new LinkedHashMap<String, String>();
	    options.put("value1", "label1");
	    options.put("value2", "label2");
	    options.put("value3", "label3");
	    String json = "lalalalalala";
	    String son = new Gson().toJson(options);
	    
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(son);
	
	}

}
