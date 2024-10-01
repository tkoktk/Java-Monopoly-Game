package gui;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;						 
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import sustainopoly.*;

public class Overview extends JFrame {

	// This serial ID just gets rid of an error
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JLabel title;
	public static SustainopolyGame game = SustainopolyGame.getInstance();

	public Overview(SustainopolyGame game) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Player Overview");
		setIconImage(new ImageIcon("images/govanIcon.png").getImage());

		// ContentPane: All other elements are inside of this
		contentPane = new JPanel();
		//contentPane.setBackground(new Color(91, 87, 80));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		// Title
		JPanel titlePane = new JPanel();
		titlePane.setBackground(new Color(125, 219, 67));
		titlePane.setLayout(new FlowLayout());
		title = new JLabel("Player Overview");
		title.setFont(new FontTwo(50));
		title.setForeground(Color.WHITE);
		
		titlePane.add(title);
		contentPane.add(titlePane);

		// the JPanel "mainPane" contains all the Player images, names etc.
		// This panel will use a GridBagLayout as it allows us to organise the elements
		// in rows and columns easily
		JPanel mainPane = new JPanel();
		mainPane.setBackground(new Color(91, 87, 80));
		mainPane.setLayout(new GridBagLayout());
		// The constraint x dictates an element's position horizontally,
		// constraint y dictates its position vertically.
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		constraints.fill = GridBagConstraints.NONE;
		// Space between elements:
		constraints.insets = new Insets(15, 15, 1, 15);

		// First Panel is a larger panel holding all the portraits
		JPanel portraitPanel = new JPanel();
		portraitPanel.setLayout(new GridBagLayout());
		// Two rows this way

		for (int row = 0; row < 2; row++) {
			for (int col = 0; col < 4; col++) {
				// Goes row 0: Col 1, row 0: col 2 etc so thought this is the best way to access
				// the player

				int i = row * 4 + col;

				// Getting Player Colour here since it's used in a few elements
				Color playerColour = game.getPlayer(i).getColour();

				// This panel is for an individual portrait and the player's name under,
				// iterated 6 times
				JPanel playerPanel = new JPanel();
				playerPanel.setBackground(playerColour);
				playerPanel.setLayout(new GridBagLayout());

				// Add the image to the panel
				// JLabel portraitLabel = new JLabel(portrait[i]);
				//JLabel portraitLabel = new JLabel(game.getPlayer(i).getPortrait());
				JLabel portraitLabel = new JLabel();
				// Maximum Size needs decided

				ImageIcon icon = game.getPlayer(i).getPortrait(125, 125);
				portraitLabel.setIcon(icon);

				// Custom Player Colour Border
				portraitLabel.setBorder(new LineBorder(Color.WHITE, 10));
				constraints.gridy = 0; // Puts the images appearing first

				playerPanel.add(portraitLabel, constraints);

				// Player Username labels

				// Set Label text + colour using player class variables
				//This label text's content is the player's name + their money + their expertise
				JTextArea detailsLabel = new JTextArea(game.getPlayer(i).getName() + "\nMoney: $"+ game.getPlayer(i).getMoney() + "\nExpertise: " +
				game.getPlayer(i).getExpertise() + "\n" + game.getPlayer(i).getRole().toString());
				detailsLabel.setEditable(false);
				detailsLabel.setBackground(game.getPlayer(i).getColour());
				//detailsLabel.setLineWrap(true);
				//detailsLabel.setWrapStyleWord(true);
				detailsLabel.setFont(new FontOne(16));
				detailsLabel.setForeground(Color.WHITE);
				
				detailsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				
				// gridy = 1 puts it a level below the portraits
				constraints.gridy = 1;
				// Centres Label to be under
				constraints.anchor = GridBagConstraints.CENTER;
				//Add little bit of space below for there to be more colour for styling purposes
				constraints.insets = new Insets(0, 0, 1, 0);
				playerPanel.add(detailsLabel, constraints);
				
				
				

				if(row == 0) {//Set the Bottom inset to 7px for top-row components
					constraints.insets.bottom = 7;
					constraints.insets.top = 15;
				}
				if(row == 1) {//Set the bottom row inset to 15px for bottom-row components
					constraints.insets.bottom = 15;
					constraints.insets.top = 7;
				}
				constraints.insets.right = 15;
				constraints.insets.left = 15;
				
				//constraints.insets = new Insets(15, 15, 1, 15);
				constraints.gridx = col;
				constraints.gridy = row;
				portraitPanel.add(playerPanel, constraints);
			}
		}
		
		mainPane.add(portraitPanel, constraints);
		mainPane.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		contentPane.add(mainPane);

		/*
		 * Adding a button into a JFrame like this requires also adding JPanel of type
		 * FlowLayout so that the button can fit exactly in the horizontal centre of the
		 * screen, without disturbing the other elements.
		 */
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(new Color(125, 219, 67));
		buttonPane.setLayout(new FlowLayout());
		//Made custom button class SleekButton with styling and MouseListeners for hovering
		SleekButton startButton = new SleekButton("Start Game");
		startButton.setFont(new FontTwo(30));
		
		// Action Listeners for when Button is clicked on
		startButton.addActionListener(new ActionListener() {
			//When clicked, go to main game Frame
			public void actionPerformed(ActionEvent e) {
				dispose();
				game.setGUIVisible();
			}
			
		});
		
		buttonPane.add(startButton);
		contentPane.add(buttonPane);
		setContentPane(contentPane);

		pack();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}
}
