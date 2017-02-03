package emails;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Spedisce la mail con una notifica delle modifiche apportate ad un profilo utente
 *
 * @author IO-PC
 */
public class SendEmail_Modifica_Profilo {

    /**
     * Invia mail di avvenuta modifica al profilo
     *
     * @param name nome utente da inviare
     * @param surname cognome utente da inviare
     * @param email indirizzo email utente a cui inviare la mail
     * @param nick nickname utente da inviare
     * @param psw password utente da inviare
     */
    public SendEmail_Modifica_Profilo(String name, String surname, String email, String nick, String psw) {
        try{
            final String username = "DoveCibo@gmail.com";
            final String password = "DoveCibo123";
            // Get a Properties object to set the mailing configuration
            // parameters
            Properties props = System.getProperties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.port", "465");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put( "mail.debug", "true" );
            //We create the session object with the authentication information
            Session session = Session.getDefaultInstance(props, new Authenticator(){
                @Override
                protected PasswordAuthentication
                getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
       
            //Create a new message
            Message msg = new MimeMessage(session);

            //Set the FROM and TO fields –
            msg.setFrom(new InternetAddress(username + ""));
            
            //sostituire la mia email con "email"
            msg.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(email,false));
            msg.setSubject("DoveCibo cambio profilo");
            msg.setText("Ciao, "+ name + "\nla modifica del tuo profilo è avvenuta il " + new Date()
                    + "\n\nI tuoi nuovi dati sono:\nNome: " + name + "\nCognome: " + surname + "\nEmail: " + email + "\nNickname: " + nick + "\nPassword: " + psw+ "\n\n");
            msg.setSentDate(new Date());

            System.out.println("\nTrying to send email...\n");

            //We create the transport object to actually send the e-mail
            Transport transport = session.getTransport("smtps");
            transport.connect ("smtp.gmail.com", 465, username, password);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("\nEmail sent!\n");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
