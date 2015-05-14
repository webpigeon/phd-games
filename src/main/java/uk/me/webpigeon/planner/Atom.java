package uk.me.webpigeon.planner;

import java.util.Arrays;

public class Atom {
	public String predicate;
	public String[] args;

	public Atom(String predicate, String ... args ){
		this.predicate = predicate;
		this.args = args;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(args);
		result = prime * result
				+ ((predicate == null) ? 0 : predicate.hashCode());
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		
		if (this == obj) {
			return true;
		}
		
		try {
			Atom other = (Atom) obj;
			return Arrays.equals(args, other.args) && predicate.equals(other.predicate);
		} catch (ClassCastException ex) {
			return false;
		}
		
	}

	@Override
	public String toString() {
		return String.format("%s%s", predicate, Arrays.toString(args));
	}
}
