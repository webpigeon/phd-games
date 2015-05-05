package uk.me.webpigeon.games.world.stratgery.control;

import java.awt.Point;
import java.util.Collection;

import uk.me.webpigeon.games.world.Entity;
import uk.me.webpigeon.games.world.WorldView;
import uk.me.webpigeon.games.world.stratgery.Stratergy;

public class OperatorStratergy implements Stratergy<Entity> {
	private Entity entity;
	private OperInstance action;

	@Override
	public void bind(Entity entity) {
		this.entity = entity;
	}

	@Override
	public void update(WorldView world) {
		if (action == null) {
			return;
		}
		
		if (action.isComplete()) {
			action = null;
			return;
		}
		
		action.tick(entity, world);
	}

	public void setAction(OperInstance instance) {
		this.action = instance;
	}
	
	@Override
	public Collection<Point> getPoints() {
		return null;
	}

}
