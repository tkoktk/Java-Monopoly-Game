package sustainopoly;

import java.util.ArrayList;

public class Alliance {
	private DevelopmentArea developmentArea;	// DevelopmentArea over which the Alliance is formed
	private ArrayList<Player> members = new ArrayList<>();		// players who are members of the Alliance
	
	/**
	 * Creates a new Alliance over a specified DevelopmentArea, assigning the
	 * Alliance within the DevelopmentArea class
	 * 
	 * @param da      DevelopmentArea over which the Alliance is formed
	 * @param members Player instances who are members of the Alliance (TaskSquare
	 *                owners within the DevelopmentArea)
	 */
	public Alliance(DevelopmentArea da, ArrayList<Player> members) {
		this.developmentArea = da;
		this.members = members;
	}

	/**
	 * Returns the DevelopmentArea over which the Alliance is formed.
	 * 
	 * @return DevelopmentArea
	 */
	public DevelopmentArea getDevelopmentArea() {
		return this.developmentArea;
	}

	/**
	 * Returns the Players who are members of the Alliance
	 * 
	 * @return ArrayList of member players
	 */
	public ArrayList<Player> getMembers() {
		return this.members;
	}
}
