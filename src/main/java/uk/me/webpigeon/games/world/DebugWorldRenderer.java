package uk.me.webpigeon.games.world;

import java.awt.Color;
import java.awt.Graphics;

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
	
}
