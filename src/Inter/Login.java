package Inter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;


class jLogin extends JFrame implements ItemListener, ActionListener{ 
	
	JTable table = null;
	JButton btn_ok = new JButton("OK");
	JButton btn_help = new JButton("CALL WAITERESS");
	JButton btn_signup = new JButton("SIGN UP");
	JButton btn_forgot = new JButton("FORGOT");
	JButton btn_exit = new JButton("EXIT");
	JButton btn_admin = new JButton("ADMIN");
	Connection conn = null;
    java.sql.ResultSet rs = null;
    java.sql.PreparedStatement ps = null;
	
	JTextField txt_username = new JTextField();
	JPasswordField txt_password = new JPasswordField();
    int count=0;
	jLogin(){
		setTitle("LOGIN");
		setSize(340, 170);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JTable table = new JTable();
	        Statement st;
			
		      try { //Conn dan mengisi tabel
		    	  Class.forName("com.mysql.jdbc.Driver");
		          System.out.println("Connecting to database");
		          conn = DriverManager.getConnection(
		  				"jdbc:mysql://localhost:3308/kasir_restoran",
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
			      
		JPanel panellogin = new JPanel(new GridLayout(2, 2));
		JPanel panelloginbtn = new JPanel(new FlowLayout());

		JLabel lbl_username = new JLabel("USERNAME");
		JLabel lbl_password = new JLabel("PASSWORD");		
	
		
		
		
		
		panellogin.add(lbl_username);
		panellogin.add(txt_username);
		panellogin.add(lbl_password);
		panellogin.add(txt_password);
		
		panelloginbtn.add(btn_ok);
		panelloginbtn.add(btn_help);
		panelloginbtn.add(btn_forgot);
		panelloginbtn.add(btn_signup);	
		panelloginbtn.add(btn_exit);		
		panelloginbtn.add(btn_admin);
		
		
		  
		
		btn_ok.addActionListener(this);
		btn_admin.addActionListener(this);
		btn_help.addActionListener(this);
		btn_signup.addActionListener(this);
		btn_exit.addActionListener(this);
		btn_forgot.addActionListener(this);
		
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		add(panellogin);
		add(panelloginbtn);
		setVisible(true);
	}

	boolean bool = false;
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn_admin) {
			if(txt_username.getText().equals("admin")  && txt_password.getPassword().equals("admin"))
			{
				new jAdmin();
				this.dispose();
			}
		
		}
		
		if(e.getSource() == btn_ok) {
			 try {
		            
		            Statement statement = conn.createStatement();
		            ResultSet result = statement.executeQuery("select * from user");
		            while (result.next()){
		                if (txt_username.getText().equals(result.getString("username"))&& String.valueOf( txt_password.getPassword()).equals(result.getString("password"))){
		                    bool = true;
		                    Variabel.id_login=result.getString("id");
			                System.out.println(Variabel.id_login);
			               Variabel.nama=result.getString("fullname");
		                    System.out.print ("Login Success");
		                    break;
		                }
		                else
		                {
		                    bool = false;
		                }
		            }
		    		    if (bool == true){
		    		    	new jMenu();
		    				this.dispose();	
		    				
		    				JOptionPane.showMessageDialog(this, "Selamat datang "+Variabel.nama);//Message yang muncul    				
		    		    }
		    		    else {
		    		        JOptionPane.showMessageDialog (null, "Username atau Password salah", "ERROR", JOptionPane.ERROR_MESSAGE,null);
		    		    }
		        } catch (SQLException ex) {
		           ex.printStackTrace();    }
		          
			
//			JOptionPane.showMessageDialog(this, "Accepted");//Message yang muncul
		//	new jMenu();
	//		this.dispose();		
			}
		if(e.getSource() == btn_signup) {
			//JOptionPane.showMessageDialog(this, "Accepted");//Message yang muncul
			new jRegistration();
			this.dispose();		
			}
		if(e.getSource() == btn_help) {
			this.dispose();		
	        Client client = new Client();
	        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        client.frame.setVisible(true);
	        try {
				client.run();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		if(e.getSource() == btn_forgot) {
			new jRecovery();
				this.dispose();		
				}	
		
		if(e.getSource() == btn_exit) {
			this.dispose();		
			}		 
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub		
	}

}
	
public class Login{
	public static void main(String[] args){
		new jLogin(); //Langsung Munculkan Frame
	}
}