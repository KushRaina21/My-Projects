package StudentProcess;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridBagLayout;

import javax.swing.JTextField;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import RMIInterface.studentRMIInterface;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
/**
 * 
 * Name : Kush Raina
 * ID: 1001567809 
 * This class is used to simulate the student process , this gui contains values such as name of the student and subject which student wants to enter.
 * 
 *  
 * */
public class studentGUI {

	private static JFrame frmStudent;			//Jframe object is used to create StudentGUI 
	private JTextField textField;			//Text field to enter the name of student
	private JTextField textField_1;			//Text field to enter the subject which user wants to add
	public static Registry reg;			//Registry object is created which is used to communicate between different functions
	private JButton btnExit;			//Jbutton object is created so as to exit from the student process

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					studentGUI window = new studentGUI();
					window.frmStudent.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}

	public studentGUI(int x) {
	} 
	/**
	 * Create the student gui application.
	 */
	public studentGUI() {
		try {
			initialize();
		} catch (AccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws NotBoundException 
	 * @throws RemoteException 
	 * @throws AccessException 
	 */
	private void initialize() throws AccessException, RemoteException, NotBoundException {
		frmStudent = new JFrame();			//Jframe object is initialized in order to start student gui 
		frmStudent.setTitle("Student Process");			//set the title of student gui to Student Process
		frmStudent.setBounds(100, 100, 450, 300);			//set the position and size of our student gui
		frmStudent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			//To change the default behaviour of JFrame object to close
		GridBagLayout gridBagLayout = new GridBagLayout();			// Initializing a grid bag layout for our student process
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};			// Initializing the contents of our GridBag Layout system
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmStudent.getContentPane().setLayout(gridBagLayout);			//adding the grid layout to our frame which we have created.
		
		JLabel lblName = new JLabel("Name");			//Jlabel object is created in order to create Name label in our student GUI
		GridBagConstraints gbc_lblName = new GridBagConstraints();			// Initializing a grid bag layout for our sName label
		gbc_lblName.insets = new Insets(0, 0, 5, 5);			// Initializing the contents of our GridBag Layout system
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 2;
		frmStudent.getContentPane().add(lblName, gbc_lblName);			//adding the grid layout to our frame which we have created.
		
		JLabel lblSubject = new JLabel("Subject");			//Jlabel object is created in order to create Subject label in our student GUI
		GridBagConstraints gbc_lblSubject = new GridBagConstraints();			// Initializing a grid bag layout for our student label
		gbc_lblSubject.insets = new Insets(0, 0, 5, 0);			// Initializing the contents of our GridBag Layout system
		gbc_lblSubject.gridx = 3;
		gbc_lblSubject.gridy = 2;
		frmStudent.getContentPane().add(lblSubject, gbc_lblSubject);			//adding the grid layout to our frame which we have created.
		
		textField = new JTextField();			//Initialize JtextField object to get allocate memory for name text field
		GridBagConstraints gbc_textField = new GridBagConstraints();			// Initializing a grid bag layout for name text field
		gbc_textField.insets = new Insets(0, 0, 5, 5);			// Initializing the contents of our GridBag Layout system
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 3;
		frmStudent.getContentPane().add(textField, gbc_textField);			//adding the grid layout to our frame which we have created.
		textField.setColumns(10);			
		
		textField_1 = new JTextField();			//Initialize JtextField object to get allocate memory for subject text field
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();			// Initializing a grid bag layout for subject text field
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);			// Initializing the contents of our GridBag Layout system
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 3;
		gbc_textField_1.gridy = 3;
		frmStudent.getContentPane().add(textField_1, gbc_textField_1);			//adding the grid layout to our frame which we have created.
		textField_1.setColumns(10);
		JTextArea textArea = new JTextArea();
		JScrollPane scrollText=new JScrollPane(textArea);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridwidth = 2;
		gbc_textArea.insets = new Insets(0, 0, 0, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 6;
		frmStudent.getContentPane().add(scrollText, gbc_textArea);
		try {
			reg=	LocateRegistry.getRegistry("127.0.0.1", 1099);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}			//Returns a reference to the remote object Registry on the specified host and port
		
		
		
		
		
		
		JButton btnSubmit = new JButton("Submit");			//Initialize Jbutton object to allocate memory for submit button 
		btnSubmit.addActionListener(new ActionListener() {			//creating an action listener implementation class which will prevent us from forming other class for action listner
			
			public void actionPerformed(ActionEvent arg0) {			//function created by action class to look up in the registry which we have created and send the data to message queue
				try {
					reg=	LocateRegistry.getRegistry("127.0.0.1", 1099);			//Returns a reference to the remote object Registry on the specified host and port
					studentRMIInterface s1=(studentRMIInterface)reg.lookup("studentRMI");			//Returns the remote reference bound to the specified name [studentRMI] in this registry.
					System.out.println(textField.getText()+ textField_1.getText()+" to advisor");
					s1.sendData(textField.getText(), textField_1.getText()+" to advisor");			//extracting the data from the text fields and sending it out to the send data function to be stored in the message queue.
					s1.showMessageofMQS();			//calling function which will show the contents of message queue on to the screen.
					
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();	// Initializing a grid bag layout for submit button		
		gbc_btnSubmit.insets = new Insets(0, 0, 0, 5);			// Initializing the contents of our GridBag Layout system
		gbc_btnSubmit.gridx = 2;
		gbc_btnSubmit.gridy = 5;
		frmStudent.getContentPane().add(btnSubmit, gbc_btnSubmit);			//adding the grid layout to our frame which we have created.
		
		btnExit = new JButton("Exit");			//Initialize Jbutton object to allocate memory for exit button 
		btnExit.addActionListener(new ActionListener() {			//creating an action listener implementation class which will prevent us from forming other class for action listner
			public void actionPerformed(ActionEvent arg0) {			//function created by action class to exit from the student process.
				System.exit(0);
			}
		});
		GridBagConstraints gbc_btnExit = new GridBagConstraints();			// Initializing a grid bag layout for exit button
		gbc_btnExit.gridx = 3;
		gbc_btnExit.gridy = 5;
		frmStudent.getContentPane().add(btnExit, gbc_btnExit);	
		//adding the grid layout to our frame which we have created.
		studentRMIInterface s1=(studentRMIInterface)reg.lookup("studentRMI");			//Returns the remote reference bound to the specified name [studen
		
		String message = s1.getMessageFromMQStoStudent();			//print the notification message on the student process
		String[] str1=message.split("~");
		for(int i=0;i<str1.length;i++)
		{
			textArea.append(str1[i].replace(" to student", " "));
			textArea.append("\n");
			
		}
		try {
			s1.showMessageofMQS();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
