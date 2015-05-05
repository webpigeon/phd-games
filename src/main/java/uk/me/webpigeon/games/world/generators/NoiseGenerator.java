package uk.me.webpigeon.games.world.generators;

import java.util.Arrays;
import java.util.Random;

public class NoiseGenerator {
	private static final Integer SMOOTH_STEPS = 2;
	private Random random = new Random();
	
	public static double[][] defineGrid(int width, int height) {
		NoiseGenerator ng = new NoiseGenerator();
		
		double[][] grid = new double[width][height];
		
		ng.generateRandom(grid);
		
		for (int i=0; i<SMOOTH_STEPS; i++) {
			grid = ng.smoothNew(grid);
			System.out.println(Arrays.deepToString(grid));
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
	
	public double[][] smoothNew(double[][] grid){
		int[][] kernel = new int[][]{
				{1, 2, 1},
				{2, 4, 2},
				{1, 2, 1}
		};
		
		double[][] gridCopy = new double[grid.length][grid[0].length];
		
		for (int i=0; i<grid.length; i++) {
			for (int j=0; j<grid.length; j++) {
				gridCopy[i][j] = getBetter(grid, i, j, kernel);
			}
		}
		
		return gridCopy;
	}
	
	public double getBetter(double[][] input, int x, int y, int[][] kernel) {
		double total = 0;
		
		int startX = kernel.length/2;
		int startY = kernel[0].length/2;
		
		for (int i = 0; i< kernel.length; i++) {
			for (int j = 0; j<kernel[i].length; j++) {
				int cX = x + (i - startX);
				int cY = y + (j - startY);
				
				if (cX >= input.length || cX < 0) continue;
				if (cY >= input[cX].length || cY < 0) continue;
				
				total += kernel[i][j] * (input[cX][cY] * (1f/16));
			}
		}
		
		return total;
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
