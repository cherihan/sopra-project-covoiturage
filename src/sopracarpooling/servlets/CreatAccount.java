package sopracarpooling.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;


/**
 * Servlet implementation class CreatAccount
 */
@WebServlet("/CreatAccount")
public class CreatAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreatAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Grabe all the attributes and test if there are not null
		String lastname = request.getParameter("lastname");
		String firstName = request.getParameter("firtsname");
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		String phone = request.getParameter("phone");
		String bio = request.getParameter("bio");
		
		//check if all the parameters have been seted
		if (lastname != null && firstName != null && email != null && 
				pwd != null && phone != null){
			
			
			try{

				//Appel de la fonction d'allex qui crée le compte 
				//alex nous renvois un user id 
				User newUser = new User (-1, lastname, firstName, email, bio);
				//BB add new account (USER, PASS) return id 
				newUser.setId(666);
				
				
							
				
				
			
				
				HttpSession s = request.getSession();
				s.setAttribute("user", newUser);
				s.setAttribute("newAccount", "yes");				
				response.sendRedirect("home.html");
			}catch (Exception e){
				response.sendRedirect("signup.html");//avec message d'ereur
			}
			
		}else{
			response.sendRedirect("signup.html");//faire passer un message d'erreur
		}
		
		
	
		
	}

}
