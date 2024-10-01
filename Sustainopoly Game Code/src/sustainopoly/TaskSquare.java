package sustainopoly;

import java.util.ArrayList;

import gui.ContributeResourcesDialog;

public class TaskSquare extends Square {
	private String description;
	private boolean available = true;		// boolean value whether this Square is currently unmanaged/not taken charge of
	private Player owner;	// Player who is in charge of this TaskSquare(if there is one)
	
	private DevelopmentArea developmentArea;	// DevelopmentArea which the TaskSquare is part of
	private ArrayList<String> steps; 	// ArrayList holding the several steps which make up the TaskSquare
	private int totalNumOfSteps; 	// total number of steps within the TaskSquare, will be set to the length of the steps ArrayList
	private int currentStepNumber; 		// index of the current step within the steps ArrayList of the TaskSquare; each task is comprised of multiple steps
	
	private int price;		// money cost to take charge of task
	private int stepPrice;		// cost to complete a step of task
	private int squareTimeoutPrice;		// square timeout (our concept of time) cost of taking charge of task
	private int contribution;		// base contribution if another player lands on task while it's already managed
	private int requiredExpertise;		// required expertise to be able to take charge of task
	
	public TaskSquare(String name, String description, DevelopmentArea developmentArea,
			ArrayList<String> steps, int price, int squareTimeoutPrice, int stepPrice, int contribution, int requiredExpertise) {
		setName(name);
		this.description = description;
		this.developmentArea = developmentArea;
		this.steps = new ArrayList<String>(steps);
		this.price = price;
		this.squareTimeoutPrice = squareTimeoutPrice;
		this.stepPrice = stepPrice;
		this.contribution = contribution;
		this.requiredExpertise = requiredExpertise;
		
		this.currentStepNumber = 0;
		this.totalNumOfSteps = steps.size();
	}
	
	/**
	 * Returns a longer description of the TaskSquare's task
	 * @return String description
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Returns a boolean value; if true, the TaskSquare is not currently managed/ taken charge of
	 * @return Boolean value; if true, TaskSquare is available
	 */
	public boolean isAvailable() {
		return this.available;
	}
	
	/**
	 * Sets a boolean value whether the TaskSquare is not currently managed/ taken charge of
	 * @param available Boolean value; if set to false, TaskSquare will no longer be available
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	/**
	 * Returns the owner/Player who manages the TaskSquare
	 * @return Player managing the TaskSquare
	 */
	public Player getOwner() {
		return this.owner;
	}
	
	/**
	 * Sets the new owner/Player who now manages the TaskSquare
	 * @param owner Player now managing the TaskSquare
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}
	
	/**
	 * Returns the development area that the TaskSquare is part of
	 * @return DevelopmentArea of Task
	 */
	public DevelopmentArea getDevelopmentArea() {
		return this.developmentArea;
	}
	
	/**
	 * Return the steps for the Task
	 * @return ArrayList of Strings describing the steps of the task
	 */
	public ArrayList<String> getSteps() {
		return steps;
	}
	
	/**
	 * Returns the number of constituent steps within the task
	 * @return Number of steps
	 */
	public int getNumSteps() {
		return this.totalNumOfSteps;
	}

	/**
	 * Returns the current step number (/index of steps ArrayList) of the TaskSquare (also same as number of completed steps)
	 * @return TaskSquare's current step number/number of completed steps
	 */
	public int getCurrentStepNumber() {
		return this.currentStepNumber;
	}

	/**
	 * Returns the price to take charge of the TaskSquare
	 * @return TaskSquare's price
	 */
	public int getPrice() {
		return this.price;
	}

	/**
	 * Returns the expertise level required to take charge of the TaskSquare
	 * @return TaskSquare's required expertise level
	 */
	public int getRequiredExpertise() {
		return this.requiredExpertise;
	}

	/**
	 * Gets the square timeout price of taking charge of the TaskSquare- how many Squares the Player will have to move before being able to complete task steps again
	 * @return TaskSquare's square timeout price
	 */
	public int getSquareTimeoutPrice() {
		return this.squareTimeoutPrice;
	}
	
	/**
	 * Returns the monetary price of completing a step of the TaskSquare
	 * @return TaskSquare's step price
	 */
	public int getStepPrice() {
		return this.stepPrice;
	}
	
	/**
	 * Sets the monetary price of completing a step of the TaskSquare
	 * @param stepPrice TaskSquare's step price
	 */
	public void setStepPrice(int stepPrice) {
		this.stepPrice = stepPrice;
	}

	/**
	 * Calculates the expected contribution if a player lands on the TaskSquare while it is managed by another Player
	 * This is influenced by the base contribution specified for the TaskSquare, as well as the number of steps currently completed within the Task
	 * @return TaskSquare's expected contribution for its current state(taking into account number of steps completed)
	 */
	public int getExpectedContribution() {
		int expectedContribution = contribution;
		
		// the more steps completed within the Task, the higher the expected contribution
		int numStepsCompleted = this.getCurrentStepNumber();
		if (numStepsCompleted > 0) {
			expectedContribution = contribution * (numStepsCompleted + 1);
		}
		
		return expectedContribution;
	}
	
	/**
	 * Returns the task step description from within the steps ArrayList of the next step to be completed
	 * @return
	 */
	public String getNextTaskStep() {
		if (currentStepNumber < steps.size())
			return steps.get(currentStepNumber);
		return null;
	}
	
	/**
	 * Completing a task step increments the currentStepNumber variable by one
	 */
	public void completeStep() {
		if (!isComplete())		// can only complete a step if all steps have not yet been completed
			currentStepNumber++;
	}
	
	
	/**
	 * If the player has completed all the steps of the Task, currentStepNumber will be equal to numOfSteps
	 * We use a comparison between these variables to decide whether the task is complete or not
	 * @return true if Task is complete, false if not.
	 */
	public boolean isComplete() {
		if(currentStepNumber == totalNumOfSteps) {
			return true;
		}
		return false;
	}
	
	/**
	 * If a Player lands on a TaskSquare they don't own, they are requested to pay a contribution to the TaskSquare owner
	 */
	public void playAction() {
		if(!isAvailable()) {
			Player currentPlayer = SustainopolyGame.getInstance().getCurrentPlayer();
			if(owner != currentPlayer) {
				new ContributeResourcesDialog();
			}
		}
	}
}
