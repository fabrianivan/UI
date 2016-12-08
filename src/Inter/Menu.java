package Inter;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

class jMenu extends JFrame implements ItemListener, ActionListener{ 
	
	private Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}	
	JTable table = null;
	JButton btn_help = new JButton("CALL WAITRESS");
	JButton btn_confirm = new JButton("CONFIRM");
	JButton btn_signout = new JButton("SIGN OUT");

	JLabel txt_price1 = new JLabel("");	
	JLabel txt_price2 = new JLabel("");	
	JLabel txt_price3 = new JLabel("");	
	JLabel txt_price4 = new JLabel("");	
	JLabel txt_price5 = new JLabel("");	
	JLabel txt_price6 = new JLabel("");	
	JLabel txt_price7 = new JLabel("");	
	JTextField txt_quantity1 = new JTextField();
	JTextField txt_quantity2 = new JTextField();
	JTextField txt_quantity3 = new JTextField();
	JTextField txt_quantity4 = new JTextField();
	JTextField txt_quantity5 = new JTextField();
	JTextField txt_quantity6 = new JTextField();
	JTextField txt_quantity7 = new JTextField();
    
	jMenu() {
		setTitle("Main Menu");
		setSize(550, 480);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel panel1 = new JPanel(new GridLayout(1, 4));
		JPanel panel11 = new JPanel(new GridLayout(5, 1));//gambar food
		JPanel panel12 = new JPanel(new GridLayout(10, 2));//tulisan
		JPanel panel13 = new JPanel(new GridLayout(3, 1));//gbr drink
		JPanel panel14 = new JPanel(new GridLayout(12, 1));//tulisan
		JPanel panel15 = new JPanel(new FlowLayout());//tulisan
		JPanel panel2 = new JPanel(new FlowLayout());//button

		JLabel lbl_price1 = new JLabel("Price");	
		JLabel lbl_quantity1 = new JLabel("Qty");			
		JLabel lbl_price2 = new JLabel("Price");
		JLabel lbl_quantity2 = new JLabel("Qty");	
		JLabel lbl_price3 = new JLabel("Price");
		JLabel lbl_quantity3 = new JLabel("Qty");	
		JLabel lbl_price4 = new JLabel("Price");
		JLabel lbl_quantity4 = new JLabel("Qty");	
		JLabel lbl_price5 = new JLabel("Price");
		JLabel lbl_quantity5 = new JLabel("Qty");	
		JLabel lbl_price6 = new JLabel("Price");
		JLabel lbl_quantity6 = new JLabel("Qty");	
		JLabel lbl_price7 = new JLabel("Price");
		JLabel lbl_quantity7 = new JLabel("Qty");	
		JLabel kosong = new JLabel("");	

		File menu1 = new File("src/01.png");
		File menu2 = new File("src/02.png");
		File menu3 = new File("src/03.png");
		File menu4 = new File("src/04.png");
		File menu5 = new File("src/05.png");
		File drink1 = new File("src/d01.png");
		File drink2 = new File("src/d02.png");
		Image img;
		
		ImageIcon im = new ImageIcon();
		try {
			img = ImageIO.read(menu1);
			img = img.getScaledInstance(80, 80, 0);
			im = new ImageIcon(img);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Image img2;
		ImageIcon im2 = new ImageIcon();
		try {
			img2 = ImageIO.read(menu2);
			img2 = img2.getScaledInstance(80, 80, 0);
			im2 = new ImageIcon(img2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Image img3;
		ImageIcon im3 = new ImageIcon();
		try {
			img3 = ImageIO.read(menu3);
			img3 = img3.getScaledInstance(80, 80, 0);
			im3 = new ImageIcon(img3);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Image img4;
		ImageIcon im4 = new ImageIcon();
		try {
			img4 = ImageIO.read(menu4);
			img4 = img4.getScaledInstance(80, 80, 0);
			im4 = new ImageIcon(img4);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Image img5;
		ImageIcon im5 = new ImageIcon();
		try {
			img5 = ImageIO.read(menu5);
			img5 = img5.getScaledInstance(80, 80, 0);
			im5 = new ImageIcon(img5);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Image i1;
		ImageIcon ig1 = new ImageIcon();
		try {
			i1 = ImageIO.read(drink1);
			i1 = i1.getScaledInstance(120, 140, 0);
			ig1 = new ImageIcon(i1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Image i2;
		ImageIcon ig2 = new ImageIcon();
		try {
			i2 = ImageIO.read(drink2);
			i2 = i2.getScaledInstance(120, 140, 0);
			ig2 = new ImageIcon(i2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		try 
		{
			DBkasir db = new DBkasir();
			
			Integer code1 = 1;//Integer.parseInt(txt_phone.getText());
        	Integer currentqty1 = db.pricemenu(code1);
        	String quatity1 = db.pricemenu2(code1);
        	System.out.println(currentqty1 + " & " + quatity1);
        	txt_price1.setText(quatity1);
        	if(currentqty1 < 0) 
        	{
    		//JOptionPane.showMessageDialog(this, "Menu1 sudah habis");//Message yang muncul
        	txt_quantity1.setEnabled(false);
        	}   
		}
        catch(Exception j){
            System.out.println(j);
        } 

		txt_price1.setText("28000");
		txt_price2.setText("35000");
		txt_price3.setText("40000");
		txt_price4.setText("28000");
		txt_price5.setText("33000");
		txt_price6.setText("21000");
		txt_price7.setText("15000");
		panel11.add(new JLabel(im));
		panel11.add(new JLabel(im2));
		panel11.add(new JLabel(im3));
		panel11.add(new JLabel(im4));
		panel11.add(new JLabel(im5));
		panel12.add(lbl_price1);
		panel12.add(txt_price1);
		panel12.add(lbl_quantity1);
		panel12.add(txt_quantity1);
		panel12.add(lbl_price2);
		panel12.add(txt_price2);
		panel12.add(lbl_quantity2);
		panel12.add(txt_quantity2);
		panel12.add(lbl_price3);
		panel12.add(txt_price3);
		panel12.add(lbl_quantity3);
		panel12.add(txt_quantity3);
		panel12.add(lbl_price4);
		panel12.add(txt_price4);
		panel12.add(lbl_quantity4);
		panel12.add(txt_quantity4);
		panel12.add(lbl_price5);
		panel12.add(txt_price5);
		panel12.add(lbl_quantity5);
		panel12.add(txt_quantity5);


		//panel13.add(new JLabel());
		panel13.add(new JLabel(ig1));
		panel13.add(new JLabel(ig2));
		panel14.add(lbl_price6);
		panel14.add(txt_price6);
		panel14.add(lbl_quantity6);
		panel14.add(txt_quantity6);
		panel14.add(lbl_price7);
		panel14.add(txt_price7);
		panel14.add(lbl_quantity7);
		panel14.add(txt_quantity7);

		btn_confirm.addActionListener(this);
		btn_signout.addActionListener(this);
		btn_help.addActionListener(this);

		panel14.add(kosong);
		panel14.add(btn_help);
		panel14.add(btn_confirm);
		panel14.add(btn_signout);
		
		TitledBorder titlefood;
		titlefood = BorderFactory.createTitledBorder("Food Menu");
		panel12.setBorder(titlefood);
		TitledBorder imgfood;
		imgfood = BorderFactory.createTitledBorder("Food Picture");
		panel11.setBorder(imgfood);
		TitledBorder titledrink;
		titledrink = BorderFactory.createTitledBorder("Drink Menu");
		panel14.setBorder(titledrink);
		TitledBorder imgdrink;
		imgdrink = BorderFactory.createTitledBorder("Drink Picture");
		panel13.setBorder(imgdrink);

		//panel14.add(panel15);
		
		panel1.add(panel11);
		panel1.add(panel12);
		panel1.add(panel13);
		panel1.add(panel14);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		add(panel1);
		//add(panel2);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn_confirm) {
			try {
				Integer qty = null;
            	Integer code = null;
            	Integer price = null;
                DBkasir db = new DBkasir();
                if(!txt_quantity1.getText().equals(""))
                {
                	 qty = Integer.parseInt(txt_quantity1.getText());
                	 code = 1;//Integer.parseInt(txt_phone.getText());
                	 price = Integer.parseInt(txt_price1.getText());
                	db.buymenu(qty, code);
                	Variabel.pmenu1 = qty * price;
                	Variabel.total = Variabel.total + Variabel.pmenu1;
                	db.inserthistory("Spicy", qty, price, Variabel.pmenu1);
                }
                if(!txt_quantity2.getText().equals(""))
                {
                	 qty = Integer.parseInt(txt_quantity2.getText());
                	 code = 2;//Integer.parseInt(txt_phone.getText());
                	db.buymenu(qty, code);
                	Variabel.pmenu2 = qty * Integer.parseInt(txt_price2.getText());
                	Variabel.total = Variabel.total + Variabel.pmenu2;
                }
                if(!txt_quantity3.getText().equals(""))
                {
                	 qty = Integer.parseInt(txt_quantity3.getText());
                	 code = 3;//Integer.parseInt(txt_phone.getText());
                	db.buymenu(qty, code);
                	Variabel.pmenu3 = qty * Integer.parseInt(txt_price3.getText());
                	Variabel.total = Variabel.total + Variabel.pmenu3;
                }
                if(!txt_quantity4.getText().equals(""))
                {                
                	 qty = Integer.parseInt(txt_quantity4.getText());
                	 code = 4;//Integer.parseInt(txt_phone.getText());
                	db.buymenu(qty, code);
                	Variabel.pmenu4 = qty * Integer.parseInt(txt_price4.getText());
                	Variabel.total = Variabel.total + Variabel.pmenu4;
                }
                if(!txt_quantity5.getText().equals(""))
                {
                	 qty = Integer.parseInt(txt_quantity5.getText());
                	 code = 5;//Integer.parseInt(txt_phone.getText());
                	db.buymenu(qty, code);
                	Variabel.pmenu5 = qty * Integer.parseInt(txt_price5.getText());
                	Variabel.total = Variabel.total + Variabel.pmenu5;
                }
                if(!txt_quantity6.getText().equals(""))
                {
                	 qty = Integer.parseInt(txt_quantity6.getText());
                	 code = 6;//Integer.parseInt(txt_phone.getText());
                	db.buymenu(qty, code);
                	Variabel.pmenu6 = qty * Integer.parseInt(txt_price6.getText());
                	Variabel.total = Variabel.total + Variabel.pmenu6;
                }
                if(!txt_quantity7.getText().equals(""))
                {
                	 qty = Integer.parseInt(txt_quantity7.getText());
                	 code = 7;//Integer.parseInt(txt_phone.getText());
                	db.buymenu(qty, code);
                	Variabel.pmenu7 = qty * Integer.parseInt(txt_price7.getText());
                	Variabel.total = Variabel.total + Variabel.pmenu7;
                }
                JOptionPane.showMessageDialog(this, "HARGA TOTAL: " + Variabel.total);//Message yang muncul
                JOptionPane.showMessageDialog(this, "Silahkan tunggu pesanan Anda");//Message yang muncul
    			Variabel.total = 0;
    			new jMenu();
    			this.dispose();
            
            }
            catch(Exception j){
                System.out.println(j);
            }
			//JOptionPane.showMessageDialog(this, "Accepted");//Message yang muncul
                        	
			}
		if(e.getSource() == btn_signout) {
			new jLogin();
			this.dispose();		
			}
		if(e.getSource() == btn_help) {
			
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
}
public class Menu{
	public static void main(String[] args) throws IOException{
		new jMenu(); //Langsung Munculkan Frame
	}
}
