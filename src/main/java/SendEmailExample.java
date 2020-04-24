import javax.mail.AuthenticationFailedException;
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
public class SendEmailExample { //�ڷΰ��⸦ ��ü �޼ҵ�� ���� if���� �ΰ� ������ �����Ǹ� ��� ���ϵǵ��� �ϴ� �� �����غ���
    private String username = null; // null �̸� logout ����
    private String password = null;
    //String receiver;
    //String title = "";
   // String content = "";
    String yesorno;
	Scanner scan = new Scanner(System.in);
	boolean success = false;
    SendEmailExample(){
    }
    
	
	
    public static void clearScreen() {
        for (int i = 0; i < 80; i++)
          System.out.println("");
      }
    
    public void menu() {
	        System.out.println("����Ͻ� �ɼ��� ������ �ֽʽÿ�.");
	        System.out.println("=========================");
	        System.out.println("---�����ۼ� �� �������� �α��ΰ���---");
	        System.out.println("1.�α���");
	        System.out.println("2.���� �ۼ�");
	        System.out.println("3.���� ��������");
	        System.out.println("4.�α׾ƿ�");
	        System.out.println("5.����");
	        System.out.println("========================="); 
	        System.out.printf("�Է�: ");
	        
        }
    public void login() {
    	do {
    		username=checkId();
	    	System.out.printf("��й�ȣ�� �Է��Ͻÿ�: ");
	    	password = scan.next();
	    	clearScreen();	//ȭ�� �ø���
	    	tryLogin();
	    	if(success = true) {
	    		break;
	    	}
    	}while(true);
    }
    
    public void logout() {
    	username = null;
    	password = null;
    }
    
    public String checkId() {
    	String tmp[] = new String[2];
    	
    	while(true) {
    		System.out.printf("���� �̸����� �Է��Ͻÿ�: ");
        	username = scan.next();
    		tmp = username.split("@");
    		
    		try {	//split���� @�� ������� �˻�
    			tmp[1].equalsIgnoreCase("gmail.com");
    		}catch(Exception e) {
    			System.out.println("�̸����� �ƴմϴ�.");
    			continue;
    		}
	    	if(tmp[1].equalsIgnoreCase("gmail.com")) {	//���� �ּ� Ȯ��
	    		username = tmp[0]+"@gmail.com";
	    		break;
	    	}else {
	    		System.out.println("����� �� ���� �̸����ּ��Դϴ�.");
	    		System.out.println("�Էµ� �����ּ�: "+tmp[1]);
	    	}
    	}
    	return username;
    }
    public void tryLogin() {
    	String receiver;
    	receiver = "test@nonononononono.test";	//���� �̸���
    	//System.out.println(username+password);
    	sender(receiver,"","");
    	
    }
    
    public void sender(String receiver, String title, String content) {
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
            success = true;

        } catch (AuthenticationFailedException e) {
        	System.out.println("�α��� ����!\n ���̵�� ��й�ȣ�� �ٽ� Ȯ���� ���ʽÿ�.");
        	success = false;
            //e.printStackTrace();
        } catch(Exception e) {
        	System.out.println("���� ������ ���� �߻�!");
        	success = false;
        }
    }
    	
    public static void main(String[] args){ 
    	SendEmailExample mail = new SendEmailExample();
    	Scanner scan = new Scanner(System.in);
        String select;
        boolean act = true;
        
        while(act) {
	        mail.menu();	//�޴� ���
	    	select = scan.next();    
	    	
	    	try {	Integer.parseInt(select);	}	//���� �ƴѰ� Ȯ��
	    	catch(Exception e){ select = "0"; }
	    	
	        switch(Integer.parseInt(select)) {
	        	case 1:
	        		mail.login();
	        		break;
	        	case 2:
	        		
	        		break;
	        	case 3:
	        		break;
	        	case 4:
	        		mail.logout();
	        		break;
	        	case 5:
	        		act=false;
	        		break;
	        	default:
	        		System.out.println("�߸��Է��Ͽ����ϴ�.");
	                        	
	        }
        }/*
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
        }*/
    }
}
