package uk.me.webpigeon.games;

import java.util.Collection;
import java.util.List;

import uk.me.webpigeon.stats.TwoPlayerStats;

public interface Game {

	public void addAgent(Agent agent);
	
	public List<TwoPlayerStats> getStats(int runsPerPair);
	public TwoPlayerStats playRounds(Agent agent1, Agent agent2, int runs);
	public Double[] playRound(Agent agent1, Agent agent2);

	public Collection<Agent> getAgents();
	
}
