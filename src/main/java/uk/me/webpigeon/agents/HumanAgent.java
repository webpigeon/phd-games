package uk.me.webpigeon.agents;

import java.util.Arrays;
import java.util.Scanner;

import uk.me.webpigeon.games.GameAgent;
import uk.me.webpigeon.games.GameMove;

public class HumanAgent implements GameAgent {
	private Scanner scanner;
	
	public HumanAgent() {
		this.scanner = new Scanner(System.in);
	}
	
	@Override
	public String getName() {
		return "human";
	}

	@Override
	public GameMove getMove(GameMove[] moves) {
		System.out.println("legal moves: "+Arrays.toString(moves));
		
		String userInput = scanner.nextLine();
		for (GameMove move : moves) {
			if (move.isMove(userInput)) {
				return move;
			}
		}
		
		System.out.println("unknown move");
		return null;
	}

	@Override
	public void onGameStart() {
	}

	@Override
	public void onRoundEnd(GameMove ours, GameMove theirs, double score) {
		// TODO Auto-generated method stub
		
	}

}
