package uk.me.webpigeon.stats;

import java.util.List;
import java.util.Map;

import uk.me.webpigeon.games.Agent;

public class PlayerRoundStats extends SimpleStats {
	public int draws;
	
	public String toCSV() {
		StringBuilder builder = new StringBuilder();
		
		for (Map.Entry<Agent, List<Double>> scores : values.entrySet()) {
			Agent agent = scores.getKey();
			List<Double> results = scores.getValue();
			
			builder.append(agent);
			builder.append(",");
			for (double result : results) {
				builder.append(result);
				builder.append(",");
			}
		}
		
		return builder.toString();
	}


}
