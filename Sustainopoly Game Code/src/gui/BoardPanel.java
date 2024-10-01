package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import gui.DiceAnimator.DiceAnimateEvent;
import gui.PlayerAnimator.PlayerAnimateEvent;
import sustainopoly.GameBoard;
import sustainopoly.Player;

public class BoardPanel extends JLayeredPane {

	// This serial ID just gets rid of an error
	private static final long serialVersionUID = 1L;

	private GameBoard board;
	private ArrayList<GUISquare> guiSquares = new ArrayList<>(); // ArrayList to hold all GUISquares of the BoardPanel

	private JPanel mainPanel;
	private JPanel die1Panel;
	private JPanel die2Panel;
	private JPanel playerPanel;
	private JLabel logoPanel;
	
	private final Integer LAYER_ZERO = 0;
	private final Integer LAYER_ONE = 1;
	private final Integer LAYER_TWO = 2;
	private final Integer LAYER_THREE = 3;
	
	/**
	 * Constructs a BoardPanel representing the game board on the left of the main
	 * window
	 * 
	 * @param board GameBoardComplete instance
	 */
	public BoardPanel(GameBoard board) {
		this.board = board;

		mainPanel = new JPanel();
		mainPanel.setBorder(new LineBorder(Color.BLACK));
		mainPanel.setBackground(Color.WHITE);

		// calculate dimension of square e.g. 36 squares -> 10x10
		int numRowsCols = (board.getNumSquares() / 4) + 1;

		// set GridBagLayout for the grid
		GridBagLayout gbl_boardPanel = new GridBagLayout();
		double[] rowsColsWeights = new double[numRowsCols]; // get relative weights (% width/height of screen) for each
															// row+column- all equal on resize
		Arrays.fill(rowsColsWeights, 1.0 / numRowsCols);

		gbl_boardPanel.columnWeights = rowsColsWeights; // set relative weights so all rows and columns even
		gbl_boardPanel.rowWeights = rowsColsWeights;

		mainPanel.setLayout(gbl_boardPanel); // set layout of main panel to defined GridBagLayout

		// add to bottom layer
		add(mainPanel, LAYER_ZERO);
	}

	/*
	 * Since we use a JLayered Pane we need to force child components (mainPanel) to
	 * fill the space available, event though its a GridBagLayout
	 */
	@Override
	public void doLayout() {
		synchronized (getTreeLock()) {
			int w = getWidth();
			int h = getHeight();
			
			if((mainPanel.getWidth() != w) || (mainPanel.getHeight() != h)) {
				mainPanel.setBounds(0, 0, w, h);
				
				// move with layout change (we could scale die too if really needed)
				die1Panel.setLocation((mainPanel.getWidth() / 3), (mainPanel.getHeight() / 3));
				die2Panel.setLocation((mainPanel.getWidth() / 3), (mainPanel.getHeight() / 3) + die1Panel.getHeight());
				
				//keep enter
				logoPanel.setLocation((mainPanel.getWidth() / 2) - logoPanel.getWidth() / 2, (mainPanel.getHeight() / 2) - logoPanel.getHeight() / 2);
			}
		}
	}

	/**
	 * Set up the GUISquares in the panel
	 * 
	 * @return None
	 */
	public void setUp() {
		int numSquares = board.getNumSquares();
		int rowColNumber = numSquares / 4 + 1;
		int maxXY = rowColNumber - 1; // as rows/columns are zero-indexed, max will be 1 less e.g. 10x10 -> max x or y
										// value = 9

		for (int i = 0; i < numSquares; i++) {
			GUISquare square; // create a GUISquare from the Square

			GridBagConstraints gbc_square = new GridBagConstraints(); // will be assigned values then used to set
																		// relative position of GUISquare in grid
			gbc_square.fill = GridBagConstraints.BOTH; // expand/shrink both horizontal and vertically

			// bottom panel (incl corners)
			if (i < rowColNumber) {
				square = new GUISquare(board.getSquare(i), 0);
				guiSquares.add(square);
				gbc_square.gridx = maxXY - i;
				gbc_square.gridy = maxXY;
			}
			// left panel (excl corners)
			else if (i >= rowColNumber && i <= ((rowColNumber - 1) * 2) - 1) {
				square = new GUISquare(board.getSquare(i), 90);
				guiSquares.add(square);
				gbc_square.gridx = 0;
				gbc_square.gridy = maxXY - (i - rowColNumber) - 1;
			}
			// top panel (incl corners)
			else if (i >= (rowColNumber - 1) * 2 && i <= (rowColNumber - 1) * 3) {
				square = new GUISquare(board.getSquare(i), 180);
				guiSquares.add(square);
				gbc_square.gridx = i - (maxXY * 2);
				gbc_square.gridy = 0;
			}
			// right panel (excl corners)
			else {
				square = new GUISquare(board.getSquare(i), -90);
				guiSquares.add(square);
				gbc_square.gridx = maxXY;
				gbc_square.gridy = i - (maxXY * 3);
			}

			mainPanel.add(square, gbc_square); // add the GUISquare to the grid
		}

		// set up panels to show dice values
		die1Panel = new JPanel();
		die1Panel.setOpaque(false);
		die1Panel.setBounds(0, 0, 48, 48);
		die1Panel.setBackground(Color.WHITE);

		die2Panel = new JPanel();
		die2Panel.setOpaque(false);
		die2Panel.setBounds(0, 0, 48, 48);
		die2Panel.setBackground(Color.WHITE);
		
		// add dice to upper layer
		add(die1Panel, LAYER_TWO);
		add(die2Panel, LAYER_TWO);

		die1Panel.setVisible(false);
		die2Panel.setVisible(false);

		// create a place holder for the player animation sprite
		playerPanel = new JPanel();
		playerPanel.setOpaque(false);
		playerPanel.setBounds(0, 0, 120, 120);
		playerPanel.setBackground(Color.WHITE);
		playerPanel.setVisible(false);
		
		//add him above all else
		add(playerPanel, LAYER_THREE);
		
		//create a  board logo
		ImageIcon icon = new ImageIcon("images/Van1.png");
		Image image = icon.getImage().getScaledInstance(300, 150, Image.SCALE_SMOOTH);		
		logoPanel = new JLabel(new ImageIcon(image));
		logoPanel.setOpaque(false);
		logoPanel.setBounds(0, 0, 300, 123);
		logoPanel.setBackground(Color.WHITE);
		
		//add logo
		add(logoPanel, LAYER_ONE);
	}

