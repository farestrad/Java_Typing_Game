package model;

import java.awt.EventQueue;
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
	}

}
