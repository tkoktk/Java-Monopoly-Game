package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.LineBorder;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Window;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

import sustainopoly.Player;
import sustainopoly.Square;
import sustainopoly.SustainopolyGame;
import sustainopoly.TaskSquare;

public class ControlsPanel extends JPanel {

	// This serial ID just gets rid of an error
	private static final long serialVersionUID = 1L;

	private JButton rollDiceBtn;
	private JButton takeChargeBtn;
	private JButton completeStepBtn;
	private JButton formAllianceBtn;
	private JButton offerTaskBtn;
	private JButton endTurnBtn;
	private JButton showPlayerAlliancesBtn;

	private JLabel portraitLabel;
	private JLabel nameLabel;
	private JLabel moneyLabel;
	private JLabel expertiseLabel;
	private JLabel roleLabel;
	private JPanel playerPanel;

	private Font infoFont = new FontOne(19);
	private JPanel portraitPanel;
	private JPanel infoPanel;
	private JPanel statsBtnPanel;
	private JPanel positionPanel;
	private JLabel positionLabel;
	private IconButton statsBtn;

	private JDialog powerDialog;
	private int powerValue = 0;
	private JProgressBar progressBar;
	private Timer timer;

	public ControlsPanel() {
		setBorder(new LineBorder(Color.BLACK));

		// set GridBagLayout for overall controlsPanel- one column, three rows (for
		// playerPanel and buttonPanel)
		GridBagLayout gbl_controlsPanel = new GridBagLayout();
		gbl_controlsPanel.columnWeights = new double[] { 1.00 };
		gbl_controlsPanel.rowWeights = new double[] { 0.2, 0.2, 0.6 };
		setLayout(gbl_controlsPanel);

		setUpPlayerPanel();
		setUpPositionPanel();
		setUpButtonPanel();
	}

