package uk.me.webpigeon.games.world;

public abstract class Component {
	protected ComponentEntity entity;
	
	public void onAttach(ComponentEntity entity) {
		this.entity = entity;
	}
	
	public abstract void update(WorldView world);
	
}
