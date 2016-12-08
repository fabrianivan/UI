package Inter;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextComponent;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Inter.DBkasir;


class jAdmin extends JFrame implements ItemListener, ActionListener{ 
	
	JTable table = null;
	JButton btn_search = new JButton("Search");
	JButton btn_refresh = new JButton("Refresh");
	JButton btn_history = new JButton("History Transaction");
	JButton btn_stock = new JButton("Stock");
	JButton btn_create = new JButton("Create");
	JButton btn_chat = new JButton("Chat Server");
	JButton btn_delete = new JButton("Delete");
	JButton btn_update = new JButton("Update");
	JButton btn_signout = new JButton("Sign Out");

	JPanel panel1 = new JPanel(new BorderLayout());
	JPanel panel2 = new JPanel(new FlowLayout());//GridLayout(1, 5));
	JPanel panel3 = new JPanel(new FlowLayout());//GridLayout(1, 2));
	JPanel panel4 = new JPanel(new GridLayout(7, 2));
	
	JLabel lbl_id = new JLabel("ID");
	JLabel lbl_fullname = new JLabel("Fullname");
	JLabel lbl_gender = new JLabel("Gender");
	JLabel lbl_address = new JLabel("Address");
	JLabel lbl_phone = new JLabel("Phone");
	JLabel lbl_username = new JLabel("Username");
	JLabel lbl_password = new JLabel("Password");
	JTextField txt_id = new JTextField();
	JTextField txt_fullname = new JTextField();
	JTextField txt_gender = new JTextField();
	JTextField txt_address = new JTextField();
	JTextField txt_phone = new JTextField();
	JTextField txt_username = new JTextField();
	JTextField txt_password = new JTextField();
	private TextComponent txt_search;
	
	jAdmin(){
		setTitle("Administration");
		setSize(700, 650);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);


