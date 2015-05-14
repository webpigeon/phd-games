package uk.me.webpigeon.planner;

import java.util.List;

public interface Planner <T extends Operator> {
	
	public Plan plan(List<T> Operators, State s, State goal);

}
