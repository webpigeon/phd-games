package uk.me.webpigeon.games.world;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class WorldRunner {

	public static void main(String[] args) {
		World world = new World(50, 50);
		world.addEntity(new Entity(10,10));
		
		JComponent worldRenderer = new DebugWorldRenderer(world);
		JComponent scroll = new JScrollPane(worldRenderer);
		
		JFrame frame = new JFrame("Example World");
		frame.setPreferredSize(new Dimension(800, 600));
		frame.add(scroll);
		frame.pack();
		frame.setVisible(true);

	}

}
