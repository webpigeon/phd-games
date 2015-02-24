package uk.me.webpigeon.stats;

import uk.me.webpigeon.games.Agent;

public class TwoPlayerStats {
	private static final String STRING_FORMAT = "%s,%s,%f,%f,%d";
	private static final String CSV_FORMAT = "%s,%s,%f,%f,%d";
	
	public Agent player1;
	public Agent player2;
	
	public double player1Score;
	public double player2Score;
	
	public int draws;
	
	public String toCSV() {
		return String.format(CSV_FORMAT, player1, player2, player1Score, player2Score, draws);
	}
	
	@Override
	public String toString() {
		return String.format(STRING_FORMAT, player1, player2, player1Score, player2Score, draws);
	}
}