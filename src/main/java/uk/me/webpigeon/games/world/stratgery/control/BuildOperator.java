package uk.me.webpigeon.games.world.stratgery.control;

import java.awt.Point;
import java.util.Collection;

import uk.me.webpigeon.games.world.Cell;
import uk.me.webpigeon.games.world.Entity;
import uk.me.webpigeon.games.world.World;
import uk.me.webpigeon.games.world.WorldView;

public class BuildOperator extends WorldOperation {
	
	public BuildOperator() {
		super("build");
	}
	
	public boolean canApply(Cell cell, Collection<Entity> entities) {
		if (cell == null) {
			return false;
		}
		return !cell.walkable;
	}

	@Override
	public OperInstance apply(Point target) {
		return new Instance(target);
	}
	
	protected static class Instance extends OperInstance {
		int buildTime = 10;
		int ticksPassed = 0;
		Point target;
		
		Instance(Point target) {
			this.target = target;
		}
		
		@Override
		public boolean isComplete() {
			return ticksPassed > buildTime;
		}
	
		@Override
		public void tick(Entity entity, WorldView world) {
			if (ticksPassed < buildTime) {
				ticksPassed++;
				return;
			}
			
			Cell cell = world.getCellAt(target.x, target.y);
			cell.walkable = true;
			ticksPassed++;
		}
	}
	
}
