import javax.mail.AuthenticationFailedException;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;


public class SendEmailExample {
    private String username = null; // null �̸� logout ����
    private String password = null;
    String yesorno;
	Scanner scan = new Scanner(System.in);
	boolean success = false;
	boolean guimode = false;
    SendEmailExample(){
    }
    
	
	
    public void clearScreen() {
        for (int i = 0; i < 80; i++)
          System.out.println("");
      }
    
    public void menu() {
	        System.out.println("����Ͻ� �ɼ��� ������ �ֽʽÿ�.");
	        System.out.println("=========================");
	        if(username==null) { System.out.println("1.�α���"); }
	        else { System.out.println("1.�ٸ��������� �α���"); }
	        System.out.println("2.���� �ۼ�");
	        System.out.println("3.�α׾ƿ�");
	        System.out.println("4.����");
	        System.out.println("========================="); 
	        System.out.printf("�Է�: ");
	        
        }
    public void login() {
    	do {
    		username=checkId();
    		if(guimode==false) {
		    	System.out.printf("��й�ȣ�� �Է��Ͻÿ�: ");
		    	password = scan.next();
		    	clearScreen();	//ȭ�� �ø���
    		}
	    	tryLogin();
	    	if(success == true) {
	    		break;
	    	}
    	}while(!guimode);
    }
    
    public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public void logout() {
    	if(username!=null) {
    		username = null;
    		password = null;
    	}else {
    		System.out.println("���� �α׾ƿ� �����Դϴ�.");
    		System.out.println("");
    	}
    }
    
    public String checkId() {
    	String tmp[] = new String[2];
    	
    	do {
    		if(guimode==false) {
    			System.out.printf("���� �̸����� �Է��Ͻÿ�: ");
    			username = scan.next();
    			}
        	
        	if(!username.contains("@")) {
        		username= username+"@gmail.com";
        	}
        	tmp = username.split("@");
    		
	    	if(tmp[1].equalsIgnoreCase("gmail.com")) {	//���� �ּ� Ȯ��
	    		username = tmp[0]+"@gmail.com";
	    		break;
	    	}else {
	    		System.out.println("����� �� ���� �̸����ּ��Դϴ�.");
	    		System.out.println("�Էµ� �����ּ�: "+tmp[1]);
	    	}
    	}while(!guimode);
    	return username;
    }
    public void tryLogin() {
    	String receiver;
    	receiver = "test@lagsixtome.com";	//���� �̸���
    	sender(receiver,"","");
    	if(success) {
    		System.out.println("�α��� ����");
    	}
    	
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

            //System.out.println("Email sent.");
            success = true;

        } catch (AuthenticationFailedException e) {
        	System.out.println("�α��� ����!\n���̵�� ��й�ȣ�� �ٽ� Ȯ���� ���ʽÿ�.");
        	success = false;
            //e.printStackTrace();
        } catch(Exception e) {
        	System.out.println("���� ������ ���� �߻�!");
        	success = false;
        }
    }

    public void writeMessage() {
    	String title;
    	String content;
    	
    	if(username!=null) {
	    	while(true) {
		    	System.out.printf("��������� �̸��� �ּҸ� �Է��Ͻʽÿ�.\n: ");
		    	scan.nextLine();
		    	String receiver [] = scan.nextLine().replace(" ", "").split(","); //�̸��� �ּҵ� �Է�
		    	
		    	System.out.printf("������ ������ �Է��Ͻʽÿ�.\n: ");
		    	title = scan.nextLine();
		    	System.out.println("������ �Է��Ͻÿ�.\n: ");
				content = scan.nextLine();
				System.out.println("�����Ͻðڽ��ϱ�?(Y:���� N:���):");
				yesorno = scan.next();
				if(yesorno.equalsIgnoreCase("Y") || yesorno.contentEquals("����")) {
					System.out.println("���� ������...");
					for(int i=0;i<receiver.length;i++) {
						System.out.println(receiver[i]);
						sender(receiver[i],title,content);
					}
					System.out.println("��� ������ �߼��Ͽ����ϴ�. �� �����ðڽ��ϱ�?(Y:���� N:���ư���)");
					yesorno = scan.next();
					if(!(yesorno.equalsIgnoreCase("Y") || yesorno.contentEquals("����"))) { break; }
					
				}else {
					System.out.println("���� ������ �ߴܵǾ����ϴ�. ó������ �̵�");
				}
	    	}
    	}else {
    		System.out.println("���� �α��� ���°� �ƴմϴ�.");
    		System.out.println("");
    	}
    }
    
    public String guiMessage(String receiverarry, String title, String content) {
    	String errreceiver="";
    	String receiver [] = receiverarry.replace(" ", "").split(","); //�̸��� �ּҵ� �Է�
		System.out.println("���� ������...");
		
		for(int i=0;i<receiver.length;i++) {
			sender(receiver[i],title,content);	//����
			if(success==false) {
				errreceiver+=receiver[i]+" ";
			}
		}
	    	return errreceiver;
    }
}
