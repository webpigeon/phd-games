package uk.me.webpigeon.stats;

import uk.me.webpigeon.games.GameAgent;

public interface MultiAgentStats {
	
	public double getMean(GameAgent agent);
	public void record(GameAgent agent, double result);

}
