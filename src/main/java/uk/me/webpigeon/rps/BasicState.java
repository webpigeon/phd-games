package uk.me.webpigeon.rps;

import uk.me.webpigeon.games.GameState;

public class BasicState implements GameState {
	private String name;
	private boolean isTerminal;
	
	public BasicState(String name, boolean isTerminal) {
		this.name = name;
		this.isTerminal = isTerminal;
	}

	@Override
	public boolean isFinal() {
		return isTerminal;
	}

}
