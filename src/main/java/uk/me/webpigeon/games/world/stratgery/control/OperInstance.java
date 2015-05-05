package uk.me.webpigeon.games.world.stratgery.control;

import uk.me.webpigeon.games.world.Entity;
import uk.me.webpigeon.games.world.WorldView;

public abstract class OperInstance {
	
	public abstract boolean isComplete();
	public abstract void tick(Entity entity, WorldView world);

}
