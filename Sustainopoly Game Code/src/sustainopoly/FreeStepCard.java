package sustainopoly;

public class FreeStepCard extends Card {
	private String message;		// message displayed when card is drawn
	
	/**
	 * Creates a FreeStepCard, with associated message detailing reason for this benefit
	 * @param message Explanation message to player
	 */
	public FreeStepCard(String message) {
		this.message = message;
	}
	
	/**
	 * Returns a message explaining a reason to the player for being granted a free task step
	 * @return Explanation message to player
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * When a player draws a FreeStepCard, the next step of one of their tasks that they complete will be free
	 */
	public void applyAction() {
		Player currentPlayer = SustainopolyGame.getInstance().getCurrentPlayer();
		currentPlayer.setNextStepFree(true);
	}

}
