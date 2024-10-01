package sustainopoly.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

import sustainopoly.Alliance;
import sustainopoly.DevelopmentArea;
import sustainopoly.GameBoard;
import sustainopoly.GameBoardComplete;
import sustainopoly.Player;
import sustainopoly.SustainopolyGame;
import sustainopoly.TaskSquare;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DevelopmentAreaTest {
    
	@BeforeAll
	static void init() {
		SustainopolyGame game = SustainopolyGame.getInstance();
		GameBoard board = new GameBoardComplete();
		game.setGameBoard(board);
	}
	
	@Test
	public void testToString() {
		assertEquals("Consult Community", DevelopmentArea.CONSULT_COMMUNITY.toString());
		assertEquals("Find Funding", DevelopmentArea.FIND_FUNDING.toString());
		assertEquals("Advance Awareness", DevelopmentArea.ADVANCE_AWARENESS.toString());
		assertEquals("Train Team", DevelopmentArea.TRAIN_TEAM.toString());
		assertEquals("Organize Online Presence", DevelopmentArea.ORGANIZE_ONLINE_PRESENCE.toString());
		assertEquals("Dispense Devices", DevelopmentArea.DISPENSE_DEVICES.toString());
		assertEquals("Get GoVan", DevelopmentArea.GET_GOVAN.toString());
		assertEquals("Source Supplychain", DevelopmentArea.SOURCE_SUPPLYCHAIN.toString());
		assertEquals("Increment Infrastructure", DevelopmentArea.INCREMENT_INFRASTRUCTURE.toString());
	}
	
	/**
	 * Test  allianceFormed with valid input
	 * 
	 * Expected result: code runs as expected, with the test alliance
	 * being successfully assigned to the development area.
	 */
	@Test
	public void testAllianceFormed() {
		
		// test values
		DevelopmentArea developmentArea = DevelopmentArea.ADVANCE_AWARENESS;
		ArrayList<Player> members = new ArrayList<>();
		Player testPlayer1 = new Player();
		Player testPlayer2 = new Player();
		members.add(testPlayer1);
		members.add(testPlayer2);
		Alliance testAlliance = new Alliance(developmentArea, members);
		
		developmentArea.allianceFormed(testAlliance);
		assertEquals(testAlliance, developmentArea.getAlliance());
	}
	
	
	/**
	 * Test  addTask with valid input
	 * 
	 * Expected result: code runs as expected, with the test TaskSquare
	 * being successfully added to the development area tasks.
	 */
	@Test
	public void testAddTask() {
		
		// test values
		String name = "Test Square";
		String description = "This is a Test square";
		DevelopmentArea developmentArea = DevelopmentArea.ADVANCE_AWARENESS;
		ArrayList<String> steps = new ArrayList<String>(Arrays.asList("Test step 1", "Test step 2"));
		int price = 100;
		int squareTimeoutPrice = 5;
		int stepPrice = 10;
		int contribution = 20;
		int requiredExpertise = 50;

		TaskSquare testTaskSquare = new TaskSquare(name, description, developmentArea, steps, price, squareTimeoutPrice,
				stepPrice, contribution, requiredExpertise);
		developmentArea.addTask(testTaskSquare);
		assertTrue(developmentArea.getTasks().contains(testTaskSquare));
	}
	
	@Test
	public void testIsManagedByAllianceTrue() {
		
		// test values
		DevelopmentArea developmentArea = DevelopmentArea.ADVANCE_AWARENESS;
		ArrayList<Player> members = new ArrayList<>();
		Player testPlayer1 = new Player();
		Player testPlayer2 = new Player();
		members.add(testPlayer1);
		members.add(testPlayer2);
		Alliance testAlliance = new Alliance(developmentArea, members);
		
		developmentArea.allianceFormed(testAlliance);
		assertTrue(developmentArea.isManagedByAlliance());
	}
	
	@Test
	@Order(1)
	public void testIsManagedByAllianceFalse() {
		
		// test values
		DevelopmentArea developmentArea = DevelopmentArea.ADVANCE_AWARENESS;
		assertFalse(developmentArea.isManagedByAlliance());
	}
	
}