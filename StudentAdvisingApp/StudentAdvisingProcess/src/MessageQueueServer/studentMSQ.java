package MessageQueueServer;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import RMIImplementataion.studentRMI;


/**
 * 
 * Name : Kush Raina
 * ID: 1001567809 
 * This class is used to create RMI registry and bind remote object to that RMI registry created and load data from messagequeue.txt file to linked list.
 * 
 *  
 * */

public class studentMSQ extends studentRMI{

	public studentMSQ() throws RemoteException, MalformedURLException, FileNotFoundException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Registry reg=LocateRegistry.createRegistry(1099);			//Creates and exports a Registry instance on the local host that accepts requests on the specified port.		
		studentRMI s1=new studentRMI();			//create an RMI  object 
		Naming.rebind("studentRMI", s1);			//Binds the name[studentRMI] to the specified remote object. 
		System.out.println("Server");		
		s1.loadFileToQueue("messageQueueData.txt");			//calling loadFileToQueue function to load the data from messagequeuedata.txt file to messagequeue linked list
	   s1.showMessageofMQS();
	}

}
