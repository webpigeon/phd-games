package uk.me.webpigeon.games;

public interface Agent {
	
	public String getName();

	public GameMove getMove(GameMove[] moves);
	
	public void onGameStart();
	
	public void onRoundEnd(GameMove ours, GameMove theirs, double score);

}
