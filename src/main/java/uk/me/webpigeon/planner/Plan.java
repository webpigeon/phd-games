package uk.me.webpigeon.planner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Immutable Plans, just because ;P
 */
public class Plan {
	public static final Plan FAILURE = null;
	
	private List<Action> actions;
	
	public Plan() {
		this.actions = new ArrayList<Action>();
	}

	public Plan(Plan oldPlan) {
		this();
		actions.addAll(oldPlan.actions);
	}
	
	public State apply(State s) {
		for (Action o : actions) {
			s = o.apply(s);
		}
		return s;
	}

	public Plan concat(Plan pPrime) {
		Plan copyPlan = new Plan(this);
		copyPlan.actions.addAll(pPrime.actions);
		return copyPlan;
	}
	
	public Plan concat(Action action) {
		Plan copyPlan = new Plan(this);
		copyPlan.actions.add(action);
		return copyPlan;
	}
	
	public Iterator<Action> nextAction() {
		return actions.iterator();
	}
	
	@Override
	public String toString() {
		return actions.toString();
	}


}
