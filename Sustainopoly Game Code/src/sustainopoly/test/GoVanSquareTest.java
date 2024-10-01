package sustainopoly.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import gui.MainWindow;
import sustainopoly.FreeStepCard;
import sustainopoly.GameBoard;
import sustainopoly.GameBoardComplete;
import sustainopoly.GoVanSquare;
import sustainopoly.Player;
import sustainopoly.Role;
import sustainopoly.SustainopolyGame;

class GoVanSquareTest {
	
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
	
	/**
	 * The playAction() method increases the players money if the player lands on or
	 * passes GoVanSquare, and the amount it increases by depends on the players role.
	 */
	@Test
	public void testPlayAction() {
		
		GoVanSquare goVanSquare = new GoVanSquare();
		
		for (int i = 0; i < game.getNumPlayers(); i++) {
			Role currentPlayerRole = game.getCurrentPlayer().getRole();
			int moneyBefore = game.getCurrentPlayer().getMoney();
			goVanSquare.playAction();
			
			switch(currentPlayerRole) {
			case ASYLUM_SEEKER:
				assertEquals(game.getCurrentPlayer().getMoney(), moneyBefore + 100);
				break;
			case GOVAN_LOCAL:
				assertEquals(game.getCurrentPlayer().getMoney(), moneyBefore + 400);
				break;
			case VOLUNTEER:
				assertEquals(game.getCurrentPlayer().getMoney(), moneyBefore + 500);
				break;
			case COMMUNITY_LEADER:
				assertEquals(game.getCurrentPlayer().getMoney(), moneyBefore + 750);
				break;
			case COUNCILLOR:
				assertEquals(game.getCurrentPlayer().getMoney(), moneyBefore + 1000);
				break;
			case ORGANISATION:
				assertEquals(game.getCurrentPlayer().getMoney(), moneyBefore + 1250);
				break;
			}
			
			game.endTurnBtnClicked();
		}
	}

}
