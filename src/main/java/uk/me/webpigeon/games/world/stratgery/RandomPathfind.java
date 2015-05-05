package uk.me.webpigeon.games.world.stratgery;

import java.awt.Point;
import java.util.Collection;
import java.util.Collections;
import java.util.Queue;
import java.util.Random;

import uk.me.webpigeon.games.world.Entity;
import uk.me.webpigeon.games.world.World;
import uk.me.webpigeon.games.world.WorldView;
import uk.me.webpigeon.games.world.ai.PathFinder;

public class RandomPathfind implements Stratergy<Entity> {
	private Entity entity;
	private Queue<Point> path;
	private Random random;
	
	public void bind(Entity entity) {
		this.entity = entity;
		this.random = new Random();
	}
	
	@Override
	public void update(WorldView world) {
		if (path == null || path.isEmpty()) {
			Point goal = new Point(random.nextInt(world.getWidth()), random.nextInt(world.getHeight()));
			
			path = PathFinder.getPath(world, entity.getPosition(), goal);
		}
		
		Point nextPos = path.poll();
		if (nextPos != null) {
			entity.move(nextPos);
		}
	}

	@Override
	public Collection<Point> getPoints() {
		if (path == null) {
			return null;
		}
		
		return Collections.synchronizedCollection(path);
	}

	public String toString() {
		return "walkBehavoiur";
	}
}
