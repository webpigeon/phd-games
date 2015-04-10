package uk.me.webpigeon.rps;

import uk.me.webpigeon.games.GameMove;

public class MarkovChainPredictor implements Predictor {
	private Chain[] chains;
	private GameMove[] history;
	private int currID;
	
	public MarkovChainPredictor(int n) {
		this.history = new GameMove[n];
	}

	@Override
	public void recordMoves(GameMove us, GameMove them) {
		history[currID] = them;	
		currID = (currID + 1) % history.length;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GameMove predict(GameMove[] legalMoves) {
		return null;
	}

	
	class Chain {
		public GameMove current;
		public GameMove[] hist;
	}
}
