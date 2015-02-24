package uk.me.webpigeon.games;

import uk.me.webpigeon.ipd.IPDGame;
import uk.me.webpigeon.rps.FixedMove;
import uk.me.webpigeon.rps.FrequencyBlocker;
import uk.me.webpigeon.rps.RandomMove;
import uk.me.webpigeon.rps.RockPaperScissors;
import uk.me.webpigeon.rps.RotationPlayer;
import uk.me.webpigeon.rps.UCBPlayer;

public class GameFactory {
	
	public static Game buildRPS() {
		Game rps = new RockPaperScissors();
		rps.addAgent(new FixedMove(RockPaperScissors.ROCK));
		rps.addAgent(new FixedMove(RockPaperScissors.PAPER));
		rps.addAgent(new FixedMove(RockPaperScissors.SCISSORS));
		rps.addAgent(new RotationPlayer(1));
		rps.addAgent(new RotationPlayer(2));
		rps.addAgent(new RotationPlayer(3));
		//rps.addAgent(new RandomMove());
		rps.addAgent(new UCBPlayer(2.0, RockPaperScissors.moves));
		rps.addAgent(new FrequencyBlocker(RockPaperScissors.moves));
		
		return rps;
	}
	
	public static Game buildIPD() {
		Game ipd = new IPDGame();
		ipd.addAgent(new FixedMove(IPDGame.COOP));
		ipd.addAgent(new FixedMove(IPDGame.DEFECT));
		ipd.addAgent(new RotationPlayer(1));
		ipd.addAgent(new RotationPlayer(2));
		ipd.addAgent(new RandomMove());
		ipd.addAgent(new UCBPlayer(2.0, IPDGame.MOVES));
		//ipd.addAgent(new FrequencyBlocker(IPDGame.MOVES));
		
		return ipd;
	}

}
