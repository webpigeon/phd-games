package uk.me.webpigeon.planner.world;

import java.util.List;

import uk.me.webpigeon.games.world.Cell;
import uk.me.webpigeon.games.world.Entity;
import uk.me.webpigeon.games.world.WorldView;
import uk.me.webpigeon.planner.Atom;
import uk.me.webpigeon.planner.State;

public class WorldState extends State {
	private WorldView view;
	private Entity entity;

	public WorldState(WorldView world, Entity us) {
		this.view = world;
		this.entity = us;
		updateState();
	}
	
	private void updateState() {
		this.addAtom(new Atom("at", ""+entity.getX(), ""+entity.getY()));
		this.addAtom(new Atom("alive", ""+entity.isAlive()));
		
		System.out.println(this);
	}

	public Cell getCurrentCell() {
		return view.getCellAt(entity.getX(), entity.getY());
	}

	public List<Entity> getCurrentEntities() {
		return view.getEntities(entity.getX(), entity.getY(), 0);
	}

}
