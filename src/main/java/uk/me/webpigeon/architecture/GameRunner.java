package uk.me.webpigeon.architecture;

import uk.me.webpigeon.architecture.basics.HumanController;
import uk.me.webpigeon.architecture.basics.RandomController;
import uk.me.webpigeon.architecture.basics.TwoPlayerGame;
import uk.me.webpigeon.newgames.rps.RockPaperScissors;

public class GameRunner {

	public static void main(String[] args) {
		
		Controller human = new HumanController();
		Controller random = new RandomController();
		
		TwoPlayerGame rps = new RockPaperScissors();
		rps.setPlayers(human, random);
		
		GameEngine engine = new GameEngine(rps);
		Thread gameThread = new Thread(engine);
		gameThread.start();

	}

}
