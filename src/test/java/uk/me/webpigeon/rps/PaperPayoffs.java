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
public class PaperPayoffs {
	private static final Double[] draw = new Double[]{0.0,0.0};
	private static final Double[] p1Wins = new Double[]{1.0,-1.0};
	private static final Double[] p2Wins = new Double[]{-1.0,1.0};
	
	private GameMove player1;
	private GameMove player2;
	private Double[] scores;
	private RpsPaperGame rps;
	
    @Parameters(name= "{index}: rps({0},{1})={2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
        		{Moves.ROCK, Moves.ROCK, draw},
				{Moves.ROCK, Moves.PAPER, p2Wins},
				{Moves.ROCK, Moves.SCISSORS, p1Wins},
				{Moves.PAPER, Moves.ROCK, p1Wins},
				{Moves.PAPER, Moves.PAPER, draw},
				{Moves.PAPER, Moves.SCISSORS, p2Wins},
				{Moves.SCISSORS, Moves.ROCK, p2Wins},
				{Moves.SCISSORS, Moves.PAPER, p1Wins},
				{Moves.SCISSORS, Moves.SCISSORS, draw},
		});
    }

    
    public PaperPayoffs(GameMove p1Move, GameMove p2Move, Double[] scores) {
    	this.player1 = p1Move;
    	this.player2 = p2Move;
    	this.scores = scores;
    	this.rps = new RpsPaperGame(1);
    }

    @Test
    public void test() {	
        assertArrayEquals(scores, rps.getPayoff(null, new GameMove[]{player1, player2}));
    }
	
}
