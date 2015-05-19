package uk.me.webpigeon.games.world.ai;

import java.util.List;

import uk.me.webpigeon.games.world.Entity;
import uk.me.webpigeon.games.world.WorldView;
import uk.me.webpigeon.games.world.moves.OperInstance;

public interface Agent {

	/**
	 * Get the action which this agent is currently executing
	 * 
	 * @param percepts the things which have happened since the agent was last updated
	 * @return the current action to execute
	 */
	public OperInstance getAction(List<String> percepts);
	
	public Entity getEntity();
	
	public void setWorldView(WorldView view);
	
}
