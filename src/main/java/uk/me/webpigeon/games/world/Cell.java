package uk.me.webpigeon.games.world;

import java.awt.Color;
import java.awt.Graphics;

import uk.me.webpigeon.games.world.gui.WorldRenderer;

public class Cell {
	public static final Color BG_WALKABLE = new Color(184, 134, 11);
	public static final Color BG_NOT_WALKABLE = new Color(0, 204, 255);
	
	public double height;
	public boolean walkable;
	
	public Cell() {
		this.height = 0;
		this.walkable = true;
	}
	
	public void render(Graphics g) {
		float red,green,blue;
		
		if (walkable) {
			red = 184 / 255f;
			green = 134 / 255f;
			blue = 11 / 255f;
		} else {
			red = 0f;
			green = 204 / 255f;
			blue = 1f;
		}
		
		float waterLevel = (float)height;
		g.setColor(new Color(red * waterLevel, green * waterLevel, blue * waterLevel));
		
		g.fillRect(0, 0, WorldRenderer.TILE_SIZE, WorldRenderer.TILE_SIZE);
	}
	
	
	public String toString() {
		String src = "";
		src = src.concat(walkable?"ground":"water");
		return String.format("%s, %.2f", walkable?"ground":"water", height);
	}
}