	/**
	 * Returns the GUISquare from the guiSquares ArrayList at the specified index
	 * 
	 * @param index Index of the GUISquare to return
	 * @return GUISquare instance
	 */
	public GUISquare getGUISquare(int index) {
		return guiSquares.get(index);
	}

	/**
	 * Sets the image shown for die 1 on the board panel
	 * 
	 * @param number Die roll to be shown
	 */
	public void setDie1(int number) {
		die1Panel.removeAll();
		die1Panel.add(new JLabel(DiceAnimator.getImage(number, 40, 40)));
	}

	/**
	 * Sets the image shown for die 2 on the board panel
	 * 
	 * @param number Die roll to be shown
	 */
	public void setDie2(int number) {
		die2Panel.removeAll();
		die2Panel.add(new JLabel(DiceAnimator.getImage(number, 40, 40)));
	}

	/**
	 * Event
	 * 
	 * @param none
	 */
	public interface AnimationEvent {
		void onComplete();
	}

	/**
	 * Start a dice animation sequence
	 * @param Power level (1 - 100) 
	 * @param ae handler
	 */
	public void animateDie(int power, AnimationEvent ae) {

		//hide dice initially
		die1Panel.setVisible(false);
		die2Panel.setVisible(false);
		
		//make sure we have some power
		if(power == 0)power = 1;
		
		//adjust time based on power 		
		long time = (long)(((float)DiceAnimator.ANIMATION_TIME_MS_MAX / 100.0f) * (float)power);
		
		//adjust speed (steps) based on power 
		int speed = (int)((75.0f / 100.0f) * (float)power);

		// here we go
		new DiceAnimator(new DiceAnimateEvent() {
			@Override
			public void onAnimateComplete() {
				if (ae != null) {
		          SwingUtilities.invokeLater(new Runnable() {
		              public void run() {
		  				if (ae != null) {
		  					ae.onComplete();
		  				}
		              }
		            });											
				}
			}
		}, mainPanel, die1Panel, die2Panel, time, speed).animate();
	}
	
	/**
	 * Start player sprite animation sequence
	 * @param Player the player (for the sprite and anything else we may use)
	 * @param from gui from square
	 * @param to gui to square 
	 * @param ae handler
	 */
	public void animatePlayer(Player player, GUISquare from, GUISquare to, AnimationEvent ae) {

		//hide player initially
		playerPanel.setVisible(false);
		
		//make sure we set the panel size initially 
		playerPanel.setSize(new Dimension((int)40, (int)40));
		
		// here we go
		new PlayerAnimator(new PlayerAnimateEvent() {
			@Override
			public void onAnimateComplete() {
				if (ae != null) {
		          SwingUtilities.invokeLater(new Runnable() {
		              public void run() {
		  				//hide the panel and remove contents
		  				playerPanel.setVisible(false);
		  				playerPanel.removeAll();
		  				
		  				if (ae != null) {
		  					ae.onComplete();
		  				}
		              }
		            });		
				}
			}
		}, mainPanel, playerPanel, player.getSprite(), from, to, 50).animate();
	}
	
	public void turnHasBeenTaken(boolean turnTaken) {
		for (GUISquare guiSquare : guiSquares) {
			guiSquare.setTurnTakenFlag(turnTaken);
		}
	}
}
