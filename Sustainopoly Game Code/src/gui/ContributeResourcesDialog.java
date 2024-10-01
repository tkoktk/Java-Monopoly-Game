package gui;

import sustainopoly.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class ContributeResourcesDialog extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JLayeredPane layeredPane;
	private JPanel confirmPanel;
	
	// game
	private static SustainopolyGame game = SustainopolyGame.getInstance();
	private Font standardFont = new FontOne(24);
	private Player player;
	private TaskSquare currentSquare;
	private Player owner;
	
	//RGB Colour Strings
	
	private String playerRGBString;
	private String ownerRGBString;
	private String daRGBString;
	
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

	public ContributeResourcesDialog() {
		//Player-related variables
		player = SustainopolyGame.getInstance().getCurrentPlayer();
		currentSquare = (TaskSquare)(player.getPosition());
		owner = currentSquare.getOwner();
		//Color RGB Strings
		Color daColour = currentSquare.getDevelopmentArea().getColour();
		daRGBString = "rgb(" + daColour.getRed() + ", " + daColour.getGreen() + ", " + daColour.getBlue() + ")";
		
		Color playerColour = player.getColour();
		playerRGBString = "rgb(" + playerColour.getRed() + ", " + playerColour.getGreen() + ", "
				+ playerColour.getBlue() + ")";
		
		Color ownerColour = owner.getColour();
		ownerRGBString = "rgb(" + ownerColour.getRed() + ", " + ownerColour.getGreen() + ", "
				+ ownerColour.getBlue() + ")";

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Contribute Resources");
		setSize(800, 400);
		setResizable(false);
		setIconImage(new ImageIcon("images/govanIcon.png").getImage());
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 800, 400);
		add(layeredPane);
		
		setUpOfferPanel();
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void setUpOfferPanel() {
		
		JPanel offerPanel = new JPanel();
		offerPanel.setBounds(0, 0, 800, 400);
		offerPanel.setLayout(null);
		
		// header panel
		JPanel headerPanel = new JPanel();
		headerPanel.setBounds(0, 0, 800, 100);
		headerPanel.setLayout(null);
		headerPanel.setBackground(new Color(125, 219, 67));
		headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK)); // create border on bottom

		// label inside headerPanel
		JLabel headerLabel = new JLabel("Offer Contribution");
		headerLabel.setBounds(10, 10, 800, 70);
		headerLabel.setFont(new FontTwo(50));
		headerPanel.add(headerLabel);

		// add headerPanel to overall frame
		offerPanel.add(headerPanel);

		// offer panel containing the buttons to select player
		JPanel contributionPanel = new JPanel();
		contributionPanel.setLayout(null);
		contributionPanel.setBounds(0, 100, 800, 300);
		contributionPanel.setBackground(new Color(0xF5F2D0));

		JLabel firstLabel = new JLabel("<html><p><span style='color: " + daRGBString + "'>" + currentSquare.getName()
				+ "</span> is currently managed by <span style='color: " + ownerRGBString + "'>" + owner.getName()
				+ ".</span></p></html>");
		firstLabel.setBounds(10, 10, 775, 30);
		firstLabel.setFont(standardFont);
		contributionPanel.add(firstLabel);

		JLabel secondLabel = new JLabel(
				"<html>Would you like to contribute to assist them in carrying out their next task step? "
						+ "You would gain 5 expertise for your collaborative efforts. <br>"
						+ "Based on the current demands of their work, the suggested contribution is $"
						+ currentSquare.getExpectedContribution() + ". ");
		secondLabel.setBounds(10, 50, 775, 140);
		secondLabel.setFont(standardFont);
		secondLabel.setVerticalAlignment(JLabel.TOP);
		contributionPanel.add(secondLabel);

		if (player.getNextContributionFree())
			secondLabel.setText(secondLabel.getText()
					+ "<br>However, thanks to your next contribution free effect, you will get the cost of this contribution covered!<html>");
		else if (player.getNextContributionCostsDouble())
			secondLabel.setText(secondLabel.getText()
					+ "<br>However, due to your next contribution costs double effect, you are expected to provide extra support, and the payment would cost double...<html>");
		else
			secondLabel.setText(secondLabel.getText() + "</html>");

		SustainopolyButton yesBtn = new SustainopolyButton("Yes");
		yesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (player.getNextContributionCostsDouble())
					setUpConfirmPanel(currentSquare.getExpectedContribution() * 2);
				else
					setUpConfirmPanel(currentSquare.getExpectedContribution());
				switchPanels(confirmPanel);
			}
		});
		yesBtn.setBounds(200, 200, 150, 50);

		// if the player can't afford to pay the contribution, disable the button
		if ((player.getMoney() - currentSquare.getExpectedContribution() <= 0) || (player.getNextStepCostsDouble()
				&& player.getMoney() - 2 * currentSquare.getExpectedContribution() <= 0)) {
			yesBtn.setEnabled(false);
			yesBtn.setToolTipText("You don't have enough money to make this contribution!");
		}
		contributionPanel.add(yesBtn);

		SustainopolyButton noBtn = new SustainopolyButton("No");
		noBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		noBtn.setBounds(450, 200, 150, 50);
		contributionPanel.add(noBtn);

		// add contributionPanel to overall frame
		offerPanel.add(contributionPanel);
		
		layeredPane.add(offerPanel);
	}
	
	private void setUpConfirmPanel(int amount) {
		
		confirmPanel = new JPanel();
		confirmPanel.setBounds(0, 0, 800, 400);
		confirmPanel.setLayout(null);
		
		JPanel headerPanel = new JPanel();
		headerPanel.setBounds(0, 0, 800, 100);
		headerPanel.setLayout(null);
		headerPanel.setBackground(new Color(125, 219, 67));
		headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK)); // create border on bottom
		
		// label inside headerPanel
		JLabel headerLabel = new JLabel("Offer Received");
		headerLabel.setBounds(10, 10, 800, 70);
		headerLabel.setFont(new FontTwo(50));
		headerPanel.add(headerLabel);
		
		confirmPanel.add(headerPanel);
		
		JPanel offerPanel = new JPanel();
		offerPanel.setBounds(0, 100, 800, 300);
		offerPanel.setBackground(new Color(0xF5F2D0));
		offerPanel.setLayout(null);
		
		JLabel messageLabel = new JLabel("<html><span style='color: " + ownerRGBString + "'>" + owner.getName() + ",</span><span style='color: " + playerRGBString + "'> " + 
		player.getName() + "</span> has offered you a contribution.<br><br>"
				+ "Contribution Amount: $" + amount + "<br>"
				+ "<span style='color: " + playerRGBString + "'>" + player.getName() + "'s " + "</span>" + "money: $" + player.getMoney() + "<br><br>"
				+ "Do you accept?</html>");
		messageLabel.setFont(new FontOne(25));
		messageLabel.setBounds(20, 20, 740, 150);
		offerPanel.add(messageLabel);
		
		SustainopolyButton yesBtn = new SustainopolyButton("Yes");
		yesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player.payContribution(owner, amount);
				game.updateGUI();	// update controlsPanel
				dispose();
			}
		});
		yesBtn.setBounds(450, 200, 125, 50);
		offerPanel.add(yesBtn);
		
		SustainopolyButton noBtn = new SustainopolyButton("No");
		noBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				JOptionPane.showMessageDialog(null,
						owner.getName() + " declined the contribution offer from " + player.getName(), "Offer Declined",
						JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/issue.png"));
			}
		});
		noBtn.setBounds(600, 200, 125, 50);
		offerPanel.add(noBtn);
		
		confirmPanel.add(offerPanel);
		
		layeredPane.add(confirmPanel);
	}

}