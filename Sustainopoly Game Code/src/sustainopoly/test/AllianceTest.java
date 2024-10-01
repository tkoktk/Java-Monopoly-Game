package sustainopoly.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import sustainopoly.Alliance;
import sustainopoly.DevelopmentArea;
import sustainopoly.GameBoard;
import sustainopoly.GameBoardComplete;
import sustainopoly.Player;
import sustainopoly.SustainopolyGame;


class AllianceTest {
	
	private static SustainopolyGame game;
	
	@BeforeAll
	static void init() {
		game = SustainopolyGame.getInstance();
		GameBoard board = new GameBoardComplete();
		game.setGameBoard(board);
	}

	/**
	 * Test Constructor of Alliance Square using Valid Inputs.
	 * 
	 * Expected result: code runs as expected, with the constructors setting the
	 * class variables successfully, and so the get methods will work successfully.
	 */
	@Test
	public void testConstructorWithValidInputs() {
		
		// test values
		DevelopmentArea developmentArea = DevelopmentArea.ADVANCE_AWARENESS;
		ArrayList<Player> members = new ArrayList<>();
		Player testPlayer1 = new Player();
		Player testPlayer2 = new Player();
		members.add(testPlayer1);
		members.add(testPlayer2);

		Alliance testAlliance = new Alliance(developmentArea, members);

		assertEquals(developmentArea, testAlliance.getDevelopmentArea());
		assertEquals(members, testAlliance.getMembers());
	}


}
