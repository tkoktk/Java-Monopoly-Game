package gui;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import gui.BoardPanel.AnimationEvent;
import sustainopoly.*;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;

public class MainWindow extends JFrame {
	
	//This serial ID just gets rid of an error
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;		// overall wrapper panel for all content
	private BoardPanel boardPanel;		// GUI representation of the GameBoard
	private ControlsPanel controlsPanel;

	/**
	 * Create the frame.
	 */
	public MainWindow(GameBoard board) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("Sustainopoly");
		setIconImage(new ImageIcon("images/govanIcon.png").getImage());
		
		contentPane = new JPanel();		// content wrapper panel
		setUpBoardPanel(board);
		
		// GridBagLayout, dividing the contentPane into 1 row and 2 columns
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWeights = new double[] {0.82,0.18};
		gbl_contentPane.rowWeights = new double[] {1.00};
		contentPane.setLayout(gbl_contentPane);
		
		// sets layout details of relative width and height of boardPanel
		GridBagConstraints gbc_boardPanel = new GridBagConstraints();	
		gbc_boardPanel.fill = GridBagConstraints.BOTH;
		gbc_boardPanel.gridx = 0;
		gbc_boardPanel.gridy = 0;
		gbc_boardPanel.gridwidth = 1;
		contentPane.add(boardPanel, gbc_boardPanel);
		
		controlsPanel = new ControlsPanel();	// panel on right of screen; will hold game controls
		
		// sets layout details of relative width and height of controlsPanel
		GridBagConstraints gbc_controlsPanel = new GridBagConstraints();
		gbc_controlsPanel.fill = GridBagConstraints.BOTH;
		gbc_controlsPanel.gridx = 1;
		gbc_controlsPanel.gridy = 0;
		gbc_controlsPanel.gridwidth = 1;
		contentPane.add(controlsPanel, gbc_controlsPanel);
		
