import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JTextArea;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * Name : Kush Raina
 * ID: 1001567809 
 * This class is used to generate the Client GUI ,start the client which will make socket connection to server and get response from server
 * The code below is refrenced from below url's 
 * URL1: https://www.youtube.com/watch?v=kqBmsLvWU14 
 * URL2:https://www.youtube.com/watch?v=MFAL8x7ipsA&t=36s
 * URL3:https://www.mkyong.com/java/how-to-read-file-from-java-bufferedreader-example/ 
 * URL4:https://www.youtube.com/watch?v=vCDrGJWqR8w&t=1138s 
 * URL4:https://www.youtube.com/watch?v=vCDrGJWqR8w&t=1138s
 * 
 *  
 * */

public class clientWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;      // Jpanel object is used to create Client GUI panel
	private String str;              // String object is used to hold the string which we will passing onto the client
	JTextArea textArea;              // JTextArea object is used to create a text area where we will be able to see all the messages of client server.
	JButton btnNewButton ;           // Button object is created in order to create a button which will exit client server when user wants it
	Socket clientSocket;             // Socket object is created in order to make a connection with server
	Scanner s;                       // Scanner object is created in order to recieve input from the server 
	PrintStream pw;                  // Print Stream is used to send data to server from client
	private JButton btnSearchAgain;  // Button object is created if user wants to search for another word.
	
	
	/**
	 * This function creates client window which displays all the messages from the server 
	 * @param text which the user has given in the intial Client GUI
	 */
	
	
	public clientWindow(String text) {
		this.str=text;			//initialize the this object of clientWindow class with the text you will recieve from clientGUI
		setTitle("Client Console");			//set the title of window as Client Console
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			//To change the default behaviour of JFrame object to close
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();			//initialize the Jpanel window for clientWindow GUI
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();			//adding Grid Layout for our clientWindow GUI 
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{ 1.0, Double.MIN_VALUE, 0.0, 0.0, 1.0};
		gbl_contentPane.rowWeights = new double[]{ 1.0, 1.0};
		contentPane.setLayout(gbl_contentPane);			//setting the layout of gui as Grid Layout as defined above 
		 
		  btnNewButton = new JButton("Exit");			//creating Exit button to exit from client window gui
		  btnNewButton.addActionListener(new ActionListener() {			//creating an action listener implementation class which will prevent us from forming other class for action listner
		  	public void actionPerformed(ActionEvent arg0) {
		  		System.exit(0);			//this command will make us exit from our current operations
		  	}
		  });
		 GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		 gbc_btnNewButton.gridwidth = 10;
		 gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		 gbc_btnNewButton.gridx = 0;
		 gbc_btnNewButton.gridy = 0;
		 contentPane.add(btnNewButton, gbc_btnNewButton);			//adding the exit button to the contentPane  
		 
		 btnSearchAgain = new JButton("Search Again");			//creating Search Again button if user wants to search again for some other word
		 btnSearchAgain.addActionListener(new ActionListener() {		//creating an action listener implementation class which will prevent us from forming other class for action listner
		 	public void actionPerformed(ActionEvent arg0) {			//this function will call the clientGUI again for the user another word for his search.
		 		clientGUI g=new clientGUI();
		 		String [] str1={"ggh","hgh"};
		 		try {
					g.main(str1);
					contentPane.setVisible(false);			//setting the visibility of the current clientWindow as false
					dispose();			//close the current GUI window

					
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 		
		 	}
		 });
		 GridBagConstraints gbc_btnSearchAgain = new GridBagConstraints();
		 gbc_btnSearchAgain.insets = new Insets(0, 0, 5, 5);
		 gbc_btnSearchAgain.gridx = 22;
		 gbc_btnSearchAgain.gridy = 0;
		 contentPane.add(btnSearchAgain, gbc_btnSearchAgain);			//adding the Search Again button to main content pane
		
		 textArea = new JTextArea(67,80);			//Creating Jtext area object to display all the messages which user wants to display at client side
		 JScrollPane scrollText=new JScrollPane(textArea);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridheight = 3;
		gbc_textArea.gridwidth = 50;
		gbc_textArea.insets = new Insets(0, 0, 5, 0);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 1;
		contentPane.add(scrollText, gbc_textArea);			// adding text area to main content pane
		setVisible(true);			//  to set visible the gui contents which you have given till now
		textArea.append("Hi this is client window "+"\n\r");			//adding our first message onto client screen
		try {
			 clientSocket=new Socket("127.0.0.1", 1342);			//creating socket connection from client side to local host on port number 1342
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			 pw=new PrintStream(clientSocket.getOutputStream());			//initializing print stream object to send whatever is written to it socket output stream
			 pw.println(str);			//sending the text which we want to search on the output stream
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		textArea.append("Client has send  \" "+str+" \" word to search in dictionary"+"\n\r");			//display the message that client has send the message to server
		try {
			 s=new Scanner(clientSocket.getInputStream());			//using scanner object to read in values from input stream of socket
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String result=s.next();			//read all the contents from the scanner object and store it in result varaiable
		while (s.hasNext()) {
			 result =result+" "+  s.next();
			
		}
		 textArea.append(str+" = "+result);			//display the word along with its synonyms on the client screen
		 pw.close();			// close the printStream object after use
		

	}

}
