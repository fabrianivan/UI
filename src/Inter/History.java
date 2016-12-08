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

class jHistory  extends JFrame implements ItemListener, ActionListener{ 
	
	JTable table = null;
	JButton btn_search = new JButton("SEARCH");
	JButton btn_update = new JButton("UPDATE");
	JButton btn_delete = new JButton("DELETE");
	JButton btn_exit = new JButton("EXIT");
	
	jHistory(){
		setTitle("Transaction History");
		setSize(500, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel panel1 = new JPanel(new FlowLayout());
		JPanel panel2 = new JPanel(new FlowLayout());//GridLayout(1, 2));
		JPanel panel3 = new JPanel(new FlowLayout());//GridLayout(1, 5));
        Connection conn;
        Statement st;
		
	      try { //Conn dan mengisi tabel
	    	  Class.forName("com.mysql.jdbc.Driver");
	          System.out.println("Connecting to database");
	          conn = DriverManager.getConnection(
	  				"jdbc:mysql://localhost:3307/kasir_restoran",
	  				"root", "");
	          System.out.println("Connected to databse");
	        
	          Vector<Vector<Object>> data = null;
		      Vector<Object> columnNames = new Vector<Object>();
		      columnNames.add("id");
		      columnNames.add("fullname");
		      columnNames.add("gender");
		      columnNames.add("address");
		      columnNames.add("phone");
		      
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
		  	JTextField txt_search = new JTextField();
		  	txt_search.setPreferredSize( new Dimension( 330, 24 ) );
			JScrollPane scrollPanel = new JScrollPane(table);

			panel1.add(scrollPanel);
			panel2.add(txt_search);
			panel2.add(btn_search);
			panel3.add(btn_update);
			panel3.add(btn_delete);
			panel3.add(btn_exit);

		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		add(panel2);
		add(panel1);
		add(panel3);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn_update) {
			JOptionPane.showMessageDialog(this, "Accepted");//Message yang muncul
			new Menu();
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
public class History{
	public static void main(String[] args){
		new jHistory(); //Langsung Munculkan Frame
	}
}