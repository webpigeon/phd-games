package uk.me.webpigeon.architecture.basics;

import uk.me.webpigeon.architecture.Action;
import uk.me.webpigeon.architecture.Controller;
import uk.me.webpigeon.architecture.GameInstance;

/**
 * A 2 player game which is played in rounds where both players make moves at the same time.
 * 
 * Examples: IPD, RPS, Coin game, battle asteroids, etc...
 */
public abstract class TwoPlayerGame implements GameInstance {
	protected boolean running;
	protected Controller player1;
	protected Controller player2;
	
	public TwoPlayerGame() {
		this.running = false;
	}
	
	public void setPlayers(Controller player1, Controller player2) {
		this.player1 = player1;
		this.player2 = player2;
	}

	@Override
	public void setup() {
		this.running = true;
	}

	@Override
	public boolean isRunning() {
		return running;
	}

	@Override
	public void shutdown() {
		running = false;
	}

	@Override
	public void tick() {
		Action p1Action = player1.getAction(this);
		Action p2Action = player2.getAction(this);
		
		update(p1Action, p2Action);
	}
	
	public abstract void update(Action p1Action, Action p2Action);

}
