package uk.me.webpigeon.rps;

import uk.me.webpigeon.games.GameMove;

/**
 * Replay the move the opponent made last turn.
 * 
 */
public class ReplayLastMove extends RpsAgent {
	private GameMove firstMove;
	private GameMove lastMove;
	
	public ReplayLastMove(GameMove firstMove){
		this.firstMove = firstMove;
		this.lastMove = firstMove;
	}

	@Override
	public String getName() {
		return "replay-last";
	}

	public String toString() {
		return "rps-"+getName();
	}

	@Override
	public GameMove getMove(GameMove[] moves) {
		return lastMove;
	}

	@Override
	public void onGameStart() {
		this.lastMove = firstMove;	
	}

	@Override
	public void onRoundEnd(GameMove ours, GameMove theirs, double score) {
		lastMove = theirs;
	}

}
