package sustainopoly.test;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import gui.MainWindow;
import sustainopoly.GameBoard;
import sustainopoly.GameBoardComplete;
import sustainopoly.SocialIssueSquare;
import sustainopoly.SustainopolyGame;

public class SocialIssueSquareTest {
	
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
		
		SocialIssueSquare socialIssue = new SocialIssueSquare();
		
		for (int i = 0; i < game.getNumPlayers(); i++) {
			
			int moneyBefore = game.getCurrentPlayer().getMoney();
			int squareTimeoutBefore = game.getCurrentPlayer().getSquareTimeout();
			boolean nextStepCostsDoubleBefore = game.getCurrentPlayer().getNextStepCostsDouble();		// will be false
			boolean nextContributionCostsDoubleBefore = game.getCurrentPlayer().getNextContributionCostsDouble();	// will be false
			
			socialIssue.playAction();
			
			int moneyAfter = game.getCurrentPlayer().getMoney();
			int squareTimeoutAfter = game.getCurrentPlayer().getSquareTimeout();
			boolean nextStepCostsDoubleAfter = game.getCurrentPlayer().getNextStepCostsDouble();
			boolean nextContributionCostsDoubleAfter = game.getCurrentPlayer().getNextContributionCostsDouble();
			
			// check negative impact has been applied (note: different for different roles and multiple possible for each role,
			// so have to keep general
			assertTrue(moneyAfter < moneyBefore || squareTimeoutAfter > squareTimeoutBefore || 
					nextStepCostsDoubleBefore != nextStepCostsDoubleAfter || nextContributionCostsDoubleBefore != nextContributionCostsDoubleAfter);
			
			game.endTurnBtnClicked();
		}
	}
}
