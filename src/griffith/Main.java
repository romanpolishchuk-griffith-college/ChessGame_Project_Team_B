package griffith;

public class Main {
	public static void main(String[] args) {

		// Create a new game and a renderer
		Game game = new Game("Super Chess", 1080, 720);
		Renderer renderer = new Renderer();

		// Run the game
		game.Run();
		
		//Resetting the timer after the game ends
		game.resetTimer(renderer);
	}
}
