package uk.me.webpigeon.games;

public interface GameLogger {

	void log(int turn, Agent agent1, GameMove a1Move, Agent agent2, GameMove a2Move);

}
