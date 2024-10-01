package gui;

import sustainopoly.*;

import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

public class FormAllianceDialog extends JFrame {

	// This serial ID just gets rid of an error
	private static final long serialVersionUID = 1L;
	JLabel devAreaLabel;
	JLabel playerLabel;
	JLabel promptLabel;
	JLabel promptLabel2;
	JLabel promptLabel3;
	JLabel acceptedLabel;
	JLabel acceptedLabel2;
	JLabel declinedLabel;
	JLabel declinedLabel2;

	// game
	public static SustainopolyGame game = SustainopolyGame.getInstance();

	public FormAllianceDialog() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(850, 540);
		setResizable(false);
		setLayout(null);
		setTitle("Form Alliance");
		setIconImage(new ImageIcon("images/govanIcon.png").getImage());
		
		// header panel
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(null);
		headerPanel.setBounds(0, 0, 850, 100);
		headerPanel.setBackground(new Color(125, 219, 67));
		headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));	// create border on bottom
		
		JLabel headerLabel = new JLabel("<html>Select the development area you wish to form an alliance on:</html>");
		headerLabel.setBounds(10, 0, 830, 100);
		headerLabel.setFont(new FontTwo(40));
		headerPanel.add(headerLabel);
		
		// add header panel to overall frame
		add(headerPanel);

		JPanel devAreaPanel = new JPanel();
		devAreaPanel.setLayout(null);
		devAreaPanel.setBounds(0, 100, 850, 440);
		devAreaPanel.setBackground(new Color(0xF5F2D0));
		
		/**
		 *Button to select Increment Infrastructure
		 *Calls promptPlayers
		 */
		DevAreaButton incrementInfBtn = new DevAreaButton(DevelopmentArea.INCREMENT_INFRASTRUCTURE);
		incrementInfBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				promptPlayers(DevelopmentArea.INCREMENT_INFRASTRUCTURE);
			}
		});
		
		incrementInfBtn.setBounds(557, 229, 250, 63);
		devAreaPanel.add(incrementInfBtn);

		/**
		 *Button to select Find Funding
		 *Calls promptPlayers
		 */
		DevAreaButton findFundingBtn = new DevAreaButton(DevelopmentArea.FIND_FUNDING);
		findFundingBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				promptPlayers(DevelopmentArea.FIND_FUNDING);
			}
		});

		findFundingBtn.setBounds(37, 35, 250, 63);
		devAreaPanel.add(findFundingBtn);

		/**
		 *Button to select Consult Community
		 *Calls promptPlayers
		 */
		DevAreaButton consultCommBtn = new DevAreaButton(DevelopmentArea.CONSULT_COMMUNITY);
		consultCommBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				promptPlayers(DevelopmentArea.CONSULT_COMMUNITY);
			}
		});

		consultCommBtn.setBounds(297, 35, 250, 63);
		devAreaPanel.add(consultCommBtn);

		/**
		 *Button to select Advance Awareness
		 *Calls promptPlayers
		 */
		DevAreaButton advanceAwareBtn = new DevAreaButton(DevelopmentArea.ADVANCE_AWARENESS);
		advanceAwareBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				promptPlayers(DevelopmentArea.ADVANCE_AWARENESS);
			}
		});

		advanceAwareBtn.setBounds(557, 35, 250, 63);
		devAreaPanel.add(advanceAwareBtn);

		/**
		 *Button to select Train Team
		 *Calls promptPlayers
		 */
		DevAreaButton trainTeamBtn = new DevAreaButton(DevelopmentArea.TRAIN_TEAM);
		trainTeamBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				promptPlayers(DevelopmentArea.TRAIN_TEAM);
			}
		});

		trainTeamBtn.setBounds(37, 135, 250, 63);
		devAreaPanel.add(trainTeamBtn);

		/**
		 *Button to select Organise Online Presence
		 *Calls promptPlayers
		 */
		DevAreaButton organiseOnlineBtn = new DevAreaButton(DevelopmentArea.ORGANIZE_ONLINE_PRESENCE);
		organiseOnlineBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				promptPlayers(DevelopmentArea.ORGANIZE_ONLINE_PRESENCE);
			}
		});

		organiseOnlineBtn.setBounds(297, 135, 250, 63);
		devAreaPanel.add(organiseOnlineBtn);

		/**
		 *Button to select Dispense Devices
		 *Calls promptPlayers
		 */
		DevAreaButton dispenseDevBtn = new DevAreaButton(DevelopmentArea.DISPENSE_DEVICES);
		dispenseDevBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				promptPlayers(DevelopmentArea.DISPENSE_DEVICES);
			}
		});
	
		dispenseDevBtn.setBounds(557, 135, 250, 63);
		devAreaPanel.add(dispenseDevBtn);

		/**
		 *Button to select Source SupplyChain
		 *Calls promptPlayers
		 */
		DevAreaButton sourceSupplyBtn = new DevAreaButton(DevelopmentArea.SOURCE_SUPPLYCHAIN);
		sourceSupplyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				promptPlayers(DevelopmentArea.SOURCE_SUPPLYCHAIN);
			}
		});

		sourceSupplyBtn.setBounds(297, 229, 250, 63);
		devAreaPanel.add(sourceSupplyBtn);

		/**
		 *Button to select Get GoVan
		 *Calls promptPlayers
		 */
		DevAreaButton getGoVanBtn = new DevAreaButton(DevelopmentArea.GET_GOVAN);
		getGoVanBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				promptPlayers(DevelopmentArea.GET_GOVAN);
			}
		});

		getGoVanBtn.setBounds(37, 229, 250, 63);
		devAreaPanel.add(getGoVanBtn);

		// cancel button- disposes of frame
		SustainopolyButton cancelButton = new SustainopolyButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		cancelButton.setBounds(607, 326, 200, 63);
		devAreaPanel.add(cancelButton);
		
		// add panel with buttons to overall frame
		add(devAreaPanel);

		setLocationRelativeTo(null);	// make frame appear in centre of screen
		setVisible(true);
	}

	/**
	 *prompts other players who are in charge of tasks in the chosen development area 
	 * @param da DevelopmentArea over which the Alliance is being formed
	 */
	public void promptPlayers(DevelopmentArea da) {
		
		ArrayList<Player> members = new ArrayList<Player>();
		TaskSquare[] tasksOfDevelopmentArea = game.getGameBoard().getTasksInDevelopmentArea(da);
		
		String prompt;
		int result = 0;
		
		// loop through other players of development area, skipping current player who has initiated the process
		for (TaskSquare task : tasksOfDevelopmentArea) {
			if (task.getOwner() == game.getCurrentPlayer())
				continue;
			else {
				prompt = task.getOwner().getName() + ", " + game.getCurrentPlayer().getName()
						+ " would like you to join an alliance with them in " + da.toString()
						+ ". Do you wish to accept?";		

				result = JOptionPane.showConfirmDialog(null, prompt, "Join Alliance", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				
				if (result == JOptionPane.YES_OPTION)
					members.add(task.getOwner());
				else {
					JOptionPane.showMessageDialog(null, task.getOwner().getName() + " declined offer to join alliance. Formation cancelled.",
							"Unable To Form Alliance", JOptionPane.INFORMATION_MESSAGE,
							new ImageIcon("images/issue.png"));
					return;
				}
			}
		}
		
		// re-confirm with current player, who initiated the form alliance, if they want to continue and form alliance
		prompt = game.getCurrentPlayer().getName() + ", are you sure you want to form an alliance in "
				+ da.toString() + "?";

		result = JOptionPane.showConfirmDialog(null, prompt, "Form Alliance", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);

		if (result == JOptionPane.YES_OPTION)
			members.add(game.getCurrentPlayer());
		else {
			JOptionPane.showMessageDialog(null, game.getCurrentPlayer().getName() + " cancelled alliance formation.",
					"Unable To Form Alliance", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/issue.png"));
			return;
		}
		
		// if reached here, everyone has agreed and alliance is formed
		game.formAlliance(da, members);
		JOptionPane.showMessageDialog(null, "An alliance has successfully been formed in " + da.toString()
				+ ". Each member has gained 5 expertise \nand there is a 20% reduction in task step costs within the development area.",
				"Alliance Formed", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/issue.png"));
	}
}

