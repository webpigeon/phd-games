package uk.me.webpigeon.games.world;

public class WalkableCost extends Component {

	@Override
	public void update(WorldView world) {
		Cell cell = world.getCellAt(entity.getX(), entity.getY());
		if (cell != null && !cell.walkable) {
			entity.health--;
		}
	}

}
