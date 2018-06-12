import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridBagLayout;

import javax.swing.JTextField;

import java.awt.GridBagConstraints;

import javax.swing.JLabel;

import java.awt.Insets;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 * 
 * Name : Kush Raina
 * ID: 1001567809 
 * This class is used to add additional feature to this application i.e to add a new word which user wants to add in the dictionary
 * The code below is refrenced from below url's 
 * URL1: https://www.youtube.com/watch?v=kqBmsLvWU14 
 * URL2:https://www.youtube.com/watch?v=MFAL8x7ipsA&t=36s
 * URL3:https://www.mkyong.com/java/how-to-read-file-from-java-bufferedreader-example/ 
 * URL4:https://www.youtube.com/watch?v=vCDrGJWqR8w&t=1138s
 * 
 *  
 * */


public class addWord {

	private JFrame frmAddNewWord;						// Jframe object is used to create gui of the window where we add new word 
	private JTextField textField;						// JtextField object is created so as to enter the new word which we want to add
	private JTextField textField_1;						//JTextField object is created so as to enter the synonyms of new word which we want to add in dictionary 
	private String word;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public addWord() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame i.e to create the adding word gui to the application.
	 * 
	 */
	private void initialize() {
		frmAddNewWord = new JFrame();							//JFrame object is initialized to create a new GUI for adding a new word in dictionary
		frmAddNewWord.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//To change the default behaviour of JFrame object to close 
		GridBagLayout gridBagLayout = new GridBagLayout();				// Initializing a grid bag layout for our add word gui
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};		// Initializing the contents of our GridBag Layout system
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmAddNewWord.getContentPane().setLayout(gridBagLayout);		//adding the grid layout to our frame which we have created.
		
		JLabel lblWord = new JLabel("Word");							//Setting up label for the text field where we have to enter our new word 
		GridBagConstraints gbc_lblWord = new GridBagConstraints();
		gbc_lblWord.insets = new Insets(0, 0, 5, 5);
		gbc_lblWord.gridx = 1;
		gbc_lblWord.gridy = 5;
		frmAddNewWord.getContentPane().add(lblWord, gbc_lblWord);
		
		JLabel lblSynonyms = new JLabel("Synonyms");					//Setting up label for the synonyms field where we have to enter meanings of our new word.
		GridBagConstraints gbc_lblSynonyms = new GridBagConstraints();
		gbc_lblSynonyms.insets = new Insets(0, 0, 5, 0);
		gbc_lblSynonyms.gridx = 4;
		gbc_lblSynonyms.gridy = 5;
		frmAddNewWord.getContentPane().add(lblSynonyms, gbc_lblSynonyms);
		
		textField = new JTextField();									//setting up the text field where we will be entering our new word
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 6;
		frmAddNewWord.getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();									//setting up the text field where we will be entering our synonyms
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 4;
		gbc_textField_1.gridy = 6;
		frmAddNewWord.getContentPane().add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");						//creating submit  button in GUI to add new word in dictionary 
		btnSubmit.addActionListener(new ActionListener() {				//creating an action listener implementation class which will prevent us from forming other class for action listner 
			public void actionPerformed(ActionEvent arg0) {				//function created by action class to add new word to thereasus file.
				writeFile(textField.getText()+"="+textField_1.getText());		// function used to append contents of file ,the parameter which we pass is the text which you want to store.
				//writeFile(word);
				frmAddNewWord.dispose();								// dispose command will close the GUI as our writing operation is done.
			}
		});
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.insets = new Insets(0, 0, 0, 5);
		gbc_btnSubmit.gridx = 2;
		gbc_btnSubmit.gridy = 9;
		frmAddNewWord.getContentPane().add(btnSubmit, gbc_btnSubmit);		// adding the Submit button to GridLayout which we had created.
		
		JButton btnExit = new JButton("Exit");			//create an EXIT button to exit from the add GUI window
		btnExit.addActionListener(new ActionListener() {			//implement the action listner class for EXIT button
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);			//this command will make us exit from our current operations 
			}
		});
		GridBagConstraints gbc_btnExit = new GridBagConstraints();
		gbc_btnExit.gridx = 4;
		gbc_btnExit.gridy = 9;
		frmAddNewWord.getContentPane().add(btnExit, gbc_btnExit);			//add the exit button to the main content pane 
		frmAddNewWord.setVisible(true);			//to make the contents of our gui visible to the front user.
	}
	/**
	 * This function is used to add the new word which we want to add into the therusasFile.txt file which is a repository of all words which we have added till now.
	 *@param - content is the word along with the synonyms which needs to be added to the file.
	 *Output - the will give us output file having the appended word in it.
	 */
	public void writeFile(String content)
	{
		

		BufferedWriter bw = null;			//BufferWriter object created so as to write contents in the file.
		FileWriter fw = null;			//FileWriter object created to get access to the file which we want to write

		try {

			

			File file = new File("therusasFile.txt");			//Create new object of File type

			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// true = append file
			fw = new FileWriter(file.getAbsoluteFile(), true);			//Keep the existing content and append the new content to the end of a file.
			
			bw = new BufferedWriter(fw);			//to direct all the contents appended onto buffered o/p stream to file
			
			bw.newLine();			//append new line on to the file
			bw.write(content);		//write the word and its synonyms onto the file

			//System.out.println("Done");

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

}
