package uk.me.webpigeon.games.agents;

import uk.me.webpigeon.games.Agent;
import uk.me.webpigeon.games.GameMove;
import uk.me.webpigeon.games.GameState;
import uk.me.webpigeon.games.PaperGame;

public abstract class AbstractAgent implements Agent {
	
	public GameMove bestCounter(PaperGame game, GameState state, GameMove[] ourMoves, GameMove oppMove) {
		
		GameMove bestMove = null;
		double bestPayoff = -Double.MAX_VALUE;
		
		for (GameMove ours : ourMoves) {
			Double[] payoffs = game.getPayoff(state, new GameMove[]{ours, oppMove});
			double ourScore = payoffs[0];
			
			if (ourScore > bestPayoff) {
				bestPayoff = ourScore;
				bestMove = ours;
			}
			
		}
		
		return bestMove;
	}
	
	public GameMove bestCounter(GameMove opp, GameMove[] legalMoves) {
		int inverse = (opp.getID() + 1) % legalMoves.length;
		return legalMoves[inverse];
	}
	
	public String toString() {
		return "wp-"+getName();
	}
	
}
