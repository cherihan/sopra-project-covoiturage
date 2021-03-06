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
					// c'est un admin donc on cr�e un admin
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
	public HashMap<PostCode,HashMap<Service,Integer>> getRidesRepartition() throws RequestDidNotWork{
		Connection connexion = null;
		Statement statement = null;	
		ResultSet sites= null;
		ResultSet codePosts= null;
		ArrayList<Service> sitesID = new ArrayList<Service>();
		ArrayList<PostCode> codes = new ArrayList<PostCode>();
		HashMap<PostCode,HashMap<Service,Integer>> res = new HashMap<PostCode,HashMap<Service,Integer>>();  
		try {
			
			connexion = Connexion();
			statement = connexion.createStatement();				
			sites = statement.executeQuery("SELECT id FROM sopra.sopra_site ;");	
			
			while(sites.next()){
				sitesID.add(new Service(sites.getInt(1)));
				//System.out.println("###DEBUG ### (AdminDataBaseAccess, getRidesRepartition) : "+sites.getInt(1));			
			}
			sites.close();
			codePosts = statement.executeQuery("SELECT DISTINCT cp FROM sopra.adresse;");
			while(codePosts.next()){
				codes.add(new PostCode(Integer.parseInt(codePosts.getString(1))));
				
			}
			codePosts.close();
			
			//Continuer ici ! 
			
			
			for (int c = 0;c<codes.size() ;c++){
				HashMap<Service, Integer> intermed = new HashMap<Service, Integer>();
				for(int s = 0; s<sitesID.size(); s++){
					
					System.out.println("###DEBUG ### (AdminDataBaseAccess, getRidesRepartition) : "
					+this.requestCountRides(codes.get(c), sitesID.get(s)));
					int count = this.requestCountRides(codes.get(c), sitesID.get(s));
					intermed.put(sitesID.get(s), count );
					
				}
				res.put(codes.get(c), intermed);
			}
			
		}catch (Exception e){
			throw new RequestDidNotWork("Respartission des rides a �chou�.");
		}finally{
		
			Close(sites, statement, connexion);
		}
		return res;
	}
	
	////////////compter selon le code postal et le site sopra (list de cp et de site sopra) ////////

	public int requestCountRides (PostCode cp, Service s) throws RequestDidNotWork {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		int count = -1;
		try{
			connexion = Connexion();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT COUNT(*) FROM rides,adresse "
					+ "WHERE cp='"+cp.toString()+"' "
					+ "AND sopra_site='"+s.getId()+"' "
					+ "AND rides.adresse=adresse.id");
			resultat.next();

			count = resultat.getInt(1);


		}catch(Exception e){
			System.err.println("Erreur compte ride : " + e);
			throw new RequestDidNotWork("Could not count the rides : "+e);
		}finally{
			Close(resultat, statement, connexion);
		}
		return count;
	}


	
}
