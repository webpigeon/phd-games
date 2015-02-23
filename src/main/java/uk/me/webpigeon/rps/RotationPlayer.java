package uk.me.webpigeon.rps;

import java.util.Random;

public class RotationPlayer implements RpsAgent {
	private int lastIndex;
	private int rotValue;
	private Move[] moves;
	private Random r = new Random();
	
	public RotationPlayer(int rotValue) {
		this.rotValue = rotValue;
		this.moves = Move.values();
	}

	@Override
	public String getName() {
		return "rotate-"+rotValue;
	}

	@Override
	public Move getMove() {		
		int id = (lastIndex + rotValue) % moves.length;
		return moves[id];
	}

	@Override
	public void onGameOver(Move player1, Move player2, int playerID, WinningPlayer player) {
		if (playerID == 1) {
			lastIndex = player2.ordinal();
		} else {
			lastIndex = player1.ordinal();
		}
	}
	
	@Override
	public String toString() {
		return "rps-"+getName();
	}
	
	@Override
	public void newOpponent() {
		this.lastIndex = r.nextInt(moves.length);
	}

}
