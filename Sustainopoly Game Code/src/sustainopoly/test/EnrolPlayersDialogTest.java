package sustainopoly.test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gui.EnrolPlayersDialog;
import gui.MainWindow;
import sustainopoly.GameBoard;
import sustainopoly.GameBoardComplete;
import sustainopoly.SustainopolyGame;

class EnrolPlayersDialogTest {

	private static SustainopolyGame game;
	
	@BeforeAll
	static void init() {
		game = SustainopolyGame.getInstance();
		GameBoard board = new GameBoardComplete();
		game.setGameBoard(board);
		
		MainWindow window = new MainWindow(board);
		game.setGUI(window);		
		window.setVisible(false);
	}
	
	// get game back to start state before each test
	@BeforeEach
	void individualInit() {
		game.startGame();
	}

	/**
	 * Test One: Checking if a player with a valid name can be added. Also testing
	 * if when a player is added, its name and colour variables are set to the
	 * correct values. A player's name is valid if it is between 2-10 characters
	 * long and contain only letters and numbers.
	 */
	@Test
	public void testAddPlayerValidName() {
//		game.startGame();
		EnrolPlayersDialog dialog = new EnrolPlayersDialog();
		dialog.playerNameField.setText("Tom1");
		dialog.addPlayer();
		assertEquals("Tom1", game.getPlayer(0).getName());
		assertEquals(new Color(1, 186, 239), game.getPlayer(0).getColour());
	}

	/**
	 * Test Two: Checking that when an invalid name is attempted, it is not assigned
	 * to a player. Likewise, if an invalid name is attempted, a colour is not
	 * assigned to a player.
	 */
	@Test
	public void testAddPlayerInvalidName() {
		EnrolPlayersDialog dialog = new EnrolPlayersDialog();
		dialog.playerNameField.setText("£");
		dialog.addPlayer();
		/**
		 * Show that name and colour were null and therefore not set when invalid values
		 * were entered.
		 */
		assertNull(SustainopolyGame.getInstance().getPlayer(0).getName());
		assertNull(SustainopolyGame.getInstance().getPlayer(0).getColour());
	}

	/**
	 * Test Three: Checking that if a user entered a name that was too short
	 * (shorter than 2 characters long), the Player's name/colour would not be set.
	 */
	@Test
	public void testAddPlayerInvalidNameTooShort() {
		EnrolPlayersDialog dialog = new EnrolPlayersDialog();
		dialog.playerNameField.setText("A");
		dialog.addPlayer();
		/**
		 * Show that name and colour were null and therefore not set when invalid values
		 * were entered.
		 */
		assertNull(SustainopolyGame.getInstance().getPlayer(0).getName());
		assertNull(SustainopolyGame.getInstance().getPlayer(0).getColour());
	}

	/**
	 * Test Four: Checking that if a user entered a name that was too long (longer
	 * than 10 characters long), the Player's name/colour would not be set.
	 */
	@Test
	public void testAddPlayerInvalidNameTooLong() {
		EnrolPlayersDialog dialog = new EnrolPlayersDialog();
		dialog.playerNameField.setText("123456789101112");
		dialog.addPlayer();
		/**
		 * Show that name and colour were null and therefore not set when invalid values
		 * were entered.
		 */
		assertNull(SustainopolyGame.getInstance().getPlayer(0).getName());
		assertNull(SustainopolyGame.getInstance().getPlayer(0).getColour());
	}

	/**
	 * Test Five: Checking that if a user entered a name that was of valid length
	 * but contained illegal symbols such as $, the Player's name/colour would not
	 * be set.
	 */
	@Test
	public void testAddPlayerInvalidSymbols() {
		EnrolPlayersDialog dialog = new EnrolPlayersDialog();
		dialog.playerNameField.setText("Tom$");
		dialog.addPlayer();
		/**
		 * Show that name and colour were null and therefore not set when invalid values
		 * were entered.
		 */
		assertNull(SustainopolyGame.getInstance().getPlayer(0).getName());
		assertNull(SustainopolyGame.getInstance().getPlayer(0).getColour());
	}

	/**
	 * Test Six: Checking that if a user entered null input, the Player's
	 * name/colour would not be set.
	 */
	@Test
	public void testAddPlayerNullInput() {
		EnrolPlayersDialog dialog = new EnrolPlayersDialog();
		dialog.playerNameField.setText(null);
		dialog.addPlayer();
		/**
		 * Show that name and colour were null and therefore not set when invalid values
		 * were entered.
		 */
		assertNull(SustainopolyGame.getInstance().getPlayer(0).getName());
		assertNull(SustainopolyGame.getInstance().getPlayer(0).getColour());
	}

