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

import model.EmailAdresse;
import model.MotDePass;
import model.User;
import database.AdminDataBaseAccess;
import database.DataBaseAccess;
import database.NotAdmin;
import database.RequestDidNotWork;

/**
 * Servlet implementation class UserManagement
 */
@WebServlet("/UserManagement")
public class UserManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserManagement() {
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
			ArrayList<User> allUsers = adminDB.requestAllUsers();
			s.setAttribute("allUsers", allUsers);
			s.setAttribute("action", "user");
			
		}catch (RequestDidNotWork e){
			s.setAttribute("performAction", "error");
		}catch(NotAdmin n){
			s.setAttribute("performAction", "error");
		}finally{
		
			response.sendRedirect("Administration");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataBaseAccess dB = new DataBaseAccess();
		HttpSession s = request.getSession();
		User u = (User) s.getAttribute("user");
		
		String action = request.getParameter("action");
		String userID = request.getParameter("user");
		
		//System.out.println("###DEBUG ### (servlets,UserManagement) : "+action+"  "+user);
		try{
			
			
			AdminDataBaseAccess adminDB = new AdminDataBaseAccess(u);
			if(action.equals("del")){
				
				adminDB.delUser(new User(Integer.parseInt(userID)));
				response.sendRedirect("UserManagement");
			}else if(action.equals("admin")){
				
				adminDB.makeAdmin(new User(Integer.parseInt(userID)));
				response.sendRedirect("UserManagement");
			}else if(action.equals("bane")){
				
				adminDB.baneAdmin(new User(Integer.parseInt(userID)));
				response.sendRedirect("UserManagement");
			}else if(action.equals("rides")){
				System.out.println("###DEBUG ### (servlets,UserManagement) : rides");
				
				s.setAttribute("administrator", u);
				s.setAttribute("user", adminDB.getUser(Integer.parseInt(userID)));				
				response.sendRedirect("RidesUpdate");
				
			}else if(action.equals("profil")){
				s.setAttribute("administrator", u);
				s.setAttribute("user", adminDB.getUser(Integer.parseInt(userID)));				
				response.sendRedirect("EditProfile");
			}
			
			
		}catch (RequestDidNotWork e){
			s.setAttribute("performAction", "error");
			response.sendRedirect("UserManagement");
		}catch(NotAdmin n){
			s.setAttribute("performAction", "error");
			response.sendRedirect("UserManagement");
		}
	}

}
