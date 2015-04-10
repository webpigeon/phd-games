package uk.me.webpigeon;

import java.util.HashMap;
import java.util.Map;

public class Histogram <T> {
	private Map<T, Integer> counts;
	
	public Histogram() {
		this.counts = new HashMap<>();
	}
	
	public void record(T value) {
		Integer curr = counts.get(value);
		if (curr == null) {
			curr = 0;
		}
		counts.put(value, curr++);
	}
	
	public T getCommonest() {
		T big = null;
		Integer bigCount = Integer.MIN_VALUE;
		
		for (Map.Entry<T, Integer> entry : counts.entrySet()) {
			if (entry.getValue() > bigCount) {
				big = entry.getKey();
				bigCount = entry.getValue();
			}
		}
		
		return big;
	}
	
	public void reset(){
		counts.clear();
	}
	
}
