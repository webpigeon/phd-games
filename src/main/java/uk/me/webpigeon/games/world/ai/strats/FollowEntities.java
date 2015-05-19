package uk.me.webpigeon.games.world.ai.strats;

import java.awt.Point;
import java.util.Collection;
import java.util.Collections;
import java.util.Queue;
import java.util.Random;

import uk.me.webpigeon.games.world.Entity;
import uk.me.webpigeon.games.world.World;
import uk.me.webpigeon.games.world.WorldView;
import uk.me.webpigeon.games.world.ai.PathFinder;
import uk.me.webpigeon.games.world.moves.OperInstance;

public class FollowEntities extends RandomPathfind {

	public Point getNextTarget(Entity avatar, WorldView world) {
		Collection<Entity> entities = world.getEntities(avatar.getX(), avatar.getY(), 10);
		if (entities == null) {
			return null;
		}
		
		Entity target = null;
		for (Entity entity : entities) {
			if (entity.equals(avatar)) {
				continue;
			}
			
			if (target == null || random.nextBoolean()) {
				target = entity;
			}
		}
		
		if (target == null) {
			return null;
		}
		
		return new Point(target.getX(), target.getY());
	}

	public String toString() {
		return "stalkBehavour";
	}
	
}
