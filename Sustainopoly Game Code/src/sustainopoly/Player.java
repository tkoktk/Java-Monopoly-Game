package sustainopoly;

import java.awt.Color;
import java.util.ArrayList;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Player {
	private String name;	// player's name
	private int money;		// player's money
	private int expertise;		// player's expertise value between 0-100
	private Role role;		// player's role within the game, which will determine unique game play aspects on certain squares
	
	private boolean nextStepFree; 		// flag determining if player will be allowed to complete a step of a task they own for free (single use)
	private boolean nextContributionFree;		// flag determining if player will get their contribution free next time they land on someone else's task (single use)
	private boolean nextStepCostsDouble;		// flag determining if the player will be charged twice the normal amount to complete a step of a task they own (single use)
	private boolean nextContributionCostsDouble;	// flag determining if the player will be asked to contribute twice the normal amount next time they contribute to someone else's task (single use)
	private int squareTimeout;		// required number of squares to be moved before player can complete task steps again (our implemented concept of time)
	
	private Square position;		// current Square the player is situated on
	private ArrayList<TaskSquare> tasks = new ArrayList<>();		// tasks currently managed by player (**if ever add other manageable squares that aren't TaskSquare, can change to ArrayList<Square>**)
	private ArrayList<DevelopmentArea> developmentAreas = new ArrayList<>();	// development areas that the Player manages at least one task of
	
	private Color colour; // The unique colour associated with the player
	private ImageIcon portrait = new ImageIcon("images/Sprites/Asylum_Seeker_Sprite.png"); //The unique portrait of the player. Set to asylum seeker for default.
	private ImageIcon sprite; // The sprite of the player's portrait
	
	/**
	 * Creates a Player, initialising variables appropriately
	 * @param name
	 */
	public Player() {
		this.expertise = 0;
		this.nextStepFree = false;
		this.squareTimeout = 0;
		this.position = SustainopolyGame.getInstance().getGameBoard().getSquare(0);
	}
	
	/**
	 * Returns the Player's name
	 * @return Player name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Sets the Player's name to the name provided
	 * @param name Player name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the Square that the Player is currently situated on
	 * @return Current Square of Player
	 */
	public Square getPosition() {
		return this.position;
	}
	
	/**
	 * Sets the new Square that the Player will be situated on
	 * @param newPosition New Square of Player
	 */
	public void setPosition(Square newPosition) {
		this.position = newPosition;
	}
	
	/**
	 * Returns the Player's money
	 * @return Player money
	 */
	public int getMoney() {
		return this.money;
	}
	
	/**
	 * Sets the money of the Player
	 * @param money Player money
	 */
	public void setMoney(int money) {
		
		// enforce that the minimum money is 0, can't go negative
		if (money < 0)
			this.money = 0;
		else
			this.money = money;
	}
	
	/**
	 * Returns the Player's expertise level (0-100)
	 * @return Player expertise
	 */
	public int getExpertise() {
		return this.expertise;
	}
	
	/**
	 * Sets the expertise level of the Player
	 * @param expertise Player expertise
	 */
	public void setExpertise(int expertise) {
		
		// enforce that the maximum expertise is 100
		if (expertise > 100)
			this.expertise = 100;
		else
			this.expertise = expertise;
	}
	
	/**
	 * Returns the Player's role (from Role enumeration)
	 * @return Player role
	 */
	public Role getRole() {
		return this.role;
	}
	
	/**
	 * Sets the Player's role (from Role enumeration)
	 * @param role Player role
	 */
	public void setRole(Role role) {
		this.role = role;
	}
	
	/**
	 * Returns boolean value whether the Player is able to complete their next task step for free
	 * @return Boolean value; if true, Player will get next task step free
	 */
	public boolean getNextStepFree() {
		return this.nextStepFree;
	}
	
	/**
	 * Sets a boolean value whether the Player will be able to complete their next task step for free
	 * @param nextStepFree Boolean value; if set to true, Player will get next task step free
	 */
	public void setNextStepFree(boolean nextStepFree) {
		this.nextStepFree = nextStepFree;
	}
	
	/**
	 * Returns boolean value whether the Player will get their next contribution to another Player's task for free
	 * @return Boolean value; if true, Player will get next contribution free
	 */
	public boolean getNextContributionFree() {
		return this.nextContributionFree;
	}
	
	/**
	 * Sets a boolean value whether the Player will get their next contribution to another Player's task for free
	 * @param nextContributionFree Boolean value; if set to true, Player will get next contribution free
	 */
	public void setNextContributionFree(boolean nextContributionFree) {
		this.nextContributionFree = nextContributionFree;
	}
	
	/**
	 * Returns boolean value whether the Player will be charged double to complete their next task step
	 * @return Boolean value; if true, Player will be charged double
	 */
	public boolean getNextStepCostsDouble() {
		return this.nextStepCostsDouble;
	}
	
	/**
	 * Sets a boolean value whether the Player will be charged double to complete their next task step
	 * @param nextStepCostsDouble Boolean value; if set to true, Player will be charged double
	 */
	public void setNextStepCostsDouble(boolean nextStepCostsDouble) {
		this.nextStepCostsDouble = nextStepCostsDouble;
	}
	
	/**
	 * Returns boolean value whether the Player will have to contribute double next time they contribute to another Player's task
	 * @return Boolean value; if true, Player will have to contribute double
	 */
	public boolean getNextContributionCostsDouble() {
		return this.nextContributionCostsDouble;
	}
	
	/**
	 * Sets a boolean value whether the Player will have to contribute double next time they contribute to another Player's task
	 * @param nextContributionCostsDouble Boolean value; if set to true, Player will have to contribute double
	 */
	public void setNextContributionCostsDouble(boolean nextContributionCostsDouble) {
		this.nextContributionCostsDouble = nextContributionCostsDouble;
	}
	
	/**
	 * Returns the current Square timeout of the Player- the number of squares to be moved before they can complete task steps again
	 * @return Player square timeout
	 */
	public int getSquareTimeout() {
		return this.squareTimeout;
	}
	
	/**
	 * Sets the new Square timeout of the Player- the number of squares to be moved before they will be able to complete task steps again
	 * @param squareTimeout Player square timeout
	 */
	public void setSquareTimeout(int squareTimeout) {
		this.squareTimeout = squareTimeout;
	}
	
	/**
	 * Returns the TaskSquares that the Player currently manages
	 * @return ArrayList containing Player's currently managed tasks
	 */
	public ArrayList<TaskSquare> getTasks() {
		return this.tasks;
	}

	/**
	 * Returns an ArrayList containing the unique development areas that the Player manages at least one task of
	 * @return ArrayList of DevelopmentArea
	 */
	public ArrayList<DevelopmentArea> getDevelopmentAreas() {
		return this.developmentAreas;
	}
	
	/**
	 * Returns a java.awt.Color that the Player is associated with, used for styling purposes.
	 * @return colour of type java.awt.Color
	 */
	public Color getColour() {
		return this.colour;
	}

	/**
	 * Sets the player's associated colour
	 * @param colour new Player's colour
	 */
	public void setColour(Color colour) {
		this.colour = colour;
	}
	
	/**
	 * 
	 * @return
	 */
	public ImageIcon getPortrait() {
		return this.portrait;
	}
	
	/**
	 * Resizes the Player's Portrait without modifying the original image object,
	 * then returns it.
	 * 
	 * @param width:  The specified width the portrait is to be resized to.
	 * @param height: The specified height the portrait is to be resized to.
	 * @return the resized portrait as an ImageIcon
	 */
	public ImageIcon getPortrait(int width, int height) {
		Image resizedImage = this.portrait.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon resizedPortrait = new ImageIcon(resizedImage);
		return resizedPortrait;
	}

	/**
	 * Sets the Player's unique portrait, used in the GUI
	 * @param portrait unique portrait of type ImageIcon
	 */
	public void setPortrait(ImageIcon portrait) {
		this.portrait = portrait;
	}

	public ImageIcon getSprite() {
		return this.sprite;
	}

	/**
	 * Resizes the Player's Sprite without modifying the original image object, then returns it.
	 * @param width:  The specified width the sprite is to be resized to.
	 * @param height: The specified height the sprite is to be resized to.
	 * @return the resized sprite as an ImageIcon
	 */
	public ImageIcon getSprite(int width, int height) {
		Image resizedImage = this.sprite.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon resizedSprite = new ImageIcon(resizedImage);
		return resizedSprite;
	}

	/**
	 * Sets the Player's unique sprite, used in the GUI
	 * @param sprite unique sprite of type ImageIcon
	 */
	public void setSprite(ImageIcon sprite) {
		this.sprite = sprite;
	}
	
	/**
	 * Player completes a step of a provided task, removing the appropriate amount
	 * of money and advancing the completion of the task
	 * 
	 * @param task The TaskSquare of which a step is being completed
	 */
	public void completeTaskStep(TaskSquare task) {

		// Player has the nextStepFree effect- won't be charged, and single use so effect removed
		if (nextStepFree)
			nextStepFree = false;

		// Player has the nextStepCostsDouble effect- charged double, and single use so effect removed
		else if (nextStepCostsDouble) {
			this.money -= (2 * task.getStepPrice());
			nextStepCostsDouble = false;
		} else
			this.money -= task.getStepPrice();

		// increment step completion of TaskSquare
		task.completeStep();
	}
	
	/**
	 * Allows the Player to take charge of a TaskSquare
	 */
	public void takeChargeOfTask(Square pos) {
		if (pos instanceof TaskSquare) {
			TaskSquare ts = (TaskSquare) pos;

			// if the TaskSquare is available, add it to the Player's tasks and set it to unavailable

			// NOTE: verification of resources not done here, but should be done when Player
			// is moved- only if sufficient
			// money + expertise for new TaskSquare will Take Charge button be available
			if (ts.isAvailable()) {
				tasks.add(ts);
				ts.setAvailable(false);
				ts.setOwner(this);

				// if this is the first TaskSquare of a DevelopmentArea that they've taken
				// charge of, add the da to their developmentAreas ArrayList
				if (developmentAreas.indexOf(ts.getDevelopmentArea()) == -1)
					developmentAreas.add(ts.getDevelopmentArea());

				// charge the player the money and square timeout costs of the TaskSquare
				this.money -= ts.getPrice();
				this.squareTimeout += ts.getSquareTimeoutPrice();

				// increment the Player's expertise
				setExpertise(this.expertise + 5);
			}
		}
	}
	
	/**
	 * If a Player lands on a TaskSquare they do not manage, they can choose to accept to pay a contribution to the task owner
	 * @param taskOwner Another Player who is in charge of the TaskSquare
	 * @param amount Amount the Player is expected to contribute to the task
	 */
	public void payContribution(Player taskOwner, int amount) {
		
		// invalid inputs/ negative amount or player can't afford contribution
		if(amount < 0 || money - amount < 0) 
			return;
		
		// Player has the nextStepFree effect- won't be charged, and single use so effect removed
		if (nextContributionFree)
			nextContributionFree = false;

		// Player has the nextStepCostsDouble effect- charged double, and single use so effect removed
		else if (nextContributionCostsDouble) {
			this.money -= (2 * amount);
			taskOwner.money += amount;	// addition of first amount, second amount addition is done below
			nextContributionCostsDouble = false;
		} else
			this.money -= amount;
		
		taskOwner.money += amount;
		
		// increase expertise by 5 for a contribution
		setExpertise(this.expertise + 5);
	}
}
