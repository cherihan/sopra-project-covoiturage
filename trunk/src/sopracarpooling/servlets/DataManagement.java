package sopracarpooling.servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.EmailAdresse;
import model.MotDePass;
import model.PostCode;
import model.Service;
import model.User;
import database.AdminDataBaseAccess;
import database.DataBaseAccess;
import database.NotAdmin;
import database.RequestDidNotWork;

/**
 * Servlet implementation class DataManagement
 */
@WebServlet("/DataManagement")
public class DataManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataManagement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataBaseAccess dB = new DataBaseAccess();
		HttpSession s = request.getSession();
		User u = (User) s.getAttribute("user");
		
		try{
			if(u == null){
				u = dB.requestUserIsRegistered(new EmailAdresse("tristan.bardey@gmail.com"), new MotDePass("tristan"));
				s.setAttribute("user", u);
			}
			
			AdminDataBaseAccess adminDB = new AdminDataBaseAccess(u);
			HashMap<Service,HashMap<PostCode,Integer>> repartission = adminDB.getRidesRepartition();
			
			
			
		}catch (RequestDidNotWork e){
			s.setAttribute("performAction", "error");
		}catch(NotAdmin e){
			s.setAttribute("performAction", "error");
		}finally{
			RequestDispatcher rd = request.getRequestDispatcher("DataManagement.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//perform avection on data 
		
		//send responce to jsp
		
	}

}
