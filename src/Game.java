// package com.edu4java.minitennis1;
import javax.swing.JFrame;

public class Game {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Mini Tennis");
		// frame.setSize(300, 300);

		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}