package uk.me.webpigeon.stats;

import java.util.ArrayList;
import java.util.List;

public class SimpleStats implements StatsPackage {
	private final List<Double> values;
	
	public SimpleStats(){
		this.values = new ArrayList<Double>();
	}

	@Override
	public void record(double result) {
		values.add(result);
	}

}
