

package Mail;


import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import model.Adresse;
import model.Heure;
import model.JourDeLaSemaine;
import model.Service;
import model.User;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;

public class mail extends Thread {

	String destinataire;
	String objet;
	String texte;
	String from = "melinamanolias@gmail.com";
	String password ="31101992";
	String host ="smtp.gmail.com";

	public mail(String destinataire, String objet, String texte) {
		this.destinataire=destinataire;
		this.objet=objet;
		this.texte=texte;

	}


	public void sendMail() {

		// L'adresse IP de votre serveur SMTP 
		String smtpServer = "";

		Properties props = new Properties(); 
		props.setProperty("mail.transport.protocol", "smtp");

		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		props.put("mail.smtp.host", smtpServer);


		// Session encapsule pour un client donné sa connexion avec le serveur de mails
		javax.mail.Session session = javax.mail.Session.getDefaultInstance(props, null);

		// Création du message
		MimeMessage message = new MimeMessage(session);

		try {
			//	message.setFrom(new InternetAddress(from));
			message.setSender(new InternetAddress(from));
			message.setRecipients(MimeMessage.RecipientType.TO,InternetAddress.parse(this.destinataire, false));
			message.setSubject(objet);
			message.setText(texte);
			message.setHeader("X-Mailer", "LOTONtechEmail");
			Transport tr = session.getTransport();
			tr.connect(host,from, password);
			tr.sendMessage(message, InternetAddress.parse(destinataire));
			// tr.sendMessage(message, message.getRecipients(MimeMessage.RecipientType.TO));
			System.out.println("Mail Sent Successfully");
			tr.close();

		} catch (javax.mail.MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	} 

	public static void main (String [] args) {
		String destinataire = new String("melinamanolias@gmail.com");
		String objet = new String();
		String texte = new String();

		ArrayList<String> liste = new ArrayList<String> ();
// créer un ride 
		//Ride ride = new Ride(int id,User conducteur, Adresse home, Service office,JourDeLaSemaine jour, 
			//	Heure hour, Boolean sens, String com);
		
		// Construction du texte à envoyer dans le mail 
		/* mode : quand un utilisateur vient de poster un trajet : postRide ou qd un utilisateur 
		pourrait etre intéressé par un trajet : alertRide */
		String mode = new String("postRide");

		texteMail m = new texteMail();
		//a l'indice 0 se trouve l'objet du mail
		objet = (m.constructionTexte(destinataire, mode)).get(0);
		//a l'indice 1 se trouve l'objet du mail
		
		texte = (m.constructionTexte(destinataire, mode)).get(1);
		
		
		// Envoi du mail
		mail mail = new mail(destinataire,objet,texte);
		mail.sendMail();

	}
}



