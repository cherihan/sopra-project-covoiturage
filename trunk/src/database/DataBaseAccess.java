package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sun.corba.se.impl.protocol.RequestCanceledException;

import model.*;

public class DataBaseAccess {

	// ici alex implémentes tous les signaux envoyer par Mélina
	// impléménattion !
	

	// Connexion à la db
	protected Connection Connexion() {
		try {
			String url = "jdbc:mysql://localhost/sopra";
			String utilisateur = "root";
			String motDePasse = "soprabg31";
			Class.forName("com.mysql.jdbc.Driver");
			Connection connexion = DriverManager.getConnection(url,
					utilisateur, motDePasse);
			return connexion;
		} catch (Exception e) {
			System.err.println("Erreur connexion : " + e);
			return null;
		}

	}
	

	// fermeture connexion etc.
	protected void Close(ResultSet resultat, Statement statement,
			Connection connexion) {
		if (resultat != null) {
			try {
				resultat.close();
			} catch (Exception e) {
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (Exception e) {
			}
		}
		if (connexion != null) {
			try {
				connexion.close();
			} catch (Exception e) {
			}
		}
	}

	// ///////////// RequestUserIsRegistered ///////////////////

	public User requestUserIsRegistered(EmailAdresse mail, MotDePass pass)
			throws RequestDidNotWork {

		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		int isRegistered;
		User registeredUser;

		try {
			// connexion
			connexion = Connexion();
			statement = connexion.createStatement();
			// chercher si l'utilisateur existe
			String password = pass.getClaire();
			resultat = statement.executeQuery("SELECT COUNT(1) " + "FROM user "
					+ "WHERE mail='" + mail+"'"
					+ "AND password='"+password+"'");
			resultat.next();
			if (resultat.getInt(1) == 1) {
				
				// vérifier si l'utilisateur est admin ou pas
				resultat = statement.executeQuery("SELECT * FROM user WHERE mail='"+ mail+"'");
				resultat.next();
				
				int id = resultat.getInt("id");
				String lastName = resultat.getString("lastname");
				String firstName= resultat.getString("firstname");
				EmailAdresse email = new EmailAdresse(resultat.getString("mail"));
				String bio = resultat.getString("bio");
				NumeroTelephone telNum = new NumeroTelephone(resultat.getString("phone"));
				registeredUser = new User(id, lastName, firstName, email, bio, telNum);

				if (resultat.getInt("isAdmin") == 1) {
					// c'est un admin donc on crée un admin
					registeredUser = new Admin(registeredUser);
					System.out.println("c'est scarface!");
				}

			} else {
				// le mec/la gente demoiselle n'existe pas
				throw new RequestDidNotWork(
						"La personne n'a pas été trouvé dans la dataBase");

			}
			
			return registeredUser;
		} catch (Exception e) {
			System.err.println("Erreur requête utilisateur enregistré : " + e);
			throw new RequestDidNotWork(
					"La personne n'a pas été trouvé dans la dataBase");
		} finally {
			Close(resultat, statement, connexion);
		}
	}

	/////////////// RequestRides ///////////////////

	public ArrayList<Ride> requestMatchingRides(User user) throws RequestDidNotWork {
		Connection connexion = null;
		Statement statement = null;
		Statement statement2 = null;
		Statement statement3 = null;
		ResultSet resultat = null;
		ResultSet resultat2 = null;
		ResultSet resultat3 = null;
		Ride ride;
		Adresse home;
		Service service;
		ArrayList<Ride> rides = new ArrayList<Ride>();
		try {
			// connexion
			connexion = Connexion();
			statement = connexion.createStatement();
			statement2 = connexion.createStatement();
			statement3 = connexion.createStatement();
			// sélectionner les trajets de l'utilisateur
			resultat = statement
					.executeQuery("SELECT *"
							+ " FROM rides,adresse" + 
							" WHERE id_user='" + user.getID()+"'"
							+ " AND adresse=.adresse.id");
			// sélectionner les trajets qui correspondent (càd mêmes
			// adresse,site,jour et sens)
			while(resultat.next()){
			resultat2 = statement2
					.executeQuery("SELECT rides.id,user.id,lastname,firstname,bio,mail,phone,rue,ville,cp,sopra_site.name,description,sopra_site.id,sens,jours.id,jours.name,heure,commentaire,adresse.id"
							+ " FROM user,rides,adresse,sopra_site,jours"
							+ " WHERE cp='"
							+ resultat.getString("cp")
							+ "' AND sopra_site.id='"
							+ resultat.getString("sopra_site")
							+ "' AND jours.id ='"
							+ resultat.getString("jour")
							+ "' AND sens ='"
							+ resultat.getInt("sens")
							+ "' AND user.id != '"+user.getID()
							+ "' AND rides.adresse=adresse.id"
							+ " AND rides.sopra_site=sopra_site.id"
							+ " AND user.id=rides.id_user"
							+ " AND jour=jours.id");
			// tant qu'il reste des lignes dans le tableau de résultat
			while (resultat2.next()) {
				// prendre les valeurs de la ligne
				int id = resultat2.getInt(1);
				int uid = resultat2.getInt(2);
				String lastName = resultat2.getString("lastname");
				String firstName = resultat2.getString("firstname");
				String email = resultat2.getString("mail");
				String bio = resultat2.getString("bio");
				int cp = resultat2.getInt("cp");
				String site = resultat2.getString("name");
				String h = resultat2.getString("heure");
				String numPhone = resultat2.getString("phone");
				String rue = resultat2.getString("rue");
				String ville = resultat2.getString("ville");
				int j = resultat2.getInt(15);
				int id_sopra = resultat2.getInt(13);
				String description = resultat2.getString("description");
				int s = resultat2.getInt("sens");
				int idAddr = resultat2.getInt(19);
				String commentaire = resultat2.getString("commentaire");
				String jname = resultat2.getString(16);
				boolean sens=false;
				if (s==0){
					sens=false;
				}
				else if (s==1){
					sens=true;
				}			
				
				//récupérer adresse du user qui conduit pour construire le ride
				resultat3 = statement3.executeQuery("SELECT rue,ville,cp FROM adresse"
													+ " WHERE id='"+idAddr+"'");
				resultat3.next();
				
				EmailAdresse mail = new EmailAdresse(email);
				NumeroTelephone phone = new NumeroTelephone(numPhone);
				PostCode code = new PostCode(cp);
				JourDeLaSemaine jour = new JourDeLaSemaine(j,jname);
				Heure heure = new Heure(h);
				String uRue = resultat3.getString("rue");
				String uVille = resultat3.getString("ville");
				int uCp = resultat3.getInt("cp");
				PostCode uCode = new PostCode(uCp);
				home = new Adresse(uCode,uRue,uVille);
				Adresse adresse = new Adresse(code,rue,ville);
				service = new Service(id_sopra,site,description,adresse);
				User conducteur = new User(uid,lastName,firstName,mail,bio,phone);
				ride = new Ride(id, conducteur, home, service, jour, heure, sens, commentaire);
				rides.add(ride);
			}
			}
			Close(resultat, statement, connexion);
		} catch (Exception e) {
			System.err.println("Erreur lors de la recherche des trajets correspondants : " + e);
			throw new RequestDidNotWork(
					"Pas de trajet correspondant trouvé dans la database");
		} finally {
			Close(resultat, statement, connexion);
		}
		return rides;
	}
	
	/////////////// RECHERCHER RIDES ////////////////////

	public ArrayList<Ride> requestSearchRides(Adresse a, JourDeLaSemaine j, Service s) throws RequestDidNotWork{
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		Statement statement2 = null;
		ResultSet resultat2 = null;

		ArrayList<Ride> rides = new ArrayList<Ride>();
		try{
			connexion = Connexion();
			statement = connexion.createStatement();
			statement2 = connexion.createStatement();
			//rides avec même cp, même sopra_site et même jour
			resultat = statement.executeQuery("SELECT rides.id,user.id,rue,ville,cp,sopra_site.name,description,sopra_site.id,sens,jours.id,jours.name,heure,commentaire,rides.adresse "
												+ "FROM user,rides,adresse,sopra_site,jours "
												+ "WHERE jour='"+j.getJour()+"' "
												+ "AND cp='"+a.getPostCode().toString()+"' "
												+ "AND sopra_site='"+s.getId()+"' "
												+ "AND rides.jour=jours.id "
												+ "AND sopra_site.adresse=adresse.id "
												+ "AND rides.sopra_site=sopra_site.id "
												+ "AND user.id=rides.id_user;");
			while(resultat.next()){
				int id = resultat.getInt(1);
				int uid = resultat.getInt(2);
				String h = resultat.getString("heure");
				String commentaire = resultat.getString("commentaire");
				int se = resultat.getInt("sens");
				boolean sens=false;
				if (se==0){
					sens=false;
				}
				else if (se==1){
					sens=true;
				}
							
				resultat2 = statement2.executeQuery("SELECT user.*,rue,cp,ville FROM adresse,rides,user "
													+ "WHERE id_user='"+uid+"' "
													+ "AND rides.id='"+id+"' "
													+ "AND rides.id_user=user.id "
													+ "AND adresse.id=rides.adresse ");
				resultat2.next();
				String lastName = resultat2.getString("lastname");
				String firstName = resultat2.getString("firstname");
				String mail = resultat2.getString("mail");
				String bio = resultat2.getString("bio");
				String tel = resultat2.getString("phone");
				String uRue = resultat2.getString("rue");
				String uVille = resultat2.getString("ville");
				EmailAdresse email = new EmailAdresse(mail);
				NumeroTelephone telNum = new NumeroTelephone(tel);
				
				User user = new User(id, lastName, firstName, email, bio, telNum);
				Adresse home = new Adresse(a.getPostCode(), uRue, uVille);
				Heure heure = new Heure(h);			
				Ride ride = new Ride(id, user, home, s, j, heure, sens, commentaire);
				rides.add(ride);
			}

		}catch(Exception e){
			System.err.println("Erreur lors de la recherche des rides : " + e);
			throw new RequestDidNotWork(
					"Pas de ride trouvé");
		}finally{
			Close(resultat, statement, connexion);
			Close(resultat2, statement2, connexion);
		}
		return rides;
	}
	
	
	
////////////////////// JOURS SEMAINE /////////////////////////////////
	
	public ArrayList<JourDeLaSemaine> requestJours() throws RequestDidNotWork {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		
		ArrayList<JourDeLaSemaine> jours = new ArrayList<JourDeLaSemaine>();
		try{
			connexion = Connexion();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT * FROM jours");
			
			while(resultat.next()){
				int id = resultat.getInt(1);
				String nom = resultat.getString(2);
				JourDeLaSemaine jour = new JourDeLaSemaine(id, nom);
				jours.add(jour);
			}
			
		}catch(Exception e){
			System.err.println("Erreur lors de la recherche des JOURS : " + e);
			throw new RequestDidNotWork(
					"Pas de jour trouvé dans la database (dur dur...)");
		}finally{
			Close(resultat, statement, connexion);
		}
		return jours;
	}
	public JourDeLaSemaine getJour(int id) throws RequestDidNotWork{
		JourDeLaSemaine res;
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		
		try{
			connexion = Connexion();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT * FROM jours where id='"+id+"'");
			resultat.next();
			String nom = resultat.getString(2);
			res = new JourDeLaSemaine(id, nom);
			
		}catch(Exception e){
			System.err.println("Erreur lors de la recherche d'un JOURS : " + e);
			throw new RequestDidNotWork("Pas de jour trouvé dans la database (dur dur...)");
		}finally{
			Close(resultat, statement, connexion);
		}
		return res;
	}
	
	/////////////// LISTER SERVICES ////////////////////
	
	public ArrayList<Service> requestServices() throws RequestDidNotWork{
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		
		ArrayList<Service> services = new ArrayList<Service>();
		try{
			connexion = Connexion();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT * FROM sopra_site,adresse "
											+ "WHERE sopra_site.adresse=adresse.id;");
			
			while(resultat.next()){
				int id = resultat.getInt(1);
				String nom = resultat.getString("name");
				String description = resultat.getString("description");
				String rue = resultat.getString("rue");
				String ville = resultat.getString("ville");
				int cp = resultat.getInt("cp");
				PostCode code = new PostCode(cp);
				Adresse addr = new Adresse(code, rue, ville);
				Service service = new Service(id, nom, description, addr);
				services.add(service);
			}
			
		}catch(Exception e){
			System.err.println("Erreur lors de la recherche des services : " + e);
			throw new RequestDidNotWork(
					"Pas de service trouvé dans la database (dur dur...)");
		}finally{
			Close(resultat, statement, connexion);
		}
		return services;
	}
	
	
	
