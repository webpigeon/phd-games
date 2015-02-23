package uk.me.webpigeon.rps;

/**
 * Replay the move the opponent made last turn.
 * 
 */
public class ReplayLastMove implements RpsAgent {
	private Move lastMove;
	
	public ReplayLastMove(Move firstMove){
		this.lastMove = firstMove;
	}

	@Override
	public String getName() {
		return "replay-last";
	}

	@Override
	public Move getMove() {
		return lastMove;
	}

	@Override
	public void onGameOver(Move player1, Move player2, int playerID, WinningPlayer player) {
		if (playerID == 1) {
			lastMove = player2;
		} else {
			lastMove = player1;
		}
	}
	
	public String toString() {
		return "rps-"+getName();
	}

	@Override
	public void newOpponent() {
		this.lastMove = Move.ROCK;
	}

}
