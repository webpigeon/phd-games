package uk.me.webpigeon.rps;

import java.util.Random;

public class RandomMove implements RpsAgent {
	private Random random;
	
	public RandomMove() {
		this.random = new Random();
	}

	@Override
	public String getName() {
		return "random";
	}

	@Override
	public Move getMove() {
		Move[] moves = Move.values();
		return moves[random.nextInt(moves.length)];
	}

	public String toString() {
		return "rps-"+getName();
	}

	@Override
	public void onGameOver(Move player1, Move player2, int playerID, WinningPlayer player) {
	}

	@Override
	public void newOpponent() {
	}
	
}
