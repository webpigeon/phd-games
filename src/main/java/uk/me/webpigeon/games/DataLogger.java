package uk.me.webpigeon.games;

public class DataLogger implements GameLogger {

	@Override
	public void log(int turn, Agent agent, GameMove move, Agent agent2, GameMove move2) {
		if ("ma-freqpro".equals(agent.getName())){
			System.out.println(agent+" -> "+move+" vs "+agent2+" -> "+move2);
		}
	}

}
