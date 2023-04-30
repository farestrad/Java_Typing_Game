package model;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Typer {

	private JFrame frame;
	
	public ArrayList<String> level1 = new ArrayList<>();
	public ArrayList<String> level2 = new ArrayList<>();
	public ArrayList<String> level3 = new ArrayList<>();
	public ArrayList<String> level4 = new ArrayList<>();
	public ArrayList<String> level5 = new ArrayList<>();
	public ArrayList<String> level6 = new ArrayList<>();
	public ArrayList<String> level7 = new ArrayList<>();
	public ArrayList<String> level8 = new ArrayList<>();

	public ArrayList<ArrayList<String>> allLevels = new ArrayList<>();
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
		
		allLevels.add(level1);
		allLevels.add(level2);
		allLevels.add(level3);
		allLevels.add(level4);
		allLevels.add(level5);
		allLevels.add(level6);
		allLevels.add(level7);
		allLevels.add(level8);
		
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
}
