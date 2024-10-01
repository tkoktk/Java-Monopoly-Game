package sustainopoly;

import java.awt.Window;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import gui.BoardPanel.AnimationEvent;
import gui.EndGamePage;
import gui.MainWindow;

public class SustainopolyGame {
	private static SustainopolyGame uniqueInstance; // singleton instance of game manager
	private GameBoard gameBoard; // GameBoard instance to be managed by this game manager
	private Player[] players; // 8 instances of Player involved in the game
	private int currentPlayerIndex = 0; // index of the current player who's turn it is within the players array
	private final int NUM_PLAYERS = 8; // number of players in the game
	private Die[] dice; // Die[] array holding two six-sided dice

	private MainWindow gui;

	/**
	 * Returns the single existing instance of SustainopolyGame (singleton)
	 * 
	 * @return SustainopolyGame singleton instance
	 */
	public static SustainopolyGame getInstance() {
		if (uniqueInstance == null)
			uniqueInstance = new SustainopolyGame();

		return uniqueInstance;
	}

	/**
	 * Creates an instance of SustainopolyGame, initialising the players and dice
	 * arrays
	 */
	private SustainopolyGame() {
		this.players = new Player[NUM_PLAYERS];
		this.dice = new Die[] { new Die(), new Die() };
	}

	/**
	 * Returns the number of players within the game
	 * 
	 * @return Number of players
	 */
	public int getNumPlayers() {
		return this.NUM_PLAYERS;
	}

	/**
	 * Returns the index within the players array of the current Player who's turn
	 * it is
	 * 
	 * @return Players index of current player
	 */
	public int getCurrentPlayerIndex() {
		return this.currentPlayerIndex;
	}

	/**
	 * Returns the GameBoard managed by the SustainopolyGame instance
	 * 
	 * @return GameBoard instance
	 */
	public GameBoard getGameBoard() {
		return this.gameBoard;
	}

	/**
	 * Sets the GameBoard to be managed by the SustainopolyGame instance
	 * 
	 * @param board Gameboard instance
	 */
	public void setGameBoard(GameBoard board) {
		this.gameBoard = board;
	}

	/**
	 * Set the GUI which SustainopolyGame will manage
	 * 
	 * @param gui MainWindow instance
	 */
	public void setGUI(MainWindow gui) {
		this.gui = gui;
	}

	/**
	 * Returns the Player at the desired index within the players array
	 * 
	 * @param index Index of players array to retrieve Player from
	 * @return Player at specified index of players array, else null
	 */
	public Player getPlayer(int index) {
		if (index < 0 || index >= NUM_PLAYERS)
			return null;

		return players[index];
	}
 
	/**
	 * Returns the index within the players array of the specified Player instance
	 * 
	 * @param player Player instance to retrieve index of
	 * @return Index of specified Player within players array, else -1 if not found
	 */
	public int getPlayerIndex(Player player) {
		for (int i = 0; i < players.length; i++) {
			if (players[i] == player)
				return i;
		}

		return -1;
	}

	/**
	 * Returns the Player who's turn it is currently
	 * 
	 * @return Current Player
	 */
	public Player getCurrentPlayer() {
		return getPlayer(currentPlayerIndex);
	}

