package griffith;

import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class RendererTest {
	
	private Renderer renderer;

	   @BeforeEach //To prevent unexpected interactions between tests.
	   void setUp() {
		   renderer = new Renderer();
	   }
	    @Test
	    void testSetup() {
	        renderer.Setup("Test Window", 800, 600);
	        assertNotNull(renderer, "Renderer instance should not be null");
	    }
		@Test
    void testRenderGame() {
        renderer.Setup("Test Window", 800, 600);
        assertDoesNotThrow(() -> renderer.RenderGame(), "RenderGame should not throw an exception");
    }
	   

}