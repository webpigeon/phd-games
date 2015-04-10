package uk.me.webpigeon.games;

public interface GameLogger {

	void log(int turn, GameAgent agent1, GameMove a1Move, GameAgent agent2, GameMove a2Move);

}
