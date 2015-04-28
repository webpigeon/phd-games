package uk.me.webpigeon.games.world;

import java.awt.Dimension;
import java.util.Timer;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import uk.me.webpigeon.games.world.generators.WorldFactory;
import uk.me.webpigeon.games.world.stratgery.RandomPathfind;
import uk.me.webpigeon.games.world.stratgery.StratergyEntity;

public class WorldRunner {
	private static final Integer TICK_RATE = 100;

	public static void main(String[] args) {
		World world = WorldFactory.buildTidyWorld(50, 50, 0.5);
		
		for (int i=0; i<10; i++) {
			world.addEntity(new StratergyEntity(10,10, new RandomPathfind()));
		}
		
		world.addEntity(WorldFactory.buildStalker(5, 5));
		world.addEntity(WorldFactory.buildFood(10, 10));
		
		JComponent worldRenderer = new DebugWorldRenderer(world);
		JScrollPane scroll = new JScrollPane(worldRenderer);
		
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
