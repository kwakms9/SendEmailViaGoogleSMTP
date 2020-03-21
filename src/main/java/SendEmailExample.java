import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author yuchlin on 3/21/20
 */
public class SendEmailExample {
    public static void main(String[] args){

        try{

            final String username = "your gmail";
            final String password = "your gmail credential";

            Properties prop = new Properties();
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true"); //TLS

            Session session = Session.getInstance(prop,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("NotUsed@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.BCC,
                    InternetAddress.parse("someone@gmail.com")
            );
            message.setSubject("Your email title");



            message.setContent("Hi", "text/html");

            Transport.send(message);

            System.out.println("Email sent.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
