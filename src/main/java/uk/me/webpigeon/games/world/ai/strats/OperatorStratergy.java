package uk.me.webpigeon.games.world.ai.strats;

import uk.me.webpigeon.games.world.Entity;
import uk.me.webpigeon.games.world.WorldView;
import uk.me.webpigeon.games.world.moves.OperInstance;

public class OperatorStratergy implements Stratergy<Entity> {
	protected OperInstance action;

	@Override
	public OperInstance update(Entity entity, WorldView world) {
		if (action != null && action.isComplete()) {
			action = null;
		}
		
		return action;
	}

	public void setAction(OperInstance instance) {
		this.action = instance;
	}
	
	@Override
	public String toString() {
		if (action == null) {
			return "Doing Nothing";
		}
		return action.toString();
	}

}
