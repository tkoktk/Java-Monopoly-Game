package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import sustainopoly.Square;
import sustainopoly.SustainopolyGame;
import sustainopoly.TaskSquare;

public class GUISquare extends JPanel implements MouseListener {
	
	//This serial ID just gets rid of an error
	private static final long serialVersionUID = 1L;
	
	private Square square;	// Square instance represented by this GUISquare
	private JLabel[] playerIcons = new JLabel[SustainopolyGame.getInstance().getNumPlayers()];	// JLabel array holding Player sprites
	
	SquareDialog squareDialog;		// SquareDialog popup for when GUISquare is clicked
	boolean turnTaken = false;

	public GUISquare(Square square, int rotation) {
		this.square = square;	// Square which this GUISquare is representing
		setPreferredSize(new Dimension(64, 64));	// preferred dimensions
		setBorder(new LineBorder(Color.BLACK));		// plain black border
		
		addMouseListener(this);
		
		// create an 8x8 layout within the GUISquare which will be used to lay out different panels
		GridBagLayout gbl_guiSquare = new GridBagLayout();
		gbl_guiSquare.rowWeights = new double[]{0.125,0.125,0.125,0.125,0.125,0.125,0.125,0.125};
		gbl_guiSquare.columnWeights = new double[]{0.125,0.125,0.125,0.125,0.125,0.125,0.125,0.125};
		setLayout(gbl_guiSquare);
		
		// GridBagConstraints variables for each of the 3 panels- will set their size/ layout
		GridBagConstraints gbc_colourPanel = new GridBagConstraints();
		GridBagConstraints gbc_namePanel = new GridBagConstraints();
		GridBagConstraints gbc_playerPanel = new GridBagConstraints();
		gbc_colourPanel.fill = GridBagConstraints.BOTH; gbc_namePanel.fill = GridBagConstraints.NONE; gbc_playerPanel.fill = GridBagConstraints.BOTH;
		
		// colour tab of the Square
		JPanel colourPanel = new JPanel();
		
		// if a TaskSquare, set colour tab to the DevelopmentArea's associated colour
		if (square instanceof TaskSquare) {
			TaskSquare ts = (TaskSquare)square;
			colourPanel.setBackground(ts.getDevelopmentArea().getColour());
		}
		
		// creates the nameLabel to be used for bottom and/or top panel GUISquares (normal/upside down)
		JLabel nameLabel = null;
		
		// creates a nameLabel to be used for left/right panel GUISquares (rotated left/right)
		JHorizontalRotateLabel rotatedNamePanel = null;
		
		// hold icons of Players currently situated on the Square
		JPanel playerPanel = new JPanel();
		
		// sets layout of different panels within GUISquare depending on orientation of Square
		switch(rotation) {
		// bottom panel- no rotation
		case 0:				
			// colourPanel
			gbc_colourPanel.gridx = 0;
			gbc_colourPanel.gridy = 0;
			gbc_colourPanel.gridheight = 1;
			gbc_colourPanel.gridwidth = 8;
			colourPanel.setPreferredSize(new Dimension(64, 12));
			
			// nameLabel
			gbc_namePanel.gridx = 0;
			gbc_namePanel.gridy = 1;
			gbc_namePanel.gridheight = 1;
			gbc_namePanel.gridwidth = 8;
			nameLabel = new JLabel(square.getName(), SwingConstants.CENTER);
			nameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 7));
			
			// playerPanel
			playerPanel.setLayout(new GridLayout(2,4));
			gbc_playerPanel.gridx = 0;
			gbc_playerPanel.gridy = 2;
			gbc_playerPanel.gridheight = 6;
			gbc_playerPanel.gridwidth = 8;
			break;
			
		// left panel- 90 degree rotation
		case 90:
			// colourPanel
			gbc_colourPanel.gridx = 7;
			gbc_colourPanel.gridy = 0;
			gbc_colourPanel.gridheight = 8;
			gbc_colourPanel.gridwidth = 1;
			colourPanel.setPreferredSize(new Dimension(12, 64));
			
			// nameLabel
			gbc_namePanel.gridx = 6;
			gbc_namePanel.gridy = 0;
			gbc_namePanel.gridheight = 8;
			gbc_namePanel.gridwidth = 1;
			rotatedNamePanel = new JHorizontalRotateLabel(square.getName(), true);
			
			// playerPanel
			playerPanel.setLayout(new GridLayout(4,2));	
			gbc_playerPanel.gridx = 0;
			gbc_playerPanel.gridy = 0;
			gbc_playerPanel.gridheight = 8;
			gbc_playerPanel.gridwidth = 6;
			break;

