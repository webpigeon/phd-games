package uk.me.webpigeon.games.world.ai.strats;

import java.awt.Point;
import java.util.Random;

import uk.me.webpigeon.games.world.Entity;
import uk.me.webpigeon.games.world.WorldView;
import uk.me.webpigeon.games.world.moves.MoveOperator;
import uk.me.webpigeon.games.world.moves.OperInstance;

public class RandomPathfind extends OperatorStratergy {
	protected final Random random;
	private final MoveOperator move;
	
	public RandomPathfind() {
		this.move = new MoveOperator();
		this.random = new Random();
	}
	
	@Override
	public OperInstance update(Entity entity, WorldView world) {
		if (action == null || action.isComplete()) {
			Point goal = getNextTarget(entity, world);
			if (goal != null) {
				action = move.apply(goal);
			}
		}
		
		return super.update(entity, world);
	}
	
	public Point getNextTarget(Entity avatar, WorldView world) {
		return new Point(random.nextInt(world.getWidth()), random.nextInt(world.getHeight()));
	}

	public String toString() {
		return "walkBehavoiur";
	}
}
