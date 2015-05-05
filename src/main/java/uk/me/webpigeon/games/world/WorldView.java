package uk.me.webpigeon.games.world;

import java.awt.Point;
import java.util.List;

public interface WorldView {

	public int getWidth();
	public int getHeight();
	public Cell getCellAt(int x, int y);
	public List<Entity> getEntities(int x, int y, double range);
	public List<Entity> getEntities();
	public void updateView(Entity entity);
	
	public boolean isVisible(int x, int y);

}
