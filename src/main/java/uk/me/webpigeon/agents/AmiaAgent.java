package uk.me.webpigeon.agents;

import aima.core.agent.Agent;
import uk.me.webpigeon.games.GameAgent;
import uk.me.webpigeon.games.GameMove;

public class AmiaAgent implements GameAgent {
	private Agent agent;
	
	public AmiaAgent(Agent agent) {
		this.agent = agent;
	}

	@Override
	public String getName() {
		return "aima("+agent+")";
	}

	@Override
	public GameMove getMove(GameMove[] moves) {
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onGameStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRoundEnd(GameMove ours, GameMove theirs, double score) {
		// TODO Auto-generated method stub
		
	}

}
