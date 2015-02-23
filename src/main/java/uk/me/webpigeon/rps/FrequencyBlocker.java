package uk.me.webpigeon.rps;

public class FrequencyBlocker implements RpsAgent {
	private int[] freqs;
	
	public FrequencyBlocker(){
		Move[] m = Move.values();
		this.freqs = new int[m.length];
	}

	@Override
	public String getName() {
		return "freqblock";
	}

	@Override
	public Move getMove() {
		
		int bestOpt = 0;
		int bestFreq = 0;
		
		for (int i=0; i<freqs.length; i++) {
			if (freqs[i] > bestFreq) {
				bestOpt = i;
				bestFreq = freqs[i];
			}
		}
		
		Move[] moves = Move.values();
		Move oppBestMove = moves[bestOpt];
		return getOppMove(oppBestMove);
	}

	@Override
	public void onGameOver(Move player1, Move player2, int playerID, WinningPlayer winner) {
		Move oppMove = playerID==1?player2:player1;
		freqs[oppMove.ordinal()]++;
	}

	@Override
	public void newOpponent() {
		Move[] m = Move.values();
		this.freqs = new int[m.length];
	}

	public String toString() {
		return "rps-"+getName();
	}
	
	public Move getOppMove(Move curr) {
		int inverse = (curr.ordinal() + 1) % 3;
		return Move.values()[inverse];
	}
}
