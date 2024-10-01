package sustainopoly;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class GoVanSquare extends Square {
	
	/**
	 * Creates a GoVan square with name "GoVan"
	 */
	public GoVanSquare() {
		super.setName("GoVan");
	}
	
	/**
	 * Upon passing or landing on the GoVan square, the player receives a set monetary benefit, depending on their role
	 */
	public void playAction() {
		Player currentPlayer = SustainopolyGame.getInstance().getCurrentPlayer();
		Role playerRole = currentPlayer.getRole();
		int moneyBonus = 0;
		
		// different amount depending on player's role...
		switch (playerRole) {
		case ASYLUM_SEEKER:
			moneyBonus = 100;
			break;
		case GOVAN_LOCAL:
			moneyBonus = 400;
			break;
		case VOLUNTEER:
			moneyBonus = 500;
			break;
		case COMMUNITY_LEADER:
			moneyBonus = 750;
			break;
		case COUNCILLOR:
			moneyBonus = 1000;
			break;
		case ORGANISATION:
			moneyBonus = 1250;
			break;
		}
		
		currentPlayer.setMoney(currentPlayer.getMoney() + moneyBonus);
		JOptionPane.showMessageDialog(null, "You have gained $" + moneyBonus + " for passing the GoVan square!", "GoVan",
				JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/community_betterment.png"));
	}
}
