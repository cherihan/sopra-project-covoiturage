package sopracarpooling.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sun.security.util.Password;
import database.DataBaseAccess;
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
		
		 RequestDispatcher rd = request.getRequestDispatcher("signup.jsp");
		 rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DataBaseAccess dB = new DataBaseAccess();
		//Grabe all the attributes and test if there are not null
		String lastName = request.getParameter("lastname");
		String firstName = request.getParameter("firstname");
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		String phone = request.getParameter("phone");
		String bio = request.getParameter("bio");
		//System.out.println("###DEBUG ### (servlet, doPost) "+lastName+" "+firstName+" "+email+" "+pwd+" "+phone+" "+bio);
		//check if all the parameters have been seted
		if (lastName != null && firstName != null && email != null && 
				pwd != null && phone != null){
			

			
			try{

				//Appel de la fonction d'allex qui crée le compte 
				//alex nous renvois un user id 
				User newUser = new User(-1, lastName, firstName, new EmailAdresse(email), bio, new NumeroTelephone(phone));
				//BB add new account (USER, PASS) return id 
				
				MotDePass pass = new MotDePass(pwd);				
				newUser.setId(dB.newAccount(newUser, pass));				
				
				
				HttpSession s = request.getSession();
				s.setAttribute("user", newUser);
				s.setAttribute("newAccount", "yes");				
				response.sendRedirect("/SopraCarPooling/Home");
			}catch (Exception e){
				RequestDispatcher rd = request.getRequestDispatcher("signup.jsp");
				 rd.forward(request, response);
			}
			
		}else{
			RequestDispatcher rd = request.getRequestDispatcher("signup.jsp");
			 rd.forward(request, response);
		}
		
		
	
		
	}

}
