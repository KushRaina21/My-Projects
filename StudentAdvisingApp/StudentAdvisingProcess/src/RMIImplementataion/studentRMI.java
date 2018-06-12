package RMIImplementataion;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


//import MessageQueueServer.messagequeuecontent;
import RMIInterface.studentRMIInterface;


/**
 * 
 * Name : Kush Raina
 * ID: 1001567809 
 * This class is used to implement all the interfaces which have been declared in studentRMIInterface , in addition to it  also contains messageQueue Linked list which is the implementation of messagequeueserver
 * 
 *  
 * */
public class studentRMI extends UnicastRemoteObject implements studentRMIInterface  {
	 public static LinkedList<String> messageQueue = new LinkedList<String>();			//this linked list is declared which will be used as message queue .
	 public static  JFrame frmMessagequeue= new JFrame();			//jframe object is created in order to declare the jframe on which message gui is build
		public static JTextArea textArea= new JTextArea();			//text area object is created in order to display the contents of message queue
		public static JScrollPane scrollText=new JScrollPane(textArea);
		static GridBagLayout gridBagLayout= new GridBagLayout();			// layouts varaiables are created for message queue gui
	static GridBagConstraints gbc_textArea=new GridBagConstraints();			// layouts varaiables are created for message queue gui
	//PriorityQueue<String> messageQueue=new PriorityQueue();
	
