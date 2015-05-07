package uk.me.webpigeon.planner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IternetCode {

	  public static <E> List<List<E>> generatePerm(List<E> original) {
		     if (original.size() == 0) { 
		       List<List<E>> result = new ArrayList<List<E>>();
		       result.add(new ArrayList<E>());
		       return result;
		     }
		     E firstElement = original.remove(0);
		     List<List<E>> returnValue = new ArrayList<List<E>>();
		     List<List<E>> permutations = generatePerm(original);
		     for (List<E> smallerPermutated : permutations) {
		       for (int index=0; index <= smallerPermutated.size(); index++) {
		         List<E> temp = new ArrayList<E>(smallerPermutated);
		         temp.add(index, firstElement);
		         returnValue.add(temp);
		       }
		     }
		     return returnValue;
		   }
	  
	  /**
	   * quick dirty hack, CBA to twizzle, this will do.
	   * @param source
	   * @param size
	   * @return
	   */
	  public static <T> List<List<T>> permute(List<T> source, int size) {
		  List<List<T>> permutations = new ArrayList<List<T>>();
		  
		  List<List<T>> powerset = powerSet(source);
		  for (List<T> candidate : powerset) {
			  if (candidate.size() == size) {
				  permutations.add(candidate);
			  }
		  }
		  
		  return permutations;
	  }
	  
	  public static <T> List<List<T>> powerSet(List<T> originalSet) {
		    List<List<T>> sets = new ArrayList<List<T>>();
		    if (originalSet.isEmpty()) {
		    	sets.add(new ArrayList<T>());
		    	return sets;
		    }
		    List<T> list = new ArrayList<T>(originalSet);
		    T head = list.get(0);
		    List<T> rest = list.subList(1, list.size()); 
		    for (List<T> set : powerSet(rest)) {
		    	List<T> newSet = new ArrayList<T>();
		    	newSet.add(head);
		    	newSet.addAll(set);
		    	sets.add(newSet);
		    	sets.add(set);
		    }		
		    return sets;
		}
}
