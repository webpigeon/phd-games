package uk.me.webpigeon.games.world.stratgery.control;

import java.awt.Point;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import uk.me.webpigeon.games.world.Cell;
import uk.me.webpigeon.games.world.Entity;
import uk.me.webpigeon.games.world.World;
import uk.me.webpigeon.games.world.WorldView;
import uk.me.webpigeon.games.world.ai.PathFinder;

public class MoveOperator extends WorldOperation {
	
	public MoveOperator() {
		super("move");
	}
	
	public boolean canApply(Cell cell, Collection<Entity> entities) {
		return cell != null && cell.walkable;
	}

	@Override
	public OperInstance apply(Point target) {
		return new Instance(target);
	}
	
	protected static class Instance extends OperInstance {
		Point target;
		Queue<Point> path;
		
		Instance(Point target) {
			this.target = target;
		}
		
		@Override
		public boolean isComplete() {
			return target == null || (path != null && path.isEmpty());
		}
	
		@Override
		public void tick(Entity entity, WorldView world) {
			if (path == null) {
				path = PathFinder.getPath(world, entity.getPosition(), target);
				if (path == null || path.isEmpty()) {
					System.out.println("I don't know how to get there, canceling action");
					target = null;
				}
			}
			
			Point nextPoint = path.poll();
			if (nextPoint == null) {
				path = null;
				return;
			}
			entity.move(nextPoint);
			world.updateView(entity);
		}
	}
	
}
