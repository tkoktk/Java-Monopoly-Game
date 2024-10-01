package sustainopoly.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import sustainopoly.Die;

public class DieTest {
	
	// Test should roll the die and return a number <= 6
	@Test
	public void testDiceRoll() {
		int MAX = 6;
		Die die = new Die();
		int roll = die.roll();
		
		assertTrue(roll <= MAX && roll > 0);
	}

}
