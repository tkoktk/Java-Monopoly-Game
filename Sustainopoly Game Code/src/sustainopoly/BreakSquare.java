package sustainopoly;

public class BreakSquare extends Square {

	/**
	 * Creates a Break square with name "Break"
	 */
	public BreakSquare() {
		setName("Break");
	}

	/**
	 * Nothing happens to user when they land on the Break square
	 */
	public void playAction() {
		return;
	}
}
