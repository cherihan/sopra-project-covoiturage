package sopracarpooling.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;

import model.*;

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

	private void chargerLaPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// ICI on charge la page
		String nextPage = "edit_profile.jsp";

		// demander à alex les rides
		// demander aussi à alex les Service possible !

		// request.setAttribute("weeklyRides", weeklyRides);
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		chargerLaPage(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String nextPage = "edit_profile.jsp";

		// check that the session is well established
		HttpSession s = request.getSession();
		User user = (User) s.getAttribute("user");

		// filter for session

		// make sure of with parameters are changed here with the team.

		ArrayList<Ride> userRides = new ArrayList<Ride>();

		for (int i = 0; i < 5; i++) {// pour chaque jour de la semaine
			String id = request.getParameter(i + "-id");// will not be changed
			String source = request.getParameter(i + "-code-post");
			String dest = request.getParameter(i + "-service");
			String time1 = request.getParameter(i + "-time");
			String time2 = request.getParameter(i + "-time");
			// String sens = request.getParameter(i+"-sens");
			String com1 = request.getParameter(i + "-com");
			String com2 = request.getParameter(i + "-com");
			String exist1 = request.getParameter(i + "-aller");
			String exist2 = request.getParameter(i + "-retour");
			if (id != null && source != null && dest != null && time1 != null
					&& time2 != null && exist1 != null && exist2 != null) {
				Service office = new Service(Integer.parseInt(dest));
				Adresse home = new Adresse(new PostCode(
						Integer.parseInt(source)));
				int ID = Integer.parseInt(id);
				Heure heur2 = new Heure(time1);
				Heure heur1 = new Heure(time2);
				// boolean sensBool = ( Integer.parseInt(sens)== 1)? true :
				// false;
				boolean aller = (Integer.parseInt(exist1) == 1) ? true : false;
				boolean retour = (Integer.parseInt(exist2) == 1) ? true : false;
				if (aller) {
					userRides.add(new Ride(ID, user, home, office,
							new JourDeLaSemaine(i), heur1, true, com1));
				}
				if (retour) {
					userRides.add(new Ride(ID, user, home, office,
							new JourDeLaSemaine(i), heur2, false, com2));
				}

			}
			try {
				// envoyer tout ca à alex qui nous renvoi les trajets du mec
			} catch (Exception e) {
				// ça n'a pas marché
				s.setAttribute("performAction", "error");
			}finally{
				chargerLaPage(request, response);
			}
			
		}

	}

}
