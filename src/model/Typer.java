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
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Typer {

	private JFrame frame;
	
	JLabel currentWord; //define the jLabels here so that they are used globally
	JLabel nextWord;
	String original;
	String original2;
	int sec = 90;
	Timer t; //imports timer class
	int streak = 0;
	
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
	int currentScore = 0;
	private JTextField userinput;
	private JButton Enterbtn;
	private JLabel scoreLable;
	private JLabel timerLable;
	private JButton startBtn;
	
	ImageIcon play, again;
	private JButton restartBtn;
	
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
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		timerLable = new JLabel("Time: " + sec);
		GridBagConstraints gbc_timerLable = new GridBagConstraints();
		gbc_timerLable.insets = new Insets(0, 0, 5, 5);
		gbc_timerLable.gridx = 0;
		gbc_timerLable.gridy = 0;
		panel.add(timerLable, gbc_timerLable);
		
		scoreLable = new JLabel("\"\"");
		GridBagConstraints gbc_scoreLable = new GridBagConstraints();
		gbc_scoreLable.insets = new Insets(0, 0, 5, 0);
		gbc_scoreLable.gridx = 7;
		gbc_scoreLable.gridy = 0;
		panel.add(scoreLable, gbc_scoreLable);
		
		currentWord = new JLabel("\"\"");
		currentWord.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_currentWord = new GridBagConstraints();
		gbc_currentWord.insets = new Insets(0, 0, 5, 5);
		gbc_currentWord.gridx = 3;
		gbc_currentWord.gridy = 2;
		panel.add(currentWord, gbc_currentWord);
		
		nextWord = new JLabel("\"\"");
		nextWord.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_nextWord = new GridBagConstraints();
		gbc_nextWord.insets = new Insets(0, 0, 5, 5);
		gbc_nextWord.gridx = 3;
		gbc_nextWord.gridy = 3;
		panel.add(nextWord, gbc_nextWord);
		
		userinput = new JTextField();
		userinput.setEnabled(false);
		GridBagConstraints gbc_userinput = new GridBagConstraints();
		gbc_userinput.insets = new Insets(0, 0, 5, 5);
		gbc_userinput.fill = GridBagConstraints.HORIZONTAL;
		gbc_userinput.gridx = 3;
		gbc_userinput.gridy = 4;
		panel.add(userinput, gbc_userinput);
		userinput.setColumns(10);
		userinput.setEnabled(false);
		
		Enterbtn = new JButton("Enter");
		Enterbtn.setEnabled(false);
		Enterbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userWord = userinput.getText();
				if(userWord.contentEquals(original)) {
					updateScore();
					game();
					userinput.setText("");
					userinput.requestFocusInWindow();//auto focus so user can type directly without clicking on it with mouse
					streak++;
					if(streak==5) {
						streak = 0;
						sec += 5;
						timerLable.setText("Timer: " + sec);
					}
					
				}
				else {
				if(!userWord.equals(original)) {
					sec -=5;
					timerLable.setText("Timer: " + sec);
					userinput.setText("");
					game();
				}
				}
			}
		});
		GridBagConstraints gbc_Enterbtn = new GridBagConstraints();
		gbc_Enterbtn.insets = new Insets(0, 0, 5, 5);
		gbc_Enterbtn.gridx = 3;
		gbc_Enterbtn.gridy = 5;
		panel.add(Enterbtn, gbc_Enterbtn);
		
		startBtn = new JButton("start");
		//in the next line "Icons/play.png" is the path to the icon
		play = new ImageIcon("Icons/play.png");
		Image img1 = play.getImage();
		Image newPlay = img1.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH); //resize the img
		play = new ImageIcon(newPlay);//assign play to the newly created img
		startBtn.setIcon(play); //set icon
		startBtn.setBackground(Color.GREEN); //set background
		startBtn.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				time();
				Enterbtn.setEnabled(true);
				startBtn.setEnabled(false);
				userinput.setEnabled(true);
				frame.getRootPane().setDefaultButton(Enterbtn); //sets the EnterBtn as the button to be clicked when user hits Enter on their keyboard
				userinput.requestFocusInWindow();  //focus on it so that user doesnt need to use the mouse
				startBtn.setBackground(null);
			}
		});
		GridBagConstraints gbc_startBtn = new GridBagConstraints();
		gbc_startBtn.insets = new Insets(0, 0, 0, 5);
		gbc_startBtn.gridx = 3;
		gbc_startBtn.gridy = 6;
		panel.add(startBtn, gbc_startBtn);
		
		restartBtn = new JButton("Restart");
		again = new ImageIcon("Icons/again.png");
		Image img = again.getImage();
		Image newAgain = img.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		again = new ImageIcon(newAgain);
		restartBtn.setIcon(again);
		restartBtn.setBackground(Color.LIGHT_GRAY);
				
		restartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//we want to resetgame here so we will do the method somewhere else then use it as a helper
				JOptionPane.showMessageDialog(null, "GAME OVER!" + "\n" + "Your final score is: " + currentScore);
				resetGame();
			}
		});
		GridBagConstraints gbc_restartBtn = new GridBagConstraints();
		gbc_restartBtn.gridx = 7;
		gbc_restartBtn.gridy = 6;
		panel.add(restartBtn, gbc_restartBtn);
		
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
		currentScore = 0;
		scoreLable.setText("Score: " + currentScore);
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
	public void updateScore() {
		currentScore++;
		scoreLable.setText("Score: " + currentScore);
	}
	public void time() {
		t = new Timer(1000, new ActionListener() { //calls the method every 1000 milisecond
		
		public void actionPerformed(ActionEvent e) { //this is the action performed by the action listener
		if(sec == 0) { //times up!
			t.stop();
			JOptionPane.showMessageDialog(null, "GAME OVER!" + "\n" + "Your final score is: " + currentScore);
			userinput.setEnabled(false);
			Enterbtn.setEnabled(false);
			sec = 90;
			resetGame();
		}
		sec--;
		timerLable.setText("Time: " + sec);
		}
	});
	t.start(); //starts timer when this method is called
	}
	
	
	public void resetGame() {
		//clear allLevels array list
		for(int i = 0; i < allLevels.size(); i++) {
			allLevels.get(i).clear();
		}
		//read all text files again
		for(int i = 0; i < 8; i++) {
			readText("Words/level" + (i+1) +".txt", allLevels.get(i));
			//we did this because the select random words method removes selected words
		}
		//clear all vars
		count = 0;
		currentLevel = 0;
		currentScore = 0;
		scoreLable.setText("Score: " + currentScore);
		original = "";
		if(sec != 90) {
			t.stop();
		}
		sec = 90;
		timerLable.setText("Time: " + sec);
		Enterbtn.setEnabled(false);
		userinput.setEnabled(false);
		startBtn.setEnabled(true);
		frame.getRootPane().setDefaultButton(startBtn);
		frame.repaint();
		userinput.setText("");
		userinput.requestFocusInWindow();
		firstGame();
		
	}
	
	
	
}
