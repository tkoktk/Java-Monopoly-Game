package sustainopoly;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Enumeration containing the development areas which all TaskSquare objects will belong to
 */
public enum DevelopmentArea {
	CONSULT_COMMUNITY("Consult Community", new Color(90, 90, 240)),
	FIND_FUNDING("Find Funding", new Color(220, 86, 24)),
	ADVANCE_AWARENESS("Advance Awareness", new Color(250, 67, 60)),
	TRAIN_TEAM("Train Team", new Color(160, 230, 250)), 
	ORGANIZE_ONLINE_PRESENCE("Organize Online Presence", new Color(86, 110, 58)),
	DISPENSE_DEVICES("Dispense Devices", new Color(230, 130, 255)),//Purple or violet
	GET_GOVAN("Get GoVan", new Color(250, 99, 153)),
	SOURCE_SUPPLYCHAIN("Source Supplychain", new Color(186, 90, 20)), //L gren
	INCREMENT_INFRASTRUCTURE("Increment Infrastructure", new Color(102, 102, 102));

	private String description;		// name of development area
	private ArrayList<TaskSquare> tasks = new ArrayList<>();		// TaskSquares which make up the DevelopmentArea
	private Alliance alliance;		// Alliance managing the development area
	private Color colour;
	
	/**
	 * Initialises development area name (note: numTasks initialised to 0 by default, allianceFormed to false and alliance to NULL)
	 * @param description Output-friendly name of the development area
	 */
	private DevelopmentArea(String description, Color colour) {
		this.description = description;
		this.colour = colour;
	}
	
	/**
	 * Returns an output-friendly name of the development area
	 * @return Name of the development area
	 */
	public String toString() {
		return this.description;
	}
	
	/**
	 * Returns the associated colour of the DevelopmentArea
	 * @return Color constant
	 */
	public Color getColour() {
		return this.colour;
	}
	
	/**
	 * Returns an ArrayList containing the constituent TaskSquares of the development area
	 * @return ArrayList of TaskSquares
	 */
	public ArrayList<TaskSquare> getTasks() {
		return this.tasks;
	}

	/**
	 * Returns the number of constituent tasks within the development area
	 * @return Number of tasks
	 */
	public int getNumTasks() {
		return tasks.size();
	}
	
	/**
	 * Returns boolean value whether an alliance has been formed over the development area
	 * @return Boolean value; if true, an alliance has been formed
	 */
	public boolean isManagedByAlliance() {
		if (this.alliance != null)
			return true;
		
		return false;
	}
	
	/**
	 * Returns the Alliance which manages the DevelopmentArea, else null if one has not yet been formed
	 * @return Alliance instance, or null
	 */
	public Alliance getAlliance() {
		return this.alliance;
	}
	
	/**
	 * Assigns an Alliance instance to the DevelopmentArea
	 */
	public void allianceFormed(Alliance alliance) {
		this.alliance = alliance;
	}
	
	/**
	 * Increments the number of tasks within the DevelopmentArea, called from within TaskSquare when its development area is set
	 */
	public void addTask(TaskSquare task) {
		tasks.add(task);
	}
}