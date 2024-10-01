package sustainopoly;

import java.util.ArrayList;
import java.util.Random;

public class GameBoard {
	private ArrayList<Square> squares = new ArrayList<>();		// hold all the Squares of the game
	private ArrayList<Card> chanceCards = new ArrayList<>();	// holds all the Cards of the game, simulating a deck
	
	/**
	 * Creates a GameBoard, setting the first square to a GoVan square (as this will always be the case)
	 */
	public GameBoard() {
		addSquare(new GoVanSquare());	// regardless of board layout, GoVan square will always be first Square
	}
	
	/**
	 * Adds a new Square to the GameBoard
	 * @param square Square to be added
	 */
	protected void addSquare(Square square) {
		squares.add(square);
		
		if (square instanceof TaskSquare) {
			TaskSquare ts = (TaskSquare)square;
			ts.getDevelopmentArea().addTask(ts);
		}
	}
	
	/**
	 * Adds a new Card to the GameBoard
	 * @param card Card to be added
	 */
	protected void addChanceCard(Card card) {
		chanceCards.add(card);
	}
	
	/**
	 * Draws and returns a random chance card
	 * @return Instance implementing Card interface- can be of different types
	 */
	public Card drawChanceCard() {
		
		return chanceCards.get(new Random().nextInt(chanceCards.size()));
	}
	
	/**
	 * Gets the number of squares that make up the GameBoard
	 * @return Number of constituent squares
	 */
	public int getNumSquares() {
		return squares.size();
	}
	

	/**
	 * Retrieves a Square within the squares ArrayList using its index
	 * @param index Index of squares ArrayList to access
	 * @return Square instance at specified index
	 */
	public Square getSquare(int index) {
		return (Square)squares.get(index);
	}
	
	/**
	 * Gets the index of a Square instance within the squares ArrayList
	 * @param square Square object to get index of
	 * @return Index of the provided Square within the ArrayList, else -1 if not present
	 */
	public int getSquareIndex(Square square) {
		return squares.indexOf(square);
	}
	
	/**
	 * Returns an array of the TaskSquares which belong to a specified development area
	 * @param da Development area to get TaskSquares of
	 * @return TaskSquare array containing all tasks belonging to the specified development area
	 */
	public TaskSquare[] getTasksInDevelopmentArea(DevelopmentArea da) {
		TaskSquare[] tasks = new TaskSquare[da.getNumTasks()];
		for (int i = 0; i < tasks.length; i++) {
			tasks[i] = da.getTasks().get(i);
		}
		
		return tasks;
	}
}
