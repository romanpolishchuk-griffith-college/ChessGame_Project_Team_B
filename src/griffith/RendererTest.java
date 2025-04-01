package griffith;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.Renderer;

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
	   

}