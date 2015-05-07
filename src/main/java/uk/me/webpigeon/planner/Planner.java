package uk.me.webpigeon.planner;

import java.util.List;

public interface Planner {
	
	public Plan plan(List<Operator> Operators, State s, State goal);

}
