package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import sustainopoly.DevelopmentArea;
import sustainopoly.SustainopolyGame;
import sustainopoly.TaskSquare;



public class PlayerButton extends JButton implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static SustainopolyGame game = SustainopolyGame.getInstance();
	private int playerIndex;

	public PlayerButton(int playerIndex, TaskSquare ts, ControlsPanel controlsPanel) {

		super(game.getPlayer(playerIndex).getName());
		this.playerIndex = playerIndex;
		
		setHorizontalAlignment(SwingConstants.LEFT);
		setFont(new FontOne(20));
	    setBorder(new LineBorder(Color.BLACK, 5));
		setIcon(GuiUtils.getScaledIcon(game.getPlayer(playerIndex).getPortrait(), 50, 50));
		setForeground(Color.BLACK);
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setPreferredSize(new Dimension(400, 50));
        addMouseListener(this);
        setFocusable(false);
        
		if(game.getPlayer(playerIndex) == game.getCurrentPlayer()) {
			setEnabled(false);
			setToolTipText("Can't offer a task to yourself!");
		} else if (game.getPlayer(playerIndex).getMoney() <= ts.getPrice()) {
			setEnabled(false);
			setToolTipText("Player has insufficient money- Has: £" + game.getPlayer(playerIndex).getMoney() + ", Required: £" + ts.getPrice());
		} else if(game.getPlayer(playerIndex).getExpertise() < ts.getRequiredExpertise()) {
			setEnabled(false);
			setToolTipText("Player has insufficient expertise- Has: " + game.getPlayer(playerIndex).getExpertise() + ", Required: " + ts.getRequiredExpertise());
		} else {
			setEnabled(true);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
    	if (isEnabled())
    		setBackground(game.getPlayer(playerIndex).getColour().brighter());
	}

	@Override
	public void mouseExited(MouseEvent e) {
    	if (isEnabled())
    		setBackground(game.getPlayer(playerIndex).getColour());
	}
	
    @Override
    public void setEnabled(boolean b) {
    	super.setEnabled(b);
    	if (b) {
    		setForeground(Color.BLACK);
    		setBackground(game.getPlayer(playerIndex).getColour());
    	} else {
    		setBackground(game.getPlayer(playerIndex).getColour().darker());
    		setForeground(Color.GRAY);
    	}
    }
    
		
	}



