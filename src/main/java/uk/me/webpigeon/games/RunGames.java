package uk.me.webpigeon.games;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;

import uk.me.webpigeon.rps.FixedMove;
import uk.me.webpigeon.rps.FrequencyBlocker;
import uk.me.webpigeon.rps.RockPaperScissors;
import uk.me.webpigeon.rps.RotationPlayer;
import uk.me.webpigeon.rps.UCBPlayer;
import uk.me.webpigeon.stats.RoundStats;

public class RunGames {
	
	public static void main(String[] args) throws FileNotFoundException {
		Game rps = new RockPaperScissors();
		rps.addAgent(new FixedMove(RockPaperScissors.ROCK));
		rps.addAgent(new FixedMove(RockPaperScissors.PAPER));
		rps.addAgent(new FixedMove(RockPaperScissors.SCISSORS));
		rps.addAgent(new RotationPlayer(1));
		rps.addAgent(new RotationPlayer(2));
		rps.addAgent(new RotationPlayer(3));
		//rps.addAgent(new RandomMove());
		rps.addAgent(new UCBPlayer(2.0, RockPaperScissors.moves));
		rps.addAgent(new FrequencyBlocker(RockPaperScissors.moves));
		
		//generate stats and dump them to csv
		List<RoundStats> statsList = rps.getStats(1000);
		toCsvFile("test.csv",statsList);
		
		System.out.println(statsList);
	}
	
	
	public static void toCsvFile(String filename, List<RoundStats> statsList) throws FileNotFoundException {
		PrintStream csv = new PrintStream(new FileOutputStream(filename));
		
		csv.println("player1,player2,player1Wins,player2Wins,draws");
		for (RoundStats stats : statsList) {
			csv.println(stats.toCSV());
		}
		
		csv.close();
	}

	
	
}
