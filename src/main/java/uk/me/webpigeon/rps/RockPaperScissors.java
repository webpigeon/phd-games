package uk.me.webpigeon.rps;

import uk.me.webpigeon.games.AbstractGame;
import uk.me.webpigeon.games.Agent;
import uk.me.webpigeon.games.GameMove;

public class RockPaperScissors extends AbstractGame {
	public static final GameMove ROCK = new GameMove(0, "rock");
	public static final GameMove PAPER = new GameMove(1, "paper");
	public static final GameMove SCISSORS = new GameMove(2, "scissors");
	public static final GameMove[] moves = new GameMove[]{ROCK,PAPER,SCISSORS};
	
	public WinningPlayer getWinner(GameMove p1Move, GameMove p2Move) {		
		if (p1Move.equals(p2Move)) {
			return WinningPlayer.Draw;
		}
		
		if (p1Move.isMove("rock") && p2Move.isMove("scissors")) {
			return WinningPlayer.Player1;
		}
		
		if (p1Move.isMove("paper") && p2Move.isMove("rock")) {
			return WinningPlayer.Player1;
		}
		
		if (p1Move.isMove("scissors") && p2Move.isMove("paper")) {
			return WinningPlayer.Player1;
		}
		
		return WinningPlayer.Player2;
	}

	@Override
	public Double[] playRound(Agent agent1, Agent agent2) {
		GameMove p1Move = agent1.getMove(moves);
		GameMove p2Move = agent2.getMove(moves);
		WinningPlayer winner = getWinner(p1Move, p2Move);
		
		double p1Score = 0;
		double p2Score = 0;
		if (WinningPlayer.Player1 == winner) {
			p1Score = 1;
			p2Score = -1;
		} else if (WinningPlayer.Player2 == winner) {
			p1Score = -1;
			p2Score = 1;
		} else {
			//it's a draw
			p1Score = 0;
			p1Score = 0;
		}
		
		agent1.onRoundEnd(p1Move, p2Move, p1Score);
		agent2.onRoundEnd(p2Move, p1Move, p2Score);
		
		System.out.println("[G] "+agent1+","+agent2+": "+p1Move+" "+p2Move);
		
		return new Double[]{p1Score, p2Score};
	}
	

}
