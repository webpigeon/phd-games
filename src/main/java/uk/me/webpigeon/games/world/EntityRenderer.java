package uk.me.webpigeon.games.world;

import java.awt.Color;
import java.awt.Graphics2D;

public class EntityRenderer extends Component implements Renderable {
	private final Color colour;
	
	public EntityRenderer() {
		this(Color.GRAY);
	}
	
	public EntityRenderer(Color color) {
		this.colour = color;
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(colour);
		g.fillOval(entity.getX() * WorldRenderer.TILE_SIZE, entity.getY() * WorldRenderer.TILE_SIZE, WorldRenderer.TILE_SIZE, WorldRenderer.TILE_SIZE);
	}


}
