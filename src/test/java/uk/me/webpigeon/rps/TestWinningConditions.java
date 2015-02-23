package uk.me.webpigeon.rps;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestWinningConditions {
	private Move player1;
	private Move player2;
	private WinningPlayer result;
	private RockPaperScissors rps;
	
    @Parameters(name= "{index}: rps({0},{1})={2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
        		{Move.ROCK, Move.ROCK, WinningPlayer.Draw},
				{Move.ROCK, Move.PAPER, WinningPlayer.Player2},
				{Move.ROCK, Move.SCISSORS, WinningPlayer.Player1},
				{Move.PAPER, Move.ROCK, WinningPlayer.Player1},
				{Move.PAPER, Move.PAPER, WinningPlayer.Draw},
				{Move.PAPER, Move.SCISSORS, WinningPlayer.Player2},
				{Move.SCISSORS, Move.ROCK, WinningPlayer.Player2},
				{Move.SCISSORS, Move.PAPER, WinningPlayer.Player1},
				{Move.SCISSORS, Move.SCISSORS, WinningPlayer.Draw},
		});
    }

    
    public TestWinningConditions(Move p1Move, Move p2Move, WinningPlayer result) {
    	this.player1 = p1Move;
    	this.player2 = p2Move;
    	this.result = result;
    	this.rps = new RockPaperScissors();
    }

    @Test
    public void test() {
        assertEquals(result, rps.getWinner(player1, player2));
    }
	
}
