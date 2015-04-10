package uk.me.webpigeon.rps;

import java.util.HashMap;
import java.util.Map;

import uk.me.webpigeon.games.GameMove;

public class UCBPlayer extends RpsAgent {
	private Map<GameMove, Integer> sims;
	private Map<GameMove, Integer> wins;
	private int totalPlays;
	private double expConst;
	
	public UCBPlayer(double expConst) {
		this.expConst = expConst;
		this.sims = new HashMap<>();
		this.wins = new HashMap<>();
	}

	@Override
	public String getName() {
		return "ucb";
	}
	
	//stolen from piers
	public double getUCB(GameMove move) {
		Integer sims = this.sims.get(move);
		Integer wins = this.wins.get(move);
		
		if (sims == null) {
			sims = 1;
		}
		
		if (wins == null) {
			wins = 1;
		}
		
		return (wins*1.0)/sims + (Math.sqrt(expConst * Math.log(totalPlays) / sims));
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
		sims.clear();
		wins.clear();
		totalPlays = 3; //TODO this isn't correct but zero breaks it
	}

	@Override
	public void onRoundEnd(GameMove ours, GameMove theirs, double score) {
		incMap(ours, sims);
		
		if (score >= 1) {
			incMap(ours, wins);
		}
		totalPlays++;
	}
	
	private void incMap(GameMove move, Map<GameMove,Integer> map) {
		Integer current = map.get(move);
		if (current == null) {
			current = 0;
		}
		sims.put(move, current+1);
	}

}
