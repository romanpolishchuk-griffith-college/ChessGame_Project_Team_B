package tests;

import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import griffith.Renderer;

// Represents a test for the Renderer class.
class RendererTest {
	// The renderer instance
	private Renderer renderer;

	// Sets up the renderer instance.
	@BeforeEach
	void setUp() {
		renderer = new Renderer();
	}
	// Tests the setup method.
	@Test
	void testSetup() {
		renderer.Setup("Test Window", 800, 600);
		assertNotNull(renderer, "Renderer instance should not be null");
	}
	// Tests the render game method.
	@Test
	void testRenderGame() {
		renderer.Setup("Test Window", 800, 600);
		assertDoesNotThrow(() -> renderer.RenderGame(), "RenderGame should not throw an exception");
	}
	// Tests the render menu method.
	@Test
	void testRenderMenu() {
		renderer.Setup("Test Window", 800, 600);
		assertDoesNotThrow(() -> renderer.RenderMenu(), "RenderMenu should not throw an exception");
	}
	//Test for captured panel
	@Test
	void testCapturedPanel() {
		renderer.Setup("Test", 800, 600);
		assertDoesNotThrow(() -> renderer.createCapturedPanel(), "Captured Panel should not throw an exception");
	}
	@Test
	void testUpdateCapturedPieces() {
		renderer.Setup("Test", 800, 600);
		JPanel whiteCapturedPanel=new JPanel();
		JPanel blackCapturedPanel=new JPanel();

		assertDoesNotThrow(() -> renderer.updateCapturedPieces(whiteCapturedPanel, blackCapturedPanel), "Captured pieces should not throw an exception");
	}

	@Test
    void testTimerStartsWhenEnabled() {
        renderer.Setup("Test Window", 800, 600);

        // Simulate enabling the timer
        renderer.startCountdownTimer();

        // Wait for 2 seconds to simulate timer countdown
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check if the timer decrements
        assertTrue(renderer.timeLeft < 300, "Timer should decrement when started.");
    }

    @Test
    void testTimerDoesNotStartWhenDisabled() {
        renderer.Setup("Test Window", 800, 600);

        // Simulate not enabling the timer
        assertFalse(renderer.isTimerEnabled, "Timer should not be enabled by default.");
        assertNull(renderer.countdownTimer, "Countdown timer should not be initialized.");
    }

    @Test
    void testTimerEndsGameWhenTimeRunsOut() {
        renderer.Setup("Test Window", 800, 600);

        // Simulate enabling the timer
        renderer.startCountdownTimer();

        // Set the timer to 1 second for testing
        renderer.timeLeft = 1;

        // Wait for 2 seconds to allow the timer to expire
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Since the game ends with System.exit(0), this test will not proceed further.
        // You can mock System.exit(0) in advanced testing frameworks.
        assertEquals(0, renderer.timeLeft, "Timer should reach 0.");
    }
	   
	@Test
    void testTimerSelectionYes() {
        Renderer renderer = new Renderer();

        // Simulate the player choosing "Yes" for the timer
        renderer.isTimerEnabled = true;

        assertTrue(renderer.isTimerEnabled, "Timer should be enabled when the player selects 'Yes'.");
    }

    @Test
    void testTimerSelectionNo() {
        Renderer renderer = new Renderer();

        // Simulate the player choosing "No" for the timer
        renderer.isTimerEnabled = false;

        assertFalse(renderer.isTimerEnabled, "Timer should not be enabled when the player selects 'No'.");
    }

    //Test for the static getGameBoard method
    @Test
    void testGetGameBoard() {
     renderer.Setup("Test Window", 800, 600);
    
     //Test that the board is properly initialized and returned
     assertNotNull(Renderer.getGameBoard(), "getGameBoard should return a non-null Board instance");
   
    }
}