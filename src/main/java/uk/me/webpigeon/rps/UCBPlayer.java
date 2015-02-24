package uk.me.webpigeon.rps;

import uk.me.webpigeon.games.GameMove;

public class UCBPlayer extends RpsAgent {
	private GameMove[] legalMoves;
	private int[] sims;
	private int[] wins;
	private int totalPlays;
	private double expConst;
	
	public UCBPlayer(double expConst, GameMove[] legalMoves) {
		this.expConst = expConst;
		this.legalMoves = legalMoves;
		this.sims = new int[legalMoves.length];
		this.wins = new int[legalMoves.length];
		
		for (int i=0; i<sims.length; i++) {
			sims[i] = 1;
			wins[i] = 1;
		}
	}

	@Override
	public String getName() {
		return "ucb";
	}
	
	//stolen from piers
	public double getUCB(GameMove move) {
		int index = move.getID();		
		return (wins[index]*1.0)/sims[index] + (Math.sqrt(expConst * Math.log(totalPlays) / sims[index]));
	}

	@Override
	public String toString() {
		return "rbs-"+getName();
	}

	@Override
	public GameMove getMove(GameMove[] moves) {
		double best = -Double.MAX_VALUE;
		GameMove bestMove = null;
		
		for (GameMove move : moves) {
			double score = getUCB(move);
			if (score > best) {
				bestMove = move;
				best = score;
			}
		}
		
		return bestMove;
	}

	@Override
	public void onGameStart() {
		this.sims = new int[legalMoves.length];
		this.wins = new int[legalMoves.length];
		this.totalPlays = sims.length;
		
		for (int i=0; i<sims.length; i++) {
			sims[i] = 1;
		}
	}

	@Override
	public void onRoundEnd(GameMove ours, GameMove theirs, double score) {
		
		if (score >= 1) {
			wins[ours.getID()]++;
		}
		
		sims[ours.getID()]++;
	}

}
