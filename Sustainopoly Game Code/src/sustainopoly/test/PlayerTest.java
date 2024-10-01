package sustainopoly.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gui.MainWindow;

import java.util.ArrayList;
import java.util.Arrays;

import sustainopoly.SustainopolyGame;
import sustainopoly.GameBoardComplete;
import sustainopoly.GoVanSquare;
import sustainopoly.DevelopmentArea;
import sustainopoly.GameBoard;
import sustainopoly.Player;
import sustainopoly.TaskSquare;
import sustainopoly.Square;


class PlayerTest {
	private static SustainopolyGame game;
	private TaskSquare taskSquare;
	private Player player;
	
	@BeforeAll
	static void init() {
		game = SustainopolyGame.getInstance();
		GameBoard board = new GameBoardComplete();
		game.setGameBoard(board);
	}
	
	@BeforeEach
	void individualInit() {
		
		// test TaskSquare
		String name = "Test Task Square1";
		String description = "This is a Test square";
		DevelopmentArea developmentArea = DevelopmentArea.ADVANCE_AWARENESS;
		ArrayList<String> steps = new ArrayList<String>(Arrays.asList("Test step 1", "Test step 2"));
		int price = 100;
		int squareTimeoutPrice = 5;
		int stepPrice = 10;
		int contribution = 20;
		int requiredExpertise = 50;

		taskSquare = new TaskSquare(name, description, developmentArea, steps, price, squareTimeoutPrice,
				stepPrice, contribution, requiredExpertise);
		
		player = new Player();
		player.setMoney(1000);
		
	}
	
	/**
	 * Test the set Position of the player method 
	 * Player p1=game.getPlayer(0);
	 */
	@Test
	public void testSetPosition() {
		
		//create a new square 
		GoVanSquare square = new GoVanSquare();
		
		//set the position of the current player on the new square
		player.setPosition(square);
		
		//check if the current position is equal to the new square 
		assertEquals(square, player.getPosition());
		
	}
	
	/**
	 * test the complete task step when the step is normal without any Reward/Cupons 
	 * This is checking that the current step before the method completetaskStep and after 
	 * is not the same
	 * Expected Result:Runs
	 */
	@Test
	public void testCompleteTaskStep1() {
		
		// store the players current step of the task into a variable 
		int stepCountBefore = taskSquare.getCurrentStepNumber();
		
		// call complete step
		player.completeTaskStep(taskSquare);
		
		assertEquals(stepCountBefore + 1, taskSquare.getCurrentStepNumber());	// step incremented
	}
	
	/**
	 * Test to check if the complete taskFree Step works when player has a nextStep Free Token 
	 * The player is set to have 0 money and then the complete step is called it will still work because of the
	 * next step free 
	 */
	@Test
	public void testCompleteTaskStep2() {

		// set the next step free as true 
		player.setNextStepFree(true);
		
		// set the players money to be 0
		player.setMoney(0);
		
		// store the current step of the steps in the variable 
		int stepCountBefore = taskSquare.getCurrentStepNumber();
		int moneyBefore = player.getMoney();
		
		//call the complete task step 
		player.completeTaskStep(taskSquare);
		 
		assertEquals(stepCountBefore + 1, taskSquare.getCurrentStepNumber());	// step incremented
		assertEquals(moneyBefore, player.getMoney());	// money is unchanged
	}
	
	
	/**
	 * Test to check if the complete taskStep Step works when player has a nextStepChargeDouble
	 * Set the money of the Player Exactly to double of the Step Price then after calling completeTaskStep
	 * the money is equal to 0 
	 * Expected Result:Runs
	 */
	@Test
	public void testCompleteTaskStep3() { 

		//set the next step cost double as true 
		player.setNextStepCostsDouble(true);
		
		//set the players money exactly to twice the cost of one step 
		player.setMoney(2 * taskSquare.getStepPrice());
		
		int stepCountBefore = taskSquare.getCurrentStepNumber();
		
		// complete the task step 
		player.completeTaskStep(taskSquare);
		
		assertEquals(0, player.getMoney());		// step has cost double so player's money reduced to 0
		assertEquals(stepCountBefore + 1, taskSquare.getCurrentStepNumber());	// step incremented
	}
	
	/**
	 * Test the method takeChargeofTask when the task is available 
	 * Expected Result:Runs
	 */
	@Test
	public void testTakeChargeofTask_checkTasks() {
		
		int moneyBefore = player.getMoney();

		// take the charge of the task using p1 
		player.takeChargeOfTask(taskSquare);
		
		assertTrue(player.getTasks().contains(taskSquare));		// task has been added to player's tasks
	}
	
	@Test
	public void testTakeChargeofTask_checkMoney() {
		
		int moneyBefore = player.getMoney();

		// take the charge of the task using  
		player.takeChargeOfTask(taskSquare);
		
		assertEquals(moneyBefore - taskSquare.getPrice(), player.getMoney());	// price has been deducted
	}
	
	/**
	 * Test to test if takeCharge of task adds the development area to the Players Development Area List 
	 * Expected Result:Runs
	 */
	@Test
	public void testTakeChargeofTask_checkDevelopmentAreas() {

		// take charge of the test taskSquare 
		player.takeChargeOfTask(taskSquare);
		
		// after taking charge of the square check if the player DA contains the development Area of the taskSquare 
		assertTrue(player.getDevelopmentAreas().contains(taskSquare.getDevelopmentArea()));
		
	}
	/**
	 * To test if takeChargeofTask adds the expertise to the player who took charge of the task 
	 */
	@Test
	public void testTakeChargeofTask_checkExpertise() {
		
		int expertiseBefore = player.getExpertise();
		
		// call the take charge method of the test task square 
		player.takeChargeOfTask(taskSquare);
		
		assertEquals(expertiseBefore + 5, player.getExpertise());	// expertise incremented by 5
		
	}
	
	/**
	 * To test if takeChargeofTask adds the money to the player who owns the task 
	 * Expected Result:Runs
	 */
	@Test
	public void testPayContribution() {
		
		Player player2 = new Player();
		player2.setMoney(100);
		
		//create a variable that hold the money of the p1 before contribution
		int moneyBefore = player.getMoney();
		
		//call pay contribution and contribute 2
		player2.payContribution(player, 5);
		
		assertEquals(moneyBefore + 5, player.getMoney());	// check that money has been received by player
		assertEquals(player2.getMoney(), 95);	// check that money has been removed from player2
		
	}
	
	/**
	 * To test if takeChargeofTask adds the expertise to the player who paidThecontribution   	
	 * Expected Result:Runs  
	 */
	@Test
	public void testPayContribution_checkExpertise() {
		Player player2 = new Player();
		player2.setMoney(100);
		
		int expertiseBefore=player2.getExpertise();
		
		//call pay contribution and contribute 2
		player2.payContribution(player, 5);
		
		//check if the previous expertise +5 that adds on to the previous one equal to the current one 
		assertEquals(expertiseBefore + 5, player2.getExpertise());	// check player2 has gained expertise for contribution
		
	}
	
}
