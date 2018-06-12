package WeatherReport;

//this class is used to generate the gui app for displaying the current weather conditions based on the latitude and longitude entered by the user.  
import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class currentWeatherReport {

	private JFrame frame;			//Jframe object is created in order to have a frame for our gui
	private JTextField textField;			// textfield object is created in order to enter the longitude values 
	private JTextField textField_1;			// textfield object is created in order to enter latitude values.
	private JTextArea textArea;			// jtextarea object is created in order to display the weather information.

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					currentWeatherReport window = new currentWeatherReport();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public currentWeatherReport() {
		initialize();
	}
	/**
	 * This function will display the current weather information based on the latitude and longitude values entered by the user
	 *  
	 */
	public void getWeatherInfo() {


		  File f=new File("WeatherStations.xml");			//create and initialize a file object to create an xml of all the weather stations with there latitude and longitude. 
		 boolean b=false;
		  try {
		  if(!f.exists())			// If file is already present then don't create a new file
			
				f.createNewFile();
			
	      URL url = null;
	      BufferedWriter writer = null;			//BufferWriter object created to write the contents of the webpage to the xml file
	     
	          // get URL content
	    	  StringBuilder contentBuilder = new StringBuilder();
	          String a="http://w1.weather.gov/xml/current_obs/index.xml";
	          
				url = new URL(a);			//creating an url object for our url
			
	          URLConnection conn = null;
			
				conn = url.openConnection();			//open an url connection object for our particular object.
			

	          // open the stream and put it into BufferedReader
	          BufferedReader br = null;
			
				br = new BufferedReader(
				                     new InputStreamReader(conn.getInputStream()));			//write the contents of connection stream to a read buffer object
			
	         
				writer = new BufferedWriter( new FileWriter( "WeatherStations.xml"));			//redirect the write buffer which we had initialized to the file which we want to write into the contents to.		
			
	          String inputLine;
	         
				while ((inputLine = br.readLine()) != null) {			//keep on reading from the connection stream  and appending to the file which we had created 
					  System.out.println(inputLine+System.lineSeparator());
					  writer.append( inputLine+System.lineSeparator());
				  }
			
	          
				writer.close( );			//close all the read and write buffers after use.
			
	          
				br.close();
			
	String s=contentBuilder.toString();
	String latitude=textField.getText();			//Sting object initialized to get the values of latitude 
	String longitude=textField_1.getText();			////Sting object initialized to get the values of longitude 
	
	
	File fXmlFile = new File("WeatherStations.xml");			// File object created to parse the xml which we have created
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();			// DocumentBuilderFactory created in order to parse the xml document 
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();			// DocumentBuilder created in order to parse the given xml document.
	Document doc = dBuilder.parse(fXmlFile);			
			
	//optional, but recommended
	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	doc.getDocumentElement().normalize();			//normalize the current document so that we can read the file

	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			
	NodeList nList = doc.getElementsByTagName("station");			// identify the root node of xml and get the instance of the root node
			
	System.out.println("----------------------------");
	HttpURLConnection con=null;			
	for (int temp = 0; temp<nList.getLength(); temp++) {			//iterate the whole xml document

		Node nNode = nList.item(temp);			// select each item from the Nodelist and initialize it to Node object
				
		System.out.println("\nCurrent Element :" + nNode.getNodeName());
				
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			
			
			Element eElement = (Element) nNode;			//check if latitude and longitude which user has given matches with any of the latitude and longitude calues select that respective station
			if(eElement.getElementsByTagName("latitude").item(0).getTextContent().equals(latitude)&&eElement.getElementsByTagName("longitude").item(0).getTextContent().equals(longitude))
			{

			System.out.println("Station  : " + eElement.getAttribute("station"));
			System.out.println("station_id : " + eElement.getElementsByTagName("station_id").item(0).getTextContent());
			System.out.println("xml_url : " + eElement.getElementsByTagName("xml_url").item(0).getTextContent());
			
			URL url1=new URL(eElement.getElementsByTagName("xml_url").item(0).getTextContent());			// send http request to the xml url link which was identified by matching with the latitude and longitude of the user
          
			 con= (HttpURLConnection)url1.openConnection();			
			int res=con.getResponseCode();
			
			System.out.println(res);
			
			BufferedReader br1 = new BufferedReader(new InputStreamReader(con.getInputStream()));			// store the values which was returned from the http request into a buffered reader object
			String t,str="";
			while((t = br1.readLine()) != null)			//keep on reading the values from the buffered reader onto the string variable so as to use again to write to file
				{System.out.println(t);
				str=str+t+"\n";
				}
			br1.close();
			File f1=new File("response.xml");			// store the contents of the response onto another file called response.xml
			  if(!f.exists())
			     f.createNewFile();
			  BufferedWriter writerq = null;
			  writerq = new BufferedWriter( new FileWriter("response.xml"));
			  writerq.write(str);
			  writerq.close();
			  con.disconnect();			//session disconnected after getting response
			  b=true;
			  break;
			}
			
			
		}
	}
	if(!b)
	{
		textArea.setText("Latitude and longitude not matching");
	}
	else
	{
	File fXmlFile1 = new File("response.xml");			// File object created to parse the xml which we have created
	DocumentBuilderFactory dbFactory1 = DocumentBuilderFactory.newInstance();			// DocumentBuilderFactory created in order to parse the xml document
	DocumentBuilder dBuilder1 = dbFactory1.newDocumentBuilder();			// DocumentBuilder created in order to parse the given xml document.
	Document doc1 = dBuilder1.parse(fXmlFile1);
			
	//optional, but recommended
	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	doc1.getDocumentElement().normalize();			//normalize the current document so that we can read the file

	System.out.println("Root element :" + doc1.getDocumentElement().getNodeName());
			
	NodeList nList1 = doc1.getElementsByTagName("current_observation");			// identify the root node of xml and get the instance of the root node
	Node nNode1 =nList1.item(0);			//take the first instance of item and initialize it to node object
	Element eElement1 = (Element) nNode1;			//create the elemnt object with the help of node object.
	
	textArea.setText("");
	if(eElement1.getElementsByTagName("dewpoint_string").getLength()>0)			//check if the attribute value which you want to get is present in the response xml or not, If its present then display the information on the gui otherwise print no information avaliable
	textArea.append("DewPoint Temperature "+eElement1.getElementsByTagName("dewpoint_string").item(0).getTextContent());			
	else
		textArea.append("DewPoint Temperature not avaliable ");	
	textArea.append("\n");
	if(eElement1.getElementsByTagName("wind_string").getLength()>0)			//check if the attribute value which you want to get is present in the response xml or not, If its present then display the information on the gui otherwise print no information avaliable
	textArea.append("Wind Speed and Direction "+eElement1.getElementsByTagName("wind_string").item(0).getTextContent());
	else
		textArea.append("Wind Speed and Direction not avaliable ");	
	
	textArea.append("\n");
	
	if(eElement1.getElementsByTagName("temperature_string").getLength()>0)			//check if the attribute value which you want to get is present in the response xml or not, If its present then display the information on the gui otherwise print no information avaliable
	textArea.append("Temperature "+eElement1.getElementsByTagName("temperature_string").item(0).getTextContent());
	else
		textArea.append("Temperature not avaliable ");
	
	textArea.append("\n");
	
	if(eElement1.getElementsByTagName("visibility_mi").getLength()>0)			//check if the attribute value which you want to get is present in the response xml or not, If its present then display the information on the gui otherwise print no information avaliable
	textArea.append("Visibility "+eElement1.getElementsByTagName("visibility_mi").item(0).getTextContent());
	else
		textArea.append("visibility not avaliable ");
	
	System.out.println("DewPoint Temperature "+eElement1.getElementsByTagName("dewpoint_string").item(0).getTextContent());
	System.out.println("Wind Speed and Direction"+eElement1.getElementsByTagName("wind_string").item(0).getTextContent());
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
			}
	 catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SAXException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {			//this function is used to display the gui for our application
		frame = new JFrame();			//initializing JFrame object
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			//set the default operation on Jframe
		GridBagLayout gridBagLayout = new GridBagLayout();			//initializing the GridBagLayout for our application
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);			//adding the gridbag layout on our frame
		
		JLabel lblLalitude = new JLabel("Lalitude");			//initalizing the Latitude label
		GridBagConstraints gbc_lblLalitude = new GridBagConstraints();
		gbc_lblLalitude.insets = new Insets(0, 0, 5, 5);
		gbc_lblLalitude.gridx = 3;
		gbc_lblLalitude.gridy = 1;
		frame.getContentPane().add(lblLalitude, gbc_lblLalitude);			//adding it to our frame
		
		JLabel lblLongitude = new JLabel("Longitude");			//initalizing the Longitude label
		GridBagConstraints gbc_lblLongitude = new GridBagConstraints();
		gbc_lblLongitude.insets = new Insets(0, 0, 5, 0);
		gbc_lblLongitude.gridx = 8;
		gbc_lblLongitude.gridy = 1;
		frame.getContentPane().add(lblLongitude, gbc_lblLongitude);			//adding it to our frame
		
		textField = new JTextField();			//initalizing the text area for longitude text field
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 2;
		frame.getContentPane().add(textField, gbc_textField);			//adding it to our frame
		textField.setColumns(10);
		
		textField_1 = new JTextField();			//initializing the text area for latitude field
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 8;
		gbc_textField_1.gridy = 2;
		frame.getContentPane().add(textField_1, gbc_textField_1);			//adding it to our frame
		textField_1.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");			//Initialize the submit button 
		btnSubmit.addActionListener(new ActionListener() {			//adding action listner to the submit button
			public void actionPerformed(ActionEvent arg0) {
				getWeatherInfo();			//calling the function which will retrieve the weather information
			
		}
			}
		);
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.insets = new Insets(0, 0, 5, 5);
		gbc_btnSubmit.gridx = 3;
		gbc_btnSubmit.gridy = 3;
		frame.getContentPane().add(btnSubmit, gbc_btnSubmit);			//adding it to our frame
		
		JButton btnRefresh = new JButton("Refresh");			//initializing the refresh button 
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getWeatherInfo();			//calling the function which will retrieve the weather information
			}
		});
		GridBagConstraints gbc_btnRefresh = new GridBagConstraints();
		gbc_btnRefresh.insets = new Insets(0, 0, 5, 0);
		gbc_btnRefresh.gridx = 8;
		gbc_btnRefresh.gridy = 3;
		frame.getContentPane().add(btnRefresh, gbc_btnRefresh);			//adding it to our frame
		
		 textArea = new JTextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridwidth = 9;
		gbc_textArea.insets = new Insets(0, 0, 0, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 5;
		frame.getContentPane().add(textArea, gbc_textArea);			//adding it to our frame
	}

}
