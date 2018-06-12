package RMIInterface;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.PriorityQueue;

import javax.swing.JTextArea;
/**
 * 
 * Name : Kush Raina
 * ID: 1001567809 
 * This interface is used to declare all the functions which are to be used for our implementation in student advising process 
 * 
 *
 * */

public interface studentRMIInterface extends Remote{

	public void sendData(String  name,String subject) throws RemoteException, IOException;			//sends data from student gui to message queue 
			
	public String readMessageFromMQS() throws RemoteException,InterruptedException;			//reads messages from MQS for advisor process
	public void loadFileToQueue(String filename) throws RemoteException,FileNotFoundException;			//this function loads data from file to message queue
	public void desicionProcess(String str) throws RemoteException;			//this function is used to take the decision of admitting or not admitting
	public String getResult()throws RemoteException,InterruptedException;			//get the results from MQS to notification process
	public void showMessageofMQS() throws RemoteException,InterruptedException,IOException;			//displays the content of the MQS to the user 
    public String getMessageFromMQStoStudent() throws RemoteException;			//this function is used to get the message from MQS to the student
    public void sendMessagefromNotificationTostudent(String str) throws RemoteException,InterruptedException,IOException;			//displays the content of the MQS to the user
}
