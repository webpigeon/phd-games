package uk.me.webpigeon.planner.world;

import java.awt.Point;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import uk.me.webpigeon.games.world.Entity;
import uk.me.webpigeon.games.world.WorldView;
import uk.me.webpigeon.games.world.ai.strats.Stratergy;
import uk.me.webpigeon.games.world.moves.OperInstance;
import uk.me.webpigeon.planner.Action;
import uk.me.webpigeon.planner.Atom;
import uk.me.webpigeon.planner.Plan;
import uk.me.webpigeon.planner.Planner;
import uk.me.webpigeon.planner.State;

public class PlanningStrat implements Stratergy<Entity> {
	private Planner<GameOperator> planner;
	private Entity entity;
	private List<GameOperator> operators;
	
	private Plan plan;
	private Iterator<Action> actionItr;
	private Action currentAction;
	
	public PlanningStrat(Planner<GameOperator> planner, List<GameOperator> operators) {
		this.planner = planner;
		this.operators = operators;
		this.plan = null;
	}

	@Override
	public OperInstance update(Entity entity, WorldView world) {
		if (plan == null || actionItr == null) {
			WorldState state = new WorldState(world, entity);
			
			State goal = new State();
			goal.addAtom(new Atom("at", "10", "10"));
			
			plan = planner.plan(operators, state, goal);
			if (plan == null) {
				return null;
			}
			actionItr = plan.nextAction();
			System.out.println(plan);
		}
		
		if (!actionItr.hasNext()) {
			plan = null;
			actionItr = null;
			return null;
		}
		
		currentAction = actionItr.next();
		return null;
	}

}
