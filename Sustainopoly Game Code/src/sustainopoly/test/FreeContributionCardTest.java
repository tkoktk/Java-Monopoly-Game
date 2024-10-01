package sustainopoly.test;




import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;

import sustainopoly.Player;
import org.junit.jupiter.api.Test;

import gui.MainWindow;
import sustainopoly.FreeContributionCard;
import sustainopoly.GameBoard;
import sustainopoly.GameBoardComplete;
import sustainopoly.SustainopolyGame;

class FreeContributionCardTest {

	private static SustainopolyGame game;
	private static FreeContributionCard card;
	
	@BeforeAll
	static void init() {
		game = SustainopolyGame.getInstance();
		GameBoard board = new GameBoardComplete();
		game.setGameBoard(board);
		
		MainWindow window = new MainWindow(board);
		game.setGUI(window);		
		window.setVisible(false);
		
		game.startGame();
		
		card = new FreeContributionCard("You volunteered at the local food bank and earned a free contribution!");
	}
	
	@Test
	public void testGetMessage() {
		assertEquals("You volunteered at the local food bank and earned a free contribution!", card.getMessage());
	}
	
	
	@Test
	public void testApplyAction() {
    	Player player = game.getPlayer(0);
    	player.setNextContributionFree(false);
		card.applyAction();
		assertTrue(player.getNextContributionFree());
	}
 

}
