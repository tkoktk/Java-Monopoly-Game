package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicProgressBarUI;

import sustainopoly.Player;
import sustainopoly.SustainopolyGame;
import sustainopoly.TaskSquare;

public class CompleteStepDialog extends JFrame {
	
	private JLayeredPane layeredPane;
	private JPanel stepPanel;

	/**
	 * Changes the visible panel of the frame
	 * @param panel JPanel that will become visible
	 */
	private void switchPanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	
	public CompleteStepDialog() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Complete Task Step");
		setIconImage(new ImageIcon("images/govanIcon.png").getImage());
		setSize(800, 500);
		setResizable(false);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 800, 500);
		add(layeredPane);
		
		setUpSelectTaskPanel();		// set up the first panel seen by the player
		
		setLocationRelativeTo(null);	// make frame appear in centre of screen
		setVisible(true);
	}
	
	/**
	 * Sets up first panel shown to Player, where they select the task to complete a step of
	 */
	public void setUpSelectTaskPanel() {
		
		Player player = SustainopolyGame.getInstance().getCurrentPlayer();

		// wrapper panel for first screen shown to player
		JPanel selectTaskPanel = new JPanel();
		selectTaskPanel.setBounds(0, 0, 800, 500);
		selectTaskPanel.setLayout(null);

		// panel containing title text and instructions
		JPanel headerPanel = new JPanel();
		headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));	// create border on bottom
		headerPanel.setBounds(0, 0, 800, 100);
		headerPanel.setBackground(player.getColour());
		headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));

		JLabel playerLabel = new JLabel(player.getName() + "'s Tasks");
		playerLabel.setFont(new FontTwo(40));
		playerLabel.setForeground(Color.WHITE);
		JLabel instructionLabel = new JLabel("Please select a task: ");
		instructionLabel.setFont(new FontOne(25));
		instructionLabel.setForeground(Color.WHITE);
		
		// add labels to headerPanel
		headerPanel.add(playerLabel);
		headerPanel.add(instructionLabel);

		// Add headerPanel to upper portion of selectTaskPanel
		selectTaskPanel.add(headerPanel);

		// wrapper panel for task selection buttons
		JPanel tasksPanel = new JPanel();
		tasksPanel.setBounds(0, 100, 800, 400);
		tasksPanel.setBackground(new Color(0xF5F2D0));
		tasksPanel.setLayout(null);

		// panel containing buttons corresponding to each task the player manages
		JPanel taskBtnsPanel = new JPanel();
		taskBtnsPanel.setBounds(100, 50, 600, 280);
		taskBtnsPanel.setBackground(new Color(0xF5F2D0));
		tasksPanel.add(taskBtnsPanel);

		// set different grid layouts depending on number of tasks the player manages
		ArrayList<TaskSquare> tasks = player.getTasks();
		int numTasks = tasks.size();
		if (numTasks <= 4)
			taskBtnsPanel.setLayout(new GridLayout(2, 2, 15, 15));
		else if (numTasks <= 9)
			taskBtnsPanel.setLayout(new GridLayout(3, 3, 15, 15));
		else if (numTasks <= 16)
			taskBtnsPanel.setLayout(new GridLayout(4, 4, 10, 10));

		// loop through tasks, creating a button for each with an action listener (if enabled)
		for (TaskSquare ts : tasks) {
			SustainopolyButton taskBtn = new SustainopolyButton("<html>" + ts.getName() + "<html>");
			
			// if player can't afford a step of the task, or if its already complete, disable the button
			if (ts.getStepPrice() >= player.getMoney()) {
				taskBtn.setEnabled(false);
				taskBtn.setToolTipText("You do not have sufficient money to complete a step of this task");
			} else if (ts.isComplete()) {
				taskBtn.setEnabled(false);
				taskBtn.setToolTipText("This task is already complete");
			}

			taskBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setUpStepPanel(ts);
					switchPanels(stepPanel);
				}
			});

			taskBtnsPanel.add(taskBtn);
		}

		selectTaskPanel.add(tasksPanel);

		// add overall selectTaskPanel to the layeredPane
		layeredPane.add(selectTaskPanel);
	}
	
	/**
	 * Sets up the second panel shown to the Player, showing them details of the next step of the selected task
	 * with confirm/cancel buttons
	 * @param ts
	 */
	public void setUpStepPanel(TaskSquare ts) {
		
		Player player = SustainopolyGame.getInstance().getCurrentPlayer();
		ArrayList<String> steps = ts.getSteps();
		int currentStepNumber = ts.getCurrentStepNumber();
		int totalNumSteps = ts.getNumSteps();
		
		stepPanel = new JPanel();
		stepPanel.setBounds(0, 0, 800, 500);
		stepPanel.setLayout(null);
		
		// panel containing the task name, description, and development area
		JPanel headerPanel = new JPanel();
		headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));	// create border on bottom
		headerPanel.setBounds(0, 0, 800, 150);
		headerPanel.setBackground(ts.getDevelopmentArea().getColour());		// background is set to colour of task's development area
		headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
		
		JLabel nameLabel = new JLabel("Task Name: " + ts.getName());
		nameLabel.setFont(new FontTwo(40));
		JLabel descriptionLabel = new JLabel("<html>Description: " + ts.getDescription() + "</html>");
		descriptionLabel.setFont(new FontOne(25));
		JLabel developmentAreaLabel = new JLabel("Development Area: " + ts.getDevelopmentArea());
		developmentAreaLabel.setFont(new FontOne(25));
		
		// add labels to headerPanel
		headerPanel.add(nameLabel);
		headerPanel.add(descriptionLabel);
		headerPanel.add(developmentAreaLabel);

		// Add headerPanel to upper portion of selectTaskPanel
		stepPanel.add(headerPanel);
		
		// panel containing a description and price of the next step, a progress bar with completion of the overall task, and confirm/cancel buttons
		JPanel infoPanel = new JPanel();
		infoPanel.setBounds(0, 150, 800, 350);
		infoPanel.setBackground(new Color(0xF5F2D0));
		infoPanel.setLayout(null);
		
		// JProgressBar showing % completion of the task
		JProgressBar taskCompletionBar = new JProgressBar();
		taskCompletionBar.setLayout(new BorderLayout());
		taskCompletionBar.setAlignmentX(JProgressBar.RIGHT_ALIGNMENT);
		taskCompletionBar.setBounds(475, 15, 300, 50);
		taskCompletionBar.setBorderPainted(true);
		double completion = ((double)currentStepNumber / totalNumSteps) * 100;
		taskCompletionBar.setValue((int)completion);
		taskCompletionBar.setBorder(new LineBorder(Color.BLACK, 2));
		taskCompletionBar.setBackground(new Color(0xB9FBB9));
		taskCompletionBar.setForeground(new Color(125, 219, 67));
		
		JLabel progressLabel = new JLabel("Completion: " + taskCompletionBar.getValue() + "%");
		progressLabel.setHorizontalAlignment(JLabel.CENTER);
		progressLabel.setVerticalAlignment(JLabel.CENTER);
		progressLabel.setFont(new FontOne(18));
		taskCompletionBar.add(progressLabel, BorderLayout.CENTER);
		
		taskCompletionBar.setUI(new BasicProgressBarUI() {
			protected Color getSelectionBackground() { return Color.black; }
			protected Color getSelectionForeground() { return Color.black; }
		});
		
		infoPanel.add(taskCompletionBar);
		
		// labels containing description and cost of next step
		JLabel stepLabel = new JLabel("<html>Next Step: " + steps.get(currentStepNumber) + "<html>");
		stepLabel.setFont(new FontOne(25));
		stepLabel.setBounds(15, 75, 750, 75);
		JLabel costLabel= new JLabel();
		
		// add differing notes depending on free next step, double cost next step, alliance reduction or normal
		if (player.getNextStepFree())
			costLabel.setText("Cost: FREE (next step free effect applied)");
		else if (player.getNextStepCostsDouble())
			costLabel.setText("Price: $" + (ts.getStepPrice() * 2) + " (next step costs double effect applied)");
		else if (ts.getDevelopmentArea().isManagedByAlliance())
			costLabel.setText("Price: $" + ts.getStepPrice() + " (20% alliance reduction applied)");
		else
			costLabel.setText("Price: $" + ts.getStepPrice());
		costLabel.setFont(new FontOne(25));
		costLabel.setBounds(15, 150, 750, 30);
		
		infoPanel.add(stepLabel);
		infoPanel.add(costLabel);
		
		// confirm button; when clicked, task step is completed
		SustainopolyButton confirmBtn = new SustainopolyButton("Confirm");
		confirmBtn.setBounds(425, 250, 150, 50);
		confirmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player.completeTaskStep(ts);	// complete step of TaskSquare
				SustainopolyGame.getInstance().updateGUI();		// update playerPanel
				dispose();
			}
		});
		
		SustainopolyButton cancelBtn = new SustainopolyButton("Cancel");
		cancelBtn.setBounds(600, 250, 150, 50);
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		infoPanel.add(confirmBtn);
		infoPanel.add(cancelBtn);
		
		// add infoPanel to wrapper stepPanel
		stepPanel.add(infoPanel);
		
		// add overall stepPanel to layeredPane
		layeredPane.add(stepPanel);
	}

}
