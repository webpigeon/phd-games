package uk.me.webpigeon.games.world;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

public class WorldRenderer extends JComponent {
	public static final Integer TILE_SIZE = 32;
	private static final long serialVersionUID = 1L;
	private final World world;
	
	public WorldRenderer(World world) {
		this.world = world;
		this.setPreferredSize(new Dimension(world.getWidth() * TILE_SIZE, world.getHeight() * TILE_SIZE));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (int x=0; x<world.getWidth(); x++) {
			for (int y=0; y<world.getHeight(); y++) {
				Cell cell = world.getCellAt(x, y);
				if (cell == null) {
					continue;
				}
				
				paintTerrian(cell, g.create(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE));
			}
		}
		
		for (Entity entity : world.getEntities()) {
			paintEntity(entity, g);
		}
		
	}
	
	protected void paintTerrian(Cell cell, Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, TILE_SIZE, TILE_SIZE);
	}
	
	protected void paintEntity(Entity entity, Graphics g) {
		g.setColor(Color.GRAY);
		g.fillOval(entity.getX() * TILE_SIZE, entity.getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
	}
	
}
