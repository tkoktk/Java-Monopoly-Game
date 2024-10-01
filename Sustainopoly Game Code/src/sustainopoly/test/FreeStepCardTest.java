package sustainopoly.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import gui.MainWindow;
import sustainopoly.FreeContributionCard;
import sustainopoly.FreeStepCard;
import sustainopoly.GameBoard;
import sustainopoly.GameBoardComplete;
import sustainopoly.Player;
import sustainopoly.SustainopolyGame;
class FreeStepCardTest {

	private static SustainopolyGame game;
	private static FreeStepCard card;
	
	@BeforeAll
	static void init() {
		game = SustainopolyGame.getInstance();
		GameBoard board = new GameBoardComplete();
		game.setGameBoard(board);
		
		MainWindow window = new MainWindow(board);
		game.setGUI(window);		
		window.setVisible(false);
		
		game.startGame();
		
		card = new FreeStepCard("Your next step is free");
	}
	
	@Test
	public void getMesage() {
		assertEquals("Your next step is free",card.getMessage());
	}
	
	@Test
	public void testApplyAction() {
		Player testP1 = game.getPlayer(0);
		testP1.setNextContributionFree(false);
		card.applyAction();
		assertTrue(testP1.getNextStepFree());
	}
}
