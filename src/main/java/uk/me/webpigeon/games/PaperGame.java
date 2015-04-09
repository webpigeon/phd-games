package uk.me.webpigeon.games;

import java.util.Collection;
import java.util.List;

/**
 * The representation of a game given by the paper i'm currently reading.
 * http://www.sciencedirect.com/science/article/pii/S0004370213000441?np=y
 */
public interface PaperGame {

	public Collection<GameMove[]> getPossibleMoves();
	public List<? extends GameState> getStates();
	public List<Agent> getPlayers();
	public GameState transition(GameState startingState, GameMove ... actions);
	public Double[] getPayoff(GameState s, GameMove ... actions);
	
	public void setup(Agent ... players);
	public GameState executeRound();
	public double[] getScores();
	
}
