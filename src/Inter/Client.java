package Inter;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client extends JFrame {

    BufferedReader in;
    PrintWriter out;
    JFrame frame = new JFrame("Chatter");
    JTextField textField = new JTextField(40);
    JTextArea messageArea = new JTextArea(8, 40);
   // JButton label = new JButton("Panggil pelayan");
   

    Client() {
    	
        //JFrame frame = new JFrame("Chatter");
        
        JButton label = new JButton("Panggil pelayan");
        // Layout GUI
        textField.setEditable(false);
        messageArea.setEditable(false);
        frame.getContentPane().add(textField, "North");
        frame.getContentPane().add(new JScrollPane(messageArea), "Center");
        frame.getContentPane().add(label,"South");
        frame.pack();
        frame.setSize(100,100);
        textField.setText("Server");
        frame.pack();


        // Add Listeners
        textField.addActionListener(new ActionListener() {
            /**
             * Responds to pressing the enter key in the textfield by sending
             * the contents of the text field to the server.    Then clear
             * the text area in preparation for the next message.
             */
            public void actionPerformed(ActionEvent e) {
                out.println(textField.getText());
                textField.setText("memanggil pelayan");
                textField.setEditable(false);
            }
        });
        label.addActionListener(new ActionListener(){
 			public void actionPerformed(ActionEvent e){
 				label.setText("Waiting");
 				textField.setText("Memanggil pelayan");
 				JOptionPane.showMessageDialog(frame,  "Pelayan akan datang");
 				
 				textField.setEditable(false);
 				out.println(textField.getText());
 				
				textField.setText("");
 				
 			}
 			
 		});
    }
    
    
    private String getServerAddress() {
        return JOptionPane.showInputDialog(
            frame,
            "Enter IP Address of the Server:",
            "Welcome to the Chatter",
            JOptionPane.QUESTION_MESSAGE);
    	
    }
   
    
    /**
     * Prompt for and return the desired screen name.
     */


    /**
     * Connects to the server then enters the processing loop.
     */
    private String dapatNama() {
        return JOptionPane.showInputDialog(
            frame,
            "Masukkan nama:",
            "Nama",
            JOptionPane.PLAIN_MESSAGE);
    }
    void run() throws IOException {

        // Make connection and initialize streams
        // String serverAddress = getServerAddress();
        
    	InetAddress IP = InetAddress.getLocalHost();
    	
    	//String serverAddress = IP.getHostAddress().toString();
    	String serverAddress = getServerAddress();
    	
    	
        Socket socket = new Socket(serverAddress, 9001);
        in = new BufferedReader(new InputStreamReader(
            socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        String nama = dapatNama();
        
        // Process all messages from server, according to the protocol.
        while (true) {
            String line = in.readLine();
            
            if (line.startsWith("SUBMITNAME")) {
                //out.println(getName());
            	out.println(nama);
            } else if (line.startsWith("NAMEACCEPTED")) {
                textField.setEditable(false);
            } else if (line.startsWith("MESSAGE")) {
            	//messageArea.setText(" memanggil pelayan. \n");
            	messageArea.setEditable(false);
                messageArea.append(line.substring(8) + "\n");
            }
        }
    }

    /**
     * Runs the client as an application with a closeable frame.
     */
    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.setVisible(true);
        client.run();
    }
}