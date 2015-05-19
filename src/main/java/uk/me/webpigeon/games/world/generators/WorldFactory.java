package uk.me.webpigeon.games.world.generators;

import java.awt.Color;
import java.util.Random;

import uk.me.webpigeon.games.world.Cell;
import uk.me.webpigeon.games.world.ComponentEntity;
import uk.me.webpigeon.games.world.Entity;
import uk.me.webpigeon.games.world.Shape;
import uk.me.webpigeon.games.world.WalkableCost;
import uk.me.webpigeon.games.world.World;
import uk.me.webpigeon.games.world.ai.Agent;
import uk.me.webpigeon.games.world.ai.HumanListener;
import uk.me.webpigeon.games.world.ai.SingleStratAgent;
import uk.me.webpigeon.games.world.ai.strats.FollowEntities;
import uk.me.webpigeon.games.world.ai.strats.RandomPathfind;
import uk.me.webpigeon.games.world.gui.EntityRenderer;

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
	
	public static Agent buildWanderer(Entity entity) {
		return new SingleStratAgent(entity, new RandomPathfind());
	}
	
	
	public static Agent buildStalker(Entity entity) {
		return new SingleStratAgent(entity, new FollowEntities());
	}
	
	public static ComponentEntity buildEntity(int x, int y, Color color) {
		ComponentEntity entity = new ComponentEntity(x, y);
		entity.add(new EntityRenderer(color, Shape.CIRCLE));
		entity.add(new WalkableCost());
		return entity;
	}
	
	public static Entity buildFood(int x, int y) {
		ComponentEntity entity = new ComponentEntity(x, y);
		entity.add(new EntityRenderer(new Color(85,174,58), Shape.SQUARE));
		entity.add(new WalkableCost());
		
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
				cell.height = grid[x][y];
				cell.walkable = cell.height > threshold;
				
				world.setCellAt(x, y, cell);
			}
		}
		
		return world;
	}
	
	public static double[][] buildRandomSeed(int width, int height) {
		double[][] grid  = new double[width][height];
		return grid;
	}

	public static HumanListener buildHumanAgent(Entity humanEntity) {
		return new HumanListener(humanEntity);
	}
	

}
