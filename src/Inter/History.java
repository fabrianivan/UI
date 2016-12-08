package Inter;

import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.FlowLayout;
import java.awt.GridLayout;
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

class jHistory  extends JFrame implements ItemListener, ActionListener{ 
	
	JTable table = null;
	JButton btn_search = new JButton("SEARCH");
	JButton btn_update = new JButton("UPDATE");
	JButton btn_delete = new JButton("DELETE");
	JButton btn_exit = new JButton("BACK");
	JButton btn_refresh = new JButton("REFRESH");
	
	JComboBox searchby= new JComboBox();
	
  
	
	
	
	JLabel lbl_id = new JLabel("Nomor Seri");
	JLabel lbl_fullname = new JLabel("Barang");
	JLabel lbl_gender = new JLabel("Jumlah");
	JLabel lbl_address = new JLabel("Satuan");
	JLabel lbl_phone = new JLabel("Harga");
	
	JTextField txt_id = new JTextField();
	JTextField txt_fullname = new JTextField();
	JTextField txt_gender = new JTextField();
	JTextField txt_address = new JTextField();
	JTextField txt_phone = new JTextField();
	
	JPanel panel1 = new JPanel(new BorderLayout());
	JPanel panel2 = new JPanel(new FlowLayout());//GridLayout(1, 2));
	JPanel panel3 = new JPanel(new FlowLayout());//GridLayout(1, 5));
	JPanel panel4 = new JPanel(new GridLayout(5,2));//GridLayout(1, 5));
	
	JTextField txt_search = new JTextField();
	