	//////////////////////TRAJETS D'UN UTLISATEUR /////////////////////////////////

	public ArrayList<Ride> requestUserRides(User user) throws RequestDidNotWork {
		Connection connexion = null;
		Statement statement = null;
		Statement statement2 = null;
		ResultSet resultat = null;
		ResultSet resultat2 = null;

		ArrayList<Ride> rides = new ArrayList<Ride>();
		
		int uid = user.getID();
		try{
			connexion = Connexion();
			statement = connexion.createStatement();
			statement2 = connexion.createStatement();
			resultat = statement
					.executeQuery("SELECT rides.id,rue,ville,cp,sopra_site.name,description,sopra_site.id,sens,jours.id,jours.name,heure,commentaire,rides.adresse"
							+ " FROM user,rides,adresse,sopra_site,jours"
							+ " WHERE user.id = '"+uid
							+ "' AND sopra_site.adresse=adresse.id"
							+ " AND rides.sopra_site=sopra_site.id"
							+ " AND user.id=rides.id_user"
							+ " AND jour=jours.id");
			
			while(resultat.next()){
				int id = resultat.getInt(1);
				String site = resultat.getString("name");
				String h = resultat.getString("heure");
				int j = resultat.getInt(9);
				String jname = resultat.getString(10);
				int id_sopra = resultat.getInt(7);
				String description = resultat.getString("description");
				int s = resultat.getInt("sens");
				String commentaire = resultat.getString("commentaire");
				String rue = resultat.getString("rue");
				String ville = resultat.getString("ville");
				int cp = resultat.getInt("cp");
				PostCode code = new PostCode(cp);
				Adresse adresse = new Adresse(code, rue, ville);
				boolean sens=false;
				if (s==0){
					sens=false;
				}
				else if (s==1){
					sens=true;
				}
				
				int idAddr = resultat.getInt(13);
				resultat2 = statement2.executeQuery("SELECT rue,ville,cp FROM adresse,rides,user"
						+ " WHERE rides.id_user=user.id"
						+ " AND adresse.id=rides.adresse"
						+ " AND user.id="+uid
						+ " AND adresse.id='"+idAddr+"'");
				resultat2.next();
				String uRue = resultat2.getString("rue");
				String uVille = resultat2.getString("ville");
				int uCp = resultat2.getInt("cp");
				PostCode uCode = new PostCode(uCp);
				Adresse home = new Adresse(uCode, uRue, uVille);
				JourDeLaSemaine jour = new JourDeLaSemaine(j,jname);
				Heure heure = new Heure(h);
				Service service = new Service(id_sopra,site,description,adresse);
				Ride ride = new Ride(id, user, home, service, jour, heure, sens, commentaire);
				rides.add(ride);
			}

		}catch(Exception e){
			System.err.println("Erreur lors de la recherche des rides du user : " + e);
			throw new RequestDidNotWork(
					"Pas de ride trouvé pour cet utilisateur");
		}finally{
			Close(resultat, statement, connexion);
			Close(resultat2, statement, connexion);
			
		}
		return rides;
	}


	
////////////////// NEW ACCOUNT //////////////////////////////
	public int newAccount(User user, MotDePass pass) throws RequestDidNotWork{
		Connection connexion = null;
		Statement statement = null;
		
		String lastName = user.getLastName();
		String firstName = user.getFirstName();
		String email = user.getEmail().toString();
		String bio = user.getBio();
		String phone = user.getTel().toString();
		String pwd = pass.getClaire();
		ResultSet res;
		int id = -1;
		
		int resultat; 
		try {
			// connexion
			connexion = Connexion();
			statement = connexion.createStatement();
			// insertion de l'utilisateur créé
			res = statement.executeQuery("SELECT COUNT(1) " + "FROM user "
					+ "WHERE mail='" + email+"'");
			res.next();
			if(res.getInt(1) == 0){
			resultat = statement.executeUpdate("INSERT INTO user " + "VALUES ('"
			+lastName+"','" + firstName + "','" + email + "','" + pwd
			+ "','" + phone + "','" + bio + "'," + "NULL,'0')");//never admin when account is created
			res = statement.executeQuery("SELECT id FROM user WHERE mail ='"+email+"'");
			res.next();
			id = res.getInt(1);
			}else{
				throw new RequestDidNotWork("This mail adress is already used, cannot create account");
			}
		} catch (Exception e) {
			System.err.println("Erreur requête trajets : " + e);
			throw new RequestDidNotWork("New account could not be added.");
		} finally {
			Close(null, statement, connexion);
		}
		return id;

	}
	
