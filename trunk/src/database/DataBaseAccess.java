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
	private Connection Connexion() {
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
	private void Close(ResultSet resultat, Statement statement,
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
			resultat = statement.executeQuery("SELECT COUNT(1) " + "FROM user "
					+ "WHERE mail='" + mail+"'"
					+ "AND password='"+pass+"'");
			resultat.next();
			if (resultat.getInt(1) == 1) {
				// vérifier si l'utilisateur est admin ou pas
				resultat = statement.executeQuery("SELECT * FROM sopra.user WHERE mail='"+ mail+"'");
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

	// ///////////// RequestRides ///////////////////

	public ArrayList<Ride> requestRides(String userMail) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		Ride ride;
		User user;
		Adresse home;
		Service service;
		ArrayList<Ride> rides = new ArrayList<Ride>();

		try {
			// connexion
			connexion = Connexion();
			statement = connexion.createStatement();
			// sélectionner les trajets de l'utilisateur
			resultat = statement
					.executeQuery("SELECT user.id,rides.id,rue,ville,cp,sopra_site.name,jours.name"
							+ "FROM user,rides,adresse,sopra_site,jours" + 
							"WHERE mail=" + userMail
							+ "AND rides.adresse=adresse.id"
							+ "AND rides.sopra_site=sopra_site.id"
							+ "AND user.id=rides.id_user"
							+ "AND jour=jours.id");
			// sélectionner les trajets qui correspondent (càd mêmes
			// adresse,site,jour et sens)
			resultat = statement
					.executeQuery("SELECT lastname,firstname,bio,mail,phone,rue,ville,cp,sopra_site.name,jours.name,heure"
							+ "FROM user,rides,adresse,sopra_site,jours"
							/////////////reprendre ici//////////////////////
							+ "WHERE cp="
							+ resultat.getInt("cp")
							+ "AND sopra_site="
							+ resultat.getString("sopra_site")
							+ "AND jour ="
							+ resultat.getString("jour")
							+ "AND sens ="
							+ resultat.getInt("sens"));
			// tant qu'il reste des lignes dans le tableau de résultat
			while (resultat.next()) {
				// prendre les valeurs de la ligne
				int id = resultat.getInt(1);
				String lastName = resultat.getString("lastname");
				String firstName = resultat.getString("firstname");
				String email = resultat.getString("mail");
				String bio = resultat.getString("bio");
				int cp = resultat.getInt("cp");
				String site = resultat.getString("sopra_site");
				String heure = resultat.getString("heure");
				String numPhone = resultat.getString("phone");
				
				EmailAdresse mail = new EmailAdresse(email);
				NumeroTelephone phone = new NumeroTelephone(numPhone);
				boolean sens = false;
				if (resultat.getInt("sens") == 0) {
					sens = false;
				} else if (resultat.getInt("sens") == 1) {
					sens = true;
				}
				PostCode code = new PostCode(cp);
				// créer un utilisateur
				user = new User(id, lastName, firstName, mail, bio,phone);
				// créer adresse
				home = new Adresse(code);
				// créer service
				service = new Service();
				// créer un trajet
				//ride = new Ride(user, home, service, heure, sens);
				// mettre le trajet dans le tableau de trajets
				//rides.add(ride);
			}
			Close(resultat, statement, connexion);
		} catch (Exception e) {
			System.err.println("Erreur requête trajets : " + e);
			return null;
		} finally {
			Close(resultat, statement, connexion);
		}
		return rides;
	}

	//metre tout les champs (ex : AdressEmail email)
	//je veux recevoir l'ID du met
	public int newAccount(User user, MotDePass pass) throws RequestDidNotWork{
		Connection connexion = null;
		Statement statement = null;
		
		String lastName = user.getLastName();
		String firstName = user.getFirstName();
		String email = user.getEmail().toString();
		String bio = user.getBio();
		String phone = user.getTel().ToString();
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
	
	//set rides of on user (ArrayList<Rides>)
	//tu dois avoir un tableau de rides avec au max 5 rides
	//tu ajoute chaqu'un des rides dans la data base (un par un je supose)
	
	public void addUserRides(ArrayList<Ride> rides) throws RequestDidNotWork{
		Connection connexion = null;
		Statement statement = null;
		
		RideAddapter adapter = new RideAddapter();
		
		
		
		ResultSet res;
		int id = -1;
		
		int resultat; 
		try {
			connexion = Connexion();
			statement = connexion.createStatement();
			int adressID;
			
			//on suprime tous les rides du user
			
			
			for (int i =0 ; i < rides.size(); i++){
				
				Ride ride = rides.get(i);
				HashMap <String, String> strings = adapter.adaptRideToHashMap(ride);
				
				
				System.out.println("###DEBUG ### (DataBaseAccess, addUserRides) : "+strings.get("homeCP")+" "
						+strings.get("homeRue")+" "+strings.get("homeVille"));
				//check the address and get the id
				//add the adress les infos sont dans la hash map. 
				res = statement.executeQuery("SELECT id FROM adresse WHERE rue ='"+strings.get("homeRue")
						+"' and '"+strings.get("homeCP")+"' and '"
						+strings.get("homeVille"));
				System.out.println("###DEBUG ### (DataBaseAccess, addUserRides) : "+res);
				if( res == null){//si on a pas l'adresse dans la base rentre					
					//res = statement.executeQuery("INSERT INTO adresse  values(null,'"+strings.get("homeRue")+"','"+strings.get("homeCP")+"','"+strings.get("homeCP")+"')");
				}
				//res.next();
				//adressID = res.getInt(1);
				
				//INSERT INTO sopra.adresse  values(null,'ecole','Pibrac','31200') ;
				
				//il me faut la la ligne address vill et code postal. 
				
				//identifier le site sopra
				
				
				
				if(ride.getId() == -1){//le ride n'été pas se dans la base à la base
					
					int userId = ride.getUser().getID();
					//ajouter le ride dans le base
					
					
					//inseré dans la base de donné le nouveau ride 
					
				}else{
					//modifier la table
					
				}
				
				
			}
			
			


		} catch (Exception e) {
			System.err.println("Erreur requête trajets : " + e);
			throw new RequestDidNotWork("New account could not be added.");
		} finally {
			Close(null, statement, connexion);
		}
		
		
		
		
	}
	
	
	
	
	

	// ///////////////////////// TEST TEST TEST TEST
	// //////////////////////////////////////

	private List<String> messages = new ArrayList<String>();

	public List<String> executerTests() {
		/* Chargement du driver JDBC pour MySQL */
		try {
			messages.add("Chargement du driver...");
			Class.forName("com.mysql.jdbc.Driver");
			messages.add("Driver chargé !");
		} catch (ClassNotFoundException e) {
			messages.add("Erreur lors du chargement : le driver n'a pas été trouvé dans le classpath ! <br/>"
					+ e.getMessage());
		}

		/* Connexion à la base de données */
		String url = "jdbc:mysql://localhost/sopra";
		String utilisateur = "root";
		String motDePasse = "soprabg31";
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		try {
			messages.add("Connexion à la base de données...");
			connexion = DriverManager.getConnection(url, utilisateur,
					motDePasse);
			messages.add("Connexion réussie !");

			/* Création de l'objet gérant les requêtes */
			statement = connexion.createStatement();
			messages.add("Objet requête créé !");

			/* Exécution d'une requête de lecture */
			resultat = statement
					.executeQuery("SELECT cp, ville, Adresse FROM rides;");
			messages.add("Requête \"SELECT cp, ville, Adresse FROM rides;\" effectuée !");

			/* Récupération des données du résultat de la requête de lecture */
			while (resultat.next()) {
				int cp = resultat.getInt("cp");
				String ville = resultat.getString("ville");
				String Adresse = resultat.getString("Adresse");
				/* Formatage des données pour affichage dans la JSP finale. */
				messages.add("Données retournées par la requête : cp = " + cp
						+ ", ville = " + ville + ", adresse = " + Adresse);
			}
		} catch (SQLException e) {
			messages.add("Erreur lors de la connexion : <br/>" + e.getMessage());
		} finally {
			messages.add("Fermeture de l'objet ResultSet.");
			if (resultat != null) {
				try {
					resultat.close();
				} catch (SQLException ignore) {
				}
			}
			messages.add("Fermeture de l'objet Statement.");
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException ignore) {
				}
			}
			messages.add("Fermeture de l'objet Connection.");
			if (connexion != null) {
				try {
					connexion.close();
				} catch (SQLException ignore) {
				}
			}
		}

		return messages;
	}

}