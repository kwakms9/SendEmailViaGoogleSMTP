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
   public JButton login = new JButton("�α���");
   boolean trylogin =false;

   public LoginFrame() {

     // login.setPreferredSize(new Dimension(500, 25));
      JLabel label1 = new JLabel("�̸���");
      JLabel label2 = new JLabel("��й�ȣ");
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
		if(!trylogin) {//���ӵ������� �ʰ�
        	 trylogin=true;
	    	 login.setEnabled(false);
	    	 stateMessage.setText("�α��� �õ���...");
	    	 
	    	 SendEmailExample mail = new SendEmailExample();
	    	 mail.guimode=true;
	    	 mail.setUsername(usernameText.getText());
	    	 mail.setPassword(passwordText.getText());
	    	 //System.out.println(mail.getUsername()+mail.getPassword());
	    	 
	    	 if(usernameText.getText().equals("")) {	//�α��� ���� �˻�
	    		 JOptionPane.showMessageDialog(null, "�̸����� �Է��ϼ���.");
	    	 }else if( passwordText.getText().equals("")) {
	    		 JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է��ϼ���.");
	    	 }else {
	    		 mail.login();
	    	 }
	    	 
	    	 if(mail.success==true) {
	    	  new SenderMenu(mail);
	    	  dispose();
	    	 }else {
	    	  JOptionPane.showMessageDialog(null, "�α���  ����!");
	    	  stateMessage.setText("�α��ν���! ���̵�� ��й�ȣ�� Ȯ���غ�����.");
	    	  login.setEnabled(true);
	    	 }
    	 } trylogin=false;

	}

   public static void main(String[] args) {
      // TODO Auto-generated method stub
	   if(true) { //�ָܼ��
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
		        		mail.writeMessage();
		        		break;
		        	case 3:
		        		mail.logout();
		        		break;
		        	case 4:
		        		return;
		        	default:
		        		System.out.println("�߸��Է��Ͽ����ϴ�.");
		        }
	        }
	   }else {
		   new LoginFrame();	//GUI���
		   }
	   }

}