		setContentPane(contentPane);
	}
	
	/**
	 * Starts the move of a Player on the BoardPanel of the GUI, and updates the graphics
	 * @param playerIndex The index of the Player instance within the 'players' array of SustainopolyGame to be moved 
	 * @param from Square to move the Player from
	 * @param to Square to move the Player to (use for animation destination) 
	 * @param ae Notification for when the plater animation has completed
	 */
	public void movePlayerBegin(int playerIndex, int from, int to, AnimationEvent ae) {
		GUISquare fromSquare = boardPanel.getGUISquare(from);
		GUISquare toSquare = boardPanel.getGUISquare(to);

		fromSquare.removePlayer(playerIndex);
		fromSquare.repaint();
		highlightSquare(from, false);
		
		if(ae != null) {
			Player player = SustainopolyGame.getInstance().getPlayer(playerIndex);
			boardPanel.animatePlayer(player, fromSquare, toSquare, ae);			
		}
	}
	
	/**
	 * Ends the move of a Player on the BoardPanel of the GUI, and updates the graphics
	 * @param playerIndex The index of the Player instance within the 'players' array of SustainopolyGame to be moved 
	 * @param to Square to move the Player to
	 */
	public void movePlayerEnd(int playerIndex, int to){
		GUISquare toSquare = boardPanel.getGUISquare(to);
		toSquare.addPlayer(playerIndex);
		toSquare.repaint();
		highlightSquare(to, true);		
	}
	
	/**
	 * Prepares the starting state of the game by moving each Player's sprite to the starting square
	 */
	public void startGame() {
		int numPlayers = SustainopolyGame.getInstance().getNumPlayers();
		for(int i = 0; i < numPlayers; i++) {
			movePlayerBegin(i, 0, 0, null);
			movePlayerEnd(i, 0);
		}
		
		highlightSquare(0, true);	// highlight the starting GoVan Square as this will be the position of the first current player
	}
	
	/**
	 * Sets up the GUISquares and other elements of the boardPanel
	 * @param board GameBoard instance defining the layout of the board
	 */
	public void setUpBoardPanel(GameBoard board) {
		boardPanel = new BoardPanel(board);	// panel on left of screen; will hold game board
		boardPanel.setUp();	// set up the Squares within the boardPanel
	}
	
	/**
	 * Sets the dice images shown on the board panel, displaying the player's roll
	 * @param diceRoll Array of two int values corresponding to the rolls of the two die
	 */
	public void setDiceImages(int[] diceRoll) {
    	int die1Roll = diceRoll[0];
    	int die2Roll = diceRoll[1];
    	
    	boardPanel.setDie1(die1Roll);
    	boardPanel.setDie2(die2Roll);
	}
	
	/**
	 * Starts the dice animation
	 * @param ev animation completion event
	 */
	public void animateDice(int power, AnimationEvent ev) {
		boardPanel.animateDie(power, ev);
	}
	
	/**
	 * Sets a red border around the Square of the current players
	 * @param squareIndex Index of the square to highlight in the guiSquares ArrayList of BoardPanel
	 * @param highlighted Boolean value- if true, GUISquare is highlighted and border is set to red; if false, GUISquare isn't highlighted and border is set to black
	 */
	public void highlightSquare(int squareIndex, boolean highlighted) {
		
		if (highlighted) {
			Player currentPlayer = SustainopolyGame.getInstance().getCurrentPlayer();
			boardPanel.getGUISquare(squareIndex).setBorder(new LineBorder(currentPlayer.getColour(), 2));
		} else
			boardPanel.getGUISquare(squareIndex).setBorder(new LineBorder(Color.BLACK));
	}
	
	/**
	 * Set the 'Roll Dice' button to be enabled/disabled on the controlsPanel
	 * @param enabled Boolean value- if true, button is enabled; if false, button is disabled
	 */
	public void setRollDiceEnabled(boolean enabled) {
		controlsPanel.setRollDiceEnabled(enabled);
	}
	
	/**
	 * Set the tool tip text for the 'Roll Dice' button on the controlsPanel
	 * @param message Message to be displayed
	 */
	public void setRollDiceBtnToolTip(String message) {
		controlsPanel.setRollDiceBtnToolTip(message);
	}
	
	/**
	 * Set the 'Take Charge of Task' button to be enabled/disabled on the controlsPanel
	 * @param enabled Boolean value- if true, button is enabled; if false, button is disabled
	 */
	public void setTakeChargeEnabled(boolean enabled) {
		controlsPanel.setTakeChargeEnabled(enabled);
	}
	
	/**
	 * Set the tool tip text for the 'Take Charge' button on the controlsPanel
	 * @param message Message to be displayed
	 */
	public void setTakeChargeBtnToolTip(String message) {
		controlsPanel.setTakeChargeBtnToolTip(message);
	}
	
	/**
	 * Set the 'Complete Step' button to be enabled/disabled on the controlsPanel
	 * @param enabled Boolean value- if true, button is enabled; if false, button is disabled
	 */
	public void setCompleteStepEnabled(boolean enabled) {
		controlsPanel.setCompleteStepEnabled(enabled);
	}
	
	/**
	 * Set the tool tip text for the 'Complete Step' button on the controlsPanel
	 * @param message Message to be displayed
	 */
	public void setCompleteStepBtnToolTip(String message) {
		controlsPanel.setCompleteStepBtnToolTip(message);
	}
	
	/**
	 * Set the 'Form Alliance' button to be enabled/disabled on the controlsPanel
	 * @param enabled Boolean value- if true, button is enabled; if false, button is disabled
	 */
	public void setFormAllianceEnabled(boolean enabled) {
		controlsPanel.setFormAllianceEnabled(enabled);
	}
	
	/**
	 * Set the tool tip text for the 'Form Alliance' button on the controlsPanel
	 * @param message Message to be displayed
	 */
	public void setFormAllianceBtnToolTip(String message) {
		controlsPanel.setFormAllianceBtnToolTip(message);
	}
	
	/**
	 * Set the 'Offer Task' button to be enabled/disabled on the controlsPanel
	 * @param enabled Boolean value- if true, button is enabled; if false, button is disabled
	 */
	public void setOfferTaskEnabled(boolean enabled) {
		controlsPanel.setOfferTaskEnabled(enabled);
	}
	
	/**
	 * Set the tool tip text for the 'Offer Task' button on the controlsPanel
	 * @param message Message to be displayed
	 */
	public void setOfferTaskBtnToolTip(String message) {
		controlsPanel.setOfferTaskBtnToolTip(message);
	}
	
	/**
	 * Set the 'End Turn' button to be enabled/disabled on the controlsPanel
	 * @param enabled Boolean value- if true, button is enabled; if false, button is disabled
	 */
	public void setEndTurnEnabled(boolean enabled) {
		controlsPanel.setEndTurnEnabled(enabled);
	}
	
	/**
	 * Set the tool tip text for the 'End Turn' button on the controlsPanel
	 * @param message Message to be displayed
	 */
	public void setEndTurnBtnToolTip(String message) {
		controlsPanel.setEndTurnBtnToolTip(message);
	}
	
	/**
	 * Updates playerPanel within the controlsPanel of the MainWindow
	 */
	public void update() {
		controlsPanel.updatePlayerPanel();
	}
	
	/**
	 * Used to eventually enable/ disable the 'Move' button in GUISquare popup depending if turn has been taken
	 */
	public void turnHasBeenTaken(boolean turnTaken) {
		boardPanel.turnHasBeenTaken(turnTaken);
	}


}
