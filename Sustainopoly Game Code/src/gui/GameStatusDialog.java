package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicProgressBarUI;

import sustainopoly.DevelopmentArea;
import sustainopoly.Player;
import sustainopoly.TaskSquare;

public class GameStatusDialog extends JFrame {

	public GameStatusDialog() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Game Completion");
		setSize(900, 600);
		setResizable(false);
		setIconImage(new ImageIcon("images/govanIcon.png").getImage());
		setLayout(null);
		
		// header panel containing panel title
		JPanel headerPanel = new JPanel();
		headerPanel.setBounds(0, 0, 900, 100);
		headerPanel.setBackground(new Color(125, 219, 67));
		headerPanel.setLayout(null);
		headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));	// create border on bottom
		
		// add headerPanel to overall JFrame
		add(headerPanel);
		
		// create and add title label to headerPanel
		JLabel titleLabel = new JLabel("Game Completion Status");
		titleLabel.setFont(new FontTwo(50));
		titleLabel.setBounds(20, 20, 860, 60);
		headerPanel.add(titleLabel);
		
		// information panel containing development area names, task + step completions
		JPanel infoPanel = new JPanel();
		infoPanel.setBounds(0, 100, 900, 500);
		infoPanel.setLayout(null);
		infoPanel.setBackground(new Color(0xF5F2D0));
		
		// wrapper panel for development area stats
		JPanel completionPanel = new JPanel();
		completionPanel.setBounds(10, 10, 880, 320);
		completionPanel.setBackground(new Color(0xF5F2D0));
		completionPanel.setLayout(new BoxLayout(completionPanel, BoxLayout.Y_AXIS));
		
		Font standardFont = new FontOne(20);
		
		// loop through all development areas, creating panel with details for each and getting their stats
		boolean allDevelopmentAreasFullyAddressed = true;
		for (DevelopmentArea da : DevelopmentArea.values()) {
			JPanel daPanel = new JPanel();
			daPanel.setMaximumSize(new Dimension(880, 35));
			daPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
			daPanel.setBackground(new Color(0xF5F2D0));
			
			// label containing name of development area
			
			Color daColour = da.getColour();
			String daRGBString = "rgb(" + daColour.getRed() + ", " + daColour.getGreen() + ", " + daColour.getBlue() + ")";
			
			JLabel nameLabel = new JLabel("<html><p><span style='color: " + daRGBString + "'>" + da.toString() + "</span></p></html>");
			
			//JLabel nameLabel = new JLabel(da.toString());
			nameLabel.setFont(standardFont);
			nameLabel.setPreferredSize(new Dimension(200, 35));
			daPanel.add(nameLabel);
			
			int numTasksCompleted = 0;
			int numStepsCompleted = 0;
			int totalNumSteps = 0;
			
			boolean allTasksManagedByOnePlayer = true;
			Player firstOwner = da.getTasks().get(0).getOwner();
			
			// loop through TaskSquares in development area to get total number of steps and tasks completed
			for (TaskSquare ts : da.getTasks()) {
				if (ts.isComplete())
					numTasksCompleted++;
				if (ts.getOwner() != firstOwner)
					allTasksManagedByOnePlayer = false;
				
				totalNumSteps += ts.getNumSteps();
				numStepsCompleted += ts.getCurrentStepNumber();
			}
			
			// label containing number of tasks within development area completed
			JLabel tasksLabel = new JLabel("Tasks: " + numTasksCompleted + "/" + da.getNumTasks());
			tasksLabel.setPreferredSize(new Dimension(120, 35));
			tasksLabel.setFont(standardFont);
			daPanel.add(tasksLabel);
			
			// label containing total number of steps within development area completed
			JLabel stepsLabel = new JLabel("Steps: " + numStepsCompleted + "/" + totalNumSteps);
			stepsLabel.setPreferredSize(new Dimension(120, 35));
			stepsLabel.setFont(standardFont);
			daPanel.add(stepsLabel);
			
			// JProgressBar showing % completion of the development area
			JProgressBar daCompletionBar = new JProgressBar();
			daCompletionBar.setLayout(new BorderLayout());
			daCompletionBar.setPreferredSize(new Dimension(260, 30));
			daCompletionBar.setBorderPainted(true);
			double completion = ((double)numStepsCompleted / totalNumSteps) * 100;
			daCompletionBar.setValue((int)completion);
			daCompletionBar.setBorder(new LineBorder(Color.BLACK, 2));
			//Color daColour = da.getColour();
			daCompletionBar.setBackground(new Color(daColour.getRed(), daColour.getGreen(), daColour.getBlue(), 50));
			daCompletionBar.setForeground(da.getColour());
			
			JLabel progressLabel = new JLabel("Completion: " + daCompletionBar.getValue() + "%");
			progressLabel.setHorizontalAlignment(JLabel.CENTER);
			progressLabel.setVerticalAlignment(JLabel.CENTER);
			progressLabel.setFont(new FontOne(18));
			daCompletionBar.add(progressLabel, BorderLayout.CENTER);
			
			daCompletionBar.setUI(new BasicProgressBarUI() {
				protected Color getSelectionBackground() { return Color.black; }
				protected Color getSelectionForeground() { return Color.black; }
			});
			daPanel.add(daCompletionBar);
			
			// label saying whether the development area is fully addressed or not
			JLabel fullyAddressedLabel = new JLabel("<html>Fully Addressed \u2718</html>");
			fullyAddressedLabel.setBorder(new EmptyBorder(0, 20, 0, 0));
			fullyAddressedLabel.setFont(new FontOne(20));
			
			if (numStepsCompleted == totalNumSteps && (da.isManagedByAlliance() || allTasksManagedByOnePlayer)) {
				fullyAddressedLabel.setText("<html>Fully Addressed \u2713</html>");
				fullyAddressedLabel.setForeground(Color.GREEN);
			} else {
				allDevelopmentAreasFullyAddressed = false;
				fullyAddressedLabel.setForeground(Color.RED);
				if (numStepsCompleted != totalNumSteps)
					fullyAddressedLabel.setToolTipText("All task steps have not yet been completed");
				else
					fullyAddressedLabel.setToolTipText("Development area must be managed by an alliance, or all tasks by one player");
			}	
			
			daPanel.add(fullyAddressedLabel);
				
			completionPanel.add(daPanel);
		}
		
		// add completionPanel to overall infoPanel
		infoPanel.add(completionPanel);
		
		// button to complete game, only becomes enabled once all development areas fully addressed
		SustainopolyButton completeGameBtn = new SustainopolyButton("Complete Game");
		completeGameBtn.setBounds(400, 400, 300, 50);
		if (!allDevelopmentAreasFullyAddressed) {
			completeGameBtn.setEnabled(false);
			completeGameBtn.setToolTipText("All development areas not yet fully addressed");
		}
		completeGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// close all open windows (this popup + MainWindow)
				System.gc();
				for (Window window : Window.getWindows()) {
				    window.dispose();
				}
				new EndGamePage(true);
			}
		});
		infoPanel.add(completeGameBtn);
		
		SustainopolyButton closeBtn = new SustainopolyButton("Close");
		closeBtn.setBounds(720, 400, 140, 50);
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		infoPanel.add(closeBtn);

		add(infoPanel);
		
		setLocationRelativeTo(null);	// make frame appear in centre of screen
		setVisible(true);
	}
}
