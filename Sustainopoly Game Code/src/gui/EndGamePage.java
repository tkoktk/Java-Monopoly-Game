package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicProgressBarUI;

import sustainopoly.DevelopmentArea;
import sustainopoly.TaskSquare;

public class EndGamePage extends JFrame {
	
	public EndGamePage(boolean completedGame) {
		
		setIconImage(new ImageIcon("images/govanIcon.png").getImage());
		setTitle("Game Over");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLayout(new GridLayout(1,2));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// JPanel containing the scrolling text for 'the path ahead', located on left side of JFrame
		ScrollingTextPanel scrollingTextPanel = new ScrollingTextPanel(completedGame);
		add(scrollingTextPanel);
		
		// JPanel containing the completion of the game, the player stats table etc.
		JPanel statsPanel = new JPanel();
		GridBagLayout gbl_statsPanel = new GridBagLayout();
		gbl_statsPanel.columnWeights = new double[] { 1.00 };
		gbl_statsPanel.rowWeights = new double[] { 0.03, 0.2, 0.77 };
		statsPanel.setLayout(gbl_statsPanel);
		add(statsPanel);
		
		// JPanel containing title for the statsPanel
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(new Color(0xF5F2D0));
		JLabel title = new JLabel("Game Stats", SwingConstants.CENTER);
		title.setFont(new FontTwo(35));
		titlePanel.add(title);
		
		// add titlePanel to statsPanel
		GridBagConstraints titlePanel_gbc = new GridBagConstraints();
		titlePanel_gbc.fill = GridBagConstraints.BOTH;
		titlePanel_gbc.gridx = 0;
		titlePanel_gbc.gridy = 0;
		titlePanel_gbc.gridheight = 1;
		titlePanel_gbc.gridwidth = 1;
		statsPanel.add(titlePanel, titlePanel_gbc);
		
		// JPanel containing state of completion of all 9 development areas with JProgressBars
		JPanel completionPanel = new JPanel();
		completionPanel.setBorder(new EmptyBorder(5,5,5,5));
		completionPanel.setBackground(new Color(0xF5F2D0));
		
		// 3x3 GridLayout used for 9 development areas
		completionPanel.setLayout(new GridLayout(3, 3, 3, 3));
		for (DevelopmentArea da : DevelopmentArea.values()) {
			
			JPanel daPanel = new JPanel();
			daPanel.setBackground(new Color(0xF5F2D0));
			daPanel.setLayout(new GridLayout(1, 2));
			
			// label containing name of development area
			JLabel nameLabel = new JLabel("<html><p style='text-align: right'>" + da.toString() + "</p></html>", SwingConstants.RIGHT);
			nameLabel.setFont(new FontOne(12));
			nameLabel.setBorder(new EmptyBorder(0,0,0,2));
			daPanel.add(nameLabel);
			
			int numStepsCompleted = 0;
			int totalNumSteps = 0;
			
			for (TaskSquare ts : da.getTasks()) {
				totalNumSteps += ts.getNumSteps();
				numStepsCompleted += ts.getCurrentStepNumber();
			}
			
			// JProgressBar showing % completion of the development area
			JProgressBar daCompletionBar = new JProgressBar();
			daCompletionBar.setLayout(new BorderLayout());
			daCompletionBar.setBorderPainted(true);
			double completion = ((double) numStepsCompleted / totalNumSteps) * 100;
			daCompletionBar.setValue((int) completion);
			daCompletionBar.setBorder(new LineBorder(Color.BLACK, 2));
			Color daColour = da.getColour();
			daCompletionBar.setBackground(new Color(daColour.getRed(), daColour.getGreen(), daColour.getBlue(), 50));
			daCompletionBar.setForeground(da.getColour());
			
			JLabel progressLabel = new JLabel(daCompletionBar.getValue() + "%");
			progressLabel.setHorizontalAlignment(JLabel.CENTER);
			progressLabel.setVerticalAlignment(JLabel.CENTER);
			progressLabel.setFont(new FontOne(18));
			daCompletionBar.add(progressLabel, BorderLayout.CENTER);

			daCompletionBar.setUI(new BasicProgressBarUI() {
				protected Color getSelectionBackground() {
					return Color.black;
				}

				protected Color getSelectionForeground() {
					return Color.black;
				}
			});
			daPanel.add(daCompletionBar);
			completionPanel.add(daPanel);
		}
		
		// add completionPanel to statsPanel
		GridBagConstraints completionPanel_gbc = new GridBagConstraints();
		completionPanel_gbc.fill = GridBagConstraints.BOTH;
		completionPanel_gbc.gridx = 0;
		completionPanel_gbc.gridy = 1;
		completionPanel_gbc.gridheight = 1;
		completionPanel_gbc.gridwidth = 1;
		statsPanel.add(completionPanel, completionPanel_gbc);
		
		// JPanel containing the table with all player stats at end of game
		SustainopolyPlayerStats statsTable = new SustainopolyPlayerStats();
		statsTable.setBackground(new Color(0xF5F2D0));
		
		JPanel tablePanel = new JPanel();
		tablePanel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK));	// create border on top
		tablePanel.setBackground(new Color(0xF5F2D0));
		tablePanel.setLayout(new GridLayout(1,1));
		tablePanel.add(statsTable);
		
		// add tablePanel to statsPanel
		GridBagConstraints tablePanel_gbc = new GridBagConstraints();
		tablePanel_gbc.fill = GridBagConstraints.BOTH;
		tablePanel_gbc.gridx = 0;
		tablePanel_gbc.gridy = 2;
		tablePanel_gbc.gridheight = 1;
		tablePanel_gbc.gridwidth = 1;
		statsPanel.add(tablePanel, tablePanel_gbc);

		setVisible(true);
	}
	
	
}
