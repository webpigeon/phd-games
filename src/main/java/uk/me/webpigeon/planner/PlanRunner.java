package uk.me.webpigeon.planner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import uk.me.webpigeon.planner.strips.StripsOperator;
import uk.me.webpigeon.planner.strips.StripsPlanner;

public class PlanRunner {

	public static void main(String[] args) {
		
		List<Operator> boxWorld = buildOperators();
		State start = buildStart();
		State goal = buildGoal();
		
		StripsPlanner planner = new StripsPlanner();	
		Plan p = planner.plan(boxWorld, start, goal);
		System.out.println(p);
		
		
		
	}
	
	private static State buildStart() {
		State state = new State();
		state.addAtom(new Atom("OnTable", "A"));
		state.addAtom(new Atom("On", "B","C"));
		state.addAtom(new Atom("OnTable", "C"));
		state.addAtom(new Atom("OnTable", "D"));
		state.addAtom(new Atom("Clear", "A"));
		state.addAtom(new Atom("Clear", "B"));
		state.addAtom(new Atom("Clear", "D"));
		state.addAtom(new Atom("HandEmpty"));
		
		return state;
	}
	
	private static State buildGoal() {
		State state = new State();
		state.addAtom(new Atom("OnTable", "C"));
		state.addAtom(new Atom("On", "B","C"));
		state.addAtom(new Atom("On", "A","B"));
		state.addAtom(new Atom("Clear", "A"));
		state.addAtom(new Atom("HandEmpty"));
		
		return state;
	}

	private static List<Operator> buildOperators() {
		List<Operator> operators = new ArrayList<Operator>();
		
		StripsOperator pickup = new StripsOperator("pickup", "x");
		pickup.addPrecondition(new Atom("Clear", "x"));
		pickup.addPrecondition(new Atom("OnTable", "x"));
		pickup.addPrecondition(new Atom("HandEmpty"));
		pickup.addAddition(new Atom("Holds", "x"));
		pickup.addRemoval(new Atom("HandEmpty"));
		pickup.addRemoval(new Atom("Clear", "x"));
		pickup.addRemoval(new Atom("OnTable", "x"));
		operators.add(pickup);
		
		StripsOperator putdown = new StripsOperator("putdown", "x");
		putdown.addPrecondition(new Atom("Holds", "x"));
		putdown.addAddition(new Atom("HandEmtpy"));
		putdown.addAddition(new Atom("Clear", "x"));
		putdown.addAddition(new Atom("OnTable", "x"));
		putdown.addRemoval(new Atom("Holds", "x"));
		operators.add(putdown);
		
		StripsOperator stack = new StripsOperator("stack", "x", "y");
		stack.addPrecondition(new Atom("Holds", "x"));
		stack.addPrecondition(new Atom("Clear", "y"));
		stack.addAddition(new Atom("Clear", "x"));
		stack.addAddition(new Atom("On", "x", "y"));
		stack.addAddition(new Atom("HandEmpty"));
		stack.addRemoval(new Atom("Clear", "y"));
		stack.addRemoval(new Atom("Holds", "x"));
		operators.add(stack);
		
		StripsOperator unstack = new StripsOperator("unstack", "x", "y");
		unstack.addPrecondition(new Atom("Holds", "x"));
		unstack.addPrecondition(new Atom("On", "x","y"));
		unstack.addPrecondition(new Atom("HandEmpty"));
		unstack.addAddition(new Atom("Clear", "y"));
		unstack.addAddition(new Atom("Holds", "x"));
		unstack.addRemoval(new Atom("Clear", "x"));
		unstack.addRemoval(new Atom("On", "x", "y"));
		unstack.addRemoval(new Atom("HandEmpty"));
		operators.add(unstack);
		
		return operators;
	}

}
