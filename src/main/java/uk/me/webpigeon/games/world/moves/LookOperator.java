package uk.me.webpigeon.games.world.moves;

import java.awt.Point;
import java.util.Collection;
import java.util.Collections;

import uk.me.webpigeon.games.world.Cell;
import uk.me.webpigeon.games.world.Entity;
import uk.me.webpigeon.games.world.WorldView;

public class LookOperator extends WorldOperation {

	public LookOperator() {
		super("look");
	}

	@Override
	public boolean canApply(Cell cell, Collection<Entity> entities) {
		return cell != null;
	}

	@Override
	public OperInstance apply(Point target) {
		return new Instance(target);
	}
	
	protected static class Instance extends OperInstance {
		boolean hasLooked = false;
		Point target;
		
		Instance(Point target) {
			this.target = target;
		}
		
		@Override
		public boolean isComplete() {
			return hasLooked;
		}
	
		@Override
		public Collection<String> tick(Entity entity, WorldView world) {
			Cell cell = world.getCellAt(target.x, target.y);
			Collection<Entity> entityList = world.getEntities(target.x, target.y, 1);
			System.out.println(cell+", you see: "+entityList);
			hasLooked = true;
			return Collections.emptyList();
		}
	}

}
