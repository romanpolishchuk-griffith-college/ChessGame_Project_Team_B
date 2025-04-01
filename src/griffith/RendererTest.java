package griffith;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class RendererTest {
	
	private Renderer renderer;

	   @BeforeEach //To prevent unexpected interactions between tests.
	   void setUp() {
		   renderer = new Renderer();
	   }
}