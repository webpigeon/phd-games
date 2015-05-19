package uk.me.webpigeon.planner.world;

import java.util.List;
import java.util.Map;

import uk.me.webpigeon.games.world.Cell;
import uk.me.webpigeon.games.world.Entity;
import uk.me.webpigeon.games.world.moves.WorldOperation;
import uk.me.webpigeon.planner.Action;
import uk.me.webpigeon.planner.Atom;
import uk.me.webpigeon.planner.Operator;
import uk.me.webpigeon.planner.State;

public class GameOperator implements Operator {
	private WorldOperation operation;
	
	public GameOperator(WorldOperation operation) {
		this.operation = operation;
	}

	@Override
	public String getName() {
		return operation.command;
	}

	@Override
	public List<Action> ground(State goalAtom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Action ground(String... atoms) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isApplicable(State s) {
		WorldState state = (WorldState)s;
		Cell cell = state.getCurrentCell();
		List<Entity> entities = state.getCurrentEntities();
		
		return operation.canApply(cell, entities);
	}

	@Override
	public boolean hasResult(Atom goalAtom) {
		
		
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public State getContitons(Map<String, String> bindings) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public State apply(State s, Map<String, String> replacements) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Action ground(Atom goalAtom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getArgCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String[] bind(Map<String, String> bindings) {
		// TODO Auto-generated method stub
		return null;
	}

}
