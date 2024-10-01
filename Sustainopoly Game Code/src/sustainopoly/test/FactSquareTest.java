package sustainopoly.test;

import sustainopoly.GameBoardComplete;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import gui.MainWindow;
import sustainopoly.Player;
import sustainopoly.SustainopolyGame;
import sustainopoly.FactSquare;
import sustainopoly.GameBoard;
class FactSquareTest {
	
	private static SustainopolyGame game;
	
	@BeforeAll
	static void init() {
		game = SustainopolyGame.getInstance();
		GameBoard board = new GameBoardComplete();
		game.setGameBoard(board);
		
		MainWindow window = new MainWindow(board);
		game.setGUI(window);		
		window.setVisible(false);
		
		game.startGame();
	}
	
	// create a new FactSquare
	FactSquare factSquare = new FactSquare("Test FactSquare");

	
	@Test
	public void testPlayAction() {
		
		Player testP1  = game.getPlayer(0);
		testP1.setExpertise(0);
		factSquare.playAction();
		assertEquals(5, testP1.getExpertise());
	}
}
