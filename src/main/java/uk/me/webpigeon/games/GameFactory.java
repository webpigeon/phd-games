package uk.me.webpigeon.games;

import uk.me.webpigeon.agents.FixedMove;
import uk.me.webpigeon.agents.RandomMove;
import uk.me.webpigeon.agents.RotationPlayer;
import uk.me.webpigeon.ipd.IPDGame;
import uk.me.webpigeon.rps.FrequencyPredictor;
import uk.me.webpigeon.rps.NaiveCounterAgent;
import uk.me.webpigeon.rps.RandomPredictor;
import uk.me.webpigeon.games.agents.FrequencyBlocker;
import uk.me.webpigeon.games.modeling.ModelingAgent;
import uk.me.webpigeon.ipd.IPDGame;
import uk.me.webpigeon.rps.RockPaperScissors;
import uk.me.webpigeon.rps.UCBPlayer;

public class GameFactory {

	public static WebpigeonGame buildRPS() {
		WebpigeonGame rps = new RockPaperScissors(new DataLogger());
		return rps;
	}
	
	public static WebpigeonGame buildIPD() {
		WebpigeonGame ipd = new IPDGame(new DataLogger());
		return ipd;
	}
		

	public static void addAgents(WebpigeonGame game) {
		game.addAgent(new FixedMove(0));
		game.addAgent(new FixedMove(1));
		game.addAgent(new FixedMove(2));
		game.addAgent(new RotationPlayer(1));
		game.addAgent(new RotationPlayer(2));
		game.addAgent(new RotationPlayer(3));
		game.addAgent(new UCBPlayer(2.0));
		
		game.addAgent(new NaiveCounterAgent(new RandomPredictor()));
		game.addAgent(new NaiveCounterAgent(new FrequencyPredictor()));
	}
	
}