	///////////////// EDIT PROFILE ///////////////
	
	public void editUserProfile (User user) throws RequestDidNotWork {
		Connection connexion = null;
		Statement statement = null;
		try{
			connexion = Connexion();
			statement = connexion.createStatement();
			statement.executeUpdate("UPDATE user "
					+ "SET lastname='"+user.getLastName()+"', firstname='"+user.getFirstName()+"', "
					+ "mail='"+user.getEmail().toString()+"', "
					+ " phone='"+user.getTel().toString()+"', "
					+ "bio='"+user.getBio()+"' WHERE id='"+user.getID()+"';");
			
		}catch(Exception e){
			System.err.println("Erreur update profil : " + e);
			throw new RequestDidNotWork("Profile could not be updated : "+e);
		}finally{
			Close(null, statement, connexion);
		}
	}
	
	///////////////// EDIT MDP ///////////////

	public void editUserPassword (User user, MotDePass pass) throws RequestDidNotWork {
		Connection connexion = null;
		Statement statement = null;
		try{
			connexion = Connexion();
			statement = connexion.createStatement();
			statement.executeUpdate("UPDATE user "
					+ "SET password='"+pass.getClaire()+"' WHERE id='"+user.getID()+"';");

		}catch(Exception e){
			System.err.println("Erreur update mdp : " + e);
			throw new RequestDidNotWork("Password could not be updated : "+e);
		}finally{
			Close(null, statement, connexion);
		}
	}
	
