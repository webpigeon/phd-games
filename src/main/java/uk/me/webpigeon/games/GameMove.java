package uk.me.webpigeon.games;

import aima.core.agent.Action;

public class GameMove implements Action {
	private final int id;
	private final String str;
	
	public GameMove(int id, String str) {
		this.id = id;
		this.str = str;
	}
	
	public boolean isMove(String str) {
		return str.equals(this.str);
	}

	public int getID() {
		return id;
	}
	
	public String toString() {
		return str;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((str == null) ? 0 : str.hashCode());
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
		GameMove other = (GameMove) obj;
		if (id != other.id)
			return false;
		if (str == null) {
			if (other.str != null)
				return false;
		} else if (!str.equals(other.str))
			return false;
		return true;
	}

	@Override
	public boolean isNoOp() {
		return false;
	}

}
