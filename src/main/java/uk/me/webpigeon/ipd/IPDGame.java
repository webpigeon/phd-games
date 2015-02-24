package uk.me.webpigeon.ipd;

import uk.me.webpigeon.games.AbstractGame;
import uk.me.webpigeon.games.Agent;
import uk.me.webpigeon.games.GameMove;

public class IPDGame extends AbstractGame {
	// the legal game moves
	private static final GameMove COOP = new GameMove(0, "co-op");
	private static final GameMove DEFECT = new GameMove(0, "defect");
	private static final GameMove[] moves = new GameMove[] {
		COOP,
		DEFECT
	};
	
	//payoff names
	private final static Double T = 10.0;
	private final static Double R = 7.0;
	private final static Double P = 5.0;
	private final static Double S = 2.0;

	//payoff list
	private final Double[][][] payoffs = {
			{{R,R}, {S,T}},
			{{T,S}, {P,P}}
	};
	
	@Override
	public Double[] playRound(Agent agent1, Agent agent2) {
		GameMove p1Move = agent1.getMove(moves);
		GameMove p2Move = agent2.getMove(moves);
		
		Double[] scores = payoffs[p1Move.getID()][p2Move.getID()];
		agent1.onRoundEnd(p1Move, p2Move, scores[0]);
		agent2.onRoundEnd(p2Move, p1Move, scores[1]);
		
		return scores;
	}

}
