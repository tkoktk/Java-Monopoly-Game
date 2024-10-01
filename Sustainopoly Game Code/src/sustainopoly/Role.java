package sustainopoly;

/**
 * Enumeration containing the possible roles of players within the game
 */
public enum Role {
	ASYLUM_SEEKER("Asylum seeker"), 
	GOVAN_LOCAL("Govan local"), 
	VOLUNTEER("Volunteer"), 
	COMMUNITY_LEADER("Community leader"), 
	COUNCILLOR("Councillor"), 
	ORGANISATION("Organisation");
	
	private String description;		// name of role
	
	/**
	 * Initialises role name
	 * @param description Output-friendly name of the role
	 */
	private Role(String description) {
		this.description = description;
	}
	
	/**
	 * Returns an output-friendly name of the role
	 * @return Name of the role
	 */
	public String toString() {
		return this.description;
	}
}
