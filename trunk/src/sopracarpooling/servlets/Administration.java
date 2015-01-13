package sopracarpooling.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sun.rmi.server.Dispatcher;
import sun.security.jca.GetInstance.Instance;
import model.*;
import database.*;
/**
 * Servlet implementation class Administration
 */
@WebServlet("/Administration")
public class Administration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Administration() {
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
			
			
			
		}catch (RequestDidNotWork e){
			s.setAttribute("performAction", "error");
		}finally{
								
			RequestDispatcher rd = request.getRequestDispatcher("administration.jsp");
			rd.forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
