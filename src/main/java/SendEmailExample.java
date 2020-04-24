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
public class SendEmailExample { //뒤로가기를 전체 메소드들 마다 if문을 두고 변수로 감지되면 모두 리턴되도록 하는 것 예상해보기
    private String username = null; // null 이면 logout 상태
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
	        System.out.println("사용하실 옵션을 선택해 주십시오.");
	        System.out.println("=========================");
	        System.out.println("---메일작성 후 전송직전 로그인가능---");
	        System.out.println("1.로그인");
	        System.out.println("2.메일 작성");
	        System.out.println("3.메일 다중전송");
	        System.out.println("4.로그아웃");
	        System.out.println("5.종료");
	        System.out.println("========================="); 
	        System.out.printf("입력: ");
	        
        }
    public void login() {
    	do {
    		username=checkId();
	    	System.out.printf("비밀번호를 입력하시오: ");
	    	password = scan.next();
	    	clearScreen();	//화면 올리기
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
    		System.out.printf("구글 이메일을 입력하시오: ");
        	username = scan.next();
    		tmp = username.split("@");
    		
    		try {	//split에서 @가 없을경우 검사
    			tmp[1].equalsIgnoreCase("gmail.com");
    		}catch(Exception e) {
    			System.out.println("이메일이 아닙니다.");
    			continue;
    		}
	    	if(tmp[1].equalsIgnoreCase("gmail.com")) {	//포털 주소 확인
	    		username = tmp[0]+"@gmail.com";
	    		break;
	    	}else {
	    		System.out.println("사용할 수 없는 이메일주소입니다.");
	    		System.out.println("입력된 포털주소: "+tmp[1]);
	    	}
    	}
    	return username;
    }
    public void tryLogin() {
    	String receiver;
    	receiver = "test@nonononononono.test";	//없는 이메일
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
        	System.out.println("로그인 실패!\n 아이디와 비밀번호를 다시 확인해 보십시오.");
        	success = false;
            //e.printStackTrace();
        } catch(Exception e) {
        	System.out.println("메일 전송중 에러 발생!");
        	success = false;
        }
    }
    	
    public static void main(String[] args){ 
    	SendEmailExample mail = new SendEmailExample();
    	Scanner scan = new Scanner(System.in);
        String select;
        boolean act = true;
        
        while(act) {
	        mail.menu();	//메뉴 출력
	    	select = scan.next();    
	    	
	    	try {	Integer.parseInt(select);	}	//숫자 아닌값 확인
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
	        		System.out.println("잘못입력하였습니다.");
	                        	
	        }
        }/*
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
        }*/
    }
}
