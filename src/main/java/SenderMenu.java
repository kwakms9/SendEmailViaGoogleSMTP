import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SenderMenu extends JFrame implements ActionListener {
	SendEmailExample mail;
		JPanel panel = new JPanel(null);
		JButton helpbt = new JButton("����");
		JButton comfirmLogin = new JButton("���� ����Ȯ��");
		JButton sendbt = new JButton("���� �ۼ�");
		JButton logoutbt = new JButton("�α׾ƿ�");
		JButton exitbt = new JButton("����");
		
	SenderMenu(SendEmailExample mail){
		this.mail=mail;	
		panel.add(helpbt);
		panel.add(comfirmLogin);
		panel.add(sendbt);
		panel.add(logoutbt);
		panel.add(exitbt);
		this.add(panel);
		this.pack();
		this.setSize(300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setVisible(true);
	    this.setLocationRelativeTo(null);
	    
	    helpbt.addActionListener(this);
	    comfirmLogin.addActionListener(this);
	    sendbt.addActionListener(this);
	    logoutbt.addActionListener(this);
	    exitbt.addActionListener(this);
	    
	    
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == helpbt) {
			JOptionPane.showMessageDialog(null, "�� ���� �������α׷��� �����̿ܿ��� ������ �����ϸ�,\n ���� �ۼ��� ���� ������� ���������� �����մϴ�.");
		}else if(e.getSource()==comfirmLogin) {
			JOptionPane.showMessageDialog(null, "���� ����: "+mail.getUsername());
		}else if(e.getSource()==sendbt) {
			new Sender(mail);
		}else if(e.getSource()==logoutbt) {
			mail.logout();
			JOptionPane.showMessageDialog(null, "�α׾ƿ� �Ϸ�!");
			new LoginFrame();
			dispose();
		}else if(e.getSource()==exitbt){
			System.exit(0);
		}
		
	}
	class Sender extends JFrame implements ActionListener{
		SendEmailExample mail;
		JButton button1 = new JButton("������");
		JTextField receiverText = new JTextField(35);
		JTextField titleText = new JTextField(35);
		JTextArea contentText = new JTextArea(20,35);//y,x��
		
		Sender(SendEmailExample mail){
			this.mail=mail;
			JPanel panel1 = new JPanel();
			JPanel panel2 = new JPanel();
			JPanel panel3 = new JPanel();
			JPanel panel4 = new JPanel(new GridLayout(0, 1));
			JLabel titlelabel = new JLabel("����        ");
			JLabel contentlabel = new JLabel("����        ");
			JLabel receiver = new JLabel("�޴»��",JLabel.LEADING);
			
			JScrollPane scrollPane = new JScrollPane(contentText);
			
			
			receiver.setSize(10, 10);
			//scrollPane.setVerticalScrollBarPolicy(scrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			contentlabel.setVerticalTextPosition(JLabel.CENTER);
			contentText.setLineWrap(true);
			panel1.add(receiver);
			panel1.add(receiverText);
			panel2.add(titlelabel);
			panel2.add(titleText);
			panel3.add(contentlabel);
			panel3.add(scrollPane);
			panel4.add(panel1);
			panel4.add(panel2);
			this.setResizable(false);
			pack();
			
			button1.addActionListener(this);
			this.add(panel4, BorderLayout.NORTH);
			this.add(panel3, BorderLayout.CENTER);
			this.add(button1, BorderLayout.PAGE_END);
			this.setSize(500, 500);
			this.setLocationRelativeTo(null);
		    this.setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==button1) {
				String err;
				err=mail.guiMessage(receiverText.getText(),titleText.getText(),contentText.getText());
				if(!err.equals("")) {
					 JOptionPane.showMessageDialog(null, "���� ���ۿϷ�!\n ������: "+err);
				}else {
					 JOptionPane.showMessageDialog(null, "���� ���ۿϷ�!");
				}
			}
			
		}
	}

}