	public studentRMI() throws RemoteException {
		super();
		messagequeuecontent();
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * This function is used to add the name and subject to the message queue linked list which we have declared to keep our messages
	 *@param - this function takes two parameters name of the student and subject student wants to take
	 *Output - the function will add name and subject to the message queue and to the text file which we have created.
	 */
	public void sendData(String name, String subject)
			throws RemoteException,IOException {
		
		messageQueue.add(name+" "+subject);			//adding name and subject to the message queue
		 
		
		writeandappendFile("messageQueueData.txt", messageQueue);			//calling function to append values of message queue onto the text file created.
	}
	/**
	 * This function is used to write the contents of message queue onto the text file which we have created for storing message queue data.
	 *@param - this function takes two parameters name of the file where we have to write data and message queue which we have to use to write data
	 *Output - the function will give us a file which will have contents of messagequeue written to the file.
	 */


	private void writeandappendFile(String string,
			LinkedList<String> messageQueue2) {
		// TODO Auto-generated method stub
		BufferedWriter bw = null;			//BufferWriter object created so as to write contents in the file.
		FileWriter fw = null;			//FileWriter object created to get access to the file which we want to write

		try {

			

			File file = new File(string);			//Create new object of File type

			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// true = append file
			fw = new FileWriter(file);			//file writer object is created in order to direct the object to the buffered writer object
			
			bw = new BufferedWriter(fw);			//to direct all the contents appended onto buffered o/p stream to file
			
						//initialize a iterator object to iterate through the messageQueue Linked list
			Iterator it =messageQueue.iterator();

		     
		     while ( it.hasNext()) {			// iterate untill you have reached to the end of queue.
		    	 
		    	 bw.write((String) it.next());			//write the contents of the linked list to the buffered writer stream
		    	
		    	 bw.newLine();			//add newline to the buffered writer stream	       
		     }

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {			//closes all the buffered writers and file writers.

				if (bw != null)
					bw.close();

if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		}
	}



	


	


	/**
	 * This function is used by the advisor process to read messages from the message queue server and remove all the enteries from the list which have been read by the advisor from the message queue and the update the message queue file also
	 *
	 */
	public String readMessageFromMQS( ) throws RemoteException, InterruptedException {
		// TODO Auto-generated method stub
		
		System.out.println(" inside readfromMQS");
		
		int i=0;
		String msgtoadvisor = "";
		Iterator it =messageQueue.iterator();		//initialize the iterator for message queue so as to traverse the message queue
		
		
	     while ( it.hasNext()) {			//iterate the whole linked list 
	    	 
	    	 String str=(String) it.next();			//get the contents of the linked list one by one 
	    	 System.out.println("--"+str);
	    	 if(str.contains("to advisor"))			//check if the messages in the linked list addressed to the advisor
	    	 {
	    		 System.out.println("in this "+str);
	    		 msgtoadvisor=msgtoadvisor+str+"~";			//keep on storing each and every message to a string variable 
	    		 it.remove();			//remove the entry from the linked list as soon you found it
	    	 }
	    	
	    
	    	 
	     }
	   
	    
		 writeTomessageQueueFile(messageQueue,"messageQueueData.txt");			//write contents of messagequeue onto the text file
	     System.out.println("Elements to  advisor "+msgtoadvisor );
	     return msgtoadvisor;			//return the string which has been read to the gui to be displayed
	}
	/**
	 * This function is used by the advisor process to decide whether to give admit to the student or not and write its desicion onto the message queue and to the message queue file  
	 *@param - takes the pair of student and subject to decide if admission should be done or not.
	 */
	public void desicionProcess(String str ) throws RemoteException {
		
			 boolean val = new Random().nextInt(2)==0;			//advisor deciding the probability to admit or not
	    	 if(val)			//if val comes true student will be admitted 
	    	 {
	    		 messageQueue.add(str.replace("to advisor", "to notification")+" result=admitted");			//adding the result to the message queue 
	    		 writeToNotificationFile(str.replace("to advisor", "to notification")+" result=admitted");			//writing the contents of messagequeue onto the text file
	    		// it.remove();
	    	 }
	    	 else
	    	 {
	    		 messageQueue.add(str.replace("to advisor", "to notification")+" result=Not admitted");			//adding the result to the message queue
	    		 writeToNotificationFile(str.replace("to advisor", "to notification")+" result=Not admitted");			//writing the contents of messagequeue onto the text file
	    		
	    	 }
		
		
	}
	/**
	 * This function is used to write the contents of message queue onto the text file which we have created for storing message queue data.
	 *@param - this function takes two parameters name of the file where we have to write data and message queue which we have to use to write data
	 *Output - the function will give us a file which will have contents of messagequeue written to the file.
	 */
	private void writeTomessageQueueFile(LinkedList<String> messageQueue2,String filename) {
		// TODO Auto-generated method stub
		BufferedWriter bw = null;			//BufferWriter object created so as to write contents in the file.
		FileWriter fw = null;			//FileWriter object created to get access to the file which we want to write

		try {

			

			File file = new File(filename);			//Create new object of File type

			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			
			fw = new FileWriter(file);			//file writer object is created in order to direct the object to the buffered writer object
			
			bw = new BufferedWriter(fw);			//to direct all the contents appended onto buffered o/p stream to file
			
						
			Iterator it =messageQueue.iterator();			//initialize a iterator object to iterate through the messageQueue Linked list

		    
		     while ( it.hasNext()) {		// iterate untill you have reached to the end of list.
		    	
		    	 bw.write((String) it.next());			//write the contents of the linked list to the buffered writer stream
		    	
		    	 bw.newLine();			//add newline to the buffered writer stream
		       
		     }

					

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {			//closes all the buffered writers and file writers.

				if (bw != null)
					bw.close();

if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		}
	}

	/**
	 * This function is used to appending the contents of message queue onto the text file which we have created for storing message queue data.
	 *@param - this function takes string as a parameter which it will append to the message queue file .
	 *Output - the function will give us a file which will have the updated information from the notification process.
	 */

	private void writeToNotificationFile(String string) {
		// TODO Auto-generated method stub

		

		BufferedWriter bw = null;			//BufferWriter object created so as to write contents in the file.
		FileWriter fw = null;			//FileWriter object created to get access to the file which we want to write

		try {

			

			File file = new File("messageQueueData.txt");			//Create new object of File type

			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// true = append file
			fw = new FileWriter(file.getAbsoluteFile(),true);			//Keep the existing content and append the new content to the end of a file.
			
			bw = new BufferedWriter(fw);			//to direct all the contents appended onto buffered o/p stream to file
			
			//bw.newLine();			//append new line on to the file
			bw.write(string);		//write the word and its synonyms onto the file
			bw.newLine();
			//System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {			//closes all the buffered writers and file writers.

				if (bw != null)
					bw.close();

if (fw != null)
					fw.close();

			 
			

		
			}
			catch (IOException ex) {

				ex.printStackTrace();

			}
		}
	
	}

	/**
	 * This function is used to load the data from the message queue text file to the message queue 
	 *@param - this function takes filename as argument which will tell from which file to load data to message queue
	 *
	 */

	public void loadFileToQueue(String filename) throws RemoteException, FileNotFoundException {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		 

			BufferedReader br = null;			//Buffered Reader object created so as to read contents from the file
		FileReader fr = null;			//File Reader object created so that we can reference the therusasFile.txt file
	
	String[] split = null;			//String array created to store the word and its synonyms from the text file into this variable
	String str = null;			//String variable created so as to store the synonym values in this variable.
		try {

			
			fr = new FileReader(filename.trim());			//to initialize the file reader object we created with the reference of therusasFile.txt file
			br = new BufferedReader(fr);			//to initialize the Buffer Reader object with the filereader reference so that contents of file can be read by it.

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {			//iterate the whole file and add the contents of the file to the linked list.
				System.out.println(" message in message queue "+sCurrentLine);
				messageQueue.add(sCurrentLine);
			}

		} catch (IOException e) {

			System.out.println("-- "+filename);
			e.printStackTrace();

		}
		
		finally {

			try {			//closes all the buffered writers and file writers.

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
				

	
	}

	/**
	 * This function is used by notification process to get the results from the message queue server so that notification process can display the results of execution
	 *
	 */


	public String getResult() throws RemoteException, InterruptedException {
		// TODO Auto-generated method stub
		
		System.out.println("in getResult");
		String msdtonotification=" ";			//string variable to store the messages of notification process
		String str1="";
		Iterator<String> it =messageQueue.iterator();			//iterator is initialized which is used to get the contents from message queue
		if(it.hasNext() )			//check if queue is empty or not
		{
			it =messageQueue.iterator();
	     while ( it.hasNext()) {			// iterate the whole queue 
	    	
	    	 String str= it.next();
	    	 
	    	 if(!str.matches(".*[a-zA-Z]+.*"))
	    	 {
	    		 System.out.println("to rmove blanck line");
	    		 it.remove();
	    	 }
	    	 if(str.contains("to notification"))			//  check if the message in the queue is directed to notification process or not
	    	 {
	    		 System.out.println("in this");
	    		 msdtonotification=msdtonotification+str.replace(" to notification ", " ")+"~";
	    		 it.remove();			//remove the entry from queue once accessed by notification processs.
	   
	    	 }
	    	 str1=str1+str+" ";  	 
	     }
	     if(!str1.contains("to notification"))			//sleep for 7 seconds if the messages in the queue are not directed to notification process
	     {
	    	System.out.println("-- "+str1); 
	    	//Thread.sleep(7000);
	    	msdtonotification="no value";
	     }
		}
		else			//sleep for 7 seconds if no message is present in message queue
		{
			System.out.println(" sleeping for 7 sec");
			msdtonotification="no value";
			//Thread.sleep(7000);
			
		}
	     System.out.println("in getResult 5");
	     writeTomessageQueueFile(messageQueue,"messageQueueData.txt");			//write the contents back to the message queue file after updation from notification process
	     return msdtonotification;			//return the contents read from notification process
	}


	/**
	 * This function is used to display to the user contents of the message queue
	 *
	 */
	public void showMessageofMQS() throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		BufferedReader reader1 = new BufferedReader(new FileReader("messageQueueData.txt"));			//initalize buffer Reader instance so as to read the contents of message queue file
	    String line1;
	    StringBuilder sb = new StringBuilder();			//String builder object to store the string 
	    											
	    while ((line1 = reader1.readLine()) != null)			//read untill the end of file 
	    {
	    	sb.append(line1);			//append the string which we get from text file to string builder object
	    	System.out.println("file text "+line1);
	        sb.append(System.lineSeparator());			//append new line 
	     
	   
	    }
	    System.out.println("message in queue "+sb.toString());
	    String str=sb.toString().trim();	
	    //convert the string builder object to string variable
	    textArea.setText(str);
	    // textArea.append(str);
	    
		//JOptionPane.showMessageDialog(null, str, "MessageQueue", JOptionPane.PLAIN_MESSAGE);			// display the content of string onto the screen to the user.
	}
	/**
	 * This function is used to display to get the messages from MQS to the student process
	 *
	 */

	public String getMessageFromMQStoStudent() throws RemoteException {
		int i=0;
		String msgtoadvisor = "";
		Iterator it =messageQueue.iterator();		//initialize the iterator for message queue so as to traverse the message queue
		
		 
		
	     while ( it.hasNext()) {			//iterate the whole linked list 
	    	 
	    	 String str=(String) it.next();			//get the contents of the linked list one by one 
	    	 System.out.println("--"+str);
	    	 if(str.contains("to student"))			//check if the messages in the linked list addressed to the advisor
	    	 {
	    		 System.out.println("in this "+str);
	    		 msgtoadvisor=msgtoadvisor+str+"~";			//keep on storing each and every message to a string variable 
	    		 it.remove();			//remove the entry from the linked list as soon you found it
	    	 }
	    	
	    
	    	 
	     }
	   
	    
		 writeTomessageQueueFile(messageQueue,"messageQueueData.txt");			//write contents of messagequeue onto the text file
	     System.out.println("Elements to  advisor "+msgtoadvisor );
	     return msgtoadvisor;			//return the string which has been read to the gui to be displayed
	}
	/**
	 * This function is used to send message from notification to the student
	 *
	 */
	public void sendMessagefromNotificationTostudent(String str) throws RemoteException,
	InterruptedException, IOException {
// TODO Auto-generated method stub
messageQueue.add(str);
writeToNotificationFile(str);			//writing the contents of messagequeue onto the text file

}
	/**
	 * 
	 * This function is used to display the message queue gui  
	 * */
	public void messagequeuecontent() {
		
		frmMessagequeue.setTitle("MessageQueue");
		frmMessagequeue.setBounds(100, 100, 450, 300);
		frmMessagequeue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		frmMessagequeue.getContentPane().setLayout(gridBagLayout);
  
		gbc_textArea.gridheight = 2;
		gbc_textArea.gridwidth = 8;
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 0;
		frmMessagequeue.getContentPane().add(scrollText, gbc_textArea);
		
		frmMessagequeue.setVisible(true);
	}



}
