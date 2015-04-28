package uk.me.webpigeon.games.world.generators;

import java.util.Random;

public class NoiseGenerator {
	private static final Integer SMOOTH_STEPS = 5;
	private Random random = new Random();
	
	public static double[][] defineGrid(int width, int height) {
		NoiseGenerator ng = new NoiseGenerator();
		
		double[][] grid = new double[width][height];
		
		ng.generateRandom(grid);
		
		for (int i=0; i<SMOOTH_STEPS; i++) {
			ng.smooth(grid);
		}
		
		return grid;
	}
	
	public void generateRandom(double[][] grid) {
		for (int i=0; i<grid.length; i++) {
			for (int j=0; j<grid.length; j++) {
				grid[i][j] = random.nextDouble();
			}
		}
	}
	
	public void smooth(double[][] grid){
		int[][] coords = new int[][]{
				{-1,-1}, {0,-1}, {1,-1},
				{-1, 0}, {0, 0}, {1, 0},
				{-1, 1}, {0, 1}, {1, 1}
		};
		
		for (int i=0; i<grid.length; i++) {
			for (int j=0; j<grid.length; j++) {
				
				
				grid[i][j] = getAvg(grid, i, j, coords);
			}
		}
	}
	
	public double getAvg(double[][] grid, int x, int y, int[][] coords) {
		double total = 0;
		int validCoords = 0;
		
		for (int[] coord : coords) {
			int cX = x + coord[0];
			int cY = y + coord[1];
			
			if (cX >= grid.length || cX < 0) continue;
			if (cY >= grid[cX].length || cY < 0) continue;
			
			
			total += grid[cX][cY];
			validCoords++;
		}
		
		return total / validCoords;
	}
	

}
