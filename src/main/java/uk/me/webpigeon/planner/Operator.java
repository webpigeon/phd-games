package uk.me.webpigeon.planner;

import java.util.List;
import java.util.Map;

public interface Operator {

	public String getName();
	
	public List<Action> ground(State goalAtom);
	public Action ground(String ... atoms);

	boolean isApplicable(State s);

	boolean hasResult(Atom goalAtom);
	
	State getContitons(Map<String, String> bindings);
	State apply(State s, Map<String, String> replacements);
	public Action ground(Atom goalAtom);
	
	public int getArgCount();
	public String[] bind(Map<String, String> bindings);

}
