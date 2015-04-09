package uk.me.webpigeon.games.agents;

import uk.me.webpigeon.games.GameMove;
import uk.me.webpigeon.rps.RpsAgent;

public class FixedMove extends RpsAgent {
	private final GameMove move;

	public FixedMove(GameMove move){
		this.move = move;
	}
	
	@Override
	public String getName() {
		return "play-"+move;
	}

	@Override
	public GameMove getMove(GameMove[] moves) {
		return move;
	}

	@Override
	public void onGameStart() {

	}

	@Override
	public void onRoundEnd(GameMove ours, GameMove theirs, double score) {
		
	}

}
