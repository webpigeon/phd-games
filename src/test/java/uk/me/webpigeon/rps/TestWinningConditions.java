package uk.me.webpigeon.rps;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import uk.me.webpigeon.games.GameMove;

@RunWith(Parameterized.class)
public class TestWinningConditions {
	private static final Double[] draw = new Double[]{0.0,0.0};
	private static final Double[] p1Wins = new Double[]{1.0,0.0};
	private static final Double[] p2Wins = new Double[]{0.0,1.0};
	
	private GameMove player1;
	private GameMove player2;
	private Double[] scores;
	private RockPaperScissors rps;
	
    @Parameters(name= "{index}: rps({0},{1})={2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
        		{RockPaperScissors.ROCK, RockPaperScissors.ROCK, draw},
				{RockPaperScissors.ROCK, RockPaperScissors.PAPER, p2Wins},
				{RockPaperScissors.ROCK, RockPaperScissors.SCISSORS, p1Wins},
				{RockPaperScissors.PAPER, RockPaperScissors.ROCK, p1Wins},
				{RockPaperScissors.PAPER, RockPaperScissors.PAPER, draw},
				{RockPaperScissors.PAPER, RockPaperScissors.SCISSORS, p2Wins},
				{RockPaperScissors.SCISSORS, RockPaperScissors.ROCK, p2Wins},
				{RockPaperScissors.SCISSORS, RockPaperScissors.PAPER, p1Wins},
				{RockPaperScissors.SCISSORS, RockPaperScissors.SCISSORS, draw},
		});
    }

    
    public TestWinningConditions(GameMove p1Move, GameMove p2Move, Double[] scores) {
    	this.player1 = p1Move;
    	this.player2 = p2Move;
    	this.scores = scores;
    	this.rps = new RockPaperScissors(null);
    }

    @Test
    public void test() {
        assertArrayEquals(scores, rps.getScores(player1, player2));
    }
	
}
