package uk.me.webpigeon.games.modeling;

import java.util.Random;

import uk.me.webpigeon.games.GameAgent;
import uk.me.webpigeon.games.GameMove;

public class ModelingAgent implements GameAgent {
	private Predictor model;
	
	public ModelingAgent(Predictor model) {
		this.model = model;
	}

	@Override
	public String getName() {
		return "ma-"+model;
	}

	@Override
	public GameMove getMove(GameMove[] moves) {
		GameMove mostLikely = model.getMove();
		if (mostLikely == null) {
			return makeRandomMove(moves);
		}

		GameMove counter = model.getInverse(mostLikely);
		
		//we don't know how to counter this move
		if (counter == null) {
			Random r = new Random();
			return moves[r.nextInt(moves.length)];
		}
		
		return counter;
	}

	private GameMove makeRandomMove(GameMove[] moves) {
		Random r = new Random();
		return moves[r.nextInt(moves.length)];
	}
	
	@Override
	public void onGameStart() {
		model.clear();
	}

	@Override
	public void onRoundEnd(GameMove ours, GameMove theirs, double score) {
		model.storeResults(ours, theirs, score);
	}

	@Override
	public String toString() {
		return getName();
	}
	
}
