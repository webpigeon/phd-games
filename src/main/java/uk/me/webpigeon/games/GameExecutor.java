package uk.me.webpigeon.games;

import java.util.Arrays;

import uk.me.webpigeon.games.agents.RandomMove;
import uk.me.webpigeon.rps.RpsPaperGame;

public class GameExecutor {
	private PaperGame game;
	
	public GameExecutor(PaperGame game) {
		this.game = game;
	}
	
	public double[] executeGame(GameAgent ... players) {
		game.setup(players);
		
		GameState current = null;
		while (current == null || !current.isFinal()) {
			current = game.executeRound();
		}
		
		return game.getScores();
	}
	
	public double[] executeTrial(int trials, GameAgent ... players) {
		double[] totals = new double[players.length];
		for (int i=0; i<trials; i++) {
			double[] gameTotals = executeGame(players);
			for (int playerID=0; playerID<gameTotals.length; playerID++) {
				gameTotals[playerID] += gameTotals[playerID];
			}
		}
		
		//return mean scores per player
		double[] scores = new double[players.length];
		for (int i=0; i<scores.length; i++) {
			scores[i] = totals[i] / (totals.length * 1.0);
		}
		
		return scores;
	}
	
	public static void main(String[] args) {
		GameAgent[] players = new GameAgent[] {new RandomMove(), new RandomMove()};
		
		GameExecutor exec = new GameExecutor(new RpsPaperGame(2_000_000));
		double[] scores = exec.executeTrial(500, players);
		System.out.println(Arrays.toString(players) + " : " + Arrays.toString(scores));
	}

}
