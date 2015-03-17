package uk.me.webpigeon.agents;

import uk.me.webpigeon.games.Agent;
import uk.me.webpigeon.games.GameMove;
import aima.core.probability.bayes.BayesianNetwork;
import aima.core.probability.bayes.impl.BayesNet;

public class BayesAgent implements Agent {
	private final BayesianNetwork network;
	
	public BayesAgent() {
		this.network = new BayesNet();
	}

	@Override
	public String getName() {
		return "BayesAgent";
	}

	@Override
	public GameMove getMove(GameMove[] moves) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onGameStart() {
		
	}

	@Override
	public void onRoundEnd(GameMove ours, GameMove theirs, double score) {
		
	}

}
