package uk.me.webpigeon.ipd;

import java.util.Collections;

import uk.me.webpigeon.games.AbstractGame;
import uk.me.webpigeon.games.GameAgent;
import uk.me.webpigeon.games.GameLogger;
import uk.me.webpigeon.games.GameMove;

public class IPDGame extends AbstractGame {
	// the legal game moves
	public static final GameMove COOP = new GameMove(0, "co-op");
	public static final GameMove DEFECT = new GameMove(1, "defect");
	public static final GameMove[] MOVES = new GameMove[] {
		COOP,
		DEFECT
	};
	
	//payoff names
	private final static Double T = 3.0;
	private final static Double R = 2.0;
	private final static Double P = 1.0;
	private final static Double S = 0.0;
	
	//payoff list
	private final Double[][][] payoffs = {
			{{R,R}, {S,T}},
			{{T,S}, {P,P}}
	};
	
	public IPDGame(GameLogger logger) {
		super(logger);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Double[] playRound(GameAgent agent1, GameAgent agent2) {
		GameMove p1Move = agent1.getMove(MOVES);
		GameMove p2Move = agent2.getMove(MOVES);
		
		Double[] scores = payoffs[p1Move.getID()][p2Move.getID()];
		agent1.onRoundEnd(p1Move, p2Move, scores[0]);
		agent2.onRoundEnd(p2Move, p1Move, scores[1]);
		
		return scores;
	}
	
	@Override
	public String toString() {
		return "Iterated Prisoner's Dilemma";
	}

}
