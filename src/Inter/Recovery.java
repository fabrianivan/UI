package Inter;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

class jRecovery  extends JFrame implements ItemListener, ActionListener{ 	

	JButton btn_confirm = new JButton("CONFIRM");
	JButton btn_cancel = new JButton("CANCEL");

	jRecovery(){
		setTitle("Recovery Password");
		setSize(450, 150);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel panel1 = new JPanel(new GridLayout(3, 2));
		JPanel panel2 = new JPanel(new FlowLayout());

		JLabel lbl_username = new JLabel("USERNAME");
		JLabel lbl_phone = new JLabel("PHONE");		
		JLabel lbl_pin = new JLabel("PIN");		
		JTextField txt_username = new JTextField();
		JTextField txt_pin = new JTextField();
		JTextField txt_phone = new JTextField();

		btn_confirm.addActionListener(this);
		btn_cancel.addActionListener(this);
	  	
		panel1.add(lbl_username);
		panel1.add(txt_username);
		panel1.add(lbl_phone);
		panel1.add(txt_phone);
		panel1.add(lbl_pin);
		panel1.add(txt_pin);
		panel2.add(btn_confirm);
		panel2.add(btn_cancel);

		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		add(panel1);
		add(panel2);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn_confirm) {
			JOptionPane.showMessageDialog(this, "Accepted");//Message yang muncul
			new jMenu();
			this.dispose();		
			}
		if(e.getSource() == btn_cancel) {
			this.dispose();		
			}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
}

public class Recovery{
	public static void main(String[] args){
		new jRecovery(); //Langsung Munculkan Frame
	}
}