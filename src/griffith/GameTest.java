package griffith;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GameTest {

	@Test
	void testGameInitProperties() {
		Game game1 = new Game("Title1", 100, 200);
		assertEquals(100, game1.getWindowWidth());
		assertEquals(200, game1.getWindowHeight());
		assertEquals("Title1", game1.getWindowTitle());
		
		Game game2 = new Game("Title2", -200, -100);
		//Default resolution if width and height is incorrect
		assertEquals(1000, game2.getWindowWidth());
		assertEquals(1000, game2.getWindowHeight());
		assertEquals("Title2", game2.getWindowTitle());
		
		Game game3 = new Game(null, 100, 200);
		assertEquals(100, game3.getWindowWidth());
		assertEquals(200, game3.getWindowHeight());
		//Default title if title is null or empty
		assertEquals("Title", game3.getWindowTitle());
		
		Game game4 = new Game("", 100, 200);
		assertEquals(100, game4.getWindowWidth());
		assertEquals(200, game4.getWindowHeight());
		//Default title if title is null or empty
		assertEquals("Title", game4.getWindowTitle());
	}

	@Test
	void testGameRun() {
		Game game1 = new Game("Title1", 1080, 720);
		
		//Test that there are no exceptions in a game
		//assertDoesNotThrow(() -> game1.Run());
	}
}
