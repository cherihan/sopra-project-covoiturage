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
@WebServlet("EditProfile")
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
			
		/*HttpSession s = request.getSession();
		User user = (User) s.getAttribute("user");
		try{
			if(user == null){
				DataBaseAccess dB = new DataBaseAccess();
				user = dB.requestUserIsRegistered(new EmailAdresse("dd@yopmail.com"), new MotDePass("millieu"));
				s.setAttribute("user", user);
			}
		}catch (Exception c){
			
		}*/
		RequestDispatcher rd = request.getRequestDispatcher("edit_profile.jsp");
		rd.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//do edit profile. 
		
		HttpSession s = request.getSession();
		User user = (User) s.getAttribute("user");
		DataBaseAccess dB = new DataBaseAccess();
		
		
		String lastName = request.getParameter("lastName");
		String firstName = request.getParameter("firstName");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		String bio = request.getParameter("bio");
		
		String pwd = request.getParameter("pwd");
		String pwdC = request.getParameter("pwd_confirm");
		
		
		try{
					
			
			//System.out.println("###DEBUG ### (sevlet, editProfile) : "+ pwd+"  "+pwdC);
			if (lastName != null && firstName != null && email !=null){
				
				
				//demander à alex de changer le les infos du user
				User updatedUser = new User(user.getID(),lastName,firstName,new EmailAdresse(email),bio,new NumeroTelephone(tel));
				if(!user.equals(updatedUser)){
					dB.editUserProfile(updatedUser);
					//System.out.println("###DEBUG ### (sevlet, editProfile) : Mise a jour du user"+ updatedUser);
				}
				
				
				if( pwd != null && pwdC != null){
					if(pwd.equals(pwdC)){
						//changer le mot de pass
						dB.editUserPassword(updatedUser, new MotDePass(pwd));
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
			User admin = (User) s.getAttribute("administrator");
			if(admin != null && admin instanceof Admin){
				s.setAttribute("user", admin);
				response.sendRedirect("UserManagement");
			}else{
				response.sendRedirect("EditProfile");
			}
		}
		
		
		
		
	}

}
