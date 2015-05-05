package uk.me.webpigeon.games.world.stratgery;

import java.awt.Point;
import java.util.Collection;

import uk.me.webpigeon.games.world.ComponentEntity;
import uk.me.webpigeon.games.world.Entity;
import uk.me.webpigeon.games.world.World;
import uk.me.webpigeon.games.world.WorldView;

public class StratergyEntity extends ComponentEntity {
	private Stratergy<Entity> strat;
	
	public StratergyEntity(int x, int y, Stratergy<Entity> strat) {
		super(x, y);
		this.strat = strat;
		strat.bind(this);
	}

	@Override
	public void update(WorldView world) {
		super.update(world);
		strat.update(world);
	}
	
	public Collection<Point> getInterestingPoints() {
		return strat.getPoints();
	}
	
	public String toString() {
		return "entity["+strat.toString()+"]";
	}
}