	/////////////////////////////// DEL rides of user /////////////////////////
	public void deletAllUserRides (User user)throws RequestDidNotWork{
		Connection connexion = null;
		Statement statement = null;
		try {
			connexion = Connexion();
			statement = connexion.createStatement();
			performExecuteDelRides(user, statement);
		}catch (Exception e){
			throw new RequestDidNotWork("Rides could note be deleted : "+e);
		}finally {
			Close(null, statement, connexion);
		}		
	}
	private void performExecuteDelRides(User user,Statement stat) throws RequestDidNotWork{
		try{
			//on ne suprime pas les addresse oui c'est mal
			stat.executeUpdate("delete from rides where id_user='"+user.getID()+"'");	
			
		}catch (Exception e){
			throw new RequestDidNotWork("Rides could note be deleted : "+e);
		}
	}
	
	
	//set rides of a user (ArrayList<Rides>)
	
	public void addUserRides(ArrayList<Ride> rides, User user) throws RequestDidNotWork{
		Connection connexion = null;
		Statement statement = null;
		
		RideAddapter adapter = new RideAddapter();	
				
		ResultSet res = null;
		
		try {
			connexion = Connexion();
			statement = connexion.createStatement();
			int adressID;
			
			//on suprime tous les rides du user
			performExecuteDelRides(user, statement);
			for (int i =0 ; i < rides.size(); i++){
				
				Ride ride = rides.get(i);
				HashMap <String, String> strings = adapter.adaptRideToHashMap(ride);
				//debug 
								
				int update;
				/*System.out.println("###DEBUG ### (DataBaseAccess, addUserRides) : "+strings.get("homeCP")+" "
						+strings.get("homeRue")+" "+strings.get("homeVille"));*/
				//check the address and get the id
				//add the adress les infos sont dans la hash map. 
				res = statement.executeQuery("SELECT COUNT(1) FROM adresse WHERE rue ='"+strings.get("homeRue")
						+"' and cp='"+strings.get("homeCP")+"' and ville='"
						+strings.get("homeVille")+"';");
				res.next();
				if( res.getInt(1) == 0){//si on a pas l'adresse dans la base rentre	
					adressID = statement.executeUpdate("INSERT INTO adresse  values(null,'"+strings.get("homeRue")+"','"
					+strings.get("homeVille")+"','"+strings.get("homeCP")+"')");
				}
				res.close();
				res = statement.executeQuery("SELECT id FROM adresse WHERE rue ='"+strings.get("homeRue")
						+"' and  cp='"+strings.get("homeCP")+"' and ville='"
						+strings.get("homeVille")+"'");
				res.next();
				adressID = res.getInt("id");
				
				
				//check sopra site existe
				//check si jour in bound
				int rideSens = (ride.getSens())? 1 :0;
				
				update = statement.executeUpdate("INSERT INTO rides VALUES(null,'"+adressID+"','"
						+strings.get("heure")+"','"+ride.getOffice().getId()+"','"
						+ ride.getJour().getJour()+"', '"
						+ rideSens+"','"
						+ strings.get("commentaire")+"', '"
						+ride.getUser().getID() +"' )");
			}
		} catch (Exception e) {
			System.err.println("Erreur requête trajets : " + e);
			e.printStackTrace(System.err);
			throw new RequestDidNotWork("New account could not be added.");
		} finally {
			Close(res, statement, connexion);
		}	
	}

}