	/**
	 * Assigns the roles, sprites, colours and starting money of the Players
	 */
	private void setUpPlayers() {
		for (int i = 0; i < NUM_PLAYERS; i++)
			players[i] = new Player();

		// create an ArrayList of 8 roles for Players
		List<Role> roles = new ArrayList<>(); // add all six roles to list
		roles.add(Role.GOVAN_LOCAL);
		roles.add(Role.ORGANISATION);
		
		roles.add(Role.COMMUNITY_LEADER);
		
		roles.add(Role.ORGANISATION);
		roles.add(Role.ORGANISATION);
		
		roles.add(Role.ASYLUM_SEEKER);
		roles.add(Role.VOLUNTEER);
		
		roles.add(Role.COUNCILLOR);
		// add two more Organisation roles
		
		//Collections.shuffle(roles); // shuffle List

		// randomly assign roles
		for (int i = 0; i < players.length; i++) {
			
			players[i].setRole(roles.get(i));
			}

		// add portraits + sprite + money to the players
		int organisationPlayerCount = 0;		// used to keep track of how many organisation roles have been assigned
		for (Player player : players) {
			switch (player.getRole()) {
			case ASYLUM_SEEKER:
				player.setPortrait(new ImageIcon("images/Portraits/Asylum_Seeker_Portrait.png"));
				player.setSprite(new ImageIcon("images/Sprites/Asylum_Seeker_Sprite.png"));
				player.setMoney(1000);
				player.setExpertise(52);
				
				break;
			case GOVAN_LOCAL:
				player.setPortrait(new ImageIcon("images/Portraits/Local_Portrait.png"));
				player.setSprite(new ImageIcon("images/Sprites/Local_Sprite.png"));
				player.setMoney(1000);
				player.setExpertise(53);
				
				break;
			case VOLUNTEER:
				player.setPortrait(new ImageIcon("images/Portraits/Volunteer_Portrait.png"));
				player.setSprite(new ImageIcon("images/Sprites/Volunteer_Sprite.png"));
				player.setMoney(1700);
				player.setExpertise(65);
				break;
			case COMMUNITY_LEADER:
				player.setPortrait(new ImageIcon("images/Portraits/Community_Leader_Portrait.png"));
				player.setSprite(new ImageIcon("images/Sprites/Community_Leader_Sprite.png"));
				player.setMoney(3100);
				player.setExpertise(50);
				break;
			case COUNCILLOR:
				player.setPortrait(new ImageIcon("images/Portraits/Councillor_Portrait.png"));
				player.setSprite(new ImageIcon("images/Sprites/Councillor_Sprite.png"));
				player.setMoney(1100);
				player.setExpertise(55);
				break;
			case ORGANISATION:
				// ensure all three organisations get separate portraits + sprites
				if (organisationPlayerCount == 0) {
					player.setPortrait(new ImageIcon("images/Portraits/Organisation1_Portrait.png"));
					player.setSprite(new ImageIcon("images/Sprites/Organisation1_Sprite.png"));
					organisationPlayerCount++;
				} else if (organisationPlayerCount == 1) {
					player.setPortrait(new ImageIcon("images/Portraits/Organisation2_Portrait.png"));
					player.setSprite(new ImageIcon("images/Sprites/Organisation2_Sprite.png"));
					organisationPlayerCount++;
				} else if (organisationPlayerCount == 2) {
					player.setPortrait(new ImageIcon("images/Portraits/Organisation3_Portrait.png"));
					player.setSprite(new ImageIcon("images/Sprites/Organisation3_Sprite.png"));
					organisationPlayerCount++;
				}
				player.setExpertise(63);
				player.setMoney(1200);
			}
		}
	}

	/**
	 * Return an int array of two randomly generated numbers from 1-6 from two
	 * independent dice being rolled
	 * 
	 * @return Int array containing two values from 1-6
	 */
	public int[] rollDice() {
		int[] diceValues = { dice[0].roll(), dice[1].roll() };

		return diceValues;

	}

	/**
	 * Moves the Player at the specified index of the players array forward by a
	 * provided number of squares
	 * 
	 * @param playerIndex Index of the Player to be moved within the players array
	 * @param diceRoll    Number of squares to move Player forward, determined by a
	 *                    dice roll
	 */
	public void movePlayer(int playerIndex, int diceRoll, AnimationEvent ae) {
		Player player = getPlayer(playerIndex);
		Square playerPosition = player.getPosition();

		// find player's current index within ArrayList<Square> squares
		int currentSquareIndex = gameBoard.getSquareIndex(playerPosition);

		// calculate player's new index within squares after dice roll, taking into
		// account it could loop back around to start of ArrayList
		int newSquareIndex = (currentSquareIndex + diceRoll) % gameBoard.getNumSquares();

		// increment money if Player has passed GoVan square (looped back to starting
		// index of squares ArrayList)
		if (newSquareIndex < currentSquareIndex)
			gameBoard.getSquare(0).playAction();

		// update player's associated Square it is positioned on
		Square newPosition = gameBoard.getSquare(newSquareIndex);
		player.setPosition(newPosition);

		// update Player's position on GUI
		gui.movePlayerBegin(playerIndex, currentSquareIndex, newSquareIndex, new AnimationEvent() {
			@Override
			public void onComplete() {
				
				//update the position of the player sprite
				gui.movePlayerEnd(playerIndex, newSquareIndex);
			
				// reduce Player's square timeout; take into account, can't have negative square
				// timeout
				player.setSquareTimeout(player.getSquareTimeout() - diceRoll);
				if (player.getSquareTimeout() < 0)
					player.setSquareTimeout(0);

				// apply the effects of the Square 
				if (!(newPosition instanceof GoVanSquare))
					newPosition.playAction();
				
				gui.update(); // Update ControlsPanel

				playerHasMoved(playerIndex);
				
				// inform listener 
				if(ae != null) {
					ae.onComplete();
				}
			}
		});	
	}

