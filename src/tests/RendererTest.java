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
	   

}