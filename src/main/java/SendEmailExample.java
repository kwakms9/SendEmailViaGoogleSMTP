import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;

/**
 * @author yuchlin on 3/21/20
 */
public class SendEmailExample {
    public static void main(String[] args){ 
 
        Scanner scan = new Scanner(System.in);
        String username;
        String password;
        String receiver;
        String title = null;
        String content = null;
        String yesorno;
        
        
        System.out.println("구글이메일을 입력하시오: ");
        username = scan.next();
        System.out.println("비밀번호를 입력하시오: ");
        password = scan.next();
        clearScreen();
        System.out.println("보낼사람의 이메일 주소를 입력하시오: ");
        receiver = scan.next();
        scan.nextLine();
        while(true) {
        	System.out.println("제목을 입력하시오: ");
        	title = scan.nextLine();
			System.out.println("내용을 입력하시오: ");
			content = scan.nextLine();
    		System.out.println("전송하시겠습니까?(Y:전송 N:다시입력):");
    		yesorno = scan.next();
    		if(yesorno.equalsIgnoreCase("Y")) {
    			System.out.println("메일 전송중...");
    			break;
    		}
        }
        
        try{

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
                    InternetAddress.parse(receiver)
            );
            message.setSubject(title);



            message.setContent(content, "text/html;charset=UTF-8");

            Transport.send(message);

            System.out.println("Email sent.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void clearScreen() {
        for (int i = 0; i < 80; i++)
          System.out.println("");
      }
}