	/**
	 * Called after a Player has moved position, setting which buttons are
	 * enabled/disabled on the controls panel
	 * 
	 * @param playerIndex Index of the Player to set controls for in the players
	 *                    array
	 */
	private void playerHasMoved(int playerIndex) {
		Player player = getPlayer(playerIndex);
		Square playerPosition = player.getPosition();
		
		// if player has run out of money following effects of square they've landed on, end the game
		if (player.getMoney() <= 0) {
			JOptionPane.showMessageDialog(null, "You've given your all towards sustainable development \n"
					+ "in Govan, but unfortunately you've run out of resources. Game Over!", "Out of Resources",
					JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/game_over.png"));
			
			// close all open windows (this popup + MainWindow)
			System.gc();
			for (Window window : Window.getWindows()) {
			    window.dispose();
			}
			new EndGamePage(false);
		}

		if (playerPosition instanceof TaskSquare) {
			TaskSquare ts = (TaskSquare) playerPosition;
			// check if TaskSquare is available
			if (ts.isAvailable()) {
				gui.setOfferTaskEnabled(true);
				// if the Player has insufficient money or expertise, can't take charge of TaskSquare
				if (ts.getPrice() >= player.getMoney() || ts.getRequiredExpertise() > player.getExpertise()) {
					gui.setTakeChargeEnabled(false);
					gui.setTakeChargeBtnToolTip("You do not have the necessary money or expertise to take charge of this task:\n" + 
							"Price: " + ts.getPrice() + ", XP: " + ts.getRequiredExpertise());
				}
				else
					gui.setTakeChargeEnabled(true); // Player has sufficient money and expertise
			} else {
				gui.setTakeChargeBtnToolTip("This task is already managed by " + ts.getOwner().getName());
				gui.setOfferTaskBtnToolTip("This task is already managed by " + ts.getOwner().getName());
			}
		} else {
			// need to re-set buttons to false; if player rolled double, on second roll might not be on TaskSquare
			gui.setOfferTaskEnabled(false);
			gui.setTakeChargeEnabled(false);
			gui.setTakeChargeBtnToolTip("Cannot take charge of this square");
			gui.setOfferTaskBtnToolTip("Cannot offer this square");
		}
	}

	/**
	 * Makes it the next Player in the players array's turn, looping back to the
	 * start if the end is reached Enables the suitable buttons depending on the
	 * Player's state in the game
	 */
	private void switchTurn() {
		currentPlayerIndex = (currentPlayerIndex + 1) % getNumPlayers();

		gui.setRollDiceEnabled(true);
		gui.turnHasBeenTaken(false);

		Player currentPlayer = getPlayer(currentPlayerIndex);
		gui.highlightSquare(gameBoard.getSquareIndex(currentPlayer.getPosition()), true); // set highlight border around GUISquare of current player

		// if the Player is in charge of at least one task, set "Form Alliance" and "Complete Step" buttons enabled
		if (currentPlayer.getTasks().size() > 0) {
			gui.setFormAllianceEnabled(true);
			if (currentPlayer.getSquareTimeout() == 0) 
				gui.setCompleteStepEnabled(true);
			else
				gui.setCompleteStepBtnToolTip("Cannot complete task steps while you have a square timeout. \nSquare timeout: " + currentPlayer.getSquareTimeout());
		} else {
			gui.setFormAllianceBtnToolTip("Must be in charge of at least one task to form an alliance");
			gui.setCompleteStepBtnToolTip("Must be in charge of at least one task to complete a task step");
		}
	}

	/**
	 * Forms an Alliance instance within the specified DevelopmentArea, setting the
	 * members
	 * 
	 * @param da      DevelopmentArea over which the alliance is formed
	 * @param members TaskSquare owners within the DevelopmentArea who are members
	 *                of the Alliance
	 */
	public void formAlliance(DevelopmentArea da, ArrayList<Player> members) {
		Alliance newAlliance = new Alliance(da, members);
		da.allianceFormed(newAlliance); // assign Alliance to da

		// set a 20% reduction in price for the task steps within the development area
		TaskSquare[] tasksOfDevelopmentArea = SustainopolyGame.getInstance().getGameBoard()
				.getTasksInDevelopmentArea(da);
		for (TaskSquare task : tasksOfDevelopmentArea) {
			task.setStepPrice((int) (task.getStepPrice() * 0.8));
		}

		// each member of the Alliance gains 5 expertise, while enforcing the max
		// expertise of 100
		for (Player player : members) {
			player.setExpertise(player.getExpertise() + 5);
		}
		
		// update controls panel to show +5 in expertise
		gui.update();
	}

	/**
	 * Takes a player, checks each development area (if it has an alliance) to see
	 * if the player is part of its alliance. If it is, the alliance is added to
	 * their playerAlliances ArrayList. This list is returned after every
	 * Development Area has been checked.
	 * 
	 * @param player The Player whose Alliances will be returned.
	 * @return
	 */
	public ArrayList<Alliance> getPlayerAlliances(Player player) {
		
		ArrayList<Alliance> playerAlliances = new ArrayList<Alliance>();
		
		// iterate through development areas
		for (DevelopmentArea dev : DevelopmentArea.values()) {
			if (dev.isManagedByAlliance()) {
				for (Player member : dev.getAlliance().getMembers()) {
					// if player provided in argument is a member of this alliance
					if (member == player) {
						// add to playerAlliances if not already there (avoid duplicates)
						if (playerAlliances.indexOf(dev.getAlliance()) == -1)
							playerAlliances.add(dev.getAlliance());
					}
				}
			}
		}
		return playerAlliances;
	}

	/**
	 * Prepares the starting state of the game
	 */
	public void startGame() {		
		setUpPlayers();
		gui.startGame();
	}

	/**
	 * Makes the GUI of the game visible
	 */
	public void setGUIVisible() {
		this.gui.update();
		this.gui.setVisible(true);
	}

	/**
	 * Provides functionality following "Roll Dice" button being clicked
	 * @param power level to apply to dice roll
	 */
	public void rollDiceBtnClicked(int power) {

		int[] diceRoll = rollDice(); // get int[] containing two dice rolls
		int diceRollValue = diceRoll[0] + diceRoll[1];

		// disable until animation complete
		gui.setRollDiceEnabled(false);

		// start animation
		gui.animateDice(power, new AnimationEvent() {

			public void onComplete() {

				gui.setDiceImages(diceRoll); // set corresponding dice images on gui

				// move the player and wait for completion before changing buttons etc
				// otherwise animation could activated twice   
				movePlayer(getCurrentPlayerIndex(), diceRollValue, new AnimationEvent() {

					@Override
					public void onComplete() {
						// disable another dice roll unless a double is rolled
						if (diceRoll[0] != diceRoll[1]) {
							gui.setRollDiceEnabled(false);
							gui.setRollDiceBtnToolTip("Turn has already been taken");
							gui.setEndTurnEnabled(true);
							gui.turnHasBeenTaken(true);
						} else {
							gui.setRollDiceEnabled(true);
							gui.setEndTurnEnabled(false); // roll dice is re-enabled, and ending turn is disabled
						}
						
						// can only complete step before dice is rolled (to avoid getting rid of square timeout unfairly)
						gui.setCompleteStepEnabled(false);
						gui.setCompleteStepBtnToolTip("Cannot complete task steps after turn has been taken");						
					}					
				});	
			}
		});	
	}

	/**
	 * Provides functionality following the 'End Turn' button being clicked
	 */
	public void endTurnBtnClicked() {
		Player currentPlayer = getPlayer(currentPlayerIndex);
		
		// remove highlight border around Square of (now) previous Player
		gui.highlightSquare(gameBoard.getSquareIndex(currentPlayer.getPosition()), false); 
		
		setAllButtonsEnabled(false);
		switchTurn();
	}

	/**
	 * Makes all buttons on the controls panel of the gui enabled/disabled
	 * 
	 * @param enabled Boolean value- if true, all buttons are enabled; if false, all
	 *                buttons are disabled
	 */
	public void setAllButtonsEnabled(boolean enabled) {
		gui.setRollDiceEnabled(enabled);
		gui.setTakeChargeEnabled(enabled);
		gui.setCompleteStepEnabled(enabled);
		gui.setFormAllianceEnabled(enabled);
		gui.setOfferTaskEnabled(enabled);
		gui.setEndTurnEnabled(enabled);
		
		// set default tool tips which may be changed later
		gui.setRollDiceBtnToolTip(null);
		gui.setTakeChargeBtnToolTip("Must take turn first");
		gui.setCompleteStepBtnToolTip("Must be in charge of at least one task to complete a task step");
		gui.setFormAllianceBtnToolTip("Must be in charge of at least one task to form an alliance");
		gui.setOfferTaskBtnToolTip("Must take turn first");
		gui.setEndTurnBtnToolTip("Must take turn first");
		
	}
	
	/**
	 * Update the ControlsPanel section of the MainWindow
	 */
	public void updateGUI() {
		gui.update();
	}
	
	/**
	 * Used outside of this class- instead in SquareDialog class- to set GUI after player has chosen move location
	 */
	public void setGUIAfterTurnTaken() {
		gui.setRollDiceEnabled(false);
		gui.setRollDiceBtnToolTip("Turn has already been taken");
		gui.setEndTurnEnabled(true);
		gui.turnHasBeenTaken(true);
		
		// can only complete step before dice is rolled (to avoid getting rid of square timeout unfairly)
		gui.setCompleteStepEnabled(false);
		gui.setCompleteStepBtnToolTip("Cannot complete task steps after turn has been taken");	
	}
}
