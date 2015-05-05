package uk.me.webpigeon.games.world.generators;

import java.awt.Color;
import java.util.Random;

import uk.me.webpigeon.games.world.Cell;
import uk.me.webpigeon.games.world.ComponentEntity;
import uk.me.webpigeon.games.world.Entity;
import uk.me.webpigeon.games.world.EntityRenderer;
import uk.me.webpigeon.games.world.Shape;
import uk.me.webpigeon.games.world.WalkableCost;
import uk.me.webpigeon.games.world.World;
import uk.me.webpigeon.games.world.WorldRenderer;
import uk.me.webpigeon.games.world.WorldView;
import uk.me.webpigeon.games.world.stratgery.FollowEntities;
import uk.me.webpigeon.games.world.stratgery.HumanListener;
import uk.me.webpigeon.games.world.stratgery.RandomPathfind;
import uk.me.webpigeon.games.world.stratgery.Stratergy;
import uk.me.webpigeon.games.world.stratgery.StratergyEntity;
import uk.me.webpigeon.games.world.stratgery.control.OperatorStratergy;

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
	
	public static Entity buildWanderer(int x, int y) {
		ComponentEntity entity = buildEntity(x, y, Color.GRAY, new RandomPathfind());
		entity.add(new WalkableCost());
		return entity;
	}
	
	
	public static Entity buildStalker(int x, int y) {
		ComponentEntity entity = buildEntity(x, y, Color.BLACK, new FollowEntities());
		entity.add(new WalkableCost());
		return entity;
	}
	
	public static ComponentEntity buildEntity(int x, int y, Color color, Stratergy<Entity> behaviour) {
		ComponentEntity entity = new StratergyEntity(x, y, behaviour);
		entity.add(new EntityRenderer(color, Shape.CIRCLE));
		return entity;
	}
	
	public static Entity buildHuman(int x, int y, WorldRenderer renderer, WorldView world) {
		OperatorStratergy controlled = new OperatorStratergy();
		renderer.addMouseListener(new HumanListener(controlled, world));	
		return buildEntity(x, y, Color.WHITE, controlled);
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
	

}
