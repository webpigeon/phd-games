package uk.me.webpigeon.games.world.stratgery;

import java.awt.Point;
import java.util.Collection;

import uk.me.webpigeon.games.world.World;


public interface Stratergy<T> {
	
	public void bind(T target);
	public void update(World world);
	public Collection<Point> getPoints();
	
}
