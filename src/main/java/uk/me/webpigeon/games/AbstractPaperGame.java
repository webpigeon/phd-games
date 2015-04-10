package uk.me.webpigeon.games;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractPaperGame implements PaperGame {
	protected GameAgent[] players;
	protected double[] scores;
	
	@Override
	public List<GameAgent> getPlayers() {
		return Arrays.asList(players);
	}

	@Override
	public void setup(GameAgent ... players) {
		this.players = players;
		this.scores = new double[players.length];
		
		for (GameAgent player : players) {
			player.onGameStart();
		}
	}

	@Override
	public double[] getScores() {
		return scores;
	}

}
