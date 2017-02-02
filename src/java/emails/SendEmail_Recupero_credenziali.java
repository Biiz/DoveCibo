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
 *
 * @author biiz
 */
public class SendEmail_Recupero_credenziali {

    /**
     * Invia mail per il recupero delle credenziali
     *
     * @param name
     * @param surname
     * @param email
     * @param nick
     * @param psw
     */
    public SendEmail_Recupero_credenziali(String name, String surname, String email, String nick, String psw) {
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
            msg.setSubject("DoveCibo recupero credenziali");
            msg.setText("Ciao, "+ name + "\nla richiesta di recupero credenziali è avvenuta il " + new Date()
                    + "\n\nI tuoi dati di accesso sono:\nNickname: " + nick + "\nPassword: " + psw + "\n\n");
            msg.setSentDate(new Date());

            //We create the transport object to actually send the e-mail
            Transport transport = session.getTransport("smtps");
            transport.connect ("smtp.gmail.com", 465, username, password);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
