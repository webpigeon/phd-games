package uk.me.webpigeon.games.world.generators;

import java.util.Random;

import uk.me.webpigeon.games.world.Cell;
import uk.me.webpigeon.games.world.World;

public class WorldFactory {

	public static World buildRandomWorld(int width, int height) {
		Random r = new Random();
		
		World world = new World(width, height);
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				Cell cell = new Cell();
				cell.walkable = r.nextBoolean();
				
				world.setCellAt(x, y, cell);
			}
		}
		
		return world;
	}
	
	public static World buildThresholdWorld(int width, int height, double threshold) {
		Random r = new Random();
		
		World world = new World(width, height);
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				Cell cell = new Cell();
				cell.walkable = r.nextDouble() > threshold;
				
				world.setCellAt(x, y, cell);
			}
		}
		
		return world;
	}
	

}
