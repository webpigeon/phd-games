package uk.me.webpigeon.architecture;

public interface GameInstance {

	void setup();

	boolean isRunning();

	void shutdown();

	void tick();

	Action[] getLegalActions(Controller player);
	
}
