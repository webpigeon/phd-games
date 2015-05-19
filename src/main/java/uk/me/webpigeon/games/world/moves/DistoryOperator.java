package uk.me.webpigeon.games.world.moves;

import java.awt.Point;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import uk.me.webpigeon.games.world.Cell;
import uk.me.webpigeon.games.world.Entity;
import uk.me.webpigeon.games.world.World;
import uk.me.webpigeon.games.world.WorldView;

public class DistoryOperator extends WorldOperation {
	
	public DistoryOperator() {
		super("remove");
	}
	
	public boolean canApply(Cell cell, Collection<Entity> entities) {
		return cell != null && cell.walkable;
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
		public List<String> tick(Entity entity, WorldView world) {
			if (ticksPassed < buildTime) {
				ticksPassed++;
				return Arrays.asList("sound(x,y,hammer)");
			}
			
			Cell cell = world.getCellAt(target.x, target.y);
			cell.walkable = false;
			ticksPassed++;
			return Arrays.asList("cell(x,y,false)");
		}
	}
	
}
