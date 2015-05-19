package uk.me.webpigeon.games.world.moves;

import java.util.Collection;

import uk.me.webpigeon.games.world.Entity;
import uk.me.webpigeon.games.world.WorldView;

public abstract class OperInstance {
	
	public abstract boolean isComplete();
	public abstract Collection<String> tick(Entity entity, WorldView world);

}
