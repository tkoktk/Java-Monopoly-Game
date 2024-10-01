package gui;

import sustainopoly.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class OfferTaskDialog extends JFrame {

	//This serial ID just gets rid of an error
	private static final long serialVersionUID = 1L;

	private ControlsPanel controlsPanel;
	private Font standardFont = new FontOne(24);

	// game
	public static SustainopolyGame game = SustainopolyGame.getInstance();

	/**
	 * Create the frame.
	 */

	public OfferTaskDialog(ControlsPanel controlsPanel) {

		this.controlsPanel = controlsPanel;
		Square currentSquare = game.getCurrentPlayer().getPosition();
		TaskSquare ts = (TaskSquare)currentSquare;

		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(525, 600);
		setLayout(null);
		setTitle("Offer Task");
		setIconImage(new ImageIcon("images/govanIcon.png").getImage());
		
		// header panel
		JPanel headerPanel = new JPanel();
		headerPanel.setBounds(0,0, 525, 100);
		headerPanel.setLayout(null);
		headerPanel.setBackground(new Color(125, 219, 67));
		headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));	// create border on bottom
		
		Color daColour = ((TaskSquare)currentSquare).getDevelopmentArea().getColour();
        String daRGBString = "rgb(" + daColour.getRed() + ", " + daColour.getGreen() + ", " + daColour.getBlue() + ")";
		JLabel taskLabel = new JLabel("<html><b>Offer Task: <span style='color: " + daRGBString + "'>" + currentSquare.getName() + "</span></b></html>");
		taskLabel.setBounds(10, 10, 525, 45);
		taskLabel.setFont(new FontTwo(40));
		
		JLabel playerLabel = new JLabel("Select player to offer this task to: ");
		playerLabel.setBounds(10, 60, 525, 45);
		playerLabel.setFont(standardFont);
		
		// add two labels to headerPanel
		headerPanel.add(taskLabel);
		headerPanel.add(playerLabel);
		
		// add headerPanel to overall frame
		add(headerPanel);
		
		// offer panel containing the buttons to select player
		JPanel offerPanel = new JPanel();
		offerPanel.setLayout(null);
		offerPanel.setBounds(0, 100, 525, 500);
		offerPanel.setBackground(new Color(0xF5F2D0));

		/**
		 *Button to select Player1
		 *Creates new TakeChargeOfTaskDialog - not visible
		 *Calls promptPlayer
		 */
		PlayerButton p1 = new PlayerButton(0, ts,controlsPanel);

		p1.setBounds(10, 10, 225, 60);
		p1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				promptPlayer(game.getPlayer(0), ts);
			}
		});
		offerPanel.add(p1);

		/**
		 *Button to select Player2
		 *Creates new TakeChargeOfTaskDialog - not visible
		 *Calls promptPlayer
		 */
		PlayerButton p2 = new PlayerButton(1, ts, controlsPanel);

		p2.setBounds(270, 10, 225, 60);
		p2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				promptPlayer(game.getPlayer(1), ts);
			}
		});
		offerPanel.add(p2);

		/**
		 *Button to select Player3
		 *Creates new TakeChargeOfTaskDialog - not visible
		 *Calls promptPlayer
		 */
		PlayerButton p3 = new PlayerButton(2, ts, controlsPanel);

		p3.setBounds(10, 100, 225, 60);
		p3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				promptPlayer(game.getPlayer(2), ts);
			}
		});
		offerPanel.add(p3);

		/**
		 *Button to select Player4
		 *Creates new TakeChargeOfTaskDialog - not visible
		 *Calls promptPlayer
		 */
		PlayerButton p4 = new PlayerButton(3,ts, controlsPanel);

		p4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				promptPlayer(game.getPlayer(3), ts);
			}
		});

		p4.setBounds(270, 100, 225, 60);
		offerPanel.add(p4);

		/**
		 *Button to select Player5
		 *Creates new TakeChargeOfTaskDialog - not visible
		 *Calls promptPlayer
		 */
		PlayerButton p5 = new PlayerButton(4,ts, controlsPanel);

		p5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				promptPlayer(game.getPlayer(4), ts);
			}
		});
		p5.setBounds(10, 190, 225, 60);
		offerPanel.add(p5);

		/**
		 *Button to select Player6
		 *Creates new TakeChargeOfTaskDialog - not visible
		 *Calls promptPlayer
		 */
		PlayerButton p6 = new PlayerButton(5, ts, controlsPanel);	

		p6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				promptPlayer(game.getPlayer(5), ts);
			}
		});
		p6.setBounds(270, 190, 225, 60);
		offerPanel.add(p6);

		/**
		 *Button to select Player7
		 *Creates new TakeChargeOfTaskDialog - not visible
		 *Calls promptPlayer
		 */
		PlayerButton p7 = new PlayerButton(6, ts, controlsPanel);	

		p7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				promptPlayer(game.getPlayer(6), ts);
			}
		});
		p7.setBounds(10, 280, 225, 60);
		offerPanel.add(p7);

		/**
		 *Button to select Player8
		 *Creates new TakeChargeOfTaskDialog - not visible
		 *Calls promptPlayer
		 */
		PlayerButton p8 = new PlayerButton(7, ts, controlsPanel);	

		p8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				promptPlayer(game.getPlayer(7), ts);
			}
		});
		p8.setBounds(270, 280, 225, 60);
		offerPanel.add(p8);

		// cancel button- disposes frame
		SustainopolyButton cancelBtn = new SustainopolyButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelBtn.setBounds(162, 370, 200, 60);
		offerPanel.add(cancelBtn);
		
		// add offerPanel to overall frame
		add(offerPanel);

		setLocationRelativeTo(null);	// make frame appear in centre of screen
		setVisible(true);
	}
	
	/**
	 *Displays a message to the player who is being offer the task, asking if they would like to view details. If so, the TakeChargeOfTaskDialog is called
	 * @param pos TaskSquare being offered
	 * @param offerPlayer Player instance who is being offered TaskSquare
	 */
	public void promptPlayer(Player offerPlayer, Square pos) {

		String prompt = offerPlayer.getName() + ", " + game.getCurrentPlayer().getName()
				+ " would like to offer you to take charge of " + pos.getName() + ". Do you wish to view task details?";

		int result = JOptionPane.showConfirmDialog(null, prompt, "Task Offer", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);

		if (result == JOptionPane.YES_OPTION) {
			new TakeChargeDialog(controlsPanel, offerPlayer);
		} else {
			JOptionPane.showMessageDialog(null,
					offerPlayer.getName() + " declined offer to take charge of " + pos.getName(), "Offer Declined",
					JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/issue.png"));
		}
	}
}