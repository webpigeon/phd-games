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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atom other = (Atom) obj;
		if (!Arrays.equals(args, other.args))
			return false;
		if (predicate == null) {
			if (other.predicate != null)
				return false;
		} else if (!predicate.equals(other.predicate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s%s", predicate, Arrays.toString(args));
	}
}
