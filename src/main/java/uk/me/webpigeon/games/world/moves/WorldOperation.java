package uk.me.webpigeon.games.world.moves;

import java.awt.Point;
import java.util.Collection;

import uk.me.webpigeon.games.world.Cell;
import uk.me.webpigeon.games.world.Entity;
import uk.me.webpigeon.games.world.WorldView;

public abstract class WorldOperation {
	public String name;
	public String command;
	
	public WorldOperation(String name) {
		this.name = name;
		this.command = name;
	}

	public String getCommand() {
		return command;
	}
	
	public abstract boolean canApply(Cell cell, Collection<Entity> entities);
	
	public abstract OperInstance apply(Point target);
	
	public String toString() {
		return name;
	}


}
