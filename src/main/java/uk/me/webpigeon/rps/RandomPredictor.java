package uk.me.webpigeon.rps;

import java.util.Random;

import uk.me.webpigeon.games.GameMove;

/**
 * Simplest (and worst) predictor of them all, choose randomly.
 */
public class RandomPredictor implements Predictor {
	private Random random;
	
	public RandomPredictor() {
		this.random = new Random();
	}
	
	@Override
	public GameMove predict(GameMove[] legalMoves) {
		return legalMoves[random.nextInt(legalMoves.length)];
	}

	@Override
	public void recordMoves(GameMove us, GameMove them) {
		
	}

	@Override
	public void reset() {
		
	}

}
