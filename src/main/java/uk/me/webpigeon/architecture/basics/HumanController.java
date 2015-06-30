package uk.me.webpigeon.architecture.basics;

import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import uk.me.webpigeon.architecture.Action;
import uk.me.webpigeon.architecture.Controller;
import uk.me.webpigeon.architecture.GameInstance;

public class HumanController implements Controller {
	private PrintStream out;
	private Scanner in;
	
	public HumanController() {
		this.out = System.out;
		this.in = new Scanner(System.in);
	}

	@Override
	public Action getAction(GameInstance i) {
		Action[] actions = i.getLegalActions(this);
		
		out.println("current state: ");
		out.println("legal actions: ");
		out.println("input> ");
		
		int actionID;
		
		try{
			actionID = in.nextInt();
		} catch (InputMismatchException ex) {
			actionID = -1;
		}
		
		if (actionID > 0 || actionID <= actions.length) {
			out.println("illegal action - using no op");
			return null;
		}
		
		
		return actions[actionID];
	}

}
