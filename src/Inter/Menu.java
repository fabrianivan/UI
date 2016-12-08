package Inter;

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

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
	
	jMenu() {
		setTitle("Main Menu");
		setSize(550, 450);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel panel1 = new JPanel(new GridLayout(1, 4));
		JPanel panel11 = new JPanel(new GridLayout(5, 1));//gambar food
		JPanel panel12 = new JPanel(new GridLayout(10, 2));//tulisan
		JPanel panel13 = new JPanel(new GridLayout(3, 1));//gbr drink
		JPanel panel14 = new JPanel(new GridLayout(4, 2));//tulisan
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
		JTextField txt_price1 = new JTextField();
		JTextField txt_price2 = new JTextField();
		JTextField txt_price3 = new JTextField();
		JTextField txt_price4 = new JTextField();
		JTextField txt_price5 = new JTextField();
		JTextField txt_price6 = new JTextField();
		JTextField txt_price7 = new JTextField();
		JTextField txt_quantity1 = new JTextField();
		JTextField txt_quantity2 = new JTextField();
		JTextField txt_quantity3 = new JTextField();
		JTextField txt_quantity4 = new JTextField();
		JTextField txt_quantity5 = new JTextField();
		JTextField txt_quantity6 = new JTextField();
		JTextField txt_quantity7 = new JTextField();
		
		File makan1 = new File("/04.png");
		Image img;
		ImageIcon im = new ImageIcon();
		try {
			img = ImageIO.read(makan1);
			img = img.getScaledInstance(50, 50, 0);
			im = new ImageIcon(img);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		panel11.add(new JLabel(im));
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


		panel13.add(new JLabel());
		panel14.add(lbl_price6);
		panel14.add(txt_price6);
		panel14.add(lbl_quantity6);
		panel14.add(txt_quantity6);
		panel14.add(lbl_price7);
		panel14.add(txt_price7);
		panel14.add(lbl_quantity7);
		panel14.add(txt_quantity7);
		
		panel1.add(panel11);
		panel1.add(panel12);
		panel1.add(panel13);
		panel1.add(panel14);

		btn_confirm.addActionListener(this);
		btn_signout.addActionListener(this);
		btn_help.addActionListener(this);

		panel15.add(btn_help);
		panel15.add(btn_confirm);
		panel15.add(btn_signout);
		
		TitledBorder titlefood;
		titlefood = BorderFactory.createTitledBorder("Food Menu");
		panel12.setBorder(titlefood);
		TitledBorder titledrink;
		titledrink = BorderFactory.createTitledBorder("Drink Menu");
		panel14.setBorder(titledrink);
		
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		add(panel1);
		//add(panel2);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn_confirm) {
			JOptionPane.showMessageDialog(this, "Accepted");//Message yang muncul
			new jConfirm();
			this.dispose();		
			}
		if(e.getSource() == btn_signout) {
			new jLogin();
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
