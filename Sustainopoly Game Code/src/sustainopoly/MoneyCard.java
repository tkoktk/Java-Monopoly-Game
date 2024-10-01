package sustainopoly;

public class MoneyCard extends Card {
	private int amount;		// money gained/lost from card
	private String message;		// message displayed when card is drawn
	
	/**
	 * Creates a MoneyCard, with associated message detailing reason for this benefit/loss and value for the amount of the gain/loss
	 * @param message Explanation message to player
	 * @param amount Amount of money gained/lost by player
	 */
	public MoneyCard(String message, int amount) {
		this.message = message;
		this.amount = amount;
	}

	/**
	 * Returns a message explaining a reason to the player for the gain/loss
	 * @return Explanation message to player
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * When a player draws a MoneyCard, they can either gain or lose money
	 */
	public void applyAction() {
		Player currentPlayer = SustainopolyGame.getInstance().getCurrentPlayer();
		currentPlayer.setMoney(currentPlayer.getMoney() + amount);
	}
}
