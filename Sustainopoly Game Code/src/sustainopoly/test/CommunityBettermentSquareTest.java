package sustainopoly.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import gui.MainWindow;
import sustainopoly.CommunityBettermentSquare;
import sustainopoly.GameBoard;
import sustainopoly.GameBoardComplete;
import sustainopoly.GoVanSquare;
import sustainopoly.Role;
import sustainopoly.SustainopolyGame;

public class CommunityBettermentSquareTest {
	
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
	
	
	@Test
	public void testPlayAction() {
		
		CommunityBettermentSquare commBetterment = new CommunityBettermentSquare();
		
		for (int i = 0; i < game.getNumPlayers(); i++) {
			
			int moneyBefore = game.getCurrentPlayer().getMoney();
			int expertiseBefore = game.getCurrentPlayer().getExpertise();
			boolean nextStepFreeBefore = game.getCurrentPlayer().getNextStepFree();		// will be false
			boolean nextContributionFreeBefore = game.getCurrentPlayer().getNextContributionFree();		// will be false
			
			commBetterment.playAction();
			
			int moneyAfter = game.getCurrentPlayer().getMoney();
			int expertiseAfter = game.getCurrentPlayer().getExpertise();
			boolean nextStepFreeAfter = game.getCurrentPlayer().getNextStepFree();
			boolean nextContributionFreeAfter = game.getCurrentPlayer().getNextContributionFree();
			
			// check positive impact has been applied (note: different for different roles and multiple possible for each role,
			// so have to keep general
			assertTrue(moneyAfter > moneyBefore || expertiseAfter > expertiseBefore || 
					nextStepFreeBefore != nextStepFreeAfter || nextContributionFreeBefore != nextContributionFreeAfter);
			
			game.endTurnBtnClicked();
		}
	}
}
