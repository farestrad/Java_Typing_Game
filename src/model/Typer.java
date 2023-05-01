package model;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.Font;

public class Typer {

	private JFrame frame;
	
	JLabel currentWord; //define the jLabels here so that they are used globally
	JLabel nextWord;
	String original;
	String original2;
	
	public ArrayList<String> level1 = new ArrayList<>();
	public ArrayList<String> level2 = new ArrayList<>();
	public ArrayList<String> level3 = new ArrayList<>();
	public ArrayList<String> level4 = new ArrayList<>();
	public ArrayList<String> level5 = new ArrayList<>();
	public ArrayList<String> level6 = new ArrayList<>();
	public ArrayList<String> level7 = new ArrayList<>();
	public ArrayList<String> level8 = new ArrayList<>();

	public ArrayList<ArrayList<String>> allLevels = new ArrayList<>();
	
	int count = 0; //keep count of how many words solved
	int currentLevel = 0; //keep track of which level user is at
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Typer window = new Typer();
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
	public Typer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		currentWord = new JLabel("\"\"");
		currentWord.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_currentWord = new GridBagConstraints();
		gbc_currentWord.insets = new Insets(0, 0, 5, 0);
		gbc_currentWord.gridx = 6;
		gbc_currentWord.gridy = 2;
		panel.add(currentWord, gbc_currentWord);
		
		nextWord = new JLabel("\"\"");
		nextWord.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_nextWord = new GridBagConstraints();
		gbc_nextWord.gridx = 6;
		gbc_nextWord.gridy = 3;
		panel.add(nextWord, gbc_nextWord);
		
		allLevels.add(level1);
		allLevels.add(level2);
		allLevels.add(level3);
		allLevels.add(level4);
		allLevels.add(level5);
		allLevels.add(level6);
		allLevels.add(level7);
		allLevels.add(level8);
		
		for(int i = 0;  i < 8; i++) {
			//because the text files matches the arrayLists names we can use that with index i to add them 
			readText("Words/level" + (i+1) + ".txt", allLevels.get(i));
		}
		
		//call first game method in initialize
		firstGame();
	}


	/*
	 * Load each text file into its corresponding ArrayList,
	 * it will take two parameters, first one is the path to the text document
	 * and the second is its corresponding arrayList.
	 * 	-We will use the BufferedClass to read the external files
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void readText(String path, ArrayList currentArray) {
		
		try {
			//BufferedReader will take the path as input and insert it into the arraylist
			BufferedReader buf = new BufferedReader(new FileReader(path));
			String word;
			word = buf.readLine(); //reads currentLine and moves to next line
			while(word != null) {
				currentArray.add(word);
				word = buf.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	
	public void levelCounter() {
		/*
		 * we will call this every time the user inputs a word correctly
		 */
		count++;
		if(count %9 == 0) {
			count = 0;
			currentLevel++;
		}
	}
	
	/*
	 * this methods selects a random words to appear
	 */
	public String selectRandomWord() {
		ArrayList<String> input = allLevels.get(currentLevel);
		Random r = new Random();
		int randomNumber = r.nextInt(input.size());
		String output = input.get(randomNumber);
		levelCounter();
		return output;
	}
	
	/*
	 * Create a method that takes the output from randomSelectedWords
	 * and stores them in Strings
	 * this method will call randomSelectedWords twice first time for current word and another for next word
	 */
	
	public void firstGame() { //this method is only called the very first time once game is launched or restarted
		original = selectRandomWord(); //get a random word
		currentWord.setText(original); //set the label to the random word
		original2 = selectRandomWord(); //get another random word
		nextWord.setText(original2); //set next to the second random word
	}
	
	/*
	 * This method will take the word displayed in nextWord label and display it in current word label
	 * and will call random word once and display output as nextWord label
	 * we call this method everytime the enter button is clicked
	 */
	public void game() {
		original2 = selectRandomWord(); //get a new random word
		original = nextWord.getText(); //set text of original to the String in nextLabel
		currentWord.setText(original); //now current word is original which is what used to be 'nextWord'
		nextWord.setText(original2); //now nextWord is the new randomly selected word.
	}
	
	
	
	
	
	
}
