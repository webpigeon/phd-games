package uk.me.webpigeon.rps;

public class FixedMove implements RpsAgent {
	private final Move move;

	public FixedMove(Move move){
		this.move = move;
	}
	
	@Override
	public String getName() {
		return "play-"+move;
	}

	@Override
	public Move getMove() {
		return move;
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
