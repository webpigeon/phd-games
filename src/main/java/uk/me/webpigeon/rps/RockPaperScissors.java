package uk.me.webpigeon.rps;

import uk.me.webpigeon.games.AbstractGame;
import uk.me.webpigeon.games.Agent;
import uk.me.webpigeon.games.GameLogger;
import uk.me.webpigeon.games.GameMove;

public class RockPaperScissors extends AbstractGame {
	public static final GameMove ROCK = new GameMove(0, "rock");
	public static final GameMove PAPER = new GameMove(1, "paper");
	public static final GameMove SCISSORS = new GameMove(2, "scissors");
	public static final GameMove[] moves = new GameMove[]{ROCK,PAPER,SCISSORS};
	
	public RockPaperScissors(GameLogger logger) {
		super(logger);
	}
	
	public Double[] getScores(GameMove p1Move, GameMove p2Move) {		
		if (p1Move.equals(p2Move)) {
			return new Double[]{0.0,0.0};
		}
		
		if (p1Move.isMove("rock") && p2Move.isMove("scissors")) {
			return new Double[]{1.0,-1.0};
		}
		
		if (p1Move.isMove("paper") && p2Move.isMove("rock")) {
			return new Double[]{1.0,-1.0};
		}
		
		if (p1Move.isMove("scissors") && p2Move.isMove("paper")) {
			return new Double[]{1.0,-1.0};
		}
		
		return new Double[]{-1.0,1.0};
	}

	@Override
	public Double[] playRound(Agent agent1, Agent agent2) {
		GameMove p1Move = agent1.getMove(moves);
		GameMove p2Move = agent2.getMove(moves);
		Double[] scores = getScores(p1Move, p2Move);
		
		agent1.onRoundEnd(p1Move, p2Move, scores[0]);
		agent2.onRoundEnd(p2Move, p1Move, scores[1]);
		
		log(0, agent1, p1Move, agent2, p2Move);
		return scores;
	}
	

}
