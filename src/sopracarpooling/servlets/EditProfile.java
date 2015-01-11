package sopracarpooling.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.RequestDidNotWork;
import model.*;
import database.*;

/**
 * Servlet implementation class EditProfile
 */
@WebServlet("/EditProfile")
public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
					
			RequestDispatcher rd = request.getRequestDispatcher("edit_profile.jsp");
			rd.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//do edit profile. 
		RequestDispatcher rd = request.getRequestDispatcher("edit_profile.jsp");
		HttpSession s = request.getSession();
		User user = (User) s.getAttribute("user");
		DataBaseAccess dB = new DataBaseAccess();
		
		String lastName = (String) request.getAttribute("lastName");
		String firstName = (String) request.getAttribute("firstName");
		String email = (String) request.getAttribute("email");
		String tel = (String) request.getAttribute("tel");
		String bio = (String) request.getAttribute("bio");
		
		String pwd = (String) request.getAttribute("pwd");
		String pwdC = (String) request.getAttribute("pwd_confirm");
		
		
		try{
			if (lastName != null && firstName != null && email !=null){
				
				//demander à alex de changer le les infos du user
				User updatedUser = new User(user.getID(),lastName,firstName,new EmailAdresse(email),bio,new NumeroTelephone(tel));
				
				
				
				if( pwd != null && pwdC != null){
					if(pwd == pwdC){
						//changer le mot de pass
						s.setAttribute("pwdChange", "yes");
					}else{
						s.setAttribute("pwdChange", "no");
					}
				}
				
				s.setAttribute("user", updatedUser);
			}else{				
				s.setAttribute("performAction", "error");
			}
			
		}catch (Exception e){//changer pour RequestDidNotWork
			s.setAttribute("performAction", "error");
		}finally{
			rd.forward(request, response);
		}
		
		
		
		
	}

}
