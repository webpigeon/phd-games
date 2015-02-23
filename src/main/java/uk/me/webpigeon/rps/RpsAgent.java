package uk.me.webpigeon.rps;

public interface RpsAgent {

	public String getName();

	public Move getMove();
	
	public void onGameOver(Move player1, Move player2, int playerID, WinningPlayer winner);

	public void newOpponent();
	
}
