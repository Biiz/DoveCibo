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
 * @author IO-PC
 */
public class SendEmail_Attivazione {
    public SendEmail_Attivazione(String name, String nick, String psw, String email) {
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
            msg.setSubject("DoveCibo attivazione");
            msg.setText("Ciao, "+ name + "\nti sei registrato a DoveCibo il " + new Date()
                    + "\n\nI tuoi dati sono:\nNickname: " + nick + "\nPassword: " + psw + "\n\nCollegati a http://localhost:8084/DoveCiboGit/home.jsp e cambia la password!\n");
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