	/**
	 * Test Seven: Checking that with every new player that is added, each player
	 * has a different colour set to it.
	 */
	@Test
	public void testAddPlayerSwitchingPlayers() {
		EnrolPlayersDialog dialog = new EnrolPlayersDialog();
		// Add a player
		dialog.playerNameField.setText("ValidName1");
		dialog.addPlayer();
		// Add a second Player
		dialog.playerNameField.setText("ValidName2");
		dialog.addPlayer();
		// Add a third player
		dialog.playerNameField.setText("ValidName3");
		dialog.addPlayer();
		/**
		 * Show that the colours of the three players added were not the same.
		 */
		assertNotEquals(SustainopolyGame.getInstance().getPlayer(0).getColour(),
				SustainopolyGame.getInstance().getPlayer(1).getColour());

		assertNotEquals(SustainopolyGame.getInstance().getPlayer(0).getColour(),
				SustainopolyGame.getInstance().getPlayer(2).getColour());

		assertNotEquals(SustainopolyGame.getInstance().getPlayer(1).getColour(),
				SustainopolyGame.getInstance().getPlayer(2).getColour());
	}

	/**
	 * Test Eight: Checking that the addPlayer() method clears the playerNameField's
	 * text value.
	 */
	@Test
	public void testPlayerNameFieldClear() {
		EnrolPlayersDialog dialog = new EnrolPlayersDialog();
		// Add a player
		dialog.playerNameField.setText("ValidName1");
		dialog.addPlayer();
		/**
		 * If the value of playerNameField = "", then it has been successfuly cleared.
		 */
		assertEquals("", dialog.playerNameField.getText());
	}

	/**
	 * Test Nine: Checking that Players can't set the same name. If a user types in
	 * a name that already exists, it will not be set to the player.
	 */
	@Test
	public void testAddDuplicateNameOne() {
		EnrolPlayersDialog dialog = new EnrolPlayersDialog();
		// Add a player
		dialog.playerNameField.setText("ValidName1");
		dialog.addPlayer();
		// Add another player whose name already exists.
		dialog.playerNameField.setText("ValidName1");
		dialog.addPlayer();
		
		// Show that the first player's name was set
		assertNotNull(SustainopolyGame.getInstance().getPlayer(0).getName());
		// Show that the second player's name was not set.
		assertNull(SustainopolyGame.getInstance().getPlayer(1).getName());
	}

	/**
	 * Test Ten: Checking that the game does not allow duplicate names regardless of
	 * when the names were set.
	 */
	@Test
	public void testAddDuplicateNameTwo() {
		EnrolPlayersDialog dialog = new EnrolPlayersDialog();
		// Add a player
		dialog.playerNameField.setText("ValidName1");
		dialog.addPlayer();
		// Add a second player
		dialog.playerNameField.setText("ValidName2");
		dialog.addPlayer();
		// Add a second player
		dialog.playerNameField.setText("ValidName3");
		dialog.addPlayer();
		// Add a fourth player whose name matches that of the second player.
		dialog.playerNameField.setText("ValidName2");
		dialog.addPlayer();
		
		// Show that the first player's name was set
		assertNotNull(SustainopolyGame.getInstance().getPlayer(0).getName());
		// Show that the second player's name was set
		assertNotNull(SustainopolyGame.getInstance().getPlayer(1).getName());
		// Show that the third player's name was set
		assertNotNull(SustainopolyGame.getInstance().getPlayer(2).getName());
		// Show that the fourth player's name was not set.
		assertNull(SustainopolyGame.getInstance().getPlayer(3).getName());
	}
	
	/**
	 * Test Eleven: Showing that when addPlayer() is called, 
	 * the playerNumberLabel updates accordingly.
	 */
	@Test
	public void testPlayerNumberLabelUpdate() {
		EnrolPlayersDialog dialog = new EnrolPlayersDialog();
		//Store the initial value of playerNumberLabel
		String firstLabel = dialog.playerNumberLabel.getText();
		// Add a player
		dialog.playerNameField.setText("ValidName1");
		dialog.addPlayer();
		
		/**Check that the playerNumberLabel is no longer equal to what it was
		 * before a new player was added.
		 */
		assertNotEquals(firstLabel, dialog.playerNumberLabel.getText());
	}
}
