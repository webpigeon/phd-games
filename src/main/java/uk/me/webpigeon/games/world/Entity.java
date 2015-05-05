package uk.me.webpigeon.games.world;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Collection;
import java.util.Collections;

public class Entity implements Renderable {
	protected Point position;
	protected int health;
	
	public Entity(int x, int y) {
		this.position = new Point(x, y);
		this.health = 1;
	}

	public int getY() {
		return position.y;
	}
	
	public int getX() {
		return position.x;
	}

	public void update(WorldView world) {
	}
	
	public Collection<Point> getInterestingPoints() {
		return Collections.emptyList();
	}

	public Point getPosition() {
		return new Point(position);
	}

	public void move(Point nextPos) {
		this.position = nextPos;
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.GRAY);
		g.fillOval(position.x * WorldRenderer.TILE_SIZE, position.y * WorldRenderer.TILE_SIZE, WorldRenderer.TILE_SIZE, WorldRenderer.TILE_SIZE);
	}

	public boolean isAlive() {
		return health > 0;
	}
	
}
