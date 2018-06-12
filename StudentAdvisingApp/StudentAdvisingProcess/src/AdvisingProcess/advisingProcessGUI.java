package AdvisingProcess;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;

import javax.swing.JTextArea;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;

import RMIInterface.studentRMIInterface;
import StudentProcess.studentGUI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import javax.swing.JTextField;

/**
 * 
 * Name : Kush Raina
 * ID: 1001567809 
 * This class is used to simulate the advising  process ,where advisor gets the messages from the message queue regarding the messages meant for it and process the decision whether student is admitted or not.
 * 
 *  
 * */
public class advisingProcessGUI extends studentGUI{

	private static JFrame frmAdvisingProc;			//Jframe object is used to create advisor GUI
	private static JButton btnExit;			//Jbutton object created so as to exit from the advising process.
	private static JTextArea textArea;			//Jtext Area object created in order to display messages of Advisor
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			initialize();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * Create the application.
	 * @throws RemoteException 
	 * @throws InterruptedException 
	 */
	public advisingProcessGUI() throws RemoteException, InterruptedException {
				initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws InterruptedException 
	 */
	private static void initialize() throws RemoteException, InterruptedException {
		frmAdvisingProc = new JFrame();			//Jframe object is initialized in order to allocate memory for AdvisingGUI
		frmAdvisingProc.setTitle("Advisor");			//Set the title for advisor
		frmAdvisingProc.setBounds(100, 100, 450, 300);			//set the position and size of our advisor gui
		frmAdvisingProc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			//To change the default behaviour of JFrame object to close
		GridBagLayout gridBagLayout = new GridBagLayout();			// Initializing a grid bag layout for our advising process
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};			// Initializing the contents of our GridBag Layout system
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		frmAdvisingProc.getContentPane().setLayout(gridBagLayout);
		textArea = new JTextArea();			//Intitializing the JTextArea object to create text area for advising process
		JScrollPane scrollText=new JScrollPane(textArea);			//adding scroll bar to the advising text area box.
		GridBagConstraints gbc_textArea = new GridBagConstraints();		// Initializing a grid bag layout for our text area	
		gbc_textArea.gridwidth = 3;
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 2;
		gbc_textArea.gridy = 1;
		frmAdvisingProc.getContentPane().add(scrollText, gbc_textArea);			//adding the grid layout of text area to our frame which we have created.
		
		btnExit = new JButton("Exit");			//Initialize Jbutton object to allocate memory for exit button
		btnExit.addActionListener(new ActionListener() {			//creating an action listener implementation class which will prevent us from forming other class for action listner
			public void actionPerformed(ActionEvent arg0) {			//function created by action class to exit from the advisor process.
				System.exit(0);
			}
		});
		GridBagConstraints gbc_btnExit = new GridBagConstraints();			// Initializing a grid bag layout for our exit button
		gbc_btnExit.gridx = 5;
		gbc_btnExit.gridy = 3;
		frmAdvisingProc.getContentPane().add(btnExit, gbc_btnExit);			//adding the grid layout of exit button to our frame which we have created.
		frmAdvisingProc.setVisible(true);
		String str = null;
		
				
		
		while(true){
		try {
					reg =	LocateRegistry.getRegistry("127.0.0.1", 1099);			//Returns a reference to the remote object Registry on the specified host and port
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					System.out.println("inside adising process");
					studentRMIInterface s1=(studentRMIInterface)reg.lookup("studentRMI");			//Returns the remote reference bound to the specified name [studentRMI] in this registry.
					
					
					 str=s1.readMessageFromMQS();		//the reference object created now calls the function which will read data from message queue and send it to the string varaiable declared
					System.out.println("after reading");
					if(!str.equals(""))			//checking if there are any messages for advisor or not
					{
						System.out.println("message from messagequeue to advisor" + str);
					String[] str2=str.split("~");
					for (int i = 0; i < str2.length; i++) {			//iterating on all the messages from MSQ and displaying it on advisor gui and processing the desicion for each message
						textArea.append(str2[i]);
						textArea.append("\n");					
						s1.desicionProcess(str2[i]);
					
					}
					frmAdvisingProc.setVisible(true);			//display the advisor gui to the user
					}
					else
					{
									//sleep for 3 seconds if there are no messages for advisor
						textArea.append("nomessages");			//display nomessages text on the advisor GUI if no messages are present.
						textArea.append("\n");
						frmAdvisingProc.setVisible(true);			//display the advisor gui to the user
						Thread.sleep(3000);
					}
					
					s1.showMessageofMQS();			//calling function which will show the contents of message queue on to the screen.
					
				} catch (AccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
	
	}
	}

}
