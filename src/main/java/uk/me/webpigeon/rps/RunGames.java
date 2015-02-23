package uk.me.webpigeon.rps;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;

import uk.me.webpigeon.stats.RoundStats;

public class RunGames {

	public static void main(String[] args) throws FileNotFoundException {
		RockPaperScissors rps = new RockPaperScissors();
		rps.addAgent(new FixedMove(Move.ROCK));
		rps.addAgent(new FixedMove(Move.PAPER));
		rps.addAgent(new FixedMove(Move.SCISSORS));
		rps.addAgent(new RotationPlayer(1));
		rps.addAgent(new RotationPlayer(2));
		rps.addAgent(new RotationPlayer(3));
		//rps.addAgent(new RandomMove());
		rps.addAgent(new UCBPlayer(2.0));
		rps.addAgent(new FrequencyBlocker());
		
		List<RoundStats> statsList = rps.getStats(1000);
		
		toCsvFile(statsList);
		
		for (RoundStats stats : statsList) {
			System.out.println(stats);
		}
	}
	
	
	public static void toCsvFile(List<RoundStats> statsList) throws FileNotFoundException {
		PrintStream csv = new PrintStream(new FileOutputStream("test.csv"));
		
		csv.println("player1,player2,player1Wins,player2Wins,draws");
		for (RoundStats stats : statsList) {
			csv.println(stats.toCSV());
		}
		
		csv.close();
	}

	
	
}
