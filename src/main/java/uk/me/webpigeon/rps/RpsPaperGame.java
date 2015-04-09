package uk.me.webpigeon.rps;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import uk.me.webpigeon.games.AbstractPaperGame;
import uk.me.webpigeon.games.Agent;
import uk.me.webpigeon.games.GameMove;
import uk.me.webpigeon.games.GameState;
import uk.me.webpigeon.games.PaperGame;

public class RpsPaperGame extends AbstractPaperGame {
	private final Double[][] payoffs;
	private final GameMove[] legalMoves;
	private final Integer rounds;
	private final GameState start;
	private final GameState end;
	
	private int roundsLeft;
	
	public RpsPaperGame(int rounds) {
		this.rounds = rounds;
		this.start = new BasicState("start", false);
		this.end = new BasicState("end", true);
		this.legalMoves = new GameMove[]{Moves.ROCK, Moves.PAPER, Moves.SCISSORS};
		this.payoffs = new Double[][] {
				{0.0,0.0}, {-1.0,1.0}, {1.0,-1.0},
				{1.0,-1.0}, {0.0,0.0}, {-1.0,1.0},
				{-1.0,1.0}, {1.0,-1.0}, {0.0,0.0},
		};
	}
	
	@Override
	public void setup(Agent ... players) {
		super.setup(players);
		this.roundsLeft = rounds;
	}

	@Override
	public Collection<GameMove[]> getPossibleMoves() {
		GameMove[][] moves = new GameMove[][]{
				{Moves.ROCK, Moves.ROCK}, {Moves.ROCK, Moves.PAPER}, {Moves.ROCK, Moves.SCISSORS},
				{Moves.PAPER, Moves.ROCK}, {Moves.PAPER, Moves.PAPER}, {Moves.PAPER, Moves.SCISSORS},
				{Moves.SCISSORS, Moves.ROCK}, {Moves.SCISSORS, Moves.PAPER}, {Moves.SCISSORS, Moves.SCISSORS},
		};
		
		return Arrays.asList(moves);
	}

	@Override
	public List<? extends GameState> getStates() {
		return Arrays.asList(start, end);
	}

	@Override
	public GameState transition(GameState startingState, GameMove ... actionPair) {
		return end;
	}

	@Override
	public Double[] getPayoff(GameState s, GameMove ... actions) {
		int caseID = getCase(actions);
		return payoffs[caseID];
	}
	
	@Override
	public GameState executeRound() {
		roundsLeft--;
		
		GameMove[] actions = new GameMove[players.length];
		for (int i=0; i<actions.length; i++) {
			actions[i] = players[i].getMove(legalMoves);
		}
		
		Double[] payoffs = getPayoff(start, actions);
		for (int i=0; i<scores.length; i++) {
			scores[i] += payoffs[i];
		}
		
		if (roundsLeft > 0) {
			return start;
		} else {
			return end;
		}
	}

	protected int getCase(GameMove[] actions) {
		return actions[0].getID() * 3 + actions[1].getID();
	}
	
}
