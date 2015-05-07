package uk.me.webpigeon.planner.strips;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uk.me.webpigeon.planner.Action;
import uk.me.webpigeon.planner.Atom;
import uk.me.webpigeon.planner.IternetCode;
import uk.me.webpigeon.planner.Operator;
import uk.me.webpigeon.planner.Plan;
import uk.me.webpigeon.planner.Planner;
import uk.me.webpigeon.planner.State;

public class StripsPlanner implements Planner {

	@Override
	public Plan plan(List<Operator> operators, State s, State goal) {
		Plan p = new Plan();
		
			for (Atom goalAtom : goal.getAtoms()) {
				if (!s.contains(goalAtom)) {
					System.out.println("proving atom: "+goalAtom + " for "+goal);
					//select an action from the list of legal actions
					List<Action> actions = getRelevent(operators, goalAtom);
					if (actions.isEmpty()) {
						System.out.println("no action can generate "+goalAtom);
						return Plan.FAILURE;
					}
					
					// get subgoal details
					Action action = select(actions);
					System.out.println("action "+action+" can probably help...");
					Plan pPrime = plan(operators, s, action.getContitons());
					if (pPrime == Plan.FAILURE) {
						return pPrime;
					}
					
					s = pPrime.apply(s);
					s = action.apply(s);
					
					p = (p.concat(pPrime)).concat(action);
				}
			}
		
		return p;
	}
	
	public List<Action> getRelevent(List<Operator> operators, Atom goalAtom) {
		List<Action> actions = new ArrayList<Action>();
		
		for (Operator operator : operators) {
			if (operator.hasResult(goalAtom)) {
				actions.add(operator.ground(goalAtom));
			}
		}
		
		return actions;
	}
	
	private Action select(List<Action> actions) {
		System.out.println(actions);
		return actions.get(0);
	}
	
	public List<List<String>> permute(int size, String ... argsList) {
		List<String> args = new ArrayList<String>();
		args.addAll(Arrays.asList(argsList));
		return IternetCode.permute(args, size);
	}

	public List<List<String>> permute(String ... argsList) {
		List<String> args = new ArrayList<String>();
		args.addAll(Arrays.asList(argsList));
		return IternetCode.generatePerm(args);
	}
}
