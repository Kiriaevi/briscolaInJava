package main;

public class Main {

	public static void main(String[] args) {
		Game game = new Game();
		boolean isRunning = true;
		while(isRunning) {
			if(game.controllaGameOver())
				isRunning = false;
		}
		
		
		

	}

}
