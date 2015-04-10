package uk.me.webpigeon.games.modeling;

import uk.me.webpigeon.games.GameMove;

public interface Predictor {

	GameMove getMove();

	void clear();

	void storeResults(GameMove ours, GameMove theirs, double score);

	GameMove getInverse(GameMove theirMove);

}
