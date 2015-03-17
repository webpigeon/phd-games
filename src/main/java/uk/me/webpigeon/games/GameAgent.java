package uk.me.webpigeon.games;

public interface GameAgent {
	
	public String getName();

	public GameMove getMove(GameMove[] moves);
	
	public void onGameStart();
	
	public void onRoundEnd(GameMove ours, GameMove theirs, double score);

}
