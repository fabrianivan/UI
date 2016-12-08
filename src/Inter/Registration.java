package Inter;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

class jRegistration extends JFrame implements ItemListener, ActionListener{ 

	JButton btn_ok = new JButton("OK");
	JButton btn_exit = new JButton("CANCEL");
	
	jRegistration(){
		setTitle("Registration");
		setSize(450, 250);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel panel1 = new JPanel(new GridLayout(7, 2));
		JPanel panel2 = new JPanel(new FlowLayout());

		JLabel lbl_username = new JLabel("USERNAME");
		JLabel lbl_password = new JLabel("PASSWORD");	
		JLabel lbl_pin = new JLabel("PIN");
		JLabel lbl_name = new JLabel("FULL NAME");	
		JLabel lbl_gender = new JLabel("GENDER");	
		JLabel lbl_address = new JLabel("ADDRESS");	
		JLabel lbl_phone = new JLabel("PHONE");		
		JTextField txt_username = new JTextField();	
		JTextField txt_password = new JTextField();	
		JTextField txt_pin = new JTextField();	
		JTextField txt_name = new JTextField();		
		JTextField txt_gender = new JTextField();	
		JTextField txt_address = new JTextField();
		JTextField txt_phone = new JTextField();

		panel1.add(lbl_username);
		panel1.add(txt_username);
		panel1.add(lbl_password);
		panel1.add(txt_password);
		panel1.add(lbl_pin);
		panel1.add(txt_pin);
		panel1.add(lbl_name);
		panel1.add(txt_name);
		panel1.add(lbl_gender);
		panel1.add(txt_gender);
		panel1.add(lbl_address);
		panel1.add(txt_address);
		panel1.add(lbl_phone);
		panel1.add(txt_phone);
		panel2.add(btn_ok);
		panel2.add(btn_exit);

		btn_ok.addActionListener(this);
		btn_exit.addActionListener(this);
		
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		add(panel1);
		add(panel2);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent k) {
		if(k.getSource() == btn_ok) {
			JOptionPane.showMessageDialog(this, "Success");//Message yang muncul
			new jLogin();
			this.dispose();		
			}
		if(k.getSource() == btn_exit) {
			this.dispose();		
			new jLogin();
			}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
}

public class Registration{
	public static void main(String[] args){
		new jRegistration(); //Langsung Munculkan Frame
	}
}