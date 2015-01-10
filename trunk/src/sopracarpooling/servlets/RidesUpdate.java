package sopracarpooling.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DataBaseAccess;
import database.RequestDidNotWork;

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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		DataBaseAccess dB = new DataBaseAccess();
		HttpSession s = request.getSession();
		try {

			ArrayList<JourDeLaSemaine> jours = dB.requestJours();
			s.setAttribute("jours", jours);
			
			//get services
			ArrayList<Service> sers = dB.requestServices();
			s.setAttribute("sopraSite", sers);
			//get user rides.
			User u =(User) s.getAttribute("user");
			u.setWeekRides(dB.requestUserRides(u));
			s.setAttribute("user", u);

		} catch (RequestDidNotWork e) {
			System.err
					.println("###DEBUG ### (UpdateRides, servlets) : Error  ");
			s.setAttribute("performAction", "error");
		} finally {
			
			String nextPage = "edit_route.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(nextPage);
			rd.forward(request, response);

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// check that the session is well established
		HttpSession s = request.getSession();
		User user = (User) s.getAttribute("user");
		DataBaseAccess dB = new DataBaseAccess();
		System.out.println("###DEBUG ### (UpdateRides, servlets) : connected user  : "+ user);

		// filter for session

		// make sure of with parameters are changed here with the team.
		try {
			ArrayList<Ride> userRides = new ArrayList<Ride>();
			int o = 0; // debug
			ArrayList<JourDeLaSemaine> jours = (ArrayList<JourDeLaSemaine>) s.getAttribute("jours");
			for (int k = 0; k < jours.size(); k++) {// pour chaque jour de la
													// semaine
				// System.out.println("###DEBUG ### (UpdateRides, servlets) : passage : "+i);
				// can be changed by a adapter calsse (should)
				int i = jours.get(k).getJour();

				String homeRue = request.getParameter(i + "-street");
				String homeVille = request.getParameter(i + "-city");
				String source = request.getParameter(i + "-code-post");
				String dest = request.getParameter(i + "-service");
				String ah = request.getParameter("ah" + i);
				String am = request.getParameter("am" + i);
				String rh = request.getParameter("rh" + i);
				String rm = request.getParameter("rm" + i);
				String com = request.getParameter(i + "-com");
				String exist1 = request.getParameter(i + "-aller");
				String exist2 = request.getParameter(i + "-retour");
				//System.out.println("###DEBUG ### (UpdateRides, servlets) : avant if  "+ source + " " + dest);
				if (source != null && dest != null && !source.equals("")
						&& !dest.equals("")) {

					// System.out.println("###DEBUG ### (UpdateRides, servlets) :  "+source);
					Service office = new Service(Integer.parseInt("1"));
					Adresse home = new Adresse(new PostCode(
							Integer.parseInt(source)), homeRue, homeVille);

					
					if (exist1 != null && exist1.equals("on") && ah != null && am != null){
						
						String time1 = ah + am;
						Heure heur1 = new Heure(time1);
						userRides.add(new Ride(0, user, home, office,
								new JourDeLaSemaine(i), heur1, true, com));
						// System.out.println("###DEBUG ### (UpdateRides, servlets) : "+userRides.get(o++));

					}
					if (exist2 != null && exist2.equals("on")&& rh != null && rm != null){						
						String time2 = rh + rm;
						Heure heur2 = new Heure(time2);
						userRides.add(new Ride(0, user, home, office,
								new JourDeLaSemaine(i), heur2, false, com));
						// System.out.println("###DEBUG ### (UpdateRides, servlets) : "+userRides.get(o++));

					}
				}
			}

			// la databas fait sont taf :

			if (userRides.size() > 0) {
				dB.addUserRides(userRides, user);
			} else {
				//la base de donner doit suprimer tous les rides du user
				dB.deletAllUserRides (user);
			}
			user.setWeekRides(dB.requestUserRides(user));
			s.setAttribute("user", user);
			// envoyer tout ca à alex qui nous renvoi les trajets du mec
		} catch (RequestDidNotWork e) {
			// ça n'a pas marché
			System.err.println("###DEBUG ### (UpdateRides, servlets) : Error  ");
			s.setAttribute("performAction", "error");
		} finally {
			// System.out.println("###DEBUG ### (UpdateRides, servlets) : On s'en va !");
			// chargerLaPage(request, response);
			
			String nextPage = "edit_route.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(nextPage);
			rd.forward(request, response);

		}

	}
}
