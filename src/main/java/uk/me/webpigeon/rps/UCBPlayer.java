package uk.me.webpigeon.rps;

public class UCBPlayer implements RpsAgent {
	private Move[] moves;
	private int[] sims;
	private int[] wins;
	private int totalPlays;
	private double expConst;
	
	public UCBPlayer(double expConst) {
		this.expConst = expConst;
		this.moves = Move.values();
		this.sims = new int[moves.length];
		this.wins = new int[moves.length];
		
		for (int i=0; i<sims.length; i++) {
			sims[i] = 1;
			wins[i] = 1;
		}
	}

	@Override
	public String getName() {
		return "ucb";
	}

	@Override
	public Move getMove() {
		
		double best = -Double.MAX_VALUE;
		Move bestMove = null;
		
		for (Move move : moves) {
			double score = getUCB(move);
			//System.out.println(score);
			if (score > best) {
				bestMove = move;
				best = score;
			}
		}
		
		return bestMove;
	}
	
	//stolen from piers
	public double getUCB(Move move) {
		int index = move.ordinal();		
		return (wins[index]*1.0)/sims[index] + (Math.sqrt(expConst * Math.log(totalPlays) / sims[index]));
	}

	@Override
	public void onGameOver(Move player1, Move player2, int playerID, WinningPlayer player) {
		
		Move ourMove = playerID==1?player1:player2;
		
		if (playerID == player.ordinal()) {
			wins[ourMove.ordinal()]++;
		}
		
		sims[ourMove.ordinal()]++;		
	}

	@Override
	public void newOpponent() {
		this.sims = new int[moves.length];
		this.wins = new int[moves.length];
		this.totalPlays = sims.length;
		
		for (int i=0; i<sims.length; i++) {
			sims[i] = 1;
		}
	}
	
	@Override
	public String toString() {
		return "rbs-"+getName();
	}

}
