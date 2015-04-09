package uk.me.webpigeon.games.world.stratgery;

import java.awt.Point;
import java.util.Collection;


public interface Stratergy<T> {
	
	public void bind(T target);
	public void update();
	public Collection<Point> getPoints();
	
}
