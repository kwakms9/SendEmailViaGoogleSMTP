import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame implements ActionListener, KeyListener {
   public JPanel panel = new JPanel(new GridLayout(0, 2));
   public JTextField usernameText = new JTextField(10);
   public JTextField stateMessage = new JTextField(10);
   public JPasswordField passwordText = new JPasswordField(10);
   public JButton login = new JButton("로그인");
   boolean trylogin =false;

   public LoginFrame() {

     // login.setPreferredSize(new Dimension(500, 25));
      JLabel label1 = new JLabel("이메일");
      JLabel label2 = new JLabel("비밀번호");
      //stateMessage.setEditable(false);
      //stateMessage.setBackground(Color.gray);
      
      panel.add(label1);
      panel.add(usernameText);
      panel.add(label2);
      panel.add(passwordText);

      this.setLayout(new BorderLayout());
      //setSize(500, 200);
      this.setResizable(false);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setVisible(true);
      this.setTitle("Google Email sender");
      
      this.add(panel, BorderLayout.CENTER);
      this.add(login, BorderLayout.EAST);
      this.add(stateMessage, BorderLayout.SOUTH);
      this.pack();
      this.setLocationRelativeTo(null);
      login.addKeyListener(this);
      usernameText.addKeyListener(this);
      passwordText.addKeyListener(this);
      login.addActionListener(this);     
      

   }

   public void keyPressed(KeyEvent e) {
	      if (e.getKeyCode()==KeyEvent.VK_ENTER) {
	    	  loginAction();
	    	  }
   }

   @Override
   public void keyTyped(KeyEvent e) {
      // TODO Auto-generated method stub

   }

   @Override
   public void keyReleased(KeyEvent e) {
      // TODO Auto-generated method stub

   }

   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      if (e.getSource() == login)
      {
    	  loginAction();
      }
    }

	public void loginAction() {
		if(!trylogin) {//연속동작하지 않게
        	 trylogin=true;
	    	 login.setEnabled(false);
	    	 stateMessage.setText("로그인 시도중...");
	    	 
	    	 SendEmailExample mail = new SendEmailExample();
	    	 mail.guimode=true;
	    	 mail.setUsername(usernameText.getText());
	    	 mail.setPassword(passwordText.getText());
	    	 //System.out.println(mail.getUsername()+mail.getPassword());
	    	 
	    	 if(usernameText.getText().equals("")) {	//로그인 조건 검사
	    		 JOptionPane.showMessageDialog(null, "이메일을 입력하세요.");
	    	 }else if( passwordText.getText().equals("")) {
	    		 JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요.");
	    	 }else {
	    		 mail.login();
	    	 }
	    	 
	    	 if(mail.success==true) {
	    	  new SenderMenu(mail);
	    	  dispose();
	    	 }else {
	    	  JOptionPane.showMessageDialog(null, "로그인  실패!");
	    	  stateMessage.setText("로그인실패! 아이디와 비밀번호를 확인해보세요.");
	    	  login.setEnabled(true);
	    	 }
    	 } trylogin=false;

	}

   public static void main(String[] args) {
      // TODO Auto-generated method stub
	   if(true) { //콘솔모드
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
		        		mail.writeMessage();
		        		break;
		        	case 3:
		        		mail.logout();
		        		break;
		        	case 4:
		        		return;
		        	default:
		        		System.out.println("잘못입력하였습니다.");
		        }
	        }
	   }else {
		   new LoginFrame();	//GUI모드
		   }
	   }

}