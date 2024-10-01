package sustainopoly.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import gui.MainWindow;
import sustainopoly.GameBoard;
import sustainopoly.GameBoardComplete;
import sustainopoly.MoneyCard;
import sustainopoly.Player;
import sustainopoly.SustainopolyGame;
import sustainopoly.TimeoutCard;

public class TimeoutCardTest {
	
	private static SustainopolyGame game;
	private static TimeoutCard card;

	@BeforeAll
	static void init() {
		game = SustainopolyGame.getInstance();
		GameBoard board = new GameBoardComplete();
		game.setGameBoard(board);
		
		MainWindow window = new MainWindow(board);
		game.setGUI(window);		
		window.setVisible(false);
		
		game.startGame();
		
		card = new TimeoutCard("test", 10);
	}
	
	// Testing the applyAction() method with a random timeout number
	@Test
	public void testApplyAction() {
		card.applyAction();
		
		Player currentPlayer = SustainopolyGame.getInstance().getCurrentPlayer();
		assertEquals(currentPlayer.getSquareTimeout(), 10);
	}
	
	@Test
	public void testGetMessage() {
		assertEquals("test", card.getMessage());
	}
}
