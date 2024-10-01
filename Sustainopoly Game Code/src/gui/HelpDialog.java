package gui;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JScrollBar;

public class HelpDialog extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public HelpDialog() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(880, 650);
		setTitle("How To Play");
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(1, 186, 239));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// cancel button- disposes of frame
		SustainopolyButton closeButton = new SustainopolyButton("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JLabel rules = new JLabel();
		ImageIcon mainImage = new ImageIcon("images/Rules.png");
		rules.setIcon(new ImageIcon(mainImage.getImage().getScaledInstance(855, 608, Image.SCALE_SMOOTH)));
		rules.setBounds(0, 0, 855, 608);
		add(rules);
		
		setLocationRelativeTo(null); // make frame appear in centre of screen
		setVisible(true);
	}
}


