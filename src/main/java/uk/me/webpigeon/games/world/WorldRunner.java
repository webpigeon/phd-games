package uk.me.webpigeon.games.world;

import java.awt.Dimension;
import java.util.Timer;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import uk.me.webpigeon.games.world.generators.WorldFactory;
import uk.me.webpigeon.games.world.stratgery.RandomPathfind;
import uk.me.webpigeon.games.world.stratgery.StratergyEntity;
import uk.me.webpigeon.planner.world.WorldPlannerFactory;

public class WorldRunner {
	private static final Integer TICK_RATE = 100;

	public static void main(String[] args) {
		World world = WorldFactory.buildTidyWorld(50, 50, 0.5);
		
		for (int i=0; i<10; i++) {
			world.addEntity(new StratergyEntity(10,10, new RandomPathfind()));
		}
		
		world.addEntity(WorldFactory.buildStalker(5, 5));
		world.addEntity(WorldFactory.buildFood(10, 10));
		
		world.addEntity(WorldPlannerFactory.buildEntity(11, 11));
		
		WorldView obscured = new ObscuredWorld(world);
		WorldRenderer worldRenderer = new DebugWorldRenderer(obscured);
		JScrollPane scroll = new JScrollPane(worldRenderer);
		
		world.addEntity(WorldFactory.buildHuman(8, 8, worldRenderer, obscured), obscured);
		
		JFrame frame = new JFrame("Example World");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(scroll);
		frame.pack();
		frame.setVisible(true);
		
		//timer
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new WorldUpdater(world, scroll), 0, TICK_RATE);
	}

}
