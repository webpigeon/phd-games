package uk.me.webpigeon.games.world.moves;

import java.awt.Point;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
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
		public List<String> tick(Entity entity, WorldView world) {
			if (path == null) {
				path = PathFinder.getPath(world, entity.getPosition(), target);
				if (path == null || path.isEmpty()) {
					target = null;
				}
			}
			
			Point nextPoint = path.poll();
			if (nextPoint == null) {
				path = null;
				return Collections.emptyList();
			}
			
			entity.move(nextPoint);
			world.updateView(entity);
			return Arrays.asList("at("+entity+","+nextPoint.getX()+","+nextPoint.getY()+")");
		}
		
		public String toString() {
			if (target == null) {
				return "walk to nowhere";
			}
			
			return "walk to ("+target.getX()+","+target.getY()+")";
		}
	}
	
}
