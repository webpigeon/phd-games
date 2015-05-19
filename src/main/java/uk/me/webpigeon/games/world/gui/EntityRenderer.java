package uk.me.webpigeon.games.world.gui;

import java.awt.Color;
import java.awt.Graphics2D;

import uk.me.webpigeon.games.world.Component;
import uk.me.webpigeon.games.world.Shape;
import uk.me.webpigeon.games.world.WorldView;

public class EntityRenderer extends Component implements Renderable {
	private final Color colour;
	private final Shape shape;
	
	public EntityRenderer() {
		this(Color.GRAY, Shape.CIRCLE);
	}
	
	public EntityRenderer(Color color, Shape shape) {
		this.colour = color;
		this.shape = shape;
	}

	@Override
	public void render(Graphics2D g) {
		
		
		g.setColor(colour);
		if (shape.equals(Shape.CIRCLE)) {
			g.fillOval(entity.getX() * WorldRenderer.TILE_SIZE, entity.getY() * WorldRenderer.TILE_SIZE, WorldRenderer.TILE_SIZE, WorldRenderer.TILE_SIZE);
		} else if (shape.equals(Shape.SQUARE)) {
			g.fillRect(entity.getX() * WorldRenderer.TILE_SIZE, entity.getY() * WorldRenderer.TILE_SIZE, WorldRenderer.TILE_SIZE, WorldRenderer.TILE_SIZE);
		}
	}

	@Override
	public void update(WorldView world) {
		// TODO Auto-generated method stub
		
	}


}
