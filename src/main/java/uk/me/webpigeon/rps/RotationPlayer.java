package uk.me.webpigeon.rps;

import java.util.Random;

import uk.me.webpigeon.games.GameMove;

public class RotationPlayer extends RpsAgent {
	private int lastIndex;
	private int rotValue;
	private Random r = new Random();
	
	public RotationPlayer(int rotValue) {
		this.rotValue = rotValue;
	}

	@Override
	public String getName() {
		return "rotate-"+rotValue;
	}
	
	@Override
	public String toString() {
		return "rps-"+getName();
	}

	@Override
	public GameMove getMove(GameMove[] moves) {
		int id = (lastIndex + rotValue) % moves.length;
		return moves[id];
	}

	@Override
	public void onGameStart() {
		this.lastIndex = 0;
	}

	@Override
	public void onRoundEnd(GameMove ours, GameMove theirs, double score) {
		lastIndex = theirs.getID();
	}

}
