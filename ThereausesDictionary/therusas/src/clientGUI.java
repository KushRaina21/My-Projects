import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
/**
 * Name : Kush Raina
 * ID: 1001567809 
 * This class is used to generate the client gui which will take the user input 
 * The code below is refrenced from below url's 
 * URL1: https://www.youtube.com/watch?v=kqBmsLvWU14 
 * URL2:https://www.youtube.com/watch?v=MFAL8x7ipsA&t=36s
 * URL3:https://www.mkyong.com/java/how-to-read-file-from-java-bufferedreader-example/ 
 * URL4:https://www.youtube.com/watch?v=vCDrGJWqR8w&t=1138s
 * */

public class clientGUI  {

	private JFrame frmClientGui;   // Jframe object created to instantiate client GUI
	private JTextField textField;  // JtextField object created to display text area in GUI
    private String text;           // text string to hold the value which we want to return to client server 
    JButton btnSubmit;             //Jbutton object to create the Submit button after which the client will start.
	private JButton btnExit;       //Jbutton object to create the Submit button after which the client will start.
	/**
	 * Launch the application.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		EventQueue.invokeLater(new Runnable() {			// This function is written to keep EDT (Event Dispatching Thread). on hold so that you can update the rest of the GUI
			public void run() {
				try {
					clientGUI window = new clientGUI();		//call the constructor of clientGUI class 
					window.frmClientGui.setVisible(true);			//set the contents of the clientGUI to be displayed on the screen	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		 // clientSocket = new Socket("127.0.0.1", 1342);
	}

	/**
	 * Create the application.
	 */
	public clientGUI() {
		
		initialize();			//function is used to initialize the contents of the frame
	}

	/**
	 * Initialize the contents of the frame and open client GUI so that user can enter his word to search.
	 * Input 1: User has to enter word it wants to search in the text area given in the GUI and click on the search button.
	 * Input 2: User has to click on add new word button if he wants to add a new word in the dictionary.
	 * Output : This class will create a GUI asking the user to input a word which he wants to search in the dictionary.
	 */
	private void initialize() {
		frmClientGui = new JFrame();			//initialize the Jframe object to create client gui window
		frmClientGui.setTitle("Client GUI");			//set the title for client window  
		frmClientGui.setBounds(100, 100, 450, 300);			
		frmClientGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			//To change the default behaviour of JFrame object to close
		frmClientGui.getContentPane().setLayout(null);			//this is used to place the components as they are in Frame as window cannot be resized
		
		textField = new JTextField();			//this text field is created so that we can enter the word which we want to search
		textField.setBounds(159, 102, 86, 20);
		frmClientGui.getContentPane().add(textField);			//adding the text box field to JFrame object
		textField.setColumns(10);
		
		 btnSubmit = new JButton("Submit");			//creating Submit button to give submit the word which we want to search
		btnSubmit.addActionListener(new ActionListener() {			//creating an action listener implementation class which will prevent us from forming other class for action listner         
			public void actionPerformed(ActionEvent arg0) {			//function to get the text from the text field when we press submit button and pass the word  to client Window  
				  text = textField.getText();			//get the word which user has entered from the text box
					//System.out.println(" first "+text);
					frmClientGui.dispose();			//close the clientGUI window when text recieved from user
					new clientWindow(text);			//calling the Client Window GUI application with the text from user as parameter.
					
					
			}
		});
		btnSubmit.setBounds(21, 163, 89, 23);
		frmClientGui.getContentPane().add(btnSubmit);			//add the Submit button to main pane
		
		JButton btnAddWord = new JButton("Add Word");			//create Add Word button if user wants to add a new word in dictionary
		btnAddWord.addActionListener(new ActionListener() {			//creating an action listener implementation class which will prevent us from forming other class for action listner
			public void actionPerformed(ActionEvent arg0) {			//function will call the addWord class to implement add word functionality.
				new addWord();
			}
		});
		btnAddWord.setBounds(159, 163, 113, 23);
		frmClientGui.getContentPane().add(btnAddWord);			//adding the Add Word button to the main pane
		btnExit = new JButton("Exit");			//create an EXIT button to exit from the client GUI window
		btnExit.addActionListener(new ActionListener() {			//implement the action listner class for EXIT button
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);			//this command will make us exit from our current operations
			}
		});
		btnExit.setBounds(315, 163, 89, 23);
		frmClientGui.getContentPane().add(btnExit);			//adding the exit button to main pane window
	}
}
