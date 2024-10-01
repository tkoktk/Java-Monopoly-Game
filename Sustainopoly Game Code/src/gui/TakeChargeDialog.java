package gui;

import sustainopoly.*;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class TakeChargeDialog extends JFrame {

	//This serial ID just gets rid of an error
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel confirmedPanel;
	private JLayeredPane layeredPane;

	private Font standardFont = new FontOne(24);

	// game
	private static SustainopolyGame game = SustainopolyGame.getInstance();

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

	/**
	 * Create the frame.
	 */

	public TakeChargeDialog(ControlsPanel controlsPanel, Player player) {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		setTitle("Take Charge of Task");
		setIconImage(new ImageIcon("images/govanIcon.png").getImage());
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 900, 600);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));

		setUpTaskPanel(controlsPanel, player);
		// NB: setUpConfirmedPanel() only called once square is taken charge of so that it gets updated square timeout
		
		setLocationRelativeTo(null);	// make frame appear in centre of screen
		setVisible(true);
	}
	
	private void setUpTaskPanel(ControlsPanel controlsPanel, Player player) {
		
		Square currentSquare = game.getCurrentPlayer().getPosition();
		TaskSquare ts = (TaskSquare)currentSquare;
		
		// overall wrapper panel
		JPanel taskPanel = new JPanel();
		taskPanel.setBounds(0, 0, 900, 600);
		taskPanel.setLayout(null);
		taskPanel.setBackground(Color.RED);
		
		// header panel
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(null);
		headerPanel.setBounds(0, 0, 900, 150);
		headerPanel.setBackground(new Color(125, 219, 67));
		headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));	// create border on bottom
		
		Color daColour = ((TaskSquare)currentSquare).getDevelopmentArea().getColour();
		String daRGBString = "rgb(" + daColour.getRed() + ", " + daColour.getGreen() + ", " + daColour.getBlue() + ")";
		
		JLabel headerLabel = new JLabel("<html><b><span style='color: " + daRGBString + "'>" + currentSquare.getName() + "</span></b> is available. Would you like to take charge of this task?</html>");
		headerLabel.setBounds(10, 10, 860, 130);
		headerLabel.setFont(new FontTwo(45));
		headerPanel.add(headerLabel);
		
		// add header panel to wrapper panel
		taskPanel.add(headerPanel);

		// info panel which will hold TaskSquare details
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(null);
		infoPanel.setBounds(0, 150, 900, 500);
		infoPanel.setBackground(new Color(0xF5F2D0));

		// displays the TaskSquare description
		JLabel descriptionLabel = new JLabel("<html><b>Description: </b>" + ts.getDescription() + "</html>");
		descriptionLabel.setFont(standardFont);
		descriptionLabel.setBounds(10, 10, 860, 70);
		infoPanel.add(descriptionLabel);

		// displays the TaskSquare DevelopmentArea
		JLabel devAreaLabel = new JLabel("<html><b>Development Area: </b><span style='color: " + daRGBString + "'>" + ts.getDevelopmentArea() + "</span></html>");
		devAreaLabel.setFont(standardFont);
		devAreaLabel.setBounds(10, 80, 860, 45);
		infoPanel.add(devAreaLabel);

		// displays the price of the TaskSquare
		JLabel priceLabel = new JLabel("<html><b>Price: </b>$" + ts.getPrice() + "</html>");
		priceLabel.setFont(standardFont);
		priceLabel.setBounds(10, 130, 400, 45);
		infoPanel.add(priceLabel);

		// displays the required expertise of the TaskSquare
		JLabel expertiseLabel = new JLabel("<html><b>Required Expertise: </b>" + ts.getRequiredExpertise() + "</html>");
		expertiseLabel.setFont(standardFont);
		expertiseLabel.setBounds(10, 180, 400, 45);
		infoPanel.add(expertiseLabel);
		
		// displays the number of steps of the TaskSquare
		JLabel numStepsLabel = new JLabel("<html><b>Number of Steps: </b>" + ts.getNumSteps() + "</html>");
		numStepsLabel.setFont(standardFont);
		numStepsLabel.setBounds(10, 230, 400, 45);
		infoPanel.add(numStepsLabel);
		
		// displays the base contribution of the TaskSquare
		JLabel contributionLabel = new JLabel("<html><b>Base Contribution: </b>$" + ts.getExpectedContribution() + "</html>");
		contributionLabel.setFont(standardFont);
		contributionLabel.setBounds(460, 130, 400, 45);
		infoPanel.add(contributionLabel);
		
		// displays the square timeout cost of the TaskSquare
		JLabel timeoutLabel = new JLabel("<html><b>Square Timeout Cost: </b>" + ts.getSquareTimeoutPrice() + "</html>");
		timeoutLabel.setFont(standardFont);
		timeoutLabel.setBounds(460, 180, 400, 45);
		infoPanel.add(timeoutLabel);
		
		// displays the step price of the TaskSquare
		JLabel stepPriceLabel = new JLabel("<html><b>Step Price: </b>$" + ts.getStepPrice() + "</html>");
		stepPriceLabel.setFont(standardFont);
		stepPriceLabel.setBounds(460, 230, 400, 45);
		infoPanel.add(stepPriceLabel);

		// confirm button - confirms Player instance will take charge of task
		SustainopolyButton confirmBtn = new SustainopolyButton("Confirm");
		confirmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player.takeChargeOfTask(game.getCurrentPlayer().getPosition());
				game.updateGUI(); // update playerPanel
				controlsPanel.setTakeChargeEnabled(false); // can't take charge of same task again
				controlsPanel.setTakeChargeBtnToolTip("This task is already managed");
				controlsPanel.setOfferTaskEnabled(false); // can't offer it to another player as not available
				controlsPanel.setOfferTaskBtnToolTip("This task is already managed");
				controlsPanel.setFormAllianceEnabled(true); // if couldn't form an alliance before (no tasks), can now
				setUpConfirmedPanel(controlsPanel, player);
				switchPanels(confirmedPanel);
			}
		});
		confirmBtn.setBounds(438, 330, 200, 60);
		infoPanel.add(confirmBtn);
		
		// cancel button- disposes frame
		SustainopolyButton cancelBtn = new SustainopolyButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelBtn.setBounds(660, 330, 200, 60);
		infoPanel.add(cancelBtn);

		// add infoPanel to overall taskPanel wrapper
		taskPanel.add(infoPanel);
		
		// add overall taskPanel to JLayeredPane
		layeredPane.add(taskPanel);
	}
	
	private void setUpConfirmedPanel(ControlsPanel controlsPanel, Player player) {
		
		int taskPlayer = game.getPlayerIndex(player);
		Square currentSquare = game.getCurrentPlayer().getPosition();
		
		// new panel with details of confirmation that task is now owned
		confirmedPanel = new JPanel();
		confirmedPanel.setBackground(new Color(0xF5F2D0));
		confirmedPanel.setLayout(null);
		
		// header panel
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(null);
		headerPanel.setBounds(0, 0, 900, 150);
		headerPanel.setBackground(new Color(125, 219, 67));
		headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK)); // create border on bottom

		Color daColour = ((TaskSquare) currentSquare).getDevelopmentArea().getColour();
		String daRGBString = "rgb(" + daColour.getRed() + ", " + daColour.getGreen() + ", " + daColour.getBlue() + ")";
		Color playerColour = player.getColour();
		String playerRGBString = "rgb(" + playerColour.getRed() + ", " + playerColour.getGreen() + ", " + playerColour.getBlue()
				+ ")";
		JLabel confirmedLabel = new JLabel("<html><b><span style='color: " + daRGBString + "'>"
				+ currentSquare.getName() + "</span></b> has been taken charge of by <span style='color: "
				+ playerRGBString + "'>" + player.getName() + "</html>");
		confirmedLabel.setBounds(10, 10, 860, 130);
		confirmedLabel.setFont(new FontTwo(50));
		headerPanel.add(confirmedLabel);

		// add header panel to wrapper panel
		confirmedPanel.add(headerPanel);

		// info panel which will hold TaskSquare details
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(null);
		infoPanel.setBounds(0, 150, 900, 450);
		infoPanel.setBackground(new Color(0xF5F2D0));
		
		// displays the amount of money that has been deducted of Player
		JLabel confirmedLabel2 = new JLabel("Money deducted: $" + ((TaskSquare)currentSquare).getPrice());
		confirmedLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		confirmedLabel2.setBounds(20, 50, 860 , 45);
		confirmedLabel2.setFont(new FontOne(34));
		infoPanel.add(confirmedLabel2);

		// displays players square timeout
		JLabel confirmedLabel3 = new JLabel( "New square timeout: " + game.getPlayer(taskPlayer).getSquareTimeout());
		confirmedLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		confirmedLabel3.setBounds(20, 130, 860, 45);
		confirmedLabel3.setFont(new FontOne(34));
		infoPanel.add(confirmedLabel3);


		/**
		 *OK button - disposes frame
		 */
		SustainopolyButton okBtn2 = new SustainopolyButton("OK");
		okBtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		okBtn2.setBounds(350, 330, 200, 60);
		infoPanel.add(okBtn2);
		
		// add infoPanel2 to overall confirmedPanel wrapper
		confirmedPanel.add(infoPanel);
		
		// add overall taskPanel to JLayeredPane
		layeredPane.add(confirmedPanel);
	}
}
