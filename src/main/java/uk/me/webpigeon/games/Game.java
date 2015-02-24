package uk.me.webpigeon.games;

import java.util.List;

import uk.me.webpigeon.stats.RoundStats;

public interface Game {

	public void addAgent(Agent agent);
	
	public List<RoundStats> getStats(int runsPerPair);
	public RoundStats playRounds(Agent agent1, Agent agent2, int runs);
	public Double[] playRound(Agent agent1, Agent agent2);
	
}
