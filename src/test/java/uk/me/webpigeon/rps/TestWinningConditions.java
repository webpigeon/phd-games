package uk.me.webpigeon.rps;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import uk.me.webpigeon.games.GameMove;

@RunWith(Parameterized.class)
public class TestWinningConditions {
	private GameMove player1;
	private GameMove player2;
	private WinningPlayer result;
	private RockPaperScissors rps;
	
    @Parameters(name= "{index}: rps({0},{1})={2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
        		{RockPaperScissors.ROCK, RockPaperScissors.ROCK, WinningPlayer.Draw},
				{RockPaperScissors.ROCK, RockPaperScissors.PAPER, WinningPlayer.Player2},
				{RockPaperScissors.ROCK, RockPaperScissors.SCISSORS, WinningPlayer.Player1},
				{RockPaperScissors.PAPER, RockPaperScissors.ROCK, WinningPlayer.Player1},
				{RockPaperScissors.PAPER, RockPaperScissors.PAPER, WinningPlayer.Draw},
				{RockPaperScissors.PAPER, RockPaperScissors.SCISSORS, WinningPlayer.Player2},
				{RockPaperScissors.SCISSORS, RockPaperScissors.ROCK, WinningPlayer.Player2},
				{RockPaperScissors.SCISSORS, RockPaperScissors.PAPER, WinningPlayer.Player1},
				{RockPaperScissors.SCISSORS, RockPaperScissors.SCISSORS, WinningPlayer.Draw},
		});
    }

    
    public TestWinningConditions(GameMove p1Move, GameMove p2Move, WinningPlayer result) {
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
