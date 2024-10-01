package gui;

import javax.swing.border.EmptyBorder;
import sustainopoly.*;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.*;
import javax.swing.*;

public class EnrolPlayersDialog extends JFrame {

	// This serial ID just gets rid of an error
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	public JLabel playerNumberLabel;
	public JTextField playerNameField;
	public Color playerColour = new Color(1, 186, 239);
	// Player Number
	public int playerNumber;

	// Game
	public static SustainopolyGame game = SustainopolyGame.getInstance();

	/**
	 * Create the frame.
	 */

	public EnrolPlayersDialog() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 450, 300);
		setTitle("Enrol Players");
		setIconImage(new ImageIcon("images/govanIcon.png").getImage());

		contentPane = new JPanel();
		contentPane.setBackground(new Color(1, 186, 239));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridBagLayout());

		// Constraints for setting the layout of having the Label above the Text field
		// etc
		GridBagConstraints gbc = new GridBagConstraints();

		// Player Number Label
		playerNumberLabel = new JLabel("Enter the Name for Player 1:");
		playerNumberLabel.setFont(new FontTwo(45));
		playerNumberLabel.setForeground(Color.WHITE);
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		contentPane.add(playerNumberLabel, gbc);

		// Text Field for Player Names
		playerNameField = new JTextField();
		playerNameField.setPreferredSize(new Dimension(200, 30));
		playerNameField.setColumns(10);
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 1;

		// Key listener for if the User types the Enter Key & is focused/clicked on the
		// name field
		playerNameField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER && playerNameField.hasFocus()) {
					addPlayer();
				}
			}
		});

		contentPane.add(playerNameField, gbc);

		// Next Button
		SustainopolyButton nextButton = new SustainopolyButton("Next");
		// Action listener for when the button gets clicked on
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPlayer();
			}
		});

		gbc.gridy = 2;
		contentPane.add(nextButton, gbc);

		add(contentPane);
		playerNumber = 1;

		pack();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}

	/**
	 * This method takes in the name the player has entered, then compares
	 * it with all valid names that already have been entered into the dialog
	 * and assigned.
	 * @param name: The name the user typed into the playerNameField.
	 * @return true if name already exists, false if name does not exist.
	 */
	private boolean ifExists(String name) {
		
		ArrayList<String> playerNames = new ArrayList<String>();
		// Get ArrayList of all players that already exist
		for (int i = 0; i < game.getNumPlayers(); i++) {
			if (game.getPlayer(i).getName() != null) {
				playerNames.add(game.getPlayer(i).getName());
			}
		}
		// If a player is already called that name, playerNames will contain it
		if (playerNames.contains(name)) {
			return true;
		}
		// If not, return false
		return false;
	}

	/**
	 * The user input is taken from the text field, and then validated to ensure
	 * that it's of appropriate length and doesn't contain illegal symbols. Then,
	 * the input is assigned to be the name of a Player. This happens 8 times, each
	 * time the background colour of the contentPane Panel is updated to a colour
	 * which is set as the Player Colour.
	 */
	public void addPlayer() {

		// Takes user input from text field and assigns to String playerName
		String playerName = playerNameField.getText();

		// The regular expression can contain letters a-z, both capitals and
		// non-capitals, and numbers 0-9
		String regex = "^[A-Za-z0-9]\\w{1,9}$";

		// Compile the regular expression, setting it to a Pattern
		Pattern namePattern = Pattern.compile(regex);
		/*
		 * The matcher method creates a Matcher which holds a .matches variable for
		 * seeing if the name entered matches the pattern.
		 */
		Matcher match = namePattern.matcher(playerName);
		// If it doesn't match the regular expression then it's invalid

		// THIS IS JUST IN PLACE SO THAT YOU CAN CYCLE THROUGH EASILY.
		// The actual code that will be here is
		// if (match.matches()) {
		if (match.matches()) {
			// SET IF STATEMENT TO if (match.matches()) {
			// if somebody has already entered the same name
			// This removes duplicates
			if (!ifExists(playerName)) {

				// Set Player name + Colour
				game.getPlayer(playerNumber - 1).setName(playerName);
				game.getPlayer(playerNumber - 1).setColour(playerColour);
				// Clear Text Field
				playerNameField.setText("");

				// Change Label
				playerNumber++;
				playerNumberLabel.setText("Enter the Name for Player " + playerNumber + ":");

				// Swap Colours etc
				switch (playerNumber) {

				case 1:
					// Blue
					playerColour = new Color(1, 186, 239);
					break;
				case 2:
					// Pink
					playerColour = new Color(245, 176, 203);
					break;
				case 3:
					// Pumpkin Orange
					playerColour = new Color(250, 121, 33);
					break;
				case 4:
					// Yellow
					playerColour = new Color(244, 185, 66);
					break;
				case 5:
					// Red
					playerColour = new Color(166, 60, 6);
					break;
				case 6:
					// Purple
					playerColour = new Color(97, 63, 117);
					break;
				case 7:
					// Beige
					playerColour = new Color(220, 170, 130);
					break;
				case 8:
					// Brown
					playerColour = new Color(114, 94, 84);
					break;

				case 9:
					// Navigation to the Overview Frame
					Overview nextWindow = new Overview(game);
					nextWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
					nextWindow.setVisible(true);
					dispose();
					break;
				}
				contentPane.setBackground(playerColour);
			}

			else {
				// Display error message popup instead of allowing the user to advance
				JOptionPane.showMessageDialog(null, "This name already exists! We do not allow duplicate names.",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		// If the name doesn't match the pattern:
		else {
			// Display error message popup instead of allowing the user to advance
			JOptionPane.showMessageDialog(null,
					"The name must be between 2-10 characters long and contain only letters and numbers!", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
