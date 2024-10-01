package sustainopoly;

public class Die {
	private static final int MAX = 6;		// maximum number that can be rolled- 6 sided die
	
	/**
	 * Rolls the die, returning a random value between 1 and 6
	 * @return Value of the dice roll
	 */
	public int roll() {
		return (int)(Math.random() * MAX) + 1;
	}
}
