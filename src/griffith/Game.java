package griffith;

public class Game {
	private String windowTitle;
	private int windowWidth;
	private int windowHeight;
	
	private boolean gameActive = true;
	
	enum GAME_STATE {
		MENU,
		ACTIVE_GAME
	}
	
	private static GAME_STATE gameState = GAME_STATE.MENU;
	
	public Game(String windowTitle, int windowWidth, int windowHeight) {
		
		if(windowWidth <= 0) {
			//Default value if value is invalid
			windowWidth = 1000;
		}
		
		if(windowHeight <= 0) {
			//Default value if value is invalid
			windowHeight = 1000;
		}
		
		if(windowTitle == null || windowTitle.equals("")) {
			//Default value if value is invalid
			windowTitle = "Title";
		}
		
		this.windowTitle = windowTitle;
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
	}
	
	public static GAME_STATE getGameState() {
		return gameState;
	}
	
	public static void setGameState(GAME_STATE state) {
		gameState = state;
	}
	
	public void Run() {
		Renderer renderer = new Renderer();
		GameLogic gameLogic = new GameLogic();
		Multiplayer ai = new Multiplayer();
		
		renderer.Setup(windowTitle, windowWidth, windowHeight);
		
		GAME_STATE lastState = null;
		
		while(gameActive) {
			renderer.Render();
			gameLogic.HandleLogic();
			ai.run();
			if(gameState != lastState) {  // Only render when state changes
				if(gameState == GAME_STATE.MENU) {
					renderer.RenderMenu();
				}
				else if (gameState == GAME_STATE.ACTIVE_GAME) {
					renderer.RenderGame();
				}
				lastState = gameState;
			}
			
			if (gameState == GAME_STATE.ACTIVE_GAME) {
				gameLogic.HandleLogic();
			}
			
			 try {
			 	Thread.sleep(16);  // Add a small delay to prevent CPU overuse
			 } catch (InterruptedException e) {
			 	e.printStackTrace();
			 }
		}
	}

	public String getWindowTitle() {
		return windowTitle;
	}

	public void setWindowTitle(String windowTitle) {
		this.windowTitle = windowTitle;
	}

	public int getWindowWidth() {
		return windowWidth;
	}

	public void setWindowWidth(int windowWidth) {
		this.windowWidth = windowWidth;
	}

	public int getWindowHeight() {
		return windowHeight;
	}

	public void setWindowHeight(int windowHeight) {
		this.windowHeight = windowHeight;
	}
}
