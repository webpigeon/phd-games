package uk.me.webpigeon.stats;

import uk.me.webpigeon.games.GameAgent;

public class TwoPlayerStats {
	private static final String STRING_FORMAT = "%s (%.2f) vs %s (%.2f), draws: %d";
	private static final String CSV_FORMAT = "%s,%s,%f,%f,%d";
	
	public GameAgent player1;
	public GameAgent player2;
	
	public double player1Score;
	public double player2Score;
	
	public int draws;
	
	public String toCSV() {
		return String.format(CSV_FORMAT, player1, player2, player1Score, player2Score, draws);
	}
	
	@Override
	public String toString() {
		return String.format(STRING_FORMAT, player1, player1Score, player2, player2Score, draws);
	}
}
