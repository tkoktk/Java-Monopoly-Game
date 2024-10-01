package sustainopoly;

public abstract class Square {
	private String name; // name of the Square

	/**
	 * Returns the name of the Square
	 * 
	 * @return Square name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name of the Square
	 * 
	 * @param name Square name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * General method for the functionality when a player lands on this Square
	 */
	public abstract void playAction();

	/**
	 * 
	 */
	public String toString() {
		return this.name;
	}
}
