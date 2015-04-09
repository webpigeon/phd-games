package uk.me.webpigeon.games.world;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Collection;

public class DebugWorldRenderer extends WorldRenderer {
	private static final long serialVersionUID = 1L;

	public DebugWorldRenderer(World world) {
		super(world);
	}

	@Override
	protected void paintTerrian(Cell cell, Graphics g) {
		super.paintTerrian(cell, g);
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, WorldRenderer.TILE_SIZE, WorldRenderer.TILE_SIZE);
	}
	
	@Override
	protected void paintEntity(Entity entity, Graphics g) {
		super.paintEntity(entity, g);
		
		g.setColor(Color.WHITE);
		Collection<Point> interesting = entity.getInterestingPoints();
		if (interesting == null) {
			return;
		}
		
		for (Point p : interesting) {
			g.drawRect(p.x * WorldRenderer.TILE_SIZE, p.y * WorldRenderer.TILE_SIZE, WorldRenderer.TILE_SIZE, WorldRenderer.TILE_SIZE);
		}
	}
	
}
