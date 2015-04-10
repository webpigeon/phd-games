package uk.me.webpigeon.rps;

import uk.me.webpigeon.games.GameMove;

public interface Predictor {
	
	public void recordMoves(GameMove us, GameMove them);

	public void reset();

	GameMove predict(GameMove[] legalMoves);
	
}
