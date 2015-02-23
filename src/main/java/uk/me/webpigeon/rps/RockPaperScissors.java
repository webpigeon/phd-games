package uk.me.webpigeon.rps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.me.webpigeon.stats.RoundStats;
import uk.me.webpigeon.stats.SimpleStats;
import uk.me.webpigeon.stats.StatsPackage;

public class RockPaperScissors {
	private List<RpsAgent> agents;
	
	public RockPaperScissors() {
		this.agents = new ArrayList<>();
	}
	
	public void addAgent(RpsAgent agent) {
		agents.add(agent);
	}
	
	public List<RoundStats> getStats(int rounds){
		
		List<RoundStats> statsList = new ArrayList<>();
		
		for (RpsAgent player1 : agents) {
			for (RpsAgent player2 : agents) {
				
				RoundStats stats = new RoundStats();
				stats.player1 = player1;
				stats.player2 = player2;
				
				int[] results = playRounds(player1, player2, rounds);
				
				// get our stats
				stats.player1Wins = results[WinningPlayer.Player1.ordinal()];
				stats.player2Wins = results[WinningPlayer.Player2.ordinal()];
				stats.draws = results[WinningPlayer.Draw.ordinal()];
				
				statsList.add(stats);
			}
			
		}

		return statsList;
	}
	
	
	public int[] playRounds(RpsAgent p1, RpsAgent p2, int rounds) {
		WinningPlayer[] outcomes = WinningPlayer.values();
		int[] scores = new int[outcomes.length];
		
		p1.newOpponent();
		p2.newOpponent();
		
		for (int i=0; i<rounds; i++){
			WinningPlayer result = playRound(p1, p2);
			scores[result.ordinal()]++;
		}
		
		return scores;
	}
	
	public WinningPlayer playRound(RpsAgent p1, RpsAgent p2) {
		Move p1Move = p1.getMove();
		Move p2Move = p2.getMove();
		WinningPlayer winner = getWinner(p1Move, p2Move);
		
		p1.onGameOver(p1Move, p2Move, 1, winner);
		p2.onGameOver(p1Move, p2Move, 2, winner);
		
		System.out.println("[G] "+p1+","+p2+": "+p1Move+" "+p2Move);
		
		return winner;
	}
	
	public WinningPlayer getWinner(Move player1, Move player2) {		
		if (player1.equals(player2)) {
			return WinningPlayer.Draw;
		}
		
		if (player1.ordinal() == 0 && player2.ordinal() == 2) {
			return WinningPlayer.Player1;
		}
		
		if (player1.ordinal() == 1 && player2.ordinal() == 0) {
			return WinningPlayer.Player1;
		}
		
		if (player1.ordinal() == 2 && player2.ordinal() == 1) {
			return WinningPlayer.Player1;
		}
		
		return WinningPlayer.Player2;
	}
	

}
