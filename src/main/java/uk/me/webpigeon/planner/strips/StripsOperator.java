package uk.me.webpigeon.planner.strips;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import uk.me.webpigeon.planner.Action;
import uk.me.webpigeon.planner.Atom;
import uk.me.webpigeon.planner.Operator;
import uk.me.webpigeon.planner.State;

public class StripsOperator implements Operator {
	private String name;
	private String[] vars;
	private List<Atom> conditions;
	private List<Atom> additions;
	private List<Atom> removals;
	
	public StripsOperator(String name, String ... vars) {
		this.name = name;
		this.vars = vars;
		this.conditions = new ArrayList<Atom>();
		this.additions = new ArrayList<Atom>();
		this.removals = new ArrayList<Atom>();
	}
	
	public void addPrecondition(Atom atom){
		conditions.add(atom);
	}
	
	public void addAddition(Atom atom){
		additions.add(atom);
	}
	
	public void addRemoval(Atom atom) {
		removals.add(atom);
	}

	@Override
	public State apply(State s, Map<String, String> bindings) {
		assert isApplicable(s);
		
		State newState = new State(s);
		for (Atom atom : additions) {			
			newState.addAtom(bind(atom, bindings));
		}
		
		for (Atom atom : removals) {
			newState.removeAtom(bind(atom, bindings));
		}
		
		return newState;
	}

	public boolean isApplicable(State s) {
		for (Atom atom : conditions) {
			if (!s.contains(atom)) {
				System.out.println("bailing on "+atom+" for "+s);
				return false;
			}
		}
		
		return true;
	}

	@Override
	public State getContitons(Map<String, String> bindings) {
		State required = new State();
		for (Atom atom : conditions) {
			required.addAtom(bind(atom, bindings));
		}
		
		return required;
	}
	
	private Atom bind(Atom prototype, Map<String, String> bindings) {
		String[] args = new String[prototype.args.length];
		for (int i=0; i<args.length; i++) {
			args[i] = bindings.getOrDefault(prototype.args[i], prototype.args[i]);
		}
		return new Atom(prototype.predicate, args);
	}
	
	public String[] bind(Map<String, String> bindings) {
		String[] args = new String[vars.length];
		for (int i=0; i<args.length; i++) {
			args[i] = bindings.getOrDefault(vars[i], vars[i]);
		}
		return args;
	}
	
	public String toString() {
		return name + Arrays.toString(vars);
	}

	@Override
	public List<Action> ground(State s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Action ground(String ... args) {
		if (args.length != vars.length) {
			throw new RuntimeException(this.toString()+": I need "+vars.length+" vars, got "+Arrays.toString(args));
		}
		
		assert args.length == vars.length;
		Action action = new Action(this);
		
		for (int i=0; i<args.length; i++) {
			action.bind(vars[i], args[i]);
		}
		
		return action;
	}

	@Override
	public Action ground(Atom goalAtom) {
		String[] args = goalAtom.args;
		if (args.length != vars.length) {
			
			args = new String[vars.length];
			for (int i=0; i<args.length; i++) {
				if (goalAtom.args.length < i) {
					args[i] = goalAtom.args[i];
				} else {
					args[i] = null;
				}
			}
			
		}
		
		return ground(args);
	}

	@Override
	public boolean hasResult(Atom goalAtom) {
		for (Atom addition : additions) {
			if (addition.predicate.equals(goalAtom.predicate)) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public int getArgCount() {
		return vars.length;
	}

	@Override
	public String getName() {
		return name;
	}
	
}
