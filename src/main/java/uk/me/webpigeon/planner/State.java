package uk.me.webpigeon.planner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class State {
	private List<Atom> atoms;
	
	public State() {
		this.atoms = new ArrayList<Atom>();
	}
	
	public State(State s) {
		this();
		atoms.addAll(s.atoms);
	}
	
	public void addAtom(Atom atom) {
		atoms.add(atom);
	}
	
	public void removeAtom(Atom atom) {
		atoms.remove(atom);
	}

	
	/**
	 * Check if another state's atoms are all contained within this state
	 * 
	 * This state can contain other atoms.
	 * 
	 * @param other the other state to check
	 * @return true of all atoms match, false otherwise
	 */
	public boolean isKindOf(State other) {
		return atoms.containsAll(other.atoms);
	}
	
	public String toString() {
		return "STATE "+atoms.toString();
	}

	public boolean contains(Atom goalAtom) {
		return atoms.contains(goalAtom);
	}

	public List<Atom> getAtoms() {
		return Collections.unmodifiableList(atoms);
	}
	
}
