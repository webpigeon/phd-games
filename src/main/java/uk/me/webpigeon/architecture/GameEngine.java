package uk.me.webpigeon.architecture;

public class GameEngine implements Runnable {
	private final GameInstance instance;
	
	public GameEngine(GameInstance instance) {
		this.instance = instance;
	}
	
	@Override
	public void run() {
		instance.setup();
		
		while(instance.isRunning()) {
			instance.tick();
			
		}
		
		instance.shutdown();
	}
}
