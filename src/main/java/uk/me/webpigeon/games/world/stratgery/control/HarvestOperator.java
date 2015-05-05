package uk.me.webpigeon.games.world.stratgery.control;

import java.awt.Point;
import java.util.Collection;
import java.util.List;

import uk.me.webpigeon.games.world.Cell;
import uk.me.webpigeon.games.world.Entity;
import uk.me.webpigeon.games.world.WorldView;

public class HarvestOperator extends Operator {
		
		public HarvestOperator() {
			super("harvest");
		}
		
		public boolean canApply(Cell cell, Collection<Entity> entities) {
			for (Entity entity : entities) {
				//TODO implement harvest detection
				return false;
			}
				
			return false;
		}

		@Override
		public OperInstance apply(Point target) {
			return new Instance(target);
		}
		
		protected static class Instance extends OperInstance {
			int TaskTime = 10;
			int ticksPassed = 0;
			Point target;
			
			Instance(Point target) {
				this.target = target;
			}
			
			@Override
			public boolean isComplete() {
				return ticksPassed > TaskTime;
			}
		
			@Override
			public void tick(Entity entity, WorldView world) {
				if (ticksPassed < TaskTime) {
					ticksPassed++;
					return;
				}
				
				List<Entity> targetEntities = world.getEntities(target.x, target.y, 0);
				for (Entity targetEntity : targetEntities) {
					//TODO implement harvesting
				}
				ticksPassed++;
			}
		}
}
