package uk.me.webpigeon.games.world;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import uk.me.webpigeon.games.world.ai.Agent;
import uk.me.webpigeon.games.world.moves.OperInstance;
import uk.me.webpigeon.planner.Action;

public class World implements WorldView {
	private List<Entity> entities;
	private Cell[] cells;
	private int width;
	private int height;
	private Map<Entity, WorldView> views;
	private List<Agent> agents;
	private Map<Entity, List<String>> percepts;
	
	public World(int width, int height) {
		this.width = width;
		this.height = height;
		this.cells = new Cell[width*height];
		this.entities = new ArrayList<>();
		this.views = new HashMap<Entity, WorldView>();
		this.agents = new ArrayList<Agent>();
		this.percepts = new HashMap<>();
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

	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	
	public WorldView addAgent(Entity entity, Agent controller) {
		WorldView view = new ObscuredWorld(this);
		controller.setWorldView(view);
		agents.add(controller);
		views.put(entity, view);
		
		view.updateView(entity);
		percepts.put(entity, new ArrayList<String>());
		addEntity(entity);
		
		return view;
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
		
		for (Agent agent : agents) {
			List<String> perceptList = percepts.getOrDefault(agent.getEntity(), Collections.emptyList());
			
			OperInstance action = agent.getAction(perceptList);
			perceptList.clear();
			
			if (action != null) {
				Collection<String> actionPercepts = action.tick(agent.getEntity(), this);
				updatePercepts(actionPercepts);
			}
			
			WorldView view = views.getOrDefault(agent.getEntity(), this);
			view.updateView(agent.getEntity());
		}
	}
	
	private void updatePercepts(Collection<String> newPercepts) {
		for (List<String> perceptList : percepts.values()) {
			perceptList.addAll(newPercepts);
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
