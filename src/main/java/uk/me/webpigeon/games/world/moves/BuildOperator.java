package uk.me.webpigeon.games.world.moves;

import java.awt.Point;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

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
		public Collection<String> tick(Entity entity, WorldView world) {
			if (ticksPassed < buildTime) {
				ticksPassed++;
				return Arrays.asList("sound(x,y,hammer)");
			}
			
			Cell cell = world.getCellAt(target.x, target.y);
			cell.walkable = true;
			ticksPassed++;
			return Arrays.asList("cell(x,y,true)");
		}
		
		@Override
		public String toString() {
			return "build("+target.getX()+","+target.getY()+")";
		}
	}
	
}
