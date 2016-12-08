package Inter;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
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

import com.mysql.jdbc.Connection;


class jConfirm extends JFrame implements ItemListener, ActionListener{ 
	Connection conn = null;
	JTable table = null;
	JButton btn_ok = new JButton("Bayar");
	JButton btn_exit = new JButton("CANCEL");
	JButton btn_hitung = new JButton("Hitung");
	
	 
	jConfirm(){
		
		setTitle("Confirmation");
		setSize(500, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel panel1 = new JPanel(new BorderLayout());
		JPanel panel2 = new JPanel(new FlowLayout());//GridLayout(1, 5));
		JPanel panel3 = new JPanel(new FlowLayout());//GridLayout(1, 2));
		JPanel panel4 = new JPanel(new GridLayout(4, 2));
		

		JLabel lbl_total = new JLabel("TOTAL PRICE");
		JTextField txt_total = new JTextField();	
		JLabel lbl_payment = new JLabel("PAYMENT");
		JTextField txt_payment = new JTextField();	
		JLabel lbl_charge = new JLabel("CHARGE");
		JTextField txt_charge = new JTextField();	
		
		Vector<Vector<Object>> data = null;
		Vector<Vector<Object>> total = null;
		JTable table = new JTable();
	      Vector<Object> columnNames = new Vector<Object>();
	      columnNames.add("ID");
	      columnNames.add("User ID");
	      columnNames.add("Order");
	      columnNames.add("Price");
	      columnNames.add("Quantity");
	      columnNames.add("Total Price");
	      
	            try {
		            DBkasir db = new DBkasir();
		            // 1 itu ID
		            int a = Integer.parseInt(Variabel.id_login);
		            data = db.select_pesanan(a, 0);
		            Vector<Vector<Object>> data_total = db.cari_totalharga(Integer.parseInt(Variabel.id_login), 0);
		            String b= data_total.get(1).toString();
		            txt_total.setText(b);
		            
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
	      
	   
			
			
			btn_ok.addActionListener(this);
			btn_exit.addActionListener(this);
			btn_hitung.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	DBkasir db;
					try {
						db = new DBkasir();
						int a = Integer.parseInt(Variabel.id_login);
						Vector<Vector<Object>> data_total = db.cari_totalharga(a, 0);
			            String b= data_total.get(1).toString();
			            txt_total.setText(b);
			            
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		            // 1 itu ID
		           
		            
	            }
			});
			panel1.add(scrollPanel);
			panel4.add(lbl_total);
			panel4.add(txt_total);
			
			
			panel4.add(lbl_payment);
			panel4.add(txt_payment);
			panel4.add(lbl_charge);
			panel4.add(txt_charge);
			panel3.add(btn_hitung);
			panel3.add(btn_ok);
			panel3.add(btn_exit);

		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		add(panel1);
		panel2.add(panel4);
		add(panel2);
		add(panel3);
		
		setVisible(true);
	}
	
	
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
	public int getSum(){
		int rowsCount = table.getRowCount();
		int sum = 0;
		for(int i=1;i <rowsCount;i++){
			sum = sum + Integer.parseInt(table.getValueAt(i, 5).toString());
		}
		return sum;
	}
public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn_hitung){
			
		}
		
		if(e.getSource() == btn_ok) {
			JOptionPane.showMessageDialog(this, "Accepted");//Message yang muncul
			new jMenu();
			this.dispose();		
			}
		if(e.getSource() == btn_exit) {
			new jMenu();
			this.dispose();		
		}
		
	}
	
}

public class Confirm{
	public static void main(String[] args){
		new jConfirm(); //Langsung Munculkan Frame
		
	}
	
	
}