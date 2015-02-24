package uk.me.webpigeon.games;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import uk.me.webpigeon.rps.WinningPlayer;
import uk.me.webpigeon.stats.RoundStats;

/**
 * Abstract game to make writing games as simple as possible.
 *
 */
public abstract class AbstractGame implements Game {
	private final List<Agent> agents;
	
	public AbstractGame() {
		this.agents = new ArrayList<>();
	}

	@Override
	public void addAgent(Agent agent) {
		agents.add(agent);
	}
	
	public List<RoundStats> getStats(int rounds){
		
		List<RoundStats> statsList = new ArrayList<>();
		
		System.out.println("agents: "+agents);
		
		for (Agent player1 : agents) {
			for (Agent player2 : agents) {
				RoundStats stats = playRounds(player1, player2, rounds);
				statsList.add(stats);
			}
		}

		return statsList;
	}

	@Override
	public RoundStats playRounds(Agent agent1, Agent agent2, int runs) {
		RoundStats stats = new RoundStats();
		
		stats.player1 = agent1;
		stats.player2 = agent2;
		
		agent1.onGameStart();
		agent2.onGameStart();
		
		for (int i=0; i<runs; i++){
			Double[] result = playRound(agent1, agent2);
			
			stats.player1Score += result[0];
			stats.player2Score += result[1];
			
			//track draws separately, just in case
			if (Double.compare(result[0], result[1]) == 0) {
				stats.draws++;
			}
		}
		
		return stats;
	}

}