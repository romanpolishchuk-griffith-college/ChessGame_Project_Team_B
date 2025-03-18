package griffith;

public class Game {
	private String windowTitle;
	private int windowWidth;
	private int windowHeight;
	
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
	
	public void Run() {
		Renderer renderer = new Renderer();
		renderer.Render();
		
		GameLogic gameLogic = new GameLogic();
		gameLogic.HandleLogic();
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
