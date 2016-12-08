package Inter;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextComponent;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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




class jStock extends JFrame implements ItemListener, ActionListener{ 
	
	JTable table = null;
	JButton btn_search = new JButton("Search");
	JButton btn_refresh = new JButton("Refresh");
	
	JButton btn_update = new JButton("Update");
	JButton btn_back = new JButton("Back");
	

	JPanel panel1 = new JPanel(new BorderLayout());
	JPanel panel2 = new JPanel(new FlowLayout());//GridLayout(1, 5));
	JPanel panel3 = new JPanel(new FlowLayout());//GridLayout(1, 2));
	JPanel panel4 = new JPanel(new GridLayout(4, 2));
	
	JLabel lbl_code = new JLabel("Code");
	JLabel lbl_name = new JLabel("Name");
	JLabel lbl_price = new JLabel("Price");
	JLabel lbl_stock = new JLabel("Stock");
	
	JTextField txt_code = new JTextField();
	JTextField txt_name = new JTextField();
	JTextField txt_price = new JTextField();
	JTextField txt_stock = new JTextField();
	
	private TextComponent txt_search;
	
	jStock(){
		setTitle("Stock");
		setSize(700, 450);
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
		      columnNames.add("Code");
		      columnNames.add("Menu");
		      columnNames.add("Price");
		      columnNames.add("Stock");
		      
		     try {
		            DBkasir db = new DBkasir();
		            data = db.selectMENU();
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
		  	JTextField txt_code = new JTextField();
		  	JTextField txt_name = new JTextField();
		  	JTextField txt_price = new JTextField();
		  	JTextField txt_stock = new JTextField();
		  	
		  	txt_search.setPreferredSize( new Dimension( 350, 24 ) );
		  	JComboBox searchby= new JComboBox();
		  	searchby.addItem("Code");
		  	searchby.addItem("Name");
		  	searchby.addItem("Price");
		  	searchby.addItem("Stock");
		  	
		  
		  	btn_update.addActionListener(this);
		  	btn_back.addActionListener(this);
		  	btn_refresh.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	Vector<Vector<Object>> data = null;
	  		      Vector<Object> columnNames = new Vector<Object>();
	  		      columnNames.add("Code");
	  		      columnNames.add("Name");
	  		      columnNames.add("Price");
	  		      columnNames.add("Stock");
	  		      
	  		      try {
	  		            DBkasir db = new DBkasir();
	  		            data = db.selectMENU();
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
		  	
		  	btn_search.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	               if(searchby.getSelectedItem().equals("Code")){
	            	   String cari = "%" +txt_search.getText() + "%";
					   
	   				try{
	   					DBkasir db = new DBkasir();
	   					Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	   					data = db.cari_codemenu(cari);
	   					Vector<Object> columnNames = new Vector<Object>();
	   			      columnNames.add("Code");
	   			      columnNames.add("Name");
	   			      columnNames.add("Price");
	   			      columnNames.add("Stock");
	   					
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
	               if(searchby.getSelectedItem().equals("Name")){
	            	   String cari = "%" + txt_search.getText() + "%";
					   
	   				try{
	   					DBkasir db = new DBkasir();
	   					Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	   					data = db.cari_namemenu(cari);
	   					Vector<Object> columnNames = new Vector<Object>();
	   			      columnNames.add("Code");
	   			      columnNames.add("Name");
	   			      columnNames.add("Price");
	   			      columnNames.add("Stock");
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
	               if(searchby.getSelectedItem().equals("Price")){
	            	   int cari = Integer.parseInt(txt_search.getText());
					   
	   				try{
	   					DBkasir db = new DBkasir();
	   					Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	   					data = db.cari_pricemenu(cari);
	   					Vector<Object> columnNames = new Vector<Object>();
	   					columnNames.add("Code");
		   			      columnNames.add("Name");
		   			      columnNames.add("Price");
		   			      columnNames.add("Stock");
	   					
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
	               if(searchby.getSelectedItem().equals("Stock")){
	            	   int cari = Integer.parseInt(txt_search.getText());
					   
	   				try{
	   					DBkasir db = new DBkasir();
	   					Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	   					data = db.cari_stockmenu(cari);
	   					Vector<Object> columnNames = new Vector<Object>();
	   					columnNames.add("Code");
		   			      columnNames.add("Name");
		   			      columnNames.add("Price");
		   			      columnNames.add("Stock");
	   					
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
			title = BorderFactory.createTitledBorder("Tabel Menu");
			panel4.setBorder(title);
			panel4.add(lbl_code);
			panel4.add(txt_code);
			panel4.add(lbl_name);
			panel4.add(txt_name);
			panel4.add(lbl_price);
			panel4.add(txt_price);
			panel4.add(lbl_stock);
			panel4.add(txt_stock);
			
			//panel1.add(scrollPanel);	
			panel3.add(txt_search);	
			panel3.add(searchby);
			panel3.add(btn_search);
			panel3.add(btn_refresh);
			
			panel2.add(btn_update);
			panel2.add(btn_back);

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
			
		}
		if(e.getSource() == btn_refresh) {
			JOptionPane.showMessageDialog(this, "Accepted");//Message yang muncul
			new jLogin();
			this.dispose();		
			}
		if(e.getSource() == btn_back) {
			JOptionPane.showMessageDialog(this, "Accepted");//Message yang muncul
			new jLogin();
			this.dispose();		
			}
		
		//btn search
		
		

//btn_update
		if(e.getSource() == btn_update) {
                try {
                	int id = Integer.parseInt(txt_price.getText());
                	int phone = Integer.parseInt(txt_stock.getText());
                    DBkasir db = new DBkasir();
                    db.updatemenu( txt_code.getText(), txt_name.getText(), id,  phone);
                    
                    Vector<Vector<Object>> data = null;
                    Vector<Object> columnNames = new Vector<Object>();
                    columnNames.add("Code");
      		     	columnNames.add("Menu");
      		     	columnNames.add("Price");
      		     	columnNames.add("Stock");
      		    
      		      	

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
                txt_code.setText("");
                txt_name.setText("");
                txt_price.setText("");
                txt_stock.setText("");
              }
		JOptionPane.showMessageDialog(panel2, "Sucess");//Message yang muncul
			
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
public class Stock{
	public static void main(String[] args){
		new jStock(); //Langsung Munculkan Frame
	}
}