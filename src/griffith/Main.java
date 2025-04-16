package griffith;

public class Main {
	public static void main(String[] args) {

		// Create a new game
		Game game = new Game("Super Chess", 1080, 720);

		//Creating a new renderer variable
		Renderer renderer = new Renderer();

		// Run the game
		game.Run();
		
		//Resetting the timer after the game ends
		game.resetTimer(renderer);
	}
}
