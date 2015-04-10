package uk.me.webpigeon.rps;

import java.util.HashMap;
import java.util.Map;

import uk.me.webpigeon.games.GameMove;

/**
 * Return the opponents favoured (most commonly selected) move.
 */
public class FrequencyPredictor implements Predictor {
	private Map<GameMove,Integer> frequences;
	
	public FrequencyPredictor() {
		this.frequences = new HashMap<>();
	}

	@Override
	public GameMove predict(GameMove[] legalMoves) {
		
		GameMove commonMove = null;
		Integer bestScore = Integer.MIN_VALUE;
		
		for (Map.Entry<GameMove, Integer> moves : frequences.entrySet()) {
			GameMove currMove = moves.getKey();
			Integer currFreq = moves.getValue();
			
			if (isLegal(currMove, legalMoves)) {
				if (currFreq > bestScore) {
					commonMove = currMove;
					bestScore = currFreq;
				}
			}
		}
		
		return commonMove;
	}

	@Override
	public void recordMoves(GameMove us, GameMove them) {
		doHist(them);
	}
	
	private boolean isLegal(GameMove curr, GameMove[] possible) {
		for (GameMove move : possible) {
			if (move.equals(curr)) {
				return true;
			}
		}
		return false;
	}
	
	private void doHist(GameMove move) {
		Integer score = frequences.get(move);
		if (score == null) {
			score = 0;
		}
		frequences.put(move, score+1);
	}

	@Override
	public void reset() {
		frequences.clear();
	}
	
}
