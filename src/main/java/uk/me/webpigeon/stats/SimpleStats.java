package uk.me.webpigeon.stats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.me.webpigeon.games.GameAgent;

public class SimpleStats implements MultiAgentStats {
	protected final Map<GameAgent, List<Double>> values;
	
	public SimpleStats(){
		this.values = new HashMap<>();
	}

	@Override
	public void record(GameAgent agent, double result) {
		List<Double> results = values.get(agent);
		if (results == null) {
			results = new ArrayList<>();
			values.put(agent, results);
		}
		
		results.add(result);
	}

	@Override
	public double getMean(GameAgent agent) {
		List<Double> scores = values.get(agent);
		
		if (scores == null) {
			return 0;
		}
		
		double sum = 0;
		for (Double score : scores) {
			sum += score;
		}
		
		return sum / scores.size();
	}

	@Override
	public String toString() {
		return values.toString();
	}
	
}
