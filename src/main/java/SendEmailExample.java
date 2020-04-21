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
        
        
        System.out.println("�����̸����� �Է��Ͻÿ�: ");
        username = scan.next();
        System.out.println("��й�ȣ�� �Է��Ͻÿ�: ");
        password = scan.next();
        clearScreen();
        System.out.println("��������� �̸��� �ּҸ� �Է��Ͻÿ�: ");
        receiver = scan.next();
        scan.nextLine();
        while(true) {
        	System.out.println("������ �Է��Ͻÿ�: ");
        	title = scan.nextLine();
			System.out.println("������ �Է��Ͻÿ�: ");
			content = scan.nextLine();
    		System.out.println("�����Ͻðڽ��ϱ�?(Y:���� N:�ٽ��Է�):");
    		yesorno = scan.next();
    		if(yesorno.equalsIgnoreCase("Y")) {
    			System.out.println("���� ������...");
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
