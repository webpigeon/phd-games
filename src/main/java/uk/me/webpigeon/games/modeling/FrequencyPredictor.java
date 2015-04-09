package uk.me.webpigeon.games.modeling;

import java.util.HashMap;
import java.util.Map;

import uk.me.webpigeon.games.GameMove;

public class FrequencyPredictor implements Predictor {
	private final Integer GAMEWINDOW = 10;
	
	private GameMove[] moves;
	private int games = 0;
	private Map<GameMove, GameMove> inverse;
	
	public FrequencyPredictor() {
		this.moves = new GameMove[GAMEWINDOW];
		this.inverse = new HashMap<GameMove,GameMove>();
	}

	@Override
	public GameMove getMove() {
		Map<GameMove, Integer> counts = getCounts();
		if (counts == null) {
			return null;
		}
		
		GameMove move = null;
		Integer bestCount = Integer.MIN_VALUE;
		for (Map.Entry<GameMove,Integer> entry : counts.entrySet()) {
			
			Integer count = entry.getValue();
			if (count > bestCount) {
				bestCount = count;
				move = entry.getKey();
			}
			
		}
		
		return move;
	}
	
	private Map<GameMove, Integer> getCounts() {
		if (games == 0) {
			return null;
		}
		
		Map<GameMove, Integer> counts = new HashMap<GameMove, Integer>();
		
		int min = Math.min(moves.length, games);
		
		for (int i=0; i<min; i++) {
			GameMove move = moves[i];
			Integer currentCount = counts.get(move);
			if (currentCount == null) {
				currentCount = 0;
			}
			counts.put(move, currentCount+1);
		}
		return counts;
	}

	@Override
	public void clear() {
		games = 0;
		inverse.clear();
	}

	@Override
	public void storeResults(GameMove ours, GameMove theirs, double score) {
		// we could have learnt a new combination
		if (score > 0) {
			inverse.put(theirs, ours);
		}
		moves[games%moves.length] = theirs;
		games++;
	}

	@Override
	public GameMove getInverse(GameMove theirMove) {
		return inverse.get(theirMove);
	}
	
	@Override
	public String toString() {
		return "freqpro";
	}

}
