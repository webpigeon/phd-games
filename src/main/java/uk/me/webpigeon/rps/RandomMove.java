package uk.me.webpigeon.rps;

import java.util.Random;

import uk.me.webpigeon.games.GameMove;

public class RandomMove extends RpsAgent {
	private Random random;
	
	public RandomMove() {
		this.random = new Random();
	}

	@Override
	public String getName() {
		return "random";
	}

	public String toString() {
		return "rps-"+getName();
	}

	@Override
	public GameMove getMove(GameMove[] moves) {
		return moves[random.nextInt(moves.length)];
	}

	@Override
	public void onGameStart() {
		
	}

	@Override
	public void onRoundEnd(GameMove ours, GameMove theirs, double score) {
		
	}
	
}
