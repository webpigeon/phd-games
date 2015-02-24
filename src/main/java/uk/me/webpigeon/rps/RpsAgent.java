package uk.me.webpigeon.rps;

import uk.me.webpigeon.games.Agent;

public abstract class RpsAgent implements Agent {
	
	public abstract String getName();
	
	public String toString() {
		return "rps-"+getName();
	}

}
