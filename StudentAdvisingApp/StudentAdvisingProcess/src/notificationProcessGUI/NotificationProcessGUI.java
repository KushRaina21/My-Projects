package notificationProcessGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridBagLayout;

import javax.swing.JTextField;

import java.awt.GridBagConstraints;

import javax.swing.JButton;

import RMIImplementataion.studentRMI;
import RMIInterface.studentRMIInterface;
import StudentProcess.studentGUI;

import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

/**
 * 
 * Name : Kush Raina
 * ID: 1001567809 
 * This class is used to simulate the notification  process ,where the notification process takes data from message queue if its addressed to it and displays the advisors desicion.
 * 
 *  
 * */
public class NotificationProcessGUI extends studentGUI{

	private static JFrame frmNotification;			//Jframe object is used to create notification GUI
	private static JTextArea textArea;			//JtextArea object is created so as to display the messages of notification
	private static JButton btnExit;			//Jbutton object created in order to exit the notification process when user wants

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		initialize();
		//new NotificationProcessGUI();			//create an instance of notificationprocess
	}

	/**
	 * Create the application.
	 */
	public NotificationProcessGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private static void initialize() {
		frmNotification = new JFrame();			//Jframe object is initialized in order to allocate memory for notificationGUI
		frmNotification.setTitle("Notification");			//Set the title for notification
		frmNotification.setBounds(100, 100, 450, 300);			//set the position and size of our notification gui
		frmNotification.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			//To change the default behaviour of JFrame object to close
		GridBagLayout gridBagLayout = new GridBagLayout();			// Initializing a grid bag layout for our notification process
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmNotification.getContentPane().setLayout(gridBagLayout);			//
		textArea = new JTextArea();			//initialize JTextArea object so as to allocate memory for text area object which displays notification messages
		JScrollPane scrollText=new JScrollPane(textArea);			//attach scroll bar with the text area
		GridBagConstraints gbc_textArea = new GridBagConstraints();			// Initializing a grid bag layout for our text area
		gbc_textArea.insets = new Insets(0, 0, 5, 0);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 6;
		gbc_textArea.gridy = 1;
		frmNotification.getContentPane().add(scrollText, gbc_textArea);			//adding the grid layout of text area to our frame which we have created.
		btnExit = new JButton("Exit");			//initialize Jbutton variable to allocate memory for Exit button
		btnExit.addActionListener(new ActionListener() {			//function is created to exit from notification process when exit button is clicked.
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		GridBagConstraints gbc_btnExit = new GridBagConstraints();			// Initializing a grid bag layout for our exit button
		gbc_btnExit.insets = new Insets(0, 0, 0, 5);
		gbc_btnExit.gridx = 4;
		gbc_btnExit.gridy = 6;
		frmNotification.getContentPane().add(btnExit, gbc_btnExit);			//adding the grid layout of exit button to our frame which we have created.
		frmNotification.setVisible(true);			//make Notification GUI visible
						//function created by action class to invoke the remote object and process the information from message queue  
		while(true)
		{
		try {
					reg=	LocateRegistry.getRegistry("127.0.0.1", 1099);			//Returns a reference to the remote object Registry on the specified host and port
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					studentRMIInterface s1=(studentRMIInterface)reg.lookup("studentRMI");			//Returns the remote reference bound to the specified name [studentRMI] in this registry.
					
					String str1=s1.getResult();			//calling getResult function to get all the messages from the message queue to notification process
					String[] str=str1.split("~");
					
					if(!str1.equals("no value")){			//check if there are any messages for Notification process to process and disply to screen
					for(int i=0;i<str.length;i++)			//iterate on all the messages if there are any for notification process and display it on the screen
					{
						textArea.append(str[i]);
						textArea.append("\n");
						s1.sendMessagefromNotificationTostudent(str[i]+" to student");
					}
					frmNotification.setVisible(true);
					}
					else			//display novalues text if no values are present for the notification
					{
						textArea.append("no values");
						textArea.append("\n");
						frmNotification.setVisible(true);
						Thread.sleep(7000);
					}
					s1.showMessageofMQS();
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
