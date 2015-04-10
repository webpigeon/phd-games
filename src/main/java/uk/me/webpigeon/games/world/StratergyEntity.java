package uk.me.webpigeon.games.world;

import java.awt.Point;
import java.util.Collection;

import uk.me.webpigeon.games.world.stratgery.Stratergy;

public class StratergyEntity extends Entity {
	private Stratergy<Entity> strat;
	
	public StratergyEntity(int x, int y, Stratergy<Entity> strat) {
		super(x, y);
		this.strat = strat;
		strat.bind(this);
	}

	public void update() {
		super.update();
		strat.update();
	}
	
	public Collection<Point> getInterestingPoints() {
		return strat.getPoints();
	}
}
