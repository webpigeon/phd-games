package uk.me.webpigeon.games;

import uk.me.webpigeon.games.agents.FixedMove;
import uk.me.webpigeon.games.agents.FrequencyBlocker;
import uk.me.webpigeon.games.agents.RandomMove;
import uk.me.webpigeon.games.agents.RotationPlayer;
import uk.me.webpigeon.games.modeling.FrequencyPredictor;
import uk.me.webpigeon.games.modeling.ModelingAgent;
import uk.me.webpigeon.ipd.IPDGame;
import uk.me.webpigeon.rps.RockPaperScissors;
import uk.me.webpigeon.rps.UCBPlayer;

public class GameFactory {
	
	public static Game buildRPS() {
		Game rps = new RockPaperScissors(new DataLogger());
		rps.addAgent(new FixedMove(RockPaperScissors.ROCK));
		rps.addAgent(new FixedMove(RockPaperScissors.PAPER));
		rps.addAgent(new FixedMove(RockPaperScissors.SCISSORS));
		rps.addAgent(new RotationPlayer(1));
		rps.addAgent(new RotationPlayer(2));
		rps.addAgent(new RotationPlayer(3));
		rps.addAgent(new ModelingAgent(new FrequencyPredictor()));
		//rps.addAgent(new RandomMove());
		rps.addAgent(new UCBPlayer(2.0, RockPaperScissors.moves));
		rps.addAgent(new FrequencyBlocker(RockPaperScissors.moves));
		
		return rps;
	}
	
	public static Game buildIPD() {
		Game ipd = new IPDGame(new DataLogger());
		ipd.addAgent(new FixedMove(IPDGame.COOP));
		ipd.addAgent(new FixedMove(IPDGame.DEFECT));
		ipd.addAgent(new RotationPlayer(1));
		ipd.addAgent(new RotationPlayer(2));
		ipd.addAgent(new ModelingAgent(new FrequencyPredictor()));
		ipd.addAgent(new RandomMove());
		ipd.addAgent(new UCBPlayer(2.0, IPDGame.MOVES));
		//ipd.addAgent(new FrequencyBlocker(IPDGame.MOVES));
		
		return ipd;
	}

}
