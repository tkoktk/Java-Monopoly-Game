package sustainopoly.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import sustainopoly.Square;

class SquareTest {
	/**
	 * Since Square is an abstract class, we must create a concrete implementation
	 * of it so that we can instantiate it and test its methods.
	 */
	public class ConcreteSquare extends Square {
		@Override
		public void playAction() {
			// implement playAction method
		}
	}

	// Testing: setName() method.

	/**
	 * setName() Test One: Testing that the setName() method in fact changes the
	 * name variable's value.
	 * 
	 * Expected Outcome: The setName method successfully sets the name variable to
	 * "Test Square" and so the test is a success
	 */
	@Test
	public void testSetName() {
		ConcreteSquare square = new ConcreteSquare();
		square.setName("Test Square");
		String expectedName = "Test Square";
		String actualName = square.getName();
		assertEquals(expectedName, actualName);
	}

	/**
	 * setName() Test Two: Testing that the setName() method works with a null
	 * input.
	 * 
	 * Expected Outcome: The setName method successfully sets the name variable to
	 * null, so the test is a success.
	 */
	@Test
	public void testSetNameWithEmptyInput() {
		ConcreteSquare square = new ConcreteSquare();
		square.setName(null);
		String expectedName = null;
		String actualName = square.getName();
		assertEquals(expectedName, actualName);
	}

	// Testing: getName() method.

	/*
	 * getName() Test One: Testing the getName() method with valid input.
	 * 
	 * Expected Outcome: the values are the same so the test is a success
	 */
	@Test
	public void testGetNameWithValidInput() {
		ConcreteSquare square = new ConcreteSquare();
		square.setName("Test Square");
		String expectedName = "Test Square";
		String actualName = square.getName();
		// Use assertEquals to test if the expectedName is the same as the actual name
		assertEquals(expectedName, actualName);
	}

	/**
	 * getName() Test Two: Testing the getName() method with invalid, non-matching
	 * input.
	 * 
	 * Expected Outcome: the values are not the same so the test is a success
	 */
	@Test
	public void testGetNameWithInvalidInput() {
		ConcreteSquare square = new ConcreteSquare();
		square.setName("Valid Name");
		String expectedName = "Invalid Name";
		String actualName = square.getName();
		assertNotEquals(expectedName, actualName);
	}

	/**
	 * getName() Test Three: Testing the getName() method with null input.
	 *
	 * Expected Outcome: the values are both null so the test is a success
	 */
	@Test
	public void testGetNameWithNullInput() {
		ConcreteSquare square = new ConcreteSquare();
		square.setName(null);
		String expectedName = null;
		String actualName = square.getName();
		assertEquals(expectedName, actualName);
	}

	/**
	 * getName() Test Four: Testing that the getName() method returns a non-null
	 * value after the name has been set.
	 * 
	 * Expected Outcome: the name is non-null so the test is a success
	 */
	@Test
	public void testGetNameAfterNameSet() {
		ConcreteSquare square = new ConcreteSquare();
		square.setName("Test Square");
		String actualName = square.getName();
		assertNotNull(actualName);
	}
	
	@Test
	public void testToString() {
		ConcreteSquare square = new ConcreteSquare();
		square.setName("Test Square");
		assertEquals(square.toString(), "Test Square");
	}
}
