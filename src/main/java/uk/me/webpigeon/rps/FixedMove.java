package uk.me.webpigeon.rps;

import uk.me.webpigeon.games.GameMove;

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
