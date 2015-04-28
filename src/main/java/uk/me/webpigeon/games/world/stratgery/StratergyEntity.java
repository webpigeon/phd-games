package uk.me.webpigeon.games.world.stratgery;

import java.awt.Point;
import java.util.Collection;

import uk.me.webpigeon.games.world.ComponentEntity;
import uk.me.webpigeon.games.world.Entity;
import uk.me.webpigeon.games.world.World;

public class StratergyEntity extends ComponentEntity {
	private Stratergy<Entity> strat;
	
	public StratergyEntity(int x, int y, Stratergy<Entity> strat) {
		super(x, y);
		this.strat = strat;
		strat.bind(this);
	}

	public void update(World world) {
		super.update(world);
		strat.update(world);
	}
	
	public Collection<Point> getInterestingPoints() {
		return strat.getPoints();
	}
}
