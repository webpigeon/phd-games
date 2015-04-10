package uk.me.webpigeon.games.world;

import java.awt.Point;
import java.util.Collection;
import java.util.Collections;

public class Entity {
	protected Point position;
	protected World world;
	
	public Entity(int x, int y) {
		this.position = new Point(x, y);
	}

	public int getY() {
		return position.y;
	}
	
	public int getX() {
		return position.x;
	}

	public void update() {
	}
	
	public Collection<Point> getInterestingPoints() {
		return Collections.emptyList();
	}

	public void bind(World world) {
		this.world = world;		
	}

	public World getWorld() {
		return world;
	}

	public Point getPosition() {
		return new Point(position);
	}

	public void move(Point nextPos) {
		this.position = nextPos;
	}

}
