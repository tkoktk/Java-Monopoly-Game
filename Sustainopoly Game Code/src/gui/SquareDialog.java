package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import gui.BoardPanel.AnimationEvent;
import sustainopoly.Player;
import sustainopoly.SustainopolyGame;
import sustainopoly.TaskSquare;

public class SquareDialog extends JDialog {
	
	SustainopolyButton moveBtn;

	public SquareDialog(TaskSquare ts, MouseEvent e, boolean turnTaken) {
		setUndecorated(true);
		
		JPanel contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(0xF5F2D0));
		contentPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 2), BorderFactory.createTitledBorder(ts.getName())));
		setContentPane(contentPane);
		
		// if somewhere else on screen is clicked, get rid of the dialog
		addWindowFocusListener(new WindowFocusListener() {            
		    public void windowLostFocus(WindowEvent e) {
		        dispose();
		    }            
		    public void windowGainedFocus(WindowEvent e) {
		    }
		});
		
		// if click is on lower half of screen, have dialog be formed above it; if on upper half of screen, half dialog be formed below it
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		if (e.getYOnScreen() > screenSize.getHeight() / 2) 
			setBounds(e.getXOnScreen(), e.getYOnScreen() - 350, 400, 350);
		else
			setBounds(e.getXOnScreen(), e.getYOnScreen(), 400, 350);
		
		Font standardFont = new FontOne(16);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setOpaque(false);
		infoPanel.setBounds(14, 20, 366, 290);
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		add(infoPanel);
		
		JLabel descriptionLabel = new JLabel("<html>" + ts.getDescription() + "</html>");
		descriptionLabel.setFont(standardFont);
		infoPanel.add(descriptionLabel);
		
		String stepsContent = "<html><body><ol>";
		for (String step : ts.getSteps()) {
			stepsContent += "<li>" + step + "</li>";
		}
		stepsContent += "</ol></body></html>";
		JLabel stepsLabel = new JLabel(stepsContent);
		stepsLabel.setFont(standardFont);
		infoPanel.add(stepsLabel);
		
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(null);
		btnPanel.setOpaque(false);
		btnPanel.setBounds(0, 310, 400, 40);
		btnPanel.setBackground(Color.RED);
		add(btnPanel);
		
		moveBtn = new SustainopolyButton("Move");
		moveBtn.setBounds(335, 0, 50, 25);
		moveBtn.setFont(new FontOne(18));
		moveBtn.setBorder(new LineBorder(Color.BLACK, 1));
		
		moveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Player currentPlayer = SustainopolyGame.getInstance().getCurrentPlayer();
				int currentPlayerIndex = SustainopolyGame.getInstance().getCurrentPlayerIndex();
				
				int thisSquareIndex = SustainopolyGame.getInstance().getGameBoard().getSquareIndex(ts);
				int playerSquareIndex = SustainopolyGame.getInstance().getGameBoard().getSquareIndex(currentPlayer.getPosition());
				
				// get how many squares need to be moved to go from current to selected square
				int differenceInNumSquares = thisSquareIndex - playerSquareIndex;
				
				// destination square may have an index behind current square in GameBoard 'squares' ArrayList, so if 
				// get negative value, add (really subtract as negative number) to total number of squares to get how many
				// to move
				if (differenceInNumSquares < 0)
					differenceInNumSquares = SustainopolyGame.getInstance().getGameBoard().getNumSquares() + differenceInNumSquares;
				
				SustainopolyGame.getInstance().movePlayer(currentPlayerIndex, differenceInNumSquares, null);
				SustainopolyGame.getInstance().setGUIAfterTurnTaken();
				
				moveBtn.setEnabled(false);
				moveBtn.setToolTipText("You have already moved");
				dispose();
			}
		});
		btnPanel.add(moveBtn);
		
		// if Player has insufficient expertise, disable button
		if (SustainopolyGame.getInstance().getCurrentPlayer().getExpertise() < 50) {
			moveBtn.setEnabled(false);
			moveBtn.setToolTipText("Insufficient expertise to choose move position. Required XP: 50");
		} else if (turnTaken) {
			moveBtn.setEnabled(false);
			moveBtn.setToolTipText("You have already moved");
		}
		
		setVisible(true);
	}
}
