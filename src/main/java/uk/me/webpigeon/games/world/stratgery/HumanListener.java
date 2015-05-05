package uk.me.webpigeon.games.world.stratgery;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import uk.me.webpigeon.games.world.Cell;
import uk.me.webpigeon.games.world.Entity;
import uk.me.webpigeon.games.world.WorldRenderer;
import uk.me.webpigeon.games.world.WorldView;
import uk.me.webpigeon.games.world.stratgery.control.BuildOperator;
import uk.me.webpigeon.games.world.stratgery.control.DistoryOperator;
import uk.me.webpigeon.games.world.stratgery.control.LookOperator;
import uk.me.webpigeon.games.world.stratgery.control.MoveOperator;
import uk.me.webpigeon.games.world.stratgery.control.Operator;
import uk.me.webpigeon.games.world.stratgery.control.OperatorStratergy;

public class HumanListener implements MouseListener, ActionListener {
	private final static Integer MOVE_BUTTON = MouseEvent.BUTTON1;
	private final static Integer LOOK_BUTTON = MouseEvent.BUTTON2;
	private final static Integer ACTION_BUTTON = MouseEvent.BUTTON3;
	
	private Map<String, Operator> actionSet;
	
	private OperatorStratergy stratergy;
	private WorldView world;
	private JPopupMenu actionList;
	
	private Point target;
	
	public HumanListener(OperatorStratergy stratergy, WorldView world) {
		this.stratergy = stratergy;
		this.world = world;
		this.actionList = new JPopupMenu();
		
		this.actionSet = new TreeMap<String, Operator>();
		addPossibleAction(new BuildOperator());
		addPossibleAction(new DistoryOperator());
		addPossibleAction(new LookOperator());
		addPossibleAction(new MoveOperator());
	}
	
	protected void addPossibleAction(Operator action) {
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
		
		for (Operator action : actionSet.values()) {
			if (action.canApply(cell, entities)) {
				actions.add(action.getCommand());
			}
		}
		
		return actions;
	}

	public void process(String key) {
		Operator action = actionSet.get(key);
		if (action != null) {
			stratergy.setAction(action.apply(target));
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("you clicked "+arg0.getActionCommand());
		process(arg0.getActionCommand());
	}

}
