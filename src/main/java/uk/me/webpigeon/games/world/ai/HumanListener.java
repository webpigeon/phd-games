package uk.me.webpigeon.games.world.ai;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import uk.me.webpigeon.games.world.Cell;
import uk.me.webpigeon.games.world.Entity;
import uk.me.webpigeon.games.world.WorldView;
import uk.me.webpigeon.games.world.gui.WorldRenderer;
import uk.me.webpigeon.games.world.moves.BuildOperator;
import uk.me.webpigeon.games.world.moves.DistoryOperator;
import uk.me.webpigeon.games.world.moves.LookOperator;
import uk.me.webpigeon.games.world.moves.MoveOperator;
import uk.me.webpigeon.games.world.moves.OperInstance;
import uk.me.webpigeon.games.world.moves.WorldOperation;

public class HumanListener extends MouseAdapter implements Agent, ActionListener {
	private final static Integer MOVE_BUTTON = MouseEvent.BUTTON1;
	private final static Integer LOOK_BUTTON = MouseEvent.BUTTON2;
	private final static Integer ACTION_BUTTON = MouseEvent.BUTTON3;
	
	private Map<String, WorldOperation> actionSet;
	
	private Entity entity;
	private WorldView world;
	private JPopupMenu actionList;
	
	private Point target;
	private OperInstance currAction;
	
	public HumanListener(Entity entity) {
		this.entity = entity;
		this.actionList = new JPopupMenu();
		
		this.actionSet = new TreeMap<String, WorldOperation>();
		addPossibleAction(new BuildOperator());
		addPossibleAction(new DistoryOperator());
		addPossibleAction(new LookOperator());
		addPossibleAction(new MoveOperator());
	}
	
	protected void addPossibleAction(WorldOperation action) {
		actionSet.put(action.getCommand(), action);
	}
	
	protected void addAction(String name, String key) {
		JMenuItem item = new JMenuItem(name);
		item.setActionCommand(key);
		item.addActionListener(this);
		actionList.add(item);
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		Point p = me.getPoint();
		p.x = p.x / WorldRenderer.TILE_SIZE;
		p.y = p.y / WorldRenderer.TILE_SIZE;
		target = p;
		
		if (MOVE_BUTTON == me.getButton()) {
			process("walk");
		} else if (LOOK_BUTTON == me.getButton()) {
			process("look");
		} else if (ACTION_BUTTON == me.getButton()) {
			Cell cell = world.getCellAt(p.x, p.y);
			Collection<Entity> entities = world.getEntities(p.x, p.y, 1);
			setActions(getLegalActions(cell, entities));
			actionList.show(me.getComponent(), me.getX(), me.getY());
		}
	}
	
	protected void setActions(List<String> actions) {
		actionList.removeAll();
		
		for (String action : actions) {
			addAction(action, action);
		}
	}
	
	protected List<String> getLegalActions(Cell cell, Collection<Entity> entities) {
		List<String> actions = new ArrayList<String>();
		
		for (WorldOperation action : actionSet.values()) {
			if (action.canApply(cell, entities)) {
				actions.add(action.getCommand());
			}
		}
		
		return actions;
	}

	public void process(String key) {
		WorldOperation action = actionSet.get(key);
		if (action != null) {
			currAction = action.apply(target);
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		process(arg0.getActionCommand());
	}

	@Override
	public OperInstance getAction(List<String> percepts) {
		
		if (currAction != null && currAction.isComplete()) {
			currAction = null;
		}
		
		return currAction;
	}

	@Override
	public Entity getEntity() {
		return entity;
	}

	@Override
	public void setWorldView(WorldView view) {
		this.world = view;		
	}

}
