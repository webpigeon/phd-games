package uk.me.webpigeon.games.world;

import java.util.TimerTask;

import javax.swing.JComponent;

import uk.me.webpigeon.games.world.gui.ObjectInspector;

public class WorldUpdater extends TimerTask {
	private World world;
	private JComponent view;
	private ObjectInspector panel;
	
	public WorldUpdater(World world, JComponent view, ObjectInspector panel) {
		this.world = world;
		this.view = view;
		this.panel = panel;
	}

	@Override
	public void run() {
		world.update();
		view.repaint();
		panel.updateState();
	}

}
