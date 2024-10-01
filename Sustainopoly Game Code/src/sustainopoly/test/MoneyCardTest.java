package sustainopoly.test;

import sustainopoly.FreeStepCard;
import sustainopoly.GameBoard;

import sustainopoly.GameBoardComplete;
import sustainopoly.MoneyCard;
import sustainopoly.Player;
import sustainopoly.SustainopolyGame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import gui.MainWindow;

class MoneyCardTest {
	private static SustainopolyGame game;
	private static MoneyCard card;
	
	@BeforeAll
	static void init() {
		game = SustainopolyGame.getInstance();
		GameBoard board = new GameBoardComplete();
		game.setGameBoard(board);
		
		MainWindow window = new MainWindow(board);
		game.setGUI(window);		
		window.setVisible(false);
		
		game.startGame();
		
		card = new MoneyCard("This is your free money", 100);
	}
	
	@Test
	public void testGetMessage() {
		assertEquals("This is your free money", card.getMessage());
	}
	
	@Test
	public void testApplyAction() {
    	Player player = game.getPlayer(0);
    	player.setMoney(0);
    	card.applyAction();
    	assertEquals(100, player.getMoney());
	}
	
	

}