		JTable table = new JTable();
        Connection conn;
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
		      table.setFillsViewportHeight(true);
		      table.setEnabled(false);		  	
		      JScrollPane scrollPanel = new JScrollPane(table);
		      panel1.add(scrollPanel, BorderLayout.CENTER);
		      DefaultTableModel model = (DefaultTableModel) table.getModel();
		      
	      }
	      catch (Exception e)
		    {
		        System.err.println("Got an exception!");
		        System.err.println(e.getMessage());
		    }   
		  	JTextField txt_search = new JTextField();
		  	txt_search.setPreferredSize( new Dimension( 350, 24 ) );
		  	JComboBox searchby= new JComboBox();
		  	searchby.addItem("ID");
		  	searchby.addItem("Fullname");
		  	searchby.addItem("Gender");
		  	searchby.addItem("Address");
		  	searchby.addItem("Phone");
		  	searchby.addItem("Username");
		  	
		  	btn_history.addActionListener(this);
		  	btn_create.addActionListener(this);
		  	btn_delete.addActionListener(this);
		  	btn_update.addActionListener(this);
		  	btn_signout.addActionListener(this);
		  	btn_refresh.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
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
	  		        catch (Exception g) {
	  		            g.printStackTrace();
	  		        }
	  		      JTable table = new JTable(data, columnNames);
	  		      table.setFillsViewportHeight(true);
	  		      table.setEnabled(false);		  	
	  		      JScrollPane scrollPanel = new JScrollPane(table);
	  		      panel1.add(scrollPanel, BorderLayout.CENTER);
	  		      DefaultTableModel model = (DefaultTableModel) table.getModel();
                 panel1.removeAll();
            	panel1.add(scrollPanel, BorderLayout.CENTER);
                        panel1.revalidate();
	   					
	            	}
	            	
	            	
	            	
	            
		  	});
		  	btn_stock.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	new jStock();
	           
	            }
		  	});
		  	btn_chat.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	new Server();
	            	
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
		  	});
		  	btn_search.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	               if(searchby.getSelectedItem().equals("Username")){
	            	   String cari = "%" +txt_search.getText() + "%";
					   
	   				try{
	   					DBkasir db = new DBkasir();
	   					Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	   					data = db.cari_username(cari);
	   					Vector<Object> columnNames = new Vector<Object>();
	   			      columnNames.add("id");
	   			      columnNames.add("fullname");
	   			      columnNames.add("gender");
	   			      columnNames.add("address");
	   			      columnNames.add("phone");
	   			      columnNames.add("username");
	   			      columnNames.add("password");
	   					
	   					JTable table = new JTable(data, columnNames);
	   					JScrollPane scrollPane = new JScrollPane(table);
                        table.setFillsViewportHeight(true);
                        table.setEnabled(false);
                        panel1.removeAll();
            		     panel1.add(scrollPane, BorderLayout.CENTER);
                        panel1.revalidate();
	   					System.out.println("Data berhasil dicari");
	   						
	   				}catch(Exception e2){
	   					e2.printStackTrace();
	   					System.out.println("Ada kesalahan");
	   				}
	               }
	               if(searchby.getSelectedItem().equals("Fullname")){
	            	   String cari = "%" + txt_search.getText() + "%";
					   
	   				try{
	   					DBkasir db = new DBkasir();
	   					Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	   					data = db.cari_fullname(cari);
	   					Vector<Object> columnNames = new Vector<Object>();
	   			      columnNames.add("id");
	   			      columnNames.add("fullname");
	   			      columnNames.add("gender");
	   			      columnNames.add("address");
	   			      columnNames.add("phone");
	   			      columnNames.add("username");
	   			      columnNames.add("password");
	   					
	   					JTable table = new JTable(data, columnNames);
	   					JScrollPane scrollPane = new JScrollPane(table);
                        table.setFillsViewportHeight(true);
                        table.setEnabled(false);
                        panel1.removeAll();
            		     panel1.add(scrollPane, BorderLayout.CENTER);
                        panel1.revalidate();
	   					System.out.println("Data berhasil dicari");
	   						
	   				}catch(Exception e2){
	   					e2.printStackTrace();
	   					System.out.println("Ada kesalahan");
	   				}
	               }
	               if(searchby.getSelectedItem().equals("Gender")){
	            	   String cari = "%" + txt_search.getText() + "%";
					   
	   				try{
	   					DBkasir db = new DBkasir();
	   					Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	   					data = db.cari_gender(cari);
	   					Vector<Object> columnNames = new Vector<Object>();
	   			      columnNames.add("id");
	   			      columnNames.add("fullname");
	   			      columnNames.add("gender");
	   			      columnNames.add("address");
	   			      columnNames.add("phone");
	   			      columnNames.add("username");
	   			      columnNames.add("password");
	   					
	   					JTable table = new JTable(data, columnNames);
	   					JScrollPane scrollPane = new JScrollPane(table);
                        table.setFillsViewportHeight(true);
                        table.setEnabled(false);
                        panel1.removeAll();
            		     panel1.add(scrollPane, BorderLayout.CENTER);
                        panel1.revalidate();
	   					System.out.println("Data berhasil dicari");
	   						
	   				}catch(Exception e2){
	   					e2.printStackTrace();
	   					System.out.println("Ada kesalahan");
	   				}
	               }
	               if(searchby.getSelectedItem().equals("Address")){
	            	   String cari = "%" + txt_search.getText() + "%";
					   
	   				try{
	   					DBkasir db = new DBkasir();
	   					Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	   					data = db.cari_address(cari);
	   					Vector<Object> columnNames = new Vector<Object>();
	   			      columnNames.add("id");
	   			      columnNames.add("fullname");
	   			      columnNames.add("gender");
	   			      columnNames.add("address");
	   			      columnNames.add("phone");
	   			      columnNames.add("username");
	   			      columnNames.add("password");
	   					
	   					JTable table = new JTable(data, columnNames);
	   					JScrollPane scrollPane = new JScrollPane(table);
                        table.setFillsViewportHeight(true);
                        table.setEnabled(false);
                        panel1.removeAll();
            		     panel1.add(scrollPane, BorderLayout.CENTER);
                        panel1.revalidate();
	   					System.out.println("Data berhasil dicari");
	   						
	   				}catch(Exception e2){
	   					e2.printStackTrace();
	   					System.out.println("Ada kesalahan");
	   				}
	               }
	               if(searchby.getSelectedItem().equals("Phone")){
	            	   int cari = Integer.parseInt(txt_search.getText());
					   
	   				try{
	   					DBkasir db = new DBkasir();
	   					Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	   					data = db.cari_phone(cari);
	   					Vector<Object> columnNames = new Vector<Object>();
	   			      columnNames.add("id");
	   			      columnNames.add("fullname");
	   			      columnNames.add("gender");
	   			      columnNames.add("address");
	   			      columnNames.add("phone");
	   			      columnNames.add("username");
	   			      columnNames.add("password");
	   					
	   					JTable table = new JTable(data, columnNames);
	   					JScrollPane scrollPane = new JScrollPane(table);
                        table.setFillsViewportHeight(true);
                        table.setEnabled(false);
                        panel1.removeAll();
            		     panel1.add(scrollPane, BorderLayout.CENTER);
                        panel1.revalidate();
	   					System.out.println("Data berhasil dicari");
	   						
	   				}catch(Exception e2){
	   					e2.printStackTrace();
	   					System.out.println("Ada kesalahan");
	   				}
	               }
	               if(searchby.getSelectedItem().equals("ID")){
	            	   int cari = Integer.parseInt(txt_search.getText());
					   
	   				try{
	   					DBkasir db = new DBkasir();
	   					Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	   					data = db.cari_id(cari);
	   					Vector<Object> columnNames = new Vector<Object>();
	   			      columnNames.add("id");
	   			      columnNames.add("fullname");
	   			      columnNames.add("gender");
	   			      columnNames.add("address");
	   			      columnNames.add("phone");
	   			      columnNames.add("username");
	   			      columnNames.add("password");
	   					
	   					JTable table = new JTable(data, columnNames);
	   					JScrollPane scrollPane = new JScrollPane(table);
                        table.setFillsViewportHeight(true);
                        table.setEnabled(false);
                        panel1.removeAll();
            		     panel1.add(scrollPane, BorderLayout.CENTER);
                        panel1.revalidate();
	   					System.out.println("Data berhasil dicari");
	   						
	   				}catch(Exception e2){
	   					e2.printStackTrace();
	   					System.out.println("Ada kesalahan");
	   				}
	               }
	            }
	        });
			TitledBorder title;
			title = BorderFactory.createTitledBorder("Tabel USER");
			panel4.setBorder(title);
			panel4.add(lbl_id);
			panel4.add(txt_id);
			panel4.add(lbl_fullname);
			panel4.add(txt_fullname);
			panel4.add(lbl_gender);
			panel4.add(txt_gender);
			panel4.add(lbl_address);
			panel4.add(txt_address);
			panel4.add(lbl_phone);
			panel4.add(txt_phone);
			panel4.add(lbl_username);
			panel4.add(txt_username);
			panel4.add(lbl_password);
			panel4.add(txt_password);
			//panel1.add(scrollPanel);	
			panel3.add(txt_search);	
			panel3.add(searchby);
			panel3.add(btn_search);
			panel3.add(btn_refresh);
			panel2.add(btn_history);
			panel2.add(btn_stock);
			panel2.add(btn_create);
			panel2.add(btn_delete);
			panel2.add(btn_update);
			panel2.add(btn_chat);
			panel2.add(btn_signout);

		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		add(panel3);
		add(panel1);
		add(panel4);
		add(panel2);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn_search){
			txt_search.setText("");
		}
		if(e.getSource() == btn_chat){
			
			new Client();
			
		}
		if(e.getSource() == btn_stock){
        	new jStock();	         
			this.dispose();	
		}
		if(e.getSource() == btn_signout) {
			JOptionPane.showMessageDialog(this, "Logged Out");//Message yang muncul
			new jLogin();
			this.dispose();		
			}
		if(e.getSource() == btn_history) {
			new jHistory();
			this.dispose();		
			}
		
		if(e.getSource() == btn_create) {
			 if (txt_id.getText().equals("")) {
                 JOptionPane.showMessageDialog(null, "ID tidak dapat kosong");
             }
             else {
                 try {
                     DBkasir db = new DBkasir();
                     boolean cek = db.cekID(txt_id.getText());

                     if (cek) {
     					 int id = Integer.parseInt(txt_id.getText());
     					 int phone = Integer.parseInt(txt_phone.getText());
                         db.insertuser(id, txt_fullname.getText(), txt_gender.getText(), txt_address.getText(), phone,  txt_username.getText(), txt_password.getText());
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
                             data = db.selectUSER();
                         } catch (Exception f) {
                             f.printStackTrace();
                         }

                         // Create JTable and fill it with data
                         table = new JTable(data, columnNames);
                         JScrollPane scrollPane = new JScrollPane(table);
                         table.setFillsViewportHeight(true);
                         table.setEnabled(false);
                         panel1.removeAll();
             		     panel1.add(scrollPane, BorderLayout.CENTER);
                         panel1.revalidate();
                     }
                     else
                     {
                         JOptionPane.showMessageDialog(null, "ID sudah pernah terdaftar");
                     }
                 }
                 catch(Exception g){
                     System.out.println(g);
                 }
                 txt_id.setText("");
                 txt_fullname.setText("");
                 txt_gender.setText("");
                 txt_address.setText("");
                 txt_phone.setText("");
                 txt_username.setText("");
                 txt_password.setText("");
             }
         }
		//btn search
		
		
