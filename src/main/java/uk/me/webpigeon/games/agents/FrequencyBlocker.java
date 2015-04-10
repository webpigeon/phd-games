package uk.me.webpigeon.games.agents;

import uk.me.webpigeon.games.GameMove;
import uk.me.webpigeon.rps.RpsAgent;

public class FrequencyBlocker extends AbstractAgent {
	private int[] freqs;
	
	public FrequencyBlocker(GameMove[] moves){
		this.freqs = new int[moves.length];
	}

	@Override
	public String getName() {
		return "freqblock";
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
		return bestCounter(oppBestMove, moves);
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
