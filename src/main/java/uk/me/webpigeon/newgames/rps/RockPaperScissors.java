package uk.me.webpigeon.newgames.rps;

import uk.me.webpigeon.architecture.Action;
import uk.me.webpigeon.architecture.Controller;
import uk.me.webpigeon.architecture.basics.TwoPlayerGame;

public class RockPaperScissors extends TwoPlayerGame {
	
	private final Action[] actions = new Action[]{
			new Action("rock"),
			new Action("paper"),
			new Action("scissors")
	};

	public RockPaperScissors() {
	}

	@Override
	public void setup() {
		super.setup();
	}

	@Override
	public void update(Action p1, Action p2) {
		
	}

	@Override
	public void shutdown() {
		
	}

	@Override
	public Action[] getLegalActions(Controller player) {
		return actions;
	}


}
