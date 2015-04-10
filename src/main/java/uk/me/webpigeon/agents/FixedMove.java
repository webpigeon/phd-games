package uk.me.webpigeon.agents;

import uk.me.webpigeon.games.GameMove;
import uk.me.webpigeon.rps.RpsAgent;

public class FixedMove extends RpsAgent {
	private final Integer move;

	public FixedMove(int moveID){
		this.move = moveID;
	}
	
	@Override
	public String getName() {
		return "play-"+move;
	}

	@Override
	public GameMove getMove(GameMove[] moves) {
		return moves[move % moves.length];
	}

	@Override
	public void onGameStart() {

	}

	@Override
	public void onRoundEnd(GameMove ours, GameMove theirs, double score) {
		
	}

}
