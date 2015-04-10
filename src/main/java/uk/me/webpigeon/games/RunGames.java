package uk.me.webpigeon.games;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;

import uk.me.webpigeon.stats.TwoPlayerStats;

public class RunGames {
	public static final Integer ROUNDS_PER_GAME = 20;
	public static final Integer GAMES_PER_PAIR = 5000;
	
	public static void main(String[] args) throws FileNotFoundException {
		WebpigeonGame game = GameFactory.buildIPD();		
		GameFactory.addAgents(game);
		List<TwoPlayerStats> statsList = game.getStats(ROUNDS_PER_GAME);
		toCsvFile("test.csv",statsList);
		
		System.out.println("game: "+game);
		System.out.println("agents: "+game.getAgents());
		for (TwoPlayerStats stats : statsList) {
			System.out.println(stats);
		}
	}
	
	
	public static void toCsvFile(String filename, List<TwoPlayerStats> statsList) throws FileNotFoundException {
		PrintStream csv = new PrintStream(new FileOutputStream(filename));
		
		csv.println("player1,player2,player1Wins,player2Wins,draws");
		for (TwoPlayerStats stats : statsList) {
			csv.println(stats.toCSV());
		}
		
		csv.close();
	}

	
	
}