	/**
	 * Sets up the upper playerPanel element of the ControlsPanel
	 */
	private void setUpPlayerPanel() {
		// --- PLAYER PANEL ---
		// wrapper panel on upper of controls panel holding portrait, stats and
		// leaderboard button
		playerPanel = new JPanel();
		playerPanel.setLayout(new GridLayout(1, 3)); // panel has a 1x3 layout

		// panel holding the portrait of the current player
		portraitPanel = new JPanel();
		portraitPanel.setOpaque(false); // allows background colour to be seen through
		portraitLabel = new JLabel(); // label holding portrait
		portraitPanel.add(portraitLabel);
		portraitLabel.setBorder(new LineBorder(Color.WHITE, 7));
		playerPanel.add(portraitPanel); // add portraitPanel to playerPanel

		// panel containing player stats
		infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		infoPanel.setOpaque(false); // allows background colour to be seen through

		nameLabel = new JLabel(); // label within infoPanel holding player name
		nameLabel.setFont(infoFont);
		nameLabel.setForeground(Color.WHITE);
		moneyLabel = new JLabel(); // label within infoPanel holding player money
		moneyLabel.setFont(infoFont);
		moneyLabel.setForeground(Color.WHITE);
		expertiseLabel = new JLabel(); // label within infoPanel holding player expertise
		expertiseLabel.setFont(infoFont);
		expertiseLabel.setForeground(Color.WHITE);
		roleLabel = new JLabel(); // label within infoPanel holding player role
		roleLabel.setFont(infoFont);
		roleLabel.setForeground(Color.WHITE);

		// add labels to infoPanel, then add infoPanel to playerPanel
		infoPanel.add(nameLabel);
		infoPanel.add(moneyLabel);
		infoPanel.add(expertiseLabel);
		infoPanel.add(roleLabel);
		playerPanel.add(infoPanel);

		// panel containing statsBtn, which opens the stats JTable
		statsBtnPanel = new JPanel();
		statsBtnPanel.setOpaque(false); // allows background colour to be seen through
		statsBtn = new IconButton("Player Stats", new ImageIcon("images/stats.png"));
		statsBtn.setFocusable(false); // get rid of weird line border around button
		statsBtnPanel.add(statsBtn); // add statsBtn to statsBtnPanel

		statsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SustainopolyPlayerStats stats = new SustainopolyPlayerStats();
			
				JFrame frame = new JFrame();
				frame.setPreferredSize(new Dimension(800, 600));
				frame.setResizable(false);
				frame.setTitle("Player Stats");
				frame.setIconImage(new ImageIcon("images/govanIcon.png").getImage());
				frame.getContentPane().add(stats);
				frame.pack();
				frame.setLocationRelativeTo(null); // make frame appear in centre of screen
				frame.setVisible(true);
			}
		});
		playerPanel.add(statsBtnPanel); // add statsBtnPanel to playerPanel

		// Add playerPanel to overall controlsPanel on the upper portion
		GridBagConstraints playerPanel_gbc = new GridBagConstraints();
		playerPanel_gbc.fill = GridBagConstraints.BOTH;
		playerPanel_gbc.gridx = 0;
		playerPanel_gbc.gridy = 0;
		playerPanel_gbc.gridheight = 1;
		playerPanel_gbc.gridwidth = 1;
		add(playerPanel, playerPanel_gbc);
	}
	
	/**
	 * Sets up the middle panel showcasing the Player's current position
	 */
	private void setUpPositionPanel() {
	    positionPanel = new JPanel();
	    positionPanel.setLayout(new GridBagLayout()); // Use GridBagLayout to set the constraints
	    positionPanel.setBackground(Color.WHITE); // Set the background of positionPanel to white

	    positionLabel = new JLabel("Position");
	    positionLabel.setBackground(Color.WHITE);
	       positionLabel.setFont(new FontTwo(35));
	    positionLabel.setHorizontalAlignment(JLabel.CENTER);
	    positionLabel.setOpaque(true); // Set the label to be opaque so that the background color shows
	    //set PositionLabel Border to only the top and bottom:
	    positionLabel.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.BLACK));

	    GridBagConstraints positionLabel_gbc = new GridBagConstraints();
	    positionLabel_gbc.fill = GridBagConstraints.BOTH;
	    positionLabel_gbc.gridx = 0;
	    positionLabel_gbc.gridy = 0;
	    positionLabel_gbc.weightx = 1; // Set the weight to 0.8 to take up 80% of the width, not working
	    positionPanel.add(positionLabel, positionLabel_gbc);
	    
	    // Add positionPanel to overall controlsPanel in the middle portion
	    GridBagConstraints positionPanel_gbc = new GridBagConstraints();
	    positionPanel_gbc.fill = GridBagConstraints.BOTH;
	    positionPanel_gbc.gridx = 0;
	    positionPanel_gbc.gridy = 1;
	    positionPanel_gbc.gridheight = 1;
	    positionPanel_gbc.gridwidth = 1;
	    add(positionPanel, positionPanel_gbc);
	}
	
	/**
	 * Sets up the lower buttonPanel element of the ControlsPanel
	 */
	private void setUpButtonPanel() {
		// --- BUTTON PANEL ---
		// wrapper panel to hold the various buttons on the control panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(125, 219, 67));
		buttonPanel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK));	// create border on top

		// set GridBagLayout for buttonPanel- two columns, 5 rows (space for 10 buttons
		// total)
		GridBagLayout gbl_buttonPanel = new GridBagLayout();
		gbl_buttonPanel.columnWeights = new double[] { 0.5, 0.5 };
		gbl_buttonPanel.rowWeights = new double[] { 0.16666, 0.16666, 0.16666, 0.16666, 0.16666, 0.16666 };
		buttonPanel.setLayout(gbl_buttonPanel);

		GridBagConstraints multi_gbc = new GridBagConstraints(); // will be used for setting positions of various buttons

		rollDiceBtn = new SleekButton("Roll Dice");
		rollDiceBtn.setFocusable(false); // get rid of weird line border around button text
		
		// allow player to decide how much power (dice roll time) is applied
		// by intercepting mouse press/release and using time difference to prime
		// animation time
		// We can show a power meter just to visualise it all
		rollDiceBtn.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				
				if(rollDiceBtn.isEnabled()) {
					powerDialog = new JDialog();
					powerDialog.setUndecorated(true);
					
					//set to mouse pointer
					powerDialog.setBounds(e.getXOnScreen(), e.getYOnScreen(), 50, 20);

					// create the bar and set up timers
					progressBar = new JProgressBar();
					progressBar.setBackground(Color.WHITE);
					progressBar.setForeground(Color.GREEN);
					progressBar.setStringPainted(true);

					//reset 
					powerValue = 0;
					
					timer = new Timer();
					timer.scheduleAtFixedRate ( new TimerTask() {
						
						@Override
						public void run() {
							//don't show power meter for super fast clicks
							if(powerValue > 100 && !powerDialog.isVisible()) {
								powerDialog.setVisible(true);
							}
							
							//update the value and set colour
							if(progressBar != null) {				
								progressBar.setValue(powerValue);
								powerValue += 100;
								if(powerValue > 600 && powerValue < 1600) {
									progressBar.setForeground(Color.ORANGE);	
								}
								else if(powerValue >= 1600) {
									progressBar.setForeground(Color.RED);	
								}
							}
						}					
					}, 0, 100);
					
					progressBar.setMaximum(2000);
					powerDialog.setLayout(new BorderLayout());
					powerDialog.add(progressBar, BorderLayout.CENTER);
					powerDialog.pack();
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if(timer != null) {
					timer.cancel();
				}
				
				if(powerDialog != null) {
					powerDialog.setVisible(false);
					powerDialog = null;
				}
				
				// now we have been released fire off the button click proper and supply
				// the power value to animator. This is used to calculate dice speed and
				// animation time
				if(rollDiceBtn.isEnabled()) {
					int power = (powerValue * 100) / 2000; 
					SustainopolyGame.getInstance().rollDiceBtnClicked(power);
				}
			}

		});
		multi_gbc.gridx = 0;
		multi_gbc.gridy = 0;
		buttonPanel.add(rollDiceBtn, multi_gbc);

		takeChargeBtn = new SleekButton("Take Charge of Task");
		takeChargeBtn.setFocusable(false); // get rid of weird line border around button text
		takeChargeBtn.setEnabled(false); // on first player's first turn, can't take charge of starting square (GoVan)
		takeChargeBtn.setToolTipText("Must take turn first"); // set default tool tip
		takeChargeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TakeChargeDialog(ControlsPanel.this, SustainopolyGame.getInstance().getCurrentPlayer());
			}
		});
		multi_gbc.gridx = 1;
		multi_gbc.gridy = 0;
		buttonPanel.add(takeChargeBtn, multi_gbc);

		completeStepBtn = new SleekButton("Complete Task Step");
		completeStepBtn.setFocusable(false); // get rid of weird line border around button text
		completeStepBtn.setEnabled(false); // on first player's first turn, don't have any tasks to complete step of
		completeStepBtn.setToolTipText("Must be in charge of at least one task to complete a task step"); // set default tool tip
		completeStepBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CompleteStepDialog();
			}
		});
		multi_gbc.gridx = 0;
		multi_gbc.gridy = 1;
		buttonPanel.add(completeStepBtn, multi_gbc);

		formAllianceBtn = new SleekButton("Form Alliance");
		formAllianceBtn.setFocusable(false); // get rid of weird line border around button text
		formAllianceBtn.setEnabled(false); // on first player's first turn, can't form an alliance as has no tasks
		formAllianceBtn.setToolTipText("Must be in charge of at least one task to form an alliance"); // set default tool tip
		formAllianceBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FormAllianceDialog();
			}
		});
		multi_gbc.gridx = 1;
		multi_gbc.gridy = 1;
		buttonPanel.add(formAllianceBtn, multi_gbc);

		offerTaskBtn = new SleekButton("Offer Task");
		offerTaskBtn.setFocusable(false); // get rid of weird line border around button text
		offerTaskBtn.setEnabled(false); // on first player's first turn, can't offer the starting square (GoVan)
		offerTaskBtn.setToolTipText("Must take turn first"); // set default tool tip
		offerTaskBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new OfferTaskDialog(ControlsPanel.this);
			}
		});
		multi_gbc.gridx = 0;
		multi_gbc.gridy = 2;
		buttonPanel.add(offerTaskBtn, multi_gbc);

		JButton showTasksBtn = new SleekButton("Show Tasks");
		showTasksBtn.setFocusable(false); // get rid of weird line border around button text
		showTasksBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TaskMenu menu = new TaskMenu(SustainopolyGame.getInstance().getCurrentPlayer());
				menu.show();
			}
		});
		multi_gbc.gridx = 1;
		multi_gbc.gridy = 2;
		buttonPanel.add(showTasksBtn, multi_gbc);

		showPlayerAlliancesBtn = new SleekButton("Show Alliances");
		
		showPlayerAlliancesBtn.setFocusable(false); // get rid of weird line border around button text
		showPlayerAlliancesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 AllianceTable allianceTable = new AllianceTable();
				 allianceTable.display();
			}
		});
		multi_gbc.gridx = 0;
		multi_gbc.gridy = 3;
		buttonPanel.add(showPlayerAlliancesBtn, multi_gbc);
		
		JButton giveMoneyBtn = new SleekButton("Give Money");
		giveMoneyBtn.setFocusable(false); // get rid of weird line border around button text
		giveMoneyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GiveMoneyDialog();
			}
		});
		multi_gbc.gridx = 1;
		multi_gbc.gridy = 3;
		buttonPanel.add(giveMoneyBtn, multi_gbc);

		endTurnBtn = new SleekButton("End Turn");
		endTurnBtn.setFocusable(false); // get rid of weird line border around button text
		endTurnBtn.setEnabled(false); // on first player's first turn, can't end turn before rolling dice
		endTurnBtn.setToolTipText("Must take turn first"); // set default tool tip
		endTurnBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SustainopolyGame.getInstance().endTurnBtnClicked();
				updatePlayerPanel();
			}
		});
		multi_gbc.gridx = 0;
		multi_gbc.gridy = 4;
		buttonPanel.add(endTurnBtn, multi_gbc);
		
		JButton completeGameBtn = new SleekButton("Complete Game");
		completeGameBtn.setFocusable(false); // get rid of weird line border around button text
		completeGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GameStatusDialog();
			}
		});
		multi_gbc.gridx = 1;
		multi_gbc.gridy = 4;
		buttonPanel.add(completeGameBtn, multi_gbc);
		
		JButton quitGameBtn = new SleekButton("Quit Game");
		quitGameBtn.setFocusable(false); // get rid of weird line border around button text
		quitGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int quitGameChoice = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Quit Game", JOptionPane.YES_NO_OPTION);
				if (quitGameChoice == JOptionPane.YES_OPTION) {
					// close all open windows (this popup + MainWindow)
					System.gc();
					for (Window window : Window.getWindows()) {
					    window.dispose();
					}
					new EndGamePage(false);
				}
			}
		});
		multi_gbc.gridx = 0;
		multi_gbc.gridy = 5;
		buttonPanel.add(quitGameBtn, multi_gbc);

		// add buttonPanel to overall controlsPanel on the lower portion
		GridBagConstraints buttonPanel_gbc = new GridBagConstraints();
		buttonPanel_gbc.fill = GridBagConstraints.BOTH;
		buttonPanel_gbc.gridx = 0;
		buttonPanel_gbc.gridy = 2;
		buttonPanel_gbc.gridheight = 1;
		buttonPanel_gbc.gridwidth = 1;
		add(buttonPanel, buttonPanel_gbc);
	}
	
	/**
	 * This method returns the colour associated with the square they are currently on.
	 * @return the development area's colour of the task they're on if they are on a task square, and return black if not.
	 */

	private Color getCurrentPositionColour() {
		Player currentPlayer = SustainopolyGame.getInstance().getCurrentPlayer();
		Color colour = Color.BLACK;
		Square position = currentPlayer.getPosition();
		
		//If the Player is currently on a TaskSquare, return a darkened version of its development area colour.
		if (position instanceof TaskSquare) {
		    TaskSquare taskSquare = (TaskSquare) position;
		    
		    colour = taskSquare.getDevelopmentArea().getColour();
		    float[] hsb = Color.RGBtoHSB(colour.getRed(), colour.getGreen(), colour.getBlue(), null);
		    float brightness = hsb[2] * (1 - (float) 0.1);
		    return new Color(Color.HSBtoRGB(hsb[0], hsb[1], brightness));
		    //.darker();
		}
		//If the player is not on a taskSquare, just return the default colour of Black
		return colour;
	}
	
	/**
	 * Updates the information in the playerPanel after moving to a new square,
	 * ending a turn, or having any changes to the player information in general.
	 */
	public void updatePlayerPanel() {
		Player currentPlayer = SustainopolyGame.getInstance().getCurrentPlayer();
		playerPanel.setBackground(currentPlayer.getColour());

		// update player info/stats
		nameLabel.setText(currentPlayer.getName());
		moneyLabel.setText("Money: $" + currentPlayer.getMoney());
		expertiseLabel.setText("Exp: " + currentPlayer.getExpertise());
		roleLabel.setText(currentPlayer.getRole().toString());

		// update player portrait
		ImageIcon icon = currentPlayer.getPortrait(115, 115);
		portraitLabel.setIcon(icon);
		
		// update positionLabel's background, text and text colour.
		positionPanel.setBackground(currentPlayer.getColour());
		positionLabel.setForeground(getCurrentPositionColour());
		positionLabel.setText(currentPlayer.getPosition().getName());
	}
	
	

	/**
	 * Set the 'Roll Dice' button to be enabled/disabled
	 * 
	 * @param enabled Boolean value- if true, button is enabled; if false, button is
	 *                disabled
	 */
	public void setRollDiceEnabled(boolean enabled) {
		if (enabled)
			rollDiceBtn.setToolTipText(null);
		rollDiceBtn.setEnabled(enabled);
	}
	
	public void setRollDiceBtnToolTip(String message) {
		rollDiceBtn.setToolTipText(message);
	}

	/**
	 * Set the 'Take Charge of Task' button to be enabled/disabled
	 * 
	 * @param enabled Boolean value- if true, button is enabled; if false, button is
	 *                disabled
	 */
	public void setTakeChargeEnabled(boolean enabled) {
		if (enabled)
			takeChargeBtn.setToolTipText(null);
		takeChargeBtn.setEnabled(enabled);
	}
	
	public void setAllianceTableEnabled(boolean enabled) {
		if (enabled)
			showPlayerAlliancesBtn.setToolTipText(null);
		showPlayerAlliancesBtn.setEnabled(enabled);
	}
	
	public void setTakeChargeBtnToolTip(String message) {
		takeChargeBtn.setToolTipText(message);
	}
	
	public void setCompleteStepEnabled(boolean enabled) {
		if (enabled)
			completeStepBtn.setToolTipText(null);
		completeStepBtn.setEnabled(enabled);
	}
	
	public void setCompleteStepBtnToolTip(String message) {
		completeStepBtn.setToolTipText(message);
	}

	/**
	 * Set the 'Form Alliance' button to be enabled/disabled
	 * 
	 * @param enabled Boolean value- if true, button is enabled; if false, button is
	 *                disabled
	 */
	public void setFormAllianceEnabled(boolean enabled) {
		if (enabled)
			formAllianceBtn.setToolTipText(null);
		formAllianceBtn.setEnabled(enabled);
	}
	
	public void setFormAllianceBtnToolTip(String message) {
		formAllianceBtn.setToolTipText(message);
	}

	/**
	 * Set the 'Offer Task' button to be enabled/disabled
	 * 
	 * @param enabled Boolean value- if true, button is enabled; if false, button is
	 *                disabled
	 */
	public void setOfferTaskEnabled(boolean enabled) {
		if (enabled)
			offerTaskBtn.setToolTipText(null);
		offerTaskBtn.setEnabled(enabled);
	}
	
	public void setOfferTaskBtnToolTip(String message) {
		offerTaskBtn.setToolTipText(message);
	}

	/**
	 * Set the 'End Turn' button to be enabled/disabled on the controlsPanel
	 * 
	 * @param enabled Boolean value- if true, button is enabled; if false, button is
	 *                disabled
	 */
	public void setEndTurnEnabled(boolean enabled) {
		if (enabled)
			endTurnBtn.setToolTipText(null);
		endTurnBtn.setEnabled(enabled);
	}
	
	public void setEndTurnBtnToolTip(String message) {
		endTurnBtn.setToolTipText(message);
	}
}
