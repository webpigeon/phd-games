package uk.me.webpigeon.stats;

import uk.me.webpigeon.games.Agent;

public interface MultiAgentStats {
	
	public double getMean(Agent agent);
	public void record(Agent agent, double result);

}