//btn_delete			
		if(e.getSource() == btn_delete) {
			try {
            DBkasir db = new DBkasir();

			int idint = Integer.parseInt(txt_id.getText());
			
            db.deleteUSER(idint);
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
                data = db.selectUSER();
            }
            catch (Exception g) {
                g.printStackTrace();
            }

            // Create JTable and fill it with data
            table = new JTable(data, columnNames);
            JScrollPane scrollPane = new JScrollPane(table);
            table.setFillsViewportHeight(true);
            table.setEnabled(false);
            panel1.removeAll();
		    panel1.add(scrollPane, BorderLayout.CENTER);
            panel1.revalidate();
            txt_id.setText("");
            txt_fullname.setText("");
            txt_gender.setText("");
            txt_address.setText("");
            txt_phone.setText("");
            txt_username.setText("");
            txt_password.setText("");
            //model.addRow(new Object[]{kb1.getText(),jb1.getText(),plis1.getText(),pbit1.getText(),no_rak1.getSelectedItem().toString()});
        }
        catch(Exception f){
            System.out.println(f);
        }

        txt_id.setText("");
    }

//btn_update
		if(e.getSource() == btn_update) {
                try {
                	int id = Integer.parseInt(txt_id.getText());
                	int phone = Integer.parseInt(txt_phone.getText());
                    DBkasir db = new DBkasir();
                    db.updateuser(id, txt_fullname.getText(), txt_gender.getText(), txt_address.getText(), phone,  txt_username.getText(), txt_password.getText());
                    
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
                        data = db.selectUSER();
                    }
                    catch (Exception r) {
                        r.printStackTrace();
                    }

                    // Create JTable and fill it with data
                    table = new JTable(data, columnNames);
                    JScrollPane scrollPane = new JScrollPane(table);
                    table.setFillsViewportHeight(true);
                    table.setEnabled(false);
                    panel1.removeAll();
        		    panel1.add(scrollPane, BorderLayout.CENTER);
                    panel1.revalidate();
                }
                catch(Exception j){
                    System.out.println(j);
                }
                txt_id.setText("");
                txt_fullname.setText("");
                txt_gender.setText("");
                txt_address.setText("");
                txt_phone.setText("");
                txt_username.setText("");
                txt_password.setText("");
            }
		JOptionPane.showMessageDialog(panel2, "Sucess");//Message yang muncul
			
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
}
public class Admin{
	public static void main(String[] args){
		new jAdmin(); //Langsung Munculkan Frame
		new Server();
	}
}