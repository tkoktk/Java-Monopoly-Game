package gui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;



public class SleekButton extends JButton implements MouseListener {
	
	private static final long serialVersionUID = 1L;
	private String clickSound = "sounds/Click.wav";
	
    public SleekButton(String text) {
        super(text); // call the constructor of the JButton class
        //Font and Colour
		setBackground(new Color(0, 0, 0, 0));
        setOpaque(false);
		setFont(new FontTwo(24));
		setBorder(new LineBorder(new Color(91, 87, 80), 7));
    	setForeground(new Color(91, 87, 80));
        
        // ensures that the overridden MouseListener methods work
        addMouseListener(this);
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    	if (isEnabled()) {
    		setBorder(new LineBorder(new Color(255, 255, 255), 7));
            setForeground(Color.WHITE);
    	}
    }

    @Override
    public void mouseExited(MouseEvent e) {
    	if (isEnabled()) {
    		setBorder(new LineBorder(new Color(91, 87, 80), 7));
        	setForeground(new Color(91, 87, 80));
    	}
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    	//Play sound when button is clicked
    	//playSound();
    }
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    
    @Override
    public void setEnabled(boolean b) {
    	super.setEnabled(b);
    	if (b) {
    		setBorder(new LineBorder(new Color(91, 87, 80), 7));
        	setForeground(new Color(91, 87, 80));
    	} else {
    		setBorder(new LineBorder(Color.GRAY, 7));
    	}
    }
    
    //Play sound
    public void playSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(clickSound));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
}
