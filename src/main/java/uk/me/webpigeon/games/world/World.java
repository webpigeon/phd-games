package uk.me.webpigeon.games.world;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class World {
	private Collection<Entity> entities;
	private Cell[] cells;
	private int width;
	private int height;
	
	public World(int width, int height) {
		this.width = width;
		this.height = height;
		this.cells = new Cell[width*height];
		this.entities = new ArrayList<>();
		createWorld();
	}
	
	protected void createWorld() {
		for (int i=0; i<cells.length; i++) {
			cells[i] = new Cell();
			cells[i].walkable = true;
		}
	}

	public void setCellAt(int x, int y, Cell cell) {
		cells[rowFirst(x,y, width)] = cell;
	}
	
	public Collection<Entity> getEntities() {
		return Collections.unmodifiableCollection(entities);
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public Cell getCellAt(int x, int y) {
		if (x < 0 || x >= width || y < 0 || y >= height) {
			return null;
		}
		
		return cells[rowFirst(x,y,width)];
	}
	
	protected static int rowFirst(int x, int y, int width) {
		return y * width + x;
	}

	public void addEntity(Entity entity) {
		entities.add(entity);
		entity.bind(this);
	}

	public void update() {
		for (Entity entity : entities){
			entity.update();
		}
		
	}

}