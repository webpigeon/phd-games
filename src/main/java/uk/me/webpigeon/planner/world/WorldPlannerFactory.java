package uk.me.webpigeon.planner.world;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import uk.me.webpigeon.games.world.ComponentEntity;
import uk.me.webpigeon.games.world.Entity;
import uk.me.webpigeon.games.world.generators.WorldFactory;
import uk.me.webpigeon.games.world.stratgery.control.BuildOperator;
import uk.me.webpigeon.games.world.stratgery.control.DistoryOperator;
import uk.me.webpigeon.games.world.stratgery.control.MoveOperator;
import uk.me.webpigeon.games.world.stratgery.control.WorldOperation;
import uk.me.webpigeon.planner.Operator;
import uk.me.webpigeon.planner.Planner;
import uk.me.webpigeon.planner.strips.StripsPlanner;

public class WorldPlannerFactory {

	public static List<GameOperator> buildOpers() {
		return Arrays.asList(
				new GameOperator(new MoveOperator()),
				new GameOperator(new BuildOperator()),
				new GameOperator(new DistoryOperator())
				);
	}
	
	public static Planner<GameOperator> buildStrips() {
		return new StripsPlanner<GameOperator>();
	}
	
	public static Entity buildEntity(int x, int y) {
		Planner<GameOperator> planner = buildStrips();
		PlanningStrat plannerStrat = new PlanningStrat(planner, buildOpers());
		return WorldFactory.buildEntity(x, y, Color.GREEN, plannerStrat);
	}
	
}
