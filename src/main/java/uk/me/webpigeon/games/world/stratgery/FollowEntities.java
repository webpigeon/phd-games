package uk.me.webpigeon.games.world.stratgery;

import java.awt.Point;
import java.util.Collection;
import java.util.Collections;
import java.util.Queue;
import java.util.Random;

import uk.me.webpigeon.games.world.Entity;
import uk.me.webpigeon.games.world.World;
import uk.me.webpigeon.games.world.ai.PathFinder;

public class FollowEntities implements Stratergy<Entity> {
	private Entity entity;
	private Queue<Point> path;
	private Random random;
	
	public void bind(Entity entity) {
		this.entity = entity;
		this.random = new Random();
	}
	
	@Override
	public void update(World world) {
		if (path == null || path.isEmpty()) {
			Collection<Entity> entities = world.getEntities(entity.getX(), entity.getY(), 10);
			
			Entity target = null;
			for (Entity entity : entities) {
				if (entity.equals(this.entity)) {
					continue;
				}
				
				if (target == null || random.nextBoolean()) {
					target = entity;
				}
			}
			System.out.println("targeted "+target);
			
			if (target != null) {
				Point goal = new Point(target.getX(), target.getY());
				path = PathFinder.getPath(world, entity.getPosition(), goal);
			}
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
		return "stalkBehavour";
	}
	
}