package sustainopoly;

public abstract class Card {
	
	/**
	 * Get message that will be associated with the Chance card
	 * @return a String containing the card message, will be output to user when card is drawn
	 */
	public abstract String getMessage();
	
	/**
	 * Apply appropriate consequences of the Chance card e.g money gain/loss
	 */
	public abstract void applyAction();
}
