package sustainopoly.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import sustainopoly.Role;

public class RoleTest {

	// Testing the toString() method with correct input
	@Test
	public void testCorrectToString() {
		assertEquals("Asylum seeker", Role.ASYLUM_SEEKER.toString());
		assertEquals("Govan local", Role.GOVAN_LOCAL.toString());
		assertEquals("Volunteer", Role.VOLUNTEER.toString());
		assertEquals("Community leader", Role.COMMUNITY_LEADER.toString());
		assertEquals("Councillor", Role.COUNCILLOR.toString());
		assertEquals("Organisation", Role.ORGANISATION.toString());
	}
	
	// Testing the toString() method with incorrect input - should fail
	@Test
	public void testIncorrectToString() {
		assertNotEquals("Asylum seeker3", Role.ASYLUM_SEEKER.toString());
		assertNotEquals("Govan lhocal", Role.GOVAN_LOCAL.toString());
		assertNotEquals("Volunteser", Role.VOLUNTEER.toString());
		assertNotEquals("Communitwy leader", Role.COMMUNITY_LEADER.toString());
		assertNotEquals("Coufncillor", Role.COUNCILLOR.toString());
		assertNotEquals("Organisahtion", Role.ORGANISATION.toString());
	}

}
