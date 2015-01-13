package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import model.*;

public class AdminDataBaseAccess extends DataBaseAccess{

	public AdminDataBaseAccess(User u) throws NotAdmin{
		super();
		if(!(u instanceof Admin)){
			
			throw new NotAdmin();
		}		 
	}
	
	public ArrayList<User> requestAllUsers() throws RequestDidNotWork{
		ArrayList<User> users = new ArrayList<User>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet res= null;
		User user;

		try {
			// connexion
			connexion = Connexion();
			statement = connexion.createStatement();
			
			res = statement.executeQuery("select * from sopra.user order by lastname, firstname;");
			while(res.next()){
				int id = res.getInt("id");
				String lastName = res.getString("lastname");
				String firstName= res.getString("firstname");
				EmailAdresse email = new EmailAdresse(res.getString("mail"));
				String bio = res.getString("bio");
				NumeroTelephone telNum = new NumeroTelephone(res.getString("phone"));
				user = new User(id, lastName, firstName, email, bio, telNum);
				if (res.getInt("isAdmin") == 1) {
					// c'est un admin donc on crée un admin
					user = new Admin(user);
				}
				
				users.add(user);
				//System.out.println("###DEBUG ### (AdminDataBaseAccess, requestAllUser) : "+user);
			}
			
		}catch (Exception e){
			throw new RequestDidNotWork("Nous n'avons pas pue sortir tous les users.");
		}finally{
			Close(res, statement, connexion);
		}
		return users;
	}
	
	public void delUser(User user) throws RequestDidNotWork{
		Connection connexion = null;
		Statement statement = null;		
		try {
			int res;
			connexion = Connexion();
			statement = connexion.createStatement();			
			res = statement.executeUpdate("delete from sopra.user where id='"+user.getID()+"';");
		}catch (Exception e){
			throw new RequestDidNotWork("Nous n'avons pas pue suprimer le user.");
		}finally{
			Close(null, statement, connexion);
		}
	}
	public void makeAdmin(User user) throws RequestDidNotWork{
		Connection connexion = null;
		Statement statement = null;		
		try {
			int res;
			connexion = Connexion();
			statement = connexion.createStatement();			
			res = statement.executeUpdate("update sopra.user set isAdmin='1' where id='"+user.getID()+"';");
		}catch (Exception e){
			throw new RequestDidNotWork("Nous n'avons pas pue metre en admin le user.");
		}finally{
			Close(null, statement, connexion);
		}
	}
	public void baneAdmin(User user) throws RequestDidNotWork{
		Connection connexion = null;
		Statement statement = null;		
		try {
			int res;
			connexion = Connexion();
			statement = connexion.createStatement();			
			res = statement.executeUpdate("update sopra.user set isAdmin='0' where id='"+user.getID()+"';");
		}catch (Exception e){
			throw new RequestDidNotWork("Nous n'avons pas pue metre en admin le user.");
		}finally{
			Close(null, statement, connexion);
		}
	}
	public User getUser(int id) throws RequestDidNotWork{
		Connection connexion = null;
		Statement statement = null;	
		ResultSet resultat= null;
		User user ; 
		try {
			int res;
			connexion = Connexion();
			statement = connexion.createStatement();				
			resultat = statement.executeQuery("SELECT * FROM user WHERE id='"+ id+"'");			
			resultat.next();			
			String lastName = resultat.getString("lastname");
			String firstName= resultat.getString("firstname");
			EmailAdresse email = new EmailAdresse(resultat.getString("mail"));
			String bio = resultat.getString("bio");
			NumeroTelephone telNum = new NumeroTelephone(resultat.getString("phone"));
			user = new User(id, lastName, firstName, email, bio, telNum);
			
		}catch (Exception e){
			throw new RequestDidNotWork("Nous n'avons pas pue metre en admin le user.");
		}finally{
			Close(resultat, statement, connexion);
		}
		
		return user;
	}
	public HashMap<Service,HashMap<PostCode,Integer>> getRidesRepartition() throws RequestDidNotWork{
		Connection connexion = null;
		Statement statement = null;	
		ResultSet sites= null;
		ResultSet codePosts= null;
		ArrayList<Integer> sitesID = new ArrayList<Integer>();
		ArrayList<String> codes = new ArrayList<String>();
		HashMap<Service,HashMap<PostCode,Integer>> res = new  HashMap<Service,HashMap<PostCode,Integer>>();  
		try {
			
			connexion = Connexion();
			statement = connexion.createStatement();				
			sites = statement.executeQuery("SELECT id FROM sopra.sopra_site ;");	
			
			while(sites.next()){
				sitesID.add(sites.getInt(1));
				System.out.println("###DEBUG ### (AdminDataBaseAccess, getRidesRepartition) : "+sites.getInt(1));
			
			}
			sites.close();
			codePosts = statement.executeQuery("SELECT DISTINCT cp FROM sopra.adresse;");
			while(codePosts.next()){
				codes.add(codePosts.getString(1));
				System.out.println("###DEBUG ### (AdminDataBaseAccess, getRidesRepartition) : "+codePosts.getInt(1));
			}
			codePosts.close();
			
			//Continuer ici ! 
			
			
			
			
			
		}catch (Exception e){
			throw new RequestDidNotWork("Respartission des rides a échoué.");
		}finally{
			Close(sites, statement, connexion);
		}
		return res;
	}


	
}
