package uk.me.webpigeon.games.world;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class World implements WorldView {
	private List<Entity> entities;
	private Cell[] cells;
	private int width;
	private int height;
	private Map<Entity, WorldView> views;
	
	public World(int width, int height) {
		this.width = width;
		this.height = height;
		this.cells = new Cell[width*height];
		this.entities = new ArrayList<>();
		this.views = new HashMap<Entity, WorldView>();
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
	
	public List<Entity> getEntities() {
		return Collections.unmodifiableList(entities);
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

	public void addEntity(Entity entity, WorldView view) {
		entities.add(entity);
		views.put(entity, view);
		view.updateView(entity);
	}
	
	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	public WorldView getViewFor(Entity entity) {
		return views.getOrDefault(entity, this);
	}
	
	public void update() {
		Iterator<Entity> entityItr = entities.iterator();
		while(entityItr.hasNext()) {
			Entity entity = entityItr.next();
			
			WorldView view = views.get(entity);
			view = view==null?this:view;
			entity.update(view);
			
			if (!entity.isAlive()) {
				entityItr.remove();
			}
		}
	}
	
	public List<Entity> getEntities(int x, int y, double range) {
		List<Entity> visibleEntities = new ArrayList<Entity>();
		
		for (Entity entity : entities) {
			double distance = getDistance(x, y, entity.getX(), entity.getY());
			
			if (distance <= range) {
				visibleEntities.add(entity);
			}
		}
		
		return visibleEntities;
	}
	
	private double getDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}

	@Override
	public void updateView(Entity entity) {
	}

	@Override
	public boolean isVisible(int x, int y) {
		return true;
	}

}
