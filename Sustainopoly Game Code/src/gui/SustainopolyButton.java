package gui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;



public class SustainopolyButton extends JButton implements MouseListener{
	
	private static final long serialVersionUID = 1L;
	private String clickSound = "sounds/Click.wav";
	
    public SustainopolyButton(String text) {
        super(text); // call the constructor of the JButton class
        //Font and Colour
        setFont(new FontOne(35));
        setForeground(Color.WHITE);
        setBackground(Color.RED);
        setBorder(new LineBorder(Color.BLACK, 5));
        //Size and Position
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setPreferredSize(new Dimension(400, 50));
        
        //Ensures that the overriden MouseListener methods work
        addMouseListener(this);
        
        setFocusable(false);
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    	if (isEnabled())
    		setBackground(Color.GREEN);
    }

    @Override
    public void mouseExited(MouseEvent e) {
    	if (isEnabled())
    		setBackground(Color.RED);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    
    @Override
    public void setEnabled(boolean b) {
    	super.setEnabled(b);
    	if (b) {
    		setForeground(Color.WHITE);
    		setBackground(Color.RED);
    	} else {
    		setBackground(new Color(0xFF7276));
    		setForeground(Color.GRAY);
    	}
    }
}