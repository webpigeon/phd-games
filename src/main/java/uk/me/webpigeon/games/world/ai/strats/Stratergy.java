package uk.me.webpigeon.games.world.ai.strats;

import uk.me.webpigeon.games.world.Entity;
import uk.me.webpigeon.games.world.WorldView;
import uk.me.webpigeon.games.world.moves.OperInstance;


public interface Stratergy<T> {
	public OperInstance update(Entity entity, WorldView world);
}
