package uk.me.webpigeon.games.world.ai;

import java.util.List;

import uk.me.webpigeon.games.world.Entity;
import uk.me.webpigeon.games.world.WorldView;
import uk.me.webpigeon.games.world.ai.strats.Stratergy;
import uk.me.webpigeon.games.world.moves.OperInstance;

public class SingleStratAgent implements Agent {
	private Entity entity;
	private Stratergy<Entity> strat;
	private WorldView view;
	
	public SingleStratAgent(Entity entity, Stratergy<Entity> strat) {
		this.entity = entity;
		this.strat = strat;
	}

	@Override
	public OperInstance getAction(List<String> percepts) {
		return strat.update(entity, view);
	}

	@Override
	public Entity getEntity() {
		return entity;
	}

	@Override
	public void setWorldView(WorldView view) {
		this.view = view;
	}

}