		// right panel- -90 degree rotation
		case -90:
			// colourPanel
			gbc_colourPanel.gridx = 0;
			gbc_colourPanel.gridy = 0;
			gbc_colourPanel.gridheight = 8;
			gbc_colourPanel.gridwidth = 1;
			colourPanel.setPreferredSize(new Dimension(12, 64));
			
			// nameLabel
			gbc_namePanel.gridx = 1;
			gbc_namePanel.gridy = 0;
			gbc_namePanel.gridheight = 8;
			gbc_namePanel.gridwidth = 1;
			rotatedNamePanel = new JHorizontalRotateLabel(square.getName(), false);
//			rotatedNamePanel.setPreferredSize(new Dimension(8, 64));
			
			// playerPanel
			playerPanel.setLayout(new GridLayout(4,2));
			gbc_playerPanel.gridx = 2;
			gbc_playerPanel.gridy = 0;
			gbc_playerPanel.gridheight = 8;
			gbc_playerPanel.gridwidth = 6;
			break;
		
		// top panel- 180 degree rotation
		case 180:
			// colourPanel
			gbc_colourPanel.gridx = 0;
			gbc_colourPanel.gridy = 7;
			gbc_colourPanel.gridheight = 1;
			gbc_colourPanel.gridwidth = 8;
			colourPanel.setPreferredSize(new Dimension(64, 12));
			
			// nameLabel
			gbc_namePanel.gridx = 0;
			gbc_namePanel.gridy = 6;
			gbc_namePanel.gridheight = 1;
			gbc_namePanel.gridwidth = 8;
			nameLabel = new JLabel(square.getName()) {
				
				protected void paintComponent(Graphics g) {
					Graphics2D g2 = (Graphics2D)g;
					g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					AffineTransform aT = g2.getTransform();
					Shape oldshape = g2.getClip();
					double x = getWidth()/2.0;
					double y = getHeight()/2.0;
					aT.rotate(Math.toRadians(180), x, y);
					g2.setTransform(aT);
					g2.setClip(oldshape);
					super.paintComponent(g);
					setFont(new Font("Lucida Grande", Font.PLAIN, 7));
					setHorizontalAlignment(SwingConstants.CENTER);
				}
			};
			
			// playerPanel
			playerPanel.setLayout(new GridLayout(2,4));
			gbc_playerPanel.gridx = 0;
			gbc_playerPanel.gridy = 0;
			gbc_playerPanel.gridheight = 6;
			gbc_playerPanel.gridwidth = 8;
			break;
		}
		
		add(colourPanel, gbc_colourPanel);	// add colour tab to GUISquare
		
		createPlayerIcons(playerPanel);		// initialise JLabels holding ImageIcons to be displayed within playerPanel
		add(playerPanel, gbc_playerPanel);	// add player panel to GUISquare
		
		// add appropriate namePanel to GUISquare depending on rotation
		if (rotation == 90 || rotation == -90)
			add(rotatedNamePanel, gbc_namePanel);
		else
			add(nameLabel, gbc_namePanel);
		
	}
	
	/**
	 * Returns the Square object represented by this GUISquare
	 * @return Square instance
	 */
	public Square getSquare() {
		return this.square;
	}
	
	/**
	 * Initialises JLabel objects within the playerIcons array which will be used to hold player sprites
	 * @param pnlPlayer JPanel which will display the JLabels of the playerIcons array
	 */
    private void createPlayerIcons(JPanel playerPanel) {
		for (int i = 0; i < SustainopolyGame.getInstance().getNumPlayers(); i++) {
			playerIcons[i] = new JLabel();
			playerPanel.add(playerIcons[i]);
		}
	}

	/**
	 * Add a Player's sprite to the playerPanel part of the GUISquare
	 * @param index The index of the Player instance within the 'players' array of SustainopolyGame to be added to the GUISquare
	 */
	public void addPlayer(int index) {
		ImageIcon playerSprite = SustainopolyGame.getInstance().getPlayer(index).getSprite(10, 10);
		playerIcons[index].setIcon(playerSprite);
		
	}
	
	/**
	 * Remove a Player's sprite from the playerPanel part of the GUISquare
	 * @param index The index of the Player instance within the 'players' array of SustainopolyGame to be removed from the GUISquare
	 */
	public void removePlayer(int index) {
		playerIcons[index].setIcon(null);
        this.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		// only be able to show pop-up for TaskSquares
		if (!(this.square instanceof TaskSquare))
			return;
		
		TaskSquare ts = (TaskSquare)square;
		squareDialog = new SquareDialog(ts, e, turnTaken);
	}
	
	public void setTurnTakenFlag(boolean turnTaken) {
		this.turnTaken = turnTaken;
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
