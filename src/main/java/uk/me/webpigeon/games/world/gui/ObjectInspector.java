package uk.me.webpigeon.games.world.gui;

import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JLabel;

import uk.me.webpigeon.games.world.ComponentEntity;
import uk.me.webpigeon.games.world.Entity;

public class ObjectInspector {
	private final JComponent parent;
	private final JLabel name;
	private final JLabel action;
	private ComponentEntity entity;
	
	public ObjectInspector(Entity entity) {
		this.parent = Box.createVerticalBox();
		this.name = new JLabel("<EntityName>");
		this.action = new JLabel("<Action>");
		parent.add(name);
		parent.add(action);
		
		setTarget(entity);
	}
	
	public void setTarget(Entity entity) {
		ComponentEntity stratEnt = (ComponentEntity)entity;
		this.entity = stratEnt;
	}
	
	public void updateState() {
		name.setText(entity.toString());
		//action.setText();
	}
	
	public JComponent getComponent() {
		return parent;
	}
}
