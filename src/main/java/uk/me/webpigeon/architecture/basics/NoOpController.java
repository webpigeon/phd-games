package uk.me.webpigeon.architecture.basics;

import uk.me.webpigeon.architecture.Action;
import uk.me.webpigeon.architecture.Controller;
import uk.me.webpigeon.architecture.GameInstance;

public class NoOpController implements Controller {

	@Override
	public Action getAction(GameInstance i) {
		return null;
	}
	
	
}
