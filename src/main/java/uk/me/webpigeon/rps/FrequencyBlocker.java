package uk.me.webpigeon.rps;

import uk.me.webpigeon.games.GameMove;

public class FrequencyBlocker extends RpsAgent {
	private int[] freqs;
	
	public FrequencyBlocker(GameMove[] moves){
		this.freqs = new int[moves.length];
	}

	@Override
	public String getName() {
		return "freqblock";
	}

	public String toString() {
		return "rps-"+getName();
	}
	
	public GameMove getOppMove(GameMove curr, GameMove[] legalMoves) {
		int inverse = (curr.getID() + 1) % legalMoves.length;
		return legalMoves[inverse];
	}

	@Override
	public GameMove getMove(GameMove[] moves) {
		int bestOpt = 0;
		int bestFreq = 0;
		
		for (int i=0; i<freqs.length; i++) {
			if (freqs[i] > bestFreq) {
				bestOpt = i;
				bestFreq = freqs[i];
			}
		}
		
		GameMove oppBestMove = moves[bestOpt];
		return getOppMove(oppBestMove, moves);
	}

	@Override
	public void onGameStart() {
		this.freqs = new int[freqs.length];
	}

	@Override
	public void onRoundEnd(GameMove ours, GameMove theirs, double score) {
		freqs[theirs.getID()]++;
	}

}
