package uk.me.webpigeon.games.world;

public class Component {
	protected ComponentEntity entity;
	
	public void onAttach(ComponentEntity entity) {
		this.entity = entity;
	}

	public void update() {
		
	}
	
}
