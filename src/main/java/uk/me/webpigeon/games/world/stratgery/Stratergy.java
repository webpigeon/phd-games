package uk.me.webpigeon.games.world.stratgery;

import java.awt.Point;
import java.util.Collection;

import uk.me.webpigeon.games.world.World;
import uk.me.webpigeon.games.world.WorldView;


public interface Stratergy<T> {
	
	public void bind(T target);
	public void update(WorldView world);
	public Collection<Point> getPoints();
	
}
