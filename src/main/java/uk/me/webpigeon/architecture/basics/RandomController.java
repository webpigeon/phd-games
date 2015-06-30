package uk.me.webpigeon.architecture.basics;

import java.util.Random;

import uk.me.webpigeon.architecture.Action;
import uk.me.webpigeon.architecture.Controller;
import uk.me.webpigeon.architecture.GameInstance;

public class RandomController implements Controller {
	private Random random;
	
	public RandomController() {
		this.random = new Random();
	}

	@Override
	public Action getAction(GameInstance i) {
		Action[] actions = i.getLegalActions(this);
		
		if (actions.length == 0) {
			return null;
		}
		
		return actions[random.nextInt(actions.length)];
	}
	
	
}
