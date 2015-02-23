package uk.me.webpigeon.stats;

import uk.me.webpigeon.rps.RpsAgent;

public class RoundStats {
	private static final String STRING_FORMAT = "%s,%s,%d,%d,%d";
	private static final String CSV_FORMAT = "%s,%s,%d,%d,%d";
	
	public RpsAgent player1;
	public RpsAgent player2;
	public int player1Wins;
	public int player2Wins;
	public int draws;
	
	public String toCSV() {
		return String.format(CSV_FORMAT, player1, player2, player1Wins, player2Wins, draws);
	}
	
	@Override
	public String toString() {
		return String.format(STRING_FORMAT, player1, player2, player1Wins, player2Wins, draws);
	}
}
