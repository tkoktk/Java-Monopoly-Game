package sustainopoly.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import sustainopoly.Alliance;
import sustainopoly.DevelopmentArea;
import sustainopoly.GameBoard;
import sustainopoly.GameBoardComplete;
import sustainopoly.SustainopolyGame;
import sustainopoly.TaskSquare;
import sustainopoly.Player;
import sustainopoly.Square;

import gui.MainWindow;
class SustainopolyGameTest {
	
	private static SustainopolyGame game;
	
	@BeforeAll
	static void init() {
		game = SustainopolyGame.getInstance();
		GameBoard board = new GameBoardComplete();
		game.setGameBoard(board);
		
		MainWindow window = new MainWindow(board);
		game.setGUI(window);		
		window.setVisible(false);
	}
	
	
	@Test
	public void testFormAlliance() {
		ArrayList<Player> members = new ArrayList<>(Arrays.asList(new Player(), new Player(), new Player()));
		
		int[] reducedTaskStepPrices = new int[DevelopmentArea.INCREMENT_INFRASTRUCTURE.getNumTasks()];
		for (int i = 0; i < DevelopmentArea.INCREMENT_INFRASTRUCTURE.getNumTasks(); i++) {
			reducedTaskStepPrices[i] = (int)(DevelopmentArea.INCREMENT_INFRASTRUCTURE.getTasks().get(i).getStepPrice() * 0.8);
		}
		
		game.formAlliance(DevelopmentArea.INCREMENT_INFRASTRUCTURE, members);
		
		// check reduction in step price
		for (int i = 0; i < DevelopmentArea.INCREMENT_INFRASTRUCTURE.getNumTasks(); i++) {
			assertEquals(reducedTaskStepPrices[i], DevelopmentArea.INCREMENT_INFRASTRUCTURE.getTasks().get(i).getStepPrice());
		}
		
		// check all players have had expertise incremented by 5
		for (Player member : members) {
			assertEquals(member.getExpertise(), 5);
		}
		
		// check alliance has been assigned to development area
		assertTrue(DevelopmentArea.INCREMENT_INFRASTRUCTURE.isManagedByAlliance());
		
		Alliance assignedAlliance = DevelopmentArea.INCREMENT_INFRASTRUCTURE.getAlliance();
		for (Player member : members) {
			assertTrue(assignedAlliance.getMembers().contains(member));
		}
		
	}
	
	@Test
	public void testGetPlayerAlliances() {
		
		Player player1 = new Player();
		
		game.formAlliance(DevelopmentArea.GET_GOVAN, new ArrayList<Player>(Arrays.asList(player1)));
		
		// player should have one alliance, the same that's assigned to GET_GOVAN
		Alliance expectedAlliance = DevelopmentArea.GET_GOVAN.getAlliance();
		
		// assert that all the alliances the Player is in (should be 1) is equal to the 1 alliance they are in
		for (Alliance alliance : game.getPlayerAlliances(player1))
			assertEquals(alliance, expectedAlliance);
	}
	
	@Test
	public void testMovePlayer_checkPosition() {
		game.startGame();
		
		Player player = game.getPlayer(0);
		
		// move the first player 10 squares forward
		game.movePlayer(0, 10, null);
		
		// check their position has been updated
		Square expectedPosition = game.getGameBoard().getSquare(10);
		assertEquals(player.getPosition(), expectedPosition);
	}
	
	@Test
	public void testMovePlayer_checkSquareTimeout() throws InterruptedException {
		game.startGame();
		
		Player player = game.getPlayer(0);
		player.setSquareTimeout(10);
		
		// move the first player 10 squares forward
		game.movePlayer(0, 10, null);
		
		Thread.sleep(100);		// need to wait for animation to complete before checking
		
		assertEquals(player.getSquareTimeout(), 0);		// square timeout should have been reduced to 0
	}
	
	@Test
	public void testMovePlayer_playActionCalled() throws InterruptedException {
		game.startGame();
		
		Player player = game.getPlayer(0);
		int expertiseBefore = player.getExpertise();
		
		// move the first player 12 squares forward, which will make them land on FactSquare
		game.movePlayer(0, 12, null);
		
		Thread.sleep(100);		// need to wait for animation to complete before checking
		
		assertEquals(player.getExpertise(), expertiseBefore + 5);		// playAction() of FactSquare called, so they gain 5 expertise
	}
	
	@Test
	public void testGetPlayerIndex_validPlayer() {
		Player player = game.getPlayer(0);
		
		int playerIndex = game.getPlayerIndex(player);	// should be 0
		
		assertEquals(playerIndex, 0);
	}
	
	@Test
	public void testGetPlayerIndex_randomPlayer() {
		Player player = new Player();
		
		int playerIndex = game.getPlayerIndex(player);	// should be -1 as this isn't one of the game's 8 players
		
		assertEquals(playerIndex, -1);
	}
	
	@Test
	public void testGetPlayer_validIndex() {
		Player player = game.getPlayer(0);
		
		assertNotNull(player);
	}
	
	@Test
	public void testGetCurrentPlayerIndex() {
		int playerIndex = game.getCurrentPlayerIndex();	// game just started so should be 0- first player
		
		assertEquals(playerIndex, 0);
	}
	
	@Test
	public void testGetPlayer_invalidIndex() {
		Player player = game.getPlayer(-1);
		
		assertNull(player);
	}
	
    @Test
    public void testStartGame() {
    	
        game.startGame();
        Player[] players = new Player[game.getNumPlayers()];
        for(int i=0;i<game.getNumPlayers();i++) {
        	players[i]=game.getPlayer(i);
        }
        
        assertNotNull(players);
        assertEquals(game.getNumPlayers(), players.length);
        
        // test that all roles are assigned
        boolean[] rolesAssigned = new boolean[6];
        
        for (Player player : players) {
            rolesAssigned[player.getRole().ordinal()] = true;
        }
         
        for (boolean roleAssigned : rolesAssigned) {
            assertTrue(roleAssigned);
        }
        
        
        // test that all players have a non-null portrait and sprite
        for (Player player : players) {
            assertNotNull(player.getPortrait());
            assertNotNull(player.getSprite());
        }
    }
}


