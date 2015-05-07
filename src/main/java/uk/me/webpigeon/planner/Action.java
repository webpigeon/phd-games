package uk.me.webpigeon.planner;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Action {
	private final Operator operator;
	private final Map<String, String> bindings;
	
	public Action(Operator operator) {
		this.operator = operator;
		this.bindings = new TreeMap<String, String>();
	}
	
	public void bind(String from, String to) {
		bindings.put(from, to);
	}
	
	public State getContitons() {
		return operator.getContitons(bindings);
	}

	public State apply(State s) {
		return operator.apply(s, bindings);
	}
	
	public String toString() {
		return operator.getName()+Arrays.toString(operator.bind(bindings));
	}

}
