package uk.me.webpigeon.games;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractPaperGame implements PaperGame {
	protected Agent[] players;
	protected double[] scores;
	
	@Override
	public List<Agent> getPlayers() {
		return Arrays.asList(players);
	}

	@Override
	public void setup(Agent ... players) {
		this.players = players;
		this.scores = new double[players.length];
		
		for (Agent player : players) {
			player.onGameStart();
		}
	}

	@Override
	public double[] getScores() {
		return scores;
	}

}