	jHistory(){
		setTitle("Transaction History");
		setSize(600, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	  	btn_search.addActionListener(this);
	  	
	  	btn_exit.addActionListener(this);
	  	btn_delete.addActionListener(this);
	  	btn_update.addActionListener(this);
	  	btn_refresh.addActionListener(this);
	
	  	
	  	searchby.addItem("Nomor Seri");
	  	searchby.addItem("Barang");
	  	searchby.addItem("Jumlah");
	  	searchby.addItem("Satuan");
	  	searchby.addItem("Harga");

        Connection conn;
        Statement st;
	
        TitledBorder title;
		title = BorderFactory.createTitledBorder("Tabel History");
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
		
        
        
        
        
        
	      try { //Conn dan mengisi tabel
	    	  Class.forName("com.mysql.jdbc.Driver");
	          System.out.println("Connecting to database");
	          conn = DriverManager.getConnection(
	  				"jdbc:mysql://localhost:3308/kasir_restoran",
	  				"root", "");
	          System.out.println("Connected to databse");
	        
	          Vector<Vector<Object>> data = null;
		      Vector<Object> columnNames = new Vector<Object>();
		      columnNames.add("Nomor Seri");
		      columnNames.add("Barang");
		      columnNames.add("Jumlah");
		      columnNames.add("Satuan");
		      columnNames.add("Harga");
		      
		      try {
		            DBkasir db = new DBkasir();
		            data = db.selectHISTORY();
		        }
		        catch (Exception e) {
		            e.printStackTrace();
		        }
		      
		      table = new JTable(data, columnNames);
		      JScrollPane scrollPane = new JScrollPane(table);
		      table.setFillsViewportHeight(true);
		      table.setEnabled(false);
		      panel1.add(scrollPane, BorderLayout.CENTER);
		      DefaultTableModel model = (DefaultTableModel) table.getModel();
		      
	      }
	      catch (Exception e)
		    {
		        System.err.println("Got an exception!");
		        System.err.println(e.getMessage());
		    }   
		  	
		  	txt_search.setPreferredSize( new Dimension( 330, 24 ) );
			JScrollPane scrollPanel = new JScrollPane(table);

			
			
			panel1.add(scrollPanel);
			panel2.add(txt_search);
			
			panel3.add(btn_update);
			panel3.add(btn_delete);
			panel3.add(btn_refresh);
			panel3.add(btn_exit);
			panel2.add(searchby);
			panel2.add(btn_search);
			
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		add(panel2);
		add(panel1);
		add(panel4);
		add(panel3);
		setVisible(true);
	
		btn_refresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Vector<Vector<Object>> data = null;
  		      Vector<Object> columnNames = new Vector<Object>();
  		      columnNames.add("Nomor Seri");
  		      columnNames.add("Barang");
  		      columnNames.add("Jumlah");
  		      columnNames.add("Satuan");
  		      columnNames.add("Harga");
  		      
  		      try {
  		            DBkasir db = new DBkasir();
  		            data = db.selectHISTORY();
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

	
	
	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn_update) {
			try {
            	int id = Integer.parseInt(txt_id.getText());
            	int jumlah = Integer.parseInt(txt_gender.getText());
            	int satuan = Integer.parseInt(txt_address.getText());
            	int harga = Integer.parseInt(txt_phone.getText());
                DBkasir db = new DBkasir();
                db.updateHISTORY(id, txt_fullname.getText(), jumlah, satuan, harga);
                
                Vector<Vector<Object>> data = null;
                Vector<Object> columnNames = new Vector<Object>();
                columnNames.add("Nomor Seri");
  		     	columnNames.add("Barang");
  		     	columnNames.add("Jumlah");
  		     	columnNames.add("Satuan");
  		      	columnNames.add("Harga");
  		
  		      	

                try {
                    data = db.selectHISTORY();
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
            	JOptionPane.showMessageDialog(panel2, "Sucess");
            }
            catch(Exception j){
            	JOptionPane.showMessageDialog(panel2, "Maaf, barang tidak terdapat dalam database");//Message yang muncul
                System.out.println(j);
            }
            txt_id.setText("");
            txt_fullname.setText("");
            txt_gender.setText("");
            txt_address.setText("");
            txt_phone.setText("");
            
        }
//Message yang muncul
		

			
		if(e.getSource() == btn_exit) {
			new jAdmin();
			this.dispose();		
			}
		if(e.getSource() == btn_refresh) {
				  		  
			}
		if(e.getSource() == btn_search) {
			 {
	               if(searchby.getSelectedItem().equals("Nomor Seri")){
	            	   int cari = Integer.parseInt(txt_search.getText());
					   
	   				try{
	   					DBkasir db = new DBkasir();
	   					Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	   					data = db.cari_nomor(cari);
	   					Vector<Object> columnNames = new Vector<Object>();
	   					columnNames.add("Nomor Seri");
	   	  		     	columnNames.add("Barang");
	   	  		     	columnNames.add("Jumlah");
	   	  		     	columnNames.add("Satuan");
	   	  		      	columnNames.add("Harga");
	   					
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
	               if(searchby.getSelectedItem().equals("Barang")){
	            	   String cari = "%" + txt_search.getText() + "%";
					   
	   				try{
	   					DBkasir db = new DBkasir();
	   					Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	   					data = db.cari_barang(cari);
	   					Vector<Object> columnNames = new Vector<Object>();
	   					columnNames.add("Nomor Seri");
	   	  		     	columnNames.add("Barang");
	   	  		     	columnNames.add("Jumlah");
	   	  		     	columnNames.add("Satuan");
	   	  		      	columnNames.add("Harga");
	   					
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
	               if(searchby.getSelectedItem().equals("Jumlah")){
	            	   int cari = Integer.parseInt(txt_search.getText());
					   
	   				try{
	   					DBkasir db = new DBkasir();
	   					Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	   					data = db.cari_jumlah(cari);
	   					Vector<Object> columnNames = new Vector<Object>();
	   					columnNames.add("Nomor Seri");
	   	  		     	columnNames.add("Barang");
	   	  		     	columnNames.add("Jumlah");
	   	  		     	columnNames.add("Satuan");
	   	  		      	columnNames.add("Harga");
	   					
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
	               if(searchby.getSelectedItem().equals("Satuan")){
	            	   int cari = Integer.parseInt(txt_search.getText());
					   
	   				try{
	   					DBkasir db = new DBkasir();
	   					Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	   					data = db.cari_satuan(cari);
	   					Vector<Object> columnNames = new Vector<Object>();
	   					columnNames.add("Nomor Seri");
	   	  		     	columnNames.add("Barang");
	   	  		     	columnNames.add("Jumlah");
	   	  		     	columnNames.add("Satuan");
	   	  		      	columnNames.add("Harga");
	   					
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
	               if(searchby.getSelectedItem().equals("Harga")){
	            	   int cari = Integer.parseInt(txt_search.getText());
					   
	   				try{
	   					DBkasir db = new DBkasir();
	   					Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	   					data = db.cari_harga(cari);
	   					Vector<Object> columnNames = new Vector<Object>();
	   					columnNames.add("Nomor Seri");
	   	  		     	columnNames.add("Barang");
	   	  		     	columnNames.add("Jumlah");
	   	  		     	columnNames.add("Satuan");
	   	  		      	columnNames.add("Harga");
	   					
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
	            }
	        
			
		if(e.getSource() == btn_delete) {
			try {
	            DBkasir db = new DBkasir();
	            
	            int idint = Integer.parseInt(txt_id.getText());
	            db.deleteHISTORY(idint);
	            
	            Vector<Vector<Object>> data = null;
			      Vector<Object> columnNames = new Vector<Object>();
			      columnNames.add("Nomor Seri");
			      columnNames.add("Barang");
			      columnNames.add("Jumlah");
			      columnNames.add("Satuan");
			      columnNames.add("Harga");
	          
	            try {
	                data = db.selectHISTORY();
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
	            System.out.println("TEST");

	            //model.addRow(new Object[]{kb1.getText(),jb1.getText(),plis1.getText(),pbit1.getText(),no_rak1.getSelectedItem().toString()});
	        }
	        catch(Exception f){
	            System.out.println(f);
	        }

	        txt_id.setText("");
	    }
	            
	}	

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
}
public class History{
	public static void main(String[] args){
		new jHistory(); //Langsung Munculkan Frame
	}
}