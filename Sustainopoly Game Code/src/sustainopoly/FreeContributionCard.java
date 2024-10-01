package sustainopoly;

public class FreeContributionCard extends Card {
	private String message;		// message displayed when card is drawn

	/**
	 * Creates a FreeContributionCard, with associated message detailing reason for this benefit
	 * @param message Explanation message to player
	 */
	public FreeContributionCard(String message) {
		this.message = message;
	}
	
	/**
	 * Returns a message explaining a reason to the player for being granted a free contribution
	 * @return Explanation message to player
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * When a player draws a FreeContributionCard, their next contribution (landing on someone else's task) will be free
	 */
	public void applyAction() {
		Player currentPlayer = SustainopolyGame.getInstance().getCurrentPlayer();
		currentPlayer.setNextContributionFree(true);
	}
	
	
}
