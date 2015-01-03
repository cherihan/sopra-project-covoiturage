package database;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.*;

public class DataBaseAccess implements DatabaseInterface{
	
	//ici alex implémentes tous les signaux envoyer par Mélina
	//impléménattion ! 
	
	
	//Connexion à la db
	private Connection Connexion(){
		try {    
		    String url = "jdbc:mysql://localhost/sopra";
		    String utilisateur = "root";
		    String motDePasse = "soprabg31";
	        Class.forName( "com.mysql.jdbc.Driver" );
	        Connection connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
	        return connexion;
	    } catch ( Exception e ) {
	    	System.err.println("Erreur connexion : "+e);
	    	return null;
	    }
		
	}
	
	//fermeture connexion etc.
	private void Close(ResultSet resultat, Statement statement, Connection connexion){
		if (resultat != null) {
			try {
				resultat.close();
			} catch (Exception e) {}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (Exception e) {}
		}
		if (connexion != null) {
			try {
				connexion.close();
			} catch (Exception e) {}
		}
	}
	
	public int requestUserIsRegistered(String username, String password){
		
		Connection connexion=null;
		Statement statement=null;
		ResultSet resultat=null;
		int isRegistered;
		
		try{
		//connexion
		connexion = Connexion();
		statement = connexion.createStatement();
		//chercher si l'utilisateur existe
		resultat = statement.executeQuery(
			"SELECT COUNT(1) FROM user WHERE nickname="+username+" AND password="+password );
		if(resultat.getInt(1) == 1){
			//vérifier si l'utilisateur est admin ou pas
			resultat = statement.executeQuery(
				"SELECT nickname,isAdmin FROM user WHERE nickname="+username+" AND password="+password);
			if(resultat.getInt("isAdmin") == 1){
				//c'est un admin
				isRegistered = 2;
			}else{
				//c'est un user lambda
				isRegistered = 1;
			}
			
		}else{
			//le mec/la gente demoiselle n'existe pas
			isRegistered = 0;	
		}
		return isRegistered;
		}catch (Exception e){
			System.err.println("Erreur requête utilisateur enregistré : "+e);
			return -1;
		} finally {
			Close(resultat, statement, connexion);
		}
	}
	
	public ArrayList<Ride> requestUserRides(String username){
		Connection connexion=null;
		Statement statement=null;
		ResultSet resultat=null;
		Ride ride;
		ArrayList<Ride> rides = new ArrayList<Ride>();
		
		try{
			//connexion
		connexion = Connexion();
		statement = connexion.createStatement();
		//sélectionner l'utilisateur puis ses trajets
		resultat = statement.executeQuery(
			"SELECT id FROM user WHERE nickname="+username);
		resultat = statement.executeQuery(
				"SELECT id,cp,sopra_site,sens FROM rides WHERE id="+resultat.getInt(1));
		resultat =statement.executeQuery(
				"SELECT cp,sopra_site,sens FROM rides WHERE cp="+resultat.getInt("cp")+"AND sopra_site="+resultat.getString("sopra_site"));
		//tant qu'il reste des lignes dans le tableau de résultat
        while ( resultat.next() ) {
        	//prendre les valeurs de la ligne
            int cp = resultat.getInt("cp");
            String site = resultat.getString( "sopra_site" );
            boolean sens = false;
            if (resultat.getInt("sens") == 0){
            	sens=false;
            }else if (resultat.getInt("sens") == 1){
            	sens=true;
            }            
            PostCode code = new PostCode(cp);
            //"créer" un trajet
            ride = new Ride(code,site,sens);
            //mettre tous les trajets dans un tableau de trajets
            rides.add(ride);
		}
        Close(resultat, statement, connexion);
		}catch(Exception e){
			System.err.println("Erreur requête trajets : "+e);
			return null;
		} finally {
		Close(resultat, statement, connexion);
		}
		return rides;
	}
	
	
	
	
	/////////////////////////// TEST TEST TEST TEST //////////////////////////////////////

		private List<String> messages = new ArrayList<String>();
		
		public List<String> executerTests() {
		    /* Chargement du driver JDBC pour MySQL */
		    try {
		        messages.add( "Chargement du driver..." );
		        Class.forName( "com.mysql.jdbc.Driver" );
		        messages.add( "Driver chargé !" );
		    } catch ( ClassNotFoundException e ) {
		    	messages.add( "Erreur lors du chargement : le driver n'a pas été trouvé dans le classpath ! <br/>"
		                + e.getMessage() );
		    }

		    /* Connexion à la base de données */
		    String url = "jdbc:mysql://localhost/sopra";
		    String utilisateur = "root";
		    String motDePasse = "soprabg31";
		    Connection connexion = null;
		    Statement statement = null;
		    ResultSet resultat = null;
		    try {
		        messages.add( "Connexion à la base de données..." );
		        connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
		        messages.add( "Connexion réussie !" );

		        /* Création de l'objet gérant les requêtes */
		        statement = connexion.createStatement();
		        messages.add( "Objet requête créé !" );

		        /* Exécution d'une requête de lecture */
		        resultat = statement.executeQuery( "SELECT cp, ville, Adresse FROM rides;" );
		        messages.add( "Requête \"SELECT cp, ville, Adresse FROM rides;\" effectuée !" );
		 
		        /* Récupération des données du résultat de la requête de lecture */
		        while ( resultat.next() ) {
		            int cp = resultat.getInt( "cp" );
		            String ville = resultat.getString( "ville" );
		            String Adresse = resultat.getString( "Adresse" );
		            /* Formatage des données pour affichage dans la JSP finale. */
		            messages.add( "Données retournées par la requête : cp = " + cp + ", ville = " + ville
		                    + ", adresse = " + Adresse);
		        }
		    } catch ( SQLException e ) {
		        messages.add( "Erreur lors de la connexion : <br/>"
		                + e.getMessage() );
		    } finally {
		        messages.add( "Fermeture de l'objet ResultSet." );
		        if ( resultat != null ) {
		            try {
		                resultat.close();
		            } catch ( SQLException ignore ) {
		            }
		        }
		        messages.add( "Fermeture de l'objet Statement." );
		        if ( statement != null ) {
		            try {
		                statement.close();
		            } catch ( SQLException ignore ) {
		            }
		        }
		        messages.add( "Fermeture de l'objet Connection." );
		        if ( connexion != null ) {
		            try {
		                connexion.close();
		            } catch ( SQLException ignore ) {
		            }
		        }
		    }

		    return messages;
		}

}