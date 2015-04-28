package uk.me.webpigeon.games.world;

public class WalkableCost extends Component {

	@Override
	public void update(World world) {
		Cell cell = world.getCellAt(entity.getX(), entity.getY());
		if (!cell.walkable) {
			entity.health--;
		}
	}

}
