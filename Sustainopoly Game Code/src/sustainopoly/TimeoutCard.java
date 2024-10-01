package sustainopoly;

public class TimeoutCard extends Card {
	private int numSquares;		// number of squares the player won't be able to complete a task step for
	private String message;		// message displayed when card is drawn
	
	/**
	 * Creates a TimeoutCard, with associated message detailing reason for this negative consequence and value for how many squares they are out of action
	 * @param message Explanation message to player
	 * @param numSquares Number of squares the player will be unable to complete task steps for
	 */
	public TimeoutCard(String message, int numSquares) {
		this.message = message;
		this.numSquares = numSquares;
	}
	
	/**
	 * Returns a message explaining a reason to the player for the gain/loss
	 * @return Explanation message to player
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * When a player draws a TimeoutCard, they will not be able to complete task steps for a specified number of squares
	 */
	public void applyAction() {
		Player currentPlayer = SustainopolyGame.getInstance().getCurrentPlayer();
		currentPlayer.setSquareTimeout(currentPlayer.getSquareTimeout() + numSquares);
	}

}
