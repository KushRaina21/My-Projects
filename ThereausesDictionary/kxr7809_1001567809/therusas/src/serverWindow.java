import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridBagLayout;

import javax.swing.JTextArea;

import java.awt.GridBagConstraints;

import javax.swing.JButton;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
/**
 * 
 * Name : Kush Raina
 * ID: 1001567809 
 * This class is used to generate the server GUI which will display the messages of server
 * The code below is refrenced from below url's 
 * URL1: https://www.youtube.com/watch?v=kqBmsLvWU14 
 * URL2:https://www.youtube.com/watch?v=MFAL8x7ipsA&t=36s
 * URL3:https://www.mkyong.com/java/how-to-read-file-from-java-bufferedreader-example/   
 * 
 *  
 * */

public class serverWindow {

	private static JFrame frmServerConsole;			// Jframe object is created to make server GUI
	static JButton btnNewButton;					// Button object is created in order to exit the server anytime user wants  
	static JTextArea textArea;						// JtextArea object is created in order to display all the objects of server
	static ServerSocket ss;                         // ServerSocket object is created in order to create server socket so that client can pass on the messages to server
	static Socket s1;								// Socket object is created in order to get messages from client 
	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		getInput();
	}

	

	/**
	 * Initialize the contents of the frame i.e create a Server which will display all the messages of server untill explicitly told bu user to exit
	 * @throws IOException 
	 */
	public static void getInput() throws IOException {
		
		ss=new ServerSocket(1342);			//create a server socket object to open the port for communication
		 frmServerConsole = new JFrame();		//initialize the Jframe object to create Server gui window 
		 btnNewButton = new JButton("Exit");	//initialize JButton object to create Exit button on the server console
		 textArea = new JTextArea();			//initialize JtextArea object to create a TextArea on the screen which will display server messages
		 while(true){			//server connection will be open until user will exit from server portal
			 s1=ss.accept();			//to accept any client requesting for connection on the above port
				Scanner s=new Scanner(s1.getInputStream());			//scanner object created to recieve inputs from client from the socket object created by server
				String text = s.next();			//text object is created to store the text which user wants to search
				//System.out.println("input text=  "+text);			
				 String text1=searchWord(text);			//searchWord function is called to get all the synonyms for the particular word which we are passing as argument to this function and storing the list of words in another variable
				 if(text1!=null)			//to check if above function found any related words in our text file or nothing was found. 
				{
					
					 
		frmServerConsole.setTitle("Server Console");			//setting the title for server console
		frmServerConsole.setBounds(100, 100, 450, 300);
		frmServerConsole.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			//To change the default behaviour of JFrame object to close
		textArea.append("Server is up and running"+"\n\r");			//adding or appending log messages onto the server console
        
		GridBagLayout gridBagLayout = new GridBagLayout();			// Initializing a grid bag layout for our server gui
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};			// Initializing the contents of our GridBag Layout system
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		frmServerConsole.getContentPane().setLayout(gridBagLayout);			//adding the grid layout to our frame which we have created.

		
		 btnNewButton.addActionListener(new ActionListener() {			//implement the action listner class for EXIT button
			 	public void actionPerformed(ActionEvent arg0) {
			 		System.exit(0);			//this command will make us exit from our current operations
			 	}
			 });
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();			//add the EXIT button created to the main grid layout
		gbc_btnNewButton.gridwidth = 8;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		frmServerConsole.getContentPane().add(btnNewButton, gbc_btnNewButton);			
		
		 
		GridBagConstraints gbc_textArea = new GridBagConstraints();			//add the text area we created onto the Grid Layout
		gbc_textArea.gridwidth = 8;
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 1;
		frmServerConsole.getContentPane().add(textArea, gbc_textArea);			
		textArea.append("connection recieved from client "+"\n\r");			//dispalaying messages that connection recieved from client
		textArea.append("server has recieved  \""+text+"\" from client\n\r");			//displaying what word has been recieved from server
		frmServerConsole.setVisible(true);			//display the contents which you have created for server on the screen
		PrintStream pw=new PrintStream(s1.getOutputStream());			//setup printstream object which will reference to the output port of the server 
		pw.print(text1);			//send the synonym value which we have recieved from our server onto the output port through PrintStream object.
		pw.close();			//close the print writer stream as soon as you are finished sending the data
		s1.close();			//close the socket connection 
		textArea.append("server has given response to client \n\r");			//pass on the message to server console that message sent to client
				}
				 else
				 {
					 frmServerConsole.setTitle("Server Console");			//setting the title for server console
						frmServerConsole.setBounds(100, 100, 450, 300);
						frmServerConsole.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			//To change the default behaviour of JFrame object to close
						GridBagLayout gridBagLayout = new GridBagLayout();			// Initializing a grid bag layout for our server gui
						gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
						gridBagLayout.rowHeights = new int[]{0, 0, 0};
						gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
						gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
						frmServerConsole.getContentPane().setLayout(gridBagLayout);
						
						
						 btnNewButton.addActionListener(new ActionListener() {			//implement the action listner class for EXIT button
							 	public void actionPerformed(ActionEvent arg0) {
							 		System.exit(0);			//this command will make us exit from our current operations
							 	}
							 });
						GridBagConstraints gbc_btnNewButton = new GridBagConstraints();			//add the EXIT button created to the main grid layout
						gbc_btnNewButton.gridwidth = 8;
						gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
						gbc_btnNewButton.gridx = 0;
						gbc_btnNewButton.gridy = 0;
						frmServerConsole.getContentPane().add(btnNewButton, gbc_btnNewButton);
						textArea.append("Server is up and running"+"\n\r");			//adding or appending log messages onto the server console
						 
						GridBagConstraints gbc_textArea = new GridBagConstraints();			//add the text area we created onto the Grid Layout
						gbc_textArea.gridwidth = 8;
						gbc_textArea.fill = GridBagConstraints.BOTH;
						gbc_textArea.gridx = 0;
						gbc_textArea.gridy = 1;
						frmServerConsole.getContentPane().add(textArea, gbc_textArea);
						textArea.append("connection recieved from client "+"\n\r");			//dispalaying messages that connection recieved from client
						textArea.append("server could not find the word in its dictionary"+"\n\r");			//displaying message that server could not find the word in the dictionary
						frmServerConsole.setVisible(true);			//displaying contents which we have created onto the screen
					 PrintStream pw=new PrintStream(s1.getOutputStream());			//setup printstream object which will reference to the output port of the server 
						pw.print("Word not found in dictionary");			//send message to the client that word could not be found
						pw.close();			//close the print writer stream as soon as you are finished sending the data
						
						s1.close();			//close the socket connection
				 }
	  }
	}
	
	/**
	 * This function is used to give the synonyms of word which user wants to search in the thereasus.txt file
	 *@param text - the word which user wants to find synonyms of.
	 *Output - It will return the synonyms for the word which user wants to search.
	 */

	private static String searchWord(String text) {
		BufferedReader br = null;			//Buffered Reader object created so as to read contents from the file
	FileReader fr = null;			//File Reader object created so that we can reference the therusasFile.txt file
//String[] synonyms = null;
String[] split = null;			//String array created to store the word and its synonyms from the text file into this variable
String str = null;			//String variable created so as to store the synonym values in this variable.
	try {

		
		fr = new FileReader("therusasFile.txt");			//to initialize the file reader object we created with the reference of therusasFile.txt file
		br = new BufferedReader(fr);			//to initialize the Buffer Reader object with the filereader reference so that contents of file can be read by it.

		String sCurrentLine;

		while ((sCurrentLine = br.readLine()) != null) {		//this while loop reads the file until end of file 
			split=sCurrentLine.split("=");			//split the line which is stored in format of word=synonyms so that we can match the word with the text we want to find out 
			if(split[0].equals(text))			//check if the line has the word which we are searching for
			{
				//synonyms=sCurrentLine.split("=");
				str=split[1];			//store the synonyms of the words which we found out in the string variable which will be retured at end of function
				System.out.println(str);	
				
			}
			//System.out.println(sCurrentLine);
		}

	} catch (IOException e) {

		e.printStackTrace();

	} finally {

		try {			//closes all the buffered writers and file writers.

			if (br != null)
				br.close();

			if (fr != null)
				fr.close();

		} catch (IOException ex) {

			ex.printStackTrace();

		}

	}
	return str;			//return the synonyms of the word which user wants to search.
}
}
