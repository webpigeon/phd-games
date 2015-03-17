package uk.me.webpigeon.rps;

import uk.me.webpigeon.games.GameAgent;

public abstract class RpsAgent implements GameAgent {
	
	public abstract String getName();
	
	public String toString() {
		return "rps-"+getName();
	}

}
