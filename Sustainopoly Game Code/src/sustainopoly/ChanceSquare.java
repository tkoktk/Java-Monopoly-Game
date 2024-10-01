package sustainopoly;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class ChanceSquare extends Square {
	
	/**
	 * Creates a Chance card square with provided name
	 * @param name Name of the Chance card square, as there will be multiple e.g "ChanceSquare1"
	 */
	public ChanceSquare(String name) {
		setName(name);
	}

	/**
	 * Selects a Card from the GameBoard, displays a pop-up of the card to the Player, and applies its effect
	 */
	public void playAction() {
		
		Card card = SustainopolyGame.getInstance().getGameBoard().drawChanceCard();
		
		// code to display card on GUI
		JOptionPane.showMessageDialog(null, card.getMessage(), "Chance Square", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/chance.png"));
		
		card.applyAction();
	}

}
