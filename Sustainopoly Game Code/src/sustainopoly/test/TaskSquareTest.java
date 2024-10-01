package sustainopoly.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sustainopoly.DevelopmentArea;
import sustainopoly.GameBoard;
import sustainopoly.GameBoardComplete;
import sustainopoly.Player;
import sustainopoly.SustainopolyGame;
import sustainopoly.TaskSquare;

class TaskSquareTest {
	
	private static SustainopolyGame game;
	private TaskSquare task;
	
	@BeforeAll
	static void init() {
		game = SustainopolyGame.getInstance();
		GameBoard board = new GameBoardComplete();
		game.setGameBoard(board);
	}
	
	@BeforeEach
	void individualInit() {
		
		// test TaskSquare
		String name = "Test Square";
		String description = "This is a Test square";
		DevelopmentArea developmentArea = DevelopmentArea.ADVANCE_AWARENESS;
		ArrayList<String> steps = new ArrayList<String>(Arrays.asList("Test step 1", "Test step 2"));
		int price = 100;
		int squareTimeoutPrice = 5;
		int stepPrice = 10;
		int contribution = 20;
		int requiredExpertise = 50;

		task = new TaskSquare(name, description, developmentArea, steps, price, squareTimeoutPrice,
				stepPrice, contribution, requiredExpertise);
		
	}

	/**
	 * Test Constructor of Task Square using Valid Inputs.
	 * 
	 * Expected result: code runs as expected, with the constructors setting the
	 * class variables successfully, and so the get methods will work successfully.
	 */
	@Test
	public void testConstructorWithValidInputs() {
		// Test values
		String name = "Test Square";
		String description = "This is a Test square";
		DevelopmentArea developmentArea = DevelopmentArea.ADVANCE_AWARENESS;
		ArrayList<String> steps = new ArrayList<String>(Arrays.asList("Test step 1", "Test step 2"));
		int price = 100;
		int squareTimeoutPrice = 5;
		int stepPrice = 10;
		int contribution = 20;
		int requiredExpertise = 50;

		TaskSquare taskSquare = new TaskSquare(name, description, developmentArea, steps, price, squareTimeoutPrice,
				stepPrice, contribution, requiredExpertise);

		assertEquals(name, taskSquare.getName());
		assertEquals(description, taskSquare.getDescription());
		assertEquals(developmentArea, taskSquare.getDevelopmentArea());
		assertEquals(steps, taskSquare.getSteps());
		assertEquals(price, taskSquare.getPrice());
		assertEquals(squareTimeoutPrice, taskSquare.getSquareTimeoutPrice());
		assertEquals(stepPrice, taskSquare.getStepPrice());
		assertEquals(contribution, taskSquare.getExpectedContribution());
		assertEquals(requiredExpertise, taskSquare.getRequiredExpertise());
	}

	/**
	 * Test to see if when the available variable is set to true, the isAvailable()
	 * method returns true also.
	 * 
	 * Expected result: It returns true.
	 */
	@Test
	public void testIsAvailableWithTrue() {

		// Set the available variable to true using its setter since it's not set in the
		// constructor.
		task.setAvailable(true);
		boolean expectedResult = true;
		// Use assertEqual to test if the isAvailable method returns the same value as
		// the expected result.
		assertEquals(task.isAvailable(), expectedResult);
	}

	/**
	 * Test to see if when the available variable is set to false, the isAvailable()
	 * method returns false also.
	 * 
	 * Expected result: It returns false.
	 */
	@Test
	public void testIsAvailableWithFalse() {
		
		// Set the available variable to false using its setter since it's not set in
		// the constructor.
		task.setAvailable(false);
		boolean expectedResult = false;
		// Use assertEqual to test if the isAvailable method returns the same value as
		// the expected result.
		assertEquals(task.isAvailable(), expectedResult);
	}

	/**
	 * The getExpectedContribution() method checks if a task step is greater than
	 * zero, and if it is the contribution price changes. If the task step is zero,
	 * the expectedContribution should remain the same.
	 * 
	 * When a task step is completed, the completeStep() method is called, which
	 * increments the stepNumber variable. In this test, that method will not be
	 * called in order to test what happens when no steps have been completed.
	 * 
	 * Expected result: The expectedContribution remains the same at 100 since the
	 * Task's stepNumber is zero.
	 */
	@Test
	public void testGetExpectedContributionWithNoStepsCompleted() {
		int expectedContribution = 20;
		int actualContribution = task.getExpectedContribution();
		// Use assertEqual to test if the getExpectedContribution method returns the same value as
		// the expected result.
		assertEquals(expectedContribution, actualContribution);
	}
	
	/**
	 * Test: Make sure that the expected Contribution of the task changes when a task step is completed.
	 * 
	 * Expected result: The values of the startingContribution variable and the task step's contribution variable do not match after the completeStep() method is called.
	 * 
	 * FINISH WHEN CODE IS COMPLETE AS IT IS NOT IMPLEMENTED YET
	 */
	@Test
	public void testGetExpectedContributionWithStepsCompleted() {
		
		//Set the expectedContribution of the task square to 20 when it's created.
		int startingContribution = 20;
		
		//call the completeStep() method to complete a step (this affects the getExpectedContribution method).
		task.completeStep();
		int actualContribution = task.getExpectedContribution();
		// Use assertNotEquals to ensure that the values do not match.
		assertNotEquals(startingContribution, actualContribution);
	}
	
	@Test 
	public void testGetOwner() {
		
		Player testPlayer = new Player();
		testPlayer.setMoney(100);
		testPlayer.takeChargeOfTask(task);
		assertEquals(task.getOwner(), testPlayer);
	}
	
	@Test
	public void testGetNumSteps() {
		assertEquals(task.getNumSteps(), 2);
	}
	
	@Test
	public void testIsCompleteTrue() {
		
		// has two steps so complete both
		task.completeStep();
		task.completeStep();
		
		assertTrue(task.isComplete());
	}
	
	@Test
	public void testIsCompleteFalse() {
		
		// has two steps but only complete one
		task.completeStep();
		
		assertFalse(task.isComplete());
	}
	
	@Test 
	public void testGetNextTaskStep() {
		
		// complete one step, so next will be "Test step 2"
		task.completeStep();
		
		assertEquals(task.getNextTaskStep(), "Test step 2");
	}

}
