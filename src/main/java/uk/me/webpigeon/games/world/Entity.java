package uk.me.webpigeon.games.world;

import java.awt.Point;

public class Entity {
	protected Point position;
	
	public Entity(int x, int y) {
		this.position = new Point(x, y);
	}

	public int getY() {
		return position.y;
	}
	
	public int getX() {
		return position.x;
	}

}
