package uk.me.webpigeon.games.world;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;

public class ComponentEntity extends Entity {
	private final Collection<Component> components;
	private final Collection<Renderable> renderables;

	public ComponentEntity(int x, int y) {
		super(x, y);
		this.components = new ArrayList<Component>();
		this.renderables = new ArrayList<Renderable>();
	}
	
	public void add(Component component) {
		components.add(component);
		if (component instanceof Renderable) {
			renderables.add( (Renderable)component );
		}
		component.onAttach(this);
	}
	
	public void update() {
		for (Component component : components) {
			component.update();
		}
	}
	
	public void render(Graphics2D g) {
		super.render(g);
		for (Renderable renderable : renderables){
			renderable.render(g);
		}
	}

}
