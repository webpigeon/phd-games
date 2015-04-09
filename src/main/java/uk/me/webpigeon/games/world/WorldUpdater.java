package uk.me.webpigeon.games.world;

import java.util.TimerTask;

import javax.swing.JComponent;

public class WorldUpdater extends TimerTask {
	private World world;
	private JComponent view;
	
	public WorldUpdater(World world, JComponent view) {
		this.world = world;
		this.view = view;
	}

	@Override
	public void run() {
		world.update();
		view.repaint();
	}

}
