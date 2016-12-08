package Inter;

import java.awt.FlowLayout;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

class jRecovery  extends JFrame implements ItemListener, ActionListener{ 	

	JButton btn_confirm = new JButton("Confirm");
	JButton btn_cancel = new JButton("Back");
	
	  Statement st;
	  
	  boolean bool = false; 
		      	 
	  Connection conn = null;
	    java.sql.ResultSet rs = null;
	    java.sql.PreparedStatement ps = null;
	  
	  JLabel lbl_username = new JLabel("UserName");
	  JLabel lbl_pin = new JLabel("FullName");
		JLabel lbl_phone = new JLabel("Phone");		
				
		JTextField txt_username = new JTextField();
		JTextField txt_pin = new JTextField();
		JTextField txt_phone = new JTextField();
		JTable table = new JTable();
		 
	  
	jRecovery(){
		setTitle("Recovery Password");
		setSize(450, 150);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		
		 try { //Conn dan mengisi tabel
	    	  Class.forName("com.mysql.jdbc.Driver");
	          System.out.println("Connecting to database");
	          conn = DriverManager.getConnection(
	  				"jdbc:mysql://localhost/kasir_restoran",
	  				"root", "");
	          System.out.println("Connected to databse");
	          Vector<Vector<Object>> data = null;
		      Vector<Object> columnNames = new Vector<Object>();
		      columnNames.add("id");
		      columnNames.add("fullname");
		      columnNames.add("gender");
		      columnNames.add("address");
		      columnNames.add("phone");
		      columnNames.add("username");
		      columnNames.add("password");
		      try {
		            DBkasir db = new DBkasir();
		            data = db.selectUSER();
		        }
		        catch (Exception e) {
		            e.printStackTrace();
		        }
		      table = new JTable(data, columnNames);
		      JScrollPane scrollPane = new JScrollPane(table);
		      table.setFillsViewportHeight(true);
		      table.setEnabled(false);		  	
		      JScrollPane scrollPanel = new JScrollPane(table);
		    
		      DefaultTableModel model = (DefaultTableModel) table.getModel();
		      
	      }
	          
	
		      catch (Exception e)
			    {
			        System.err.println("Got an exception!");
			        System.err.println(e.getMessage());
			    }
		JPanel panel1 = new JPanel(new GridLayout(3, 2));
		JPanel panel2 = new JPanel(new FlowLayout());

		

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
			
			 try {
		            
				  Statement statement = conn.createStatement();
			      ResultSet result = statement.executeQuery("select * from user");
		            while (result.next()){
		                if (txt_username.getText().equals(result.getString("username"))&& txt_phone.getText().equals(result.getString("phone"))&& txt_pin.getText().equals(result.getString("fullname"))){
		                	 bool = true;
		                	 Variabel.recovery = result.getString("password");
		                			 
		                			 JOptionPane.showMessageDialog(this,"Password anda adalah : "+Variabel.recovery);//Message yang muncul

		                    break;
		                }
		                else
		                	 
				                    bool = false;   
		        }} catch (SQLException ex) {
		           ex.printStackTrace();    }
			
			 if (bool == true){
				 System.out.print ("test");
				
				 // jawab.setText(result.getString("username"));
					
					
					
					
					
			    }
			    else {
			    	
			        JOptionPane.showMessageDialog (null, "Data yang anda masukan tidak tepat", "ERROR", JOptionPane.ERROR_MESSAGE,null);
			    }
			 
			 
			 
				
			}
		if(e.getSource() == btn_cancel) {
			new jLogin();
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