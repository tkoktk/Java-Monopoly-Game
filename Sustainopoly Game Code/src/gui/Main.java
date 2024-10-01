package gui;

import sustainopoly.GameBoard;
import sustainopoly.GameBoardComplete;
import sustainopoly.SustainopolyGame;

/**
 * Main application class
 */
public class Main {

	public static void main(String[] args) {
		SustainopolyGame game = SustainopolyGame.getInstance();		// SustainopolyGame instance which will manage the game
		GameBoard board = new GameBoardComplete();		// GameBoard instance which the game will be played on
		game.setGameBoard(board);		// associate the game board with the game manager
		
		MainWindow window = new MainWindow(board);		// main GUI which the players will interact with
		
		game.setGUI(window);		// associate the main GUI with the game manager	
		
		game.startGame();		// set the starting state of the game
		new MainMenu();		// open the Main Menu
	}

}
