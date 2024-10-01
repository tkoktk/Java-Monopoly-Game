package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

import sustainopoly.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
public class GiveMoneyDialog extends JFrame {
	
	
	private static SustainopolyGame game = SustainopolyGame.getInstance();

    // Labels for displaying information
    private JLabel amountLabel;
    private JTextField amountField;
    private JButton offerButton;
    private int offeredAmount;
    private JPanel contentPane;
	JLabel devAreaLabel;
	private JLabel title;
	
	private Font standardFont = new FontOne(20);
	
	class PlayerPanel extends JPanel implements MouseListener {
		
		private int playerIndex;
		
		public PlayerPanel(int i) {
			
			playerIndex = i;
			
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			// Space between elements:
			constraints.insets = new Insets(15, 15, 1, 15);
			
			Color playerColour = game.getPlayer(i).getColour();
			
			setBackground(playerColour);
			setLayout(new GridBagLayout());

			// Add the image to the panel
			// JLabel portraitLabel = new JLabel(portrait[i]);
			//JLabel portraitLabel = new JLabel(game.getPlayer(i).getPortrait());
			JLabel portraitLabel = new JLabel();
			// Maximum Size needs decided

			ImageIcon icon = game.getPlayer(i).getPortrait(100, 100);
			portraitLabel.setIcon(icon);

			// Custom Player Colour Border
			portraitLabel.setBorder(new LineBorder(Color.WHITE, 6));
			constraints.gridy = 0; // Puts the images appearing first

			add(portraitLabel, constraints);

			// Player Username labels

			// Set Label text + colour using player class variables
			//This label text's content is the player's name + their money
			JLabel nameLabel = new JLabel(
					"<html>" + game.getPlayer(i).getName() + "<br>Money: $" + game.getPlayer(i).getMoney() + "</html>");
			nameLabel.setFont(standardFont);
			nameLabel.setForeground(Color.WHITE);
			
			constraints.gridy = 1;
			constraints.anchor = GridBagConstraints.CENTER;
			constraints.insets = new Insets(0, 0, 1, 0);
			add(nameLabel, constraints);
			
			if (i != game.getCurrentPlayerIndex())
				addMouseListener(this);
			else
				setBackground(playerColour.darker());
		}
		

		@Override
		public void mouseClicked(MouseEvent e) {
			offerMoney(game.getPlayer(playerIndex));
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			setBackground(game.getPlayer(playerIndex).getColour().brighter());
		}

		@Override
		public void mouseExited(MouseEvent e) {
			setBackground(game.getPlayer(playerIndex).getColour());
		}
	}
    
    /**
     * Create the dialog.
     */
    public GiveMoneyDialog() {
    	
    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Give Money");
		setSize(800, 400);
		setResizable(false);
		setIconImage(new ImageIcon("images/govanIcon.png").getImage());
		
		// ContentPane: All other elements are inside of this
		contentPane = new JPanel();
		//contentPane.setBackground(new Color(91, 87, 80));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		// Title
		JPanel titlePane = new JPanel();
		titlePane.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		titlePane.setBackground(new Color(125, 219, 67));
		titlePane.setLayout(new FlowLayout());
		title = new JLabel("Give Money");
		title.setFont(new FontTwo(50));
		
		titlePane.add(title);
		contentPane.add(titlePane);

		// the JPanel "mainPane" contains all the Player images, names etc.
		// This panel will use a GridBagLayout as it allows us to organise the elements
		// in rows and columns easily
		JPanel mainPane = new JPanel();
		mainPane.setBackground(new Color(0xF5F2D0));
		mainPane.setLayout(new GridBagLayout());
		// The constraint x dictates an element's position horizontally,
		// constraint y dictates its position vertically.
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		// Space between elements:
		constraints.insets = new Insets(15, 15, 1, 15);

		// First Panel is a larger panel holding all the portraits
		JPanel portraitPanel = new JPanel();
		portraitPanel.setBackground(new Color(0xF5F2D0));
		portraitPanel.setLayout(new GridBagLayout());
		// Two rows this way

		for (int row = 0; row < 2; row++) {
			for (int col = 0; col < 4; col++) {
				// Goes row 0: Col 1, row 0: col 2 etc so thought this is the best way to access
				// the player

				int i = row * 4 + col;

				// This panel is for an individual portrait and the player's name under,
				// iterated 6 times
				PlayerPanel playerPanel = new PlayerPanel(i);

				if(row == 0) {
					constraints.insets.bottom = 4;
					constraints.insets.top = 8;
				}
				if(row == 1) {
					constraints.insets.bottom = 8;
					constraints.insets.top = 4;
				}
				constraints.insets.right = 8;
				constraints.insets.left = 8;
				
				constraints.gridx = col;
				constraints.gridy = row;
				portraitPanel.add(playerPanel, constraints);
			}
			mainPane.add(portraitPanel, constraints);
		}
		
		mainPane.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		contentPane.add(mainPane);
		setContentPane(contentPane);
		
		
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
    }
    
	private void offerMoney(Player player) {

		JPanel offerPanel = new JPanel();
		offerPanel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK));
		offerPanel.setBackground(new Color(125, 219, 67));
		offerPanel.setLayout(new BoxLayout(offerPanel, BoxLayout.Y_AXIS));
		Color playerColour = player.getColour();
		String playerRGBString = "rgb(" + playerColour.getRed() + ", " + playerColour.getGreen() + ", " + playerColour.getBlue()+ ")";
		amountLabel = new JLabel("<html>Enter the amount to be offered to <span style='color: " + playerRGBString + "'>" 
		+ player.getName() + "</span>"+ "<br>(maximum 25% of your money)</html>");
		amountLabel.setHorizontalAlignment(JLabel.CENTER);
		amountLabel.setFont(standardFont);
		amountLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

		amountField = new JTextField();
		amountField.setHorizontalAlignment(JTextField.CENTER);

		offerButton = new SleekButton("Offer");
		offerButton.setAlignmentX(JButton.CENTER_ALIGNMENT);

		offerButton.addActionListener(e -> {
			try {
				offeredAmount = Integer.parseInt(amountField.getText());

				// check if the player has sufficient funds (maximum custom amount is 50% of a player's money) before offering the money
				if (offeredAmount <= 0) {
					JOptionPane.showMessageDialog(this, "Please enter a valid amount.");
				} else if ((game.getCurrentPlayer().getMoney() ) >= offeredAmount) {
					game.getCurrentPlayer().setMoney(game.getCurrentPlayer().getMoney() - offeredAmount);
					//player.setMoney(offeredAmount + player.getMoney());
					JOptionPane.showMessageDialog(this, "$" + offeredAmount + " has been transferred!");
					game.updateGUI();
					dispose();
				} else if (game.getCurrentPlayer().getMoney() / 4 < offeredAmount
						&& game.getCurrentPlayer().getMoney() >= offeredAmount) {
					JOptionPane.showMessageDialog(this, "You can only give up to 25% of your money!");
				} else if (game.getCurrentPlayer().getMoney() < offeredAmount) {
					JOptionPane.showMessageDialog(this, "Insufficient funds. Please try again!");
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "Please enter a valid amount.");
			}
		});
		//Add all components with spacing to offerPanel
		offerPanel.add(Box.createVerticalStrut(2));
		offerPanel.add(amountLabel);
		offerPanel.add(Box.createVerticalStrut(2));
		offerPanel.add(amountField);
		offerPanel.add(Box.createVerticalStrut(4));
		offerPanel.add(offerButton);
		offerPanel.add(Box.createVerticalStrut(2));
		
		//Add offerPanel to contentPane
		getContentPane().add(offerPanel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}