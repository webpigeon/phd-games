package uk.me.webpigeon.games;

import java.util.Collection;
import java.util.List;

import uk.me.webpigeon.stats.TwoPlayerStats;

public interface WebpigeonGame {

	public void addAgent(GameAgent agent);
	
	public List<TwoPlayerStats> getStats(int runsPerPair);
	public TwoPlayerStats playRounds(GameAgent agent1, GameAgent agent2, int runs);
	public Double[] playRound(GameAgent agent1, GameAgent agent2);

	public Collection<GameAgent> getAgents();
	
}
