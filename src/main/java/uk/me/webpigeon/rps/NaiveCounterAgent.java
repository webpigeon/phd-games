package uk.me.webpigeon.rps;

import uk.me.webpigeon.games.GameAgent;
import uk.me.webpigeon.games.GameMove;

public class NaiveCounterAgent implements GameAgent {
	private Predictor predictor;
	
	public NaiveCounterAgent(Predictor predictor) {
		this.predictor = predictor;
	}

	@Override
	public String getName() {
		return "NaiveCounter("+predictor+")";
	}

	@Override
	public GameMove getMove(GameMove[] moves) {
		GameMove bestOpponent = predictor.predict(moves);
		
		//TODO generalise this to other games
		int inverse = (bestOpponent.getID() + 1) % moves.length;
		return moves[inverse];
	}

	@Override
	public void onGameStart() {
		predictor.reset();
	}

	@Override
	public void onRoundEnd(GameMove ours, GameMove theirs, double score) {
		predictor.recordMoves(ours, theirs);
	}
	

}
