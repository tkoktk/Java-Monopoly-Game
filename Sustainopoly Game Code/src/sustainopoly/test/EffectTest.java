package sustainopoly.test;


import sustainopoly.GameBoard;
import sustainopoly.Effect;
import sustainopoly.GameBoardComplete;
import sustainopoly.SustainopolyGame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import gui.MainWindow;

class EffectTest {

	private static SustainopolyGame game;
	
	@BeforeAll
	static void init() {
		game = SustainopolyGame.getInstance();
		GameBoard board = new GameBoardComplete();
		game.setGameBoard(board);
		
		MainWindow window = new MainWindow(board);
		game.setGUI(window);		
		window.setVisible(false);
	}
	
	@Test
	public void testGetMessage() {
		Effect effect = new Effect("Player's money should increase by 100",() -> game.getPlayer(0).setMoney(game.getPlayer(0).getMoney() +100));
		assertEquals("Player's money should increase by 100", effect.getMessage());
	}

	@Test
    public void testApplyEffect() {
        // create a test player
    	game.startGame();
    	game.getPlayer(0).setMoney(0);
        // create a test effect that increases player's money by 100
        Effect effect = new Effect("Player's money should increase by 100",() -> game.getPlayer(0).setMoney(game.getPlayer(0).getMoney() +100));
        
        // apply the effect and check that the player's money has increased by 100
        effect.applyEffect();
        assertEquals(100,game.getPlayer(0).getMoney());
       
    }

}
