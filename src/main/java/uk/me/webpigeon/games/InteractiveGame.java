package uk.me.webpigeon.games;

import uk.me.webpigeon.agents.HumanAgent;
import uk.me.webpigeon.agents.RotationPlayer;

public class InteractiveGame {
	
	public static void main(String[] args) {
		WebpigeonGame game = GameFactory.buildRPS();
		
		HumanAgent human = new HumanAgent();
		GameAgent agent = new RotationPlayer(1);
		
		while(true) {
			game.playRound(human, agent);
		}
	}

}
