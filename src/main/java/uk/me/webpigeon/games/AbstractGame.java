package uk.me.webpigeon.games;

import java.util.ArrayList;
import java.util.List;

import uk.me.webpigeon.stats.TwoPlayerStats;

/**
 * Abstract game to make writing games as simple as possible.
 *
 */
public abstract class AbstractGame implements Game {
	private final List<Agent> agents;
	private final GameLogger logger;
	
	public AbstractGame(GameLogger logger) {
		this.agents = new ArrayList<>();
		this.logger = logger;
	}

	@Override
	public void addAgent(Agent agent) {
		agents.add(agent);
	}
	
	public List<TwoPlayerStats> getStats(int rounds){
		
		List<TwoPlayerStats> statsList = new ArrayList<>();
		
		System.out.println("agents: "+agents);
		
		for (Agent player1 : agents) {
			for (Agent player2 : agents) {
				TwoPlayerStats stats = playRounds(player1, player2, rounds);
				statsList.add(stats);
			}
		}

		return statsList;
	}

	@Override
	public TwoPlayerStats playRounds(Agent agent1, Agent agent2, int runs) {
		TwoPlayerStats stats = new TwoPlayerStats();
		
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
	
	protected void log(int turn, Agent agent1, GameMove a1Move, Agent agent2, GameMove a2Move) {
		logger.log(turn, agent1, a1Move, agent2, a2Move);
	}
	

}
