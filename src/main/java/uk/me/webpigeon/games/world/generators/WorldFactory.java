package uk.me.webpigeon.games.world.generators;

import java.awt.Color;
import java.util.Random;

import uk.me.webpigeon.games.world.Cell;
import uk.me.webpigeon.games.world.ComponentEntity;
import uk.me.webpigeon.games.world.Entity;
import uk.me.webpigeon.games.world.EntityRenderer;
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
	
	public static Entity buildFood(int x, int y) {
		ComponentEntity entity = new ComponentEntity(x, y);
		entity.add(new EntityRenderer(new Color(85,174,58)));
		
		return entity;
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
	
	public static World buildTidyWorld(int width, int height, double threshold) {
		double[][] grid = NoiseGenerator.defineGrid(width, height);
		
		World world = new World(width, height);
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				Cell cell = new Cell();
				cell.walkable = grid[x][y] > threshold;
				
				world.setCellAt(x, y, cell);
			}
		}
		
		return world;
	}
	
	public static double[][] buildRandomSeed(int width, int height) {
		double[][] grid  = new double[width][height];
		return grid;
	}
	

}
