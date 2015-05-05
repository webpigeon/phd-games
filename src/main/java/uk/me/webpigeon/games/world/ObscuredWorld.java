package uk.me.webpigeon.games.world;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ObscuredWorld implements WorldView {
	private WorldView parent;
	private Set<Point> visited;
	private Point current;
	private int range = 10;
	
	public ObscuredWorld(WorldView parent) {
		this.parent = parent;
		this.visited = new HashSet<Point>();
	}

	@Override
	public int getWidth() {
		return parent.getWidth();
	}

	@Override
	public int getHeight() {
		return parent.getHeight();
	}

	@Override
	public Cell getCellAt(int x, int y) {
		if (!visited.contains(new Point(x, y))) {
			return null;
		}
		
		return parent.getCellAt(x, y);
	}

	@Override
	public List<Entity> getEntities(int x, int y, double range) {
		if (!visited.contains(new Point(x, y))) {
			return Collections.emptyList();
		}
		
		return parent.getEntities(x, y, range);
	}
	
	public List<Entity> getEntities() {
		if (current == null) {
			return Collections.emptyList();
		}
		
		return getEntities(current.x, current.y, range);
	}

	public void updateView(Entity entity) {
		current = entity.getPosition();
		
		for (Point p : buildRadius(current.x, current.y, range)) {
			visited.add(p);
		}
	}
	
	public Collection<Point> buildRadius(int x, int y, int range) {
		Collection<Point> points = new ArrayList<Point>();
		
		for (int i = -range; i<=range; i++) {
			for (int j = -range; j<=range; j++) {
				points.add(new Point(i+x, j+y));
			}
		}
		
		return points;
	}

	@Override
	public boolean isVisible(int x, int y) {
		return current.distance(new Point(x,y)) < range;
	}
	
}
