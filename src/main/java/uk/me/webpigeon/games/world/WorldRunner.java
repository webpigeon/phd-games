package uk.me.webpigeon.games.world;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.util.Random;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import uk.me.webpigeon.games.world.ai.HumanListener;
import uk.me.webpigeon.games.world.generators.WorldFactory;
import uk.me.webpigeon.games.world.gui.DialogueWindow;
import uk.me.webpigeon.games.world.gui.ObjectInspector;
import uk.me.webpigeon.games.world.gui.WorldRenderer;

public class WorldRunner {
	private static final Integer TICK_RATE = 100;

	public static void main(String[] args) {
		World world = WorldFactory.buildTidyWorld(50, 50, 0.5);
		
		for (int i=0; i<10; i++) {
			Entity avatar = WorldFactory.buildEntity(5, 5, Color.BLUE);
			world.addAgent(avatar, WorldFactory.buildWanderer(avatar));
		}
		
		Entity stalkerChar = WorldFactory.buildEntity(5, 5, Color.BLACK);
		world.addAgent(stalkerChar, WorldFactory.buildStalker(stalkerChar));
		
		world.addEntity(WorldFactory.buildFood(10, 10));
		
		//world.addEntity(WorldPlannerFactory.buildEntity(11, 11));
		
		Random placer = new Random();
		for (int i=0; i<10; i++) {
			
			//randomly get points until we find a walkable one
			Point point = new Point(0,0);
			boolean walkable = false;
			while (!walkable) {
				point = new Point(placer.nextInt(world.getWidth()), placer.nextInt(world.getHeight()));
				Cell cell = world.getCellAt(point.x, point.y);
				walkable = cell.walkable;
			}
			
			Entity avatar = WorldFactory.buildEntity(point.x, point.y, Color.BLUE);
			world.addAgent(avatar, WorldFactory.buildWanderer(avatar));
		}
		
		
		Entity humanEntity = buildHuman(world);
		HumanListener humanController = WorldFactory.buildHumanAgent(humanEntity);
		WorldView obsucred = world.addAgent(humanEntity, humanController);
		WorldRenderer worldRenderer = new WorldRenderer(obsucred);
		JScrollPane scroll = new JScrollPane(worldRenderer);
		worldRenderer.addMouseListener(humanController);
		ObjectInspector inspector = new ObjectInspector(humanEntity);
		DialogueWindow chat = new DialogueWindow();
		
		JFrame frame = new JFrame("Example World");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(scroll);
		frame.add(inspector.getComponent(), BorderLayout.EAST);
		frame.add(chat.getComponent(), BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
		
		//timer
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new WorldUpdater(world, scroll, inspector), 0, TICK_RATE);
	}
	
	private static Entity buildHuman(World world) {
		Random placer = new Random();
		
		//randomly get points until we find a walkable one
		Point point = new Point(0,0);
		boolean walkable = false;
		while (!walkable) {
			point = new Point(placer.nextInt(world.getWidth()), placer.nextInt(world.getHeight()));
			Cell cell = world.getCellAt(point.x, point.y);
			walkable = cell.walkable;
		}
		
		return WorldFactory.buildEntity(point.x, point.y, Color.WHITE);
	}
	
}
