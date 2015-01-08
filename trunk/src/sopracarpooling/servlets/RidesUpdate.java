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

		String nextPage = "edit_route.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);

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

		// filter for session

		// make sure of with parameters are changed here with the team.
		try {
			ArrayList<Ride> userRides = new ArrayList<Ride>();
			int o = 0; // debug
			for (int i = 1; i < 6; i++) {// pour chaque jour de la semaine

				// can be changed by a adapter calsse (should)
				String idalle = request.getParameter(i + "-id-1");// will not be
																	// changed
				String idretour = request.getParameter(i + "-id-2");
				String homeRue = request.getParameter(i + "-street");
				String homeVille = request.getParameter(i + "-city");
				String source = request.getParameter(i + "-code-post");
				String dest = request.getParameter(i + "-service");
				String ah = request.getParameter("ah" + i);
				String am = request.getParameter("am" + i);
				String rh = request.getParameter("rh" + i);
				String rm = request.getParameter("rm" + i);
				
				
				// String sens = request.getParameter(i+"-sens");
				String com = request.getParameter(i + "-com");
				String exist1 = request.getParameter(i + "-aller");
				String exist2 = request.getParameter(i + "-retour");
				// System.out.println("###DEBUG ### (UpdateRides, servlets) :"
				// +idalle+idretour+homeRue+homeVille+source+dest+ah+am+rh+rm+time1+time2+com+exist1+exist2);
				// System.out.println("###DEBUG ### (UpdateRides, servlets) :"+dest);
				if (idalle != null && idretour != null && source != null
						&& dest != null ) {

					System.out.println("###DEBUG ### (UpdateRides, servlets) : on est dans le if du for");
					Service office = new Service(Integer.parseInt("1"));//hard so far
					System.out.println("###DEBUG ### (UpdateRides, servlets) : "+ah+":"+am);													
					Adresse home = new Adresse(new PostCode(
							Integer.parseInt(source)), homeRue, homeVille);
					int IDalle = Integer.parseInt(idalle);
					int IDretour = Integer.parseInt(idretour);
					
					// System.out.println("###DEBUG ### (UpdateRides, servlets) : "+heur1+" "+heur2+" "+office+" "+home);
					// boolean sensBool = ( Integer.parseInt(sens)== 1)? true :
					// false;

					boolean aller = (exist1 == "on") ? false : true;
					boolean retour = (exist2 == "on") ? false : true;
					System.out.println("###DEBUG ### (UpdateRides, servlets) : "+aller+" "+ah+":"+am);
					if (aller && ah != null && am != null) {
						
						String time1 = ah + am;
						Heure heur1 = new Heure(time1);
						userRides.add(new Ride(IDalle, user, home, office,
								new JourDeLaSemaine(i), heur1, true, com));
						System.out.println("###DEBUG ### (UpdateRides, servlets) : "+ userRides.get(o++));

					}
					if (retour && rh != null && rm != null) {
						String time2 = rh + rm;
						Heure heur2 = new Heure(time2);
						userRides.add(new Ride(IDretour, user, home, office,
								new JourDeLaSemaine(i), heur2, false, com));
						System.out
								.println("###DEBUG ### (UpdateRides, servlets) : "
										+ userRides.get(o++));
					}
				}
			}
			
			//la databas fait sont taf : 
			if(userRides.size() > 0){
				dB.addUserRides(userRides);
			}
			

			// envoyer tout ca � alex qui nous renvoi les trajets du mec
		} catch (Exception e) {
			// �a n'a pas march�
			System.err.println("###DEBUG ### (UpdateRides, servlets) : Error  :"+e);
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
