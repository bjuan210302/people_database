package model.utils;

import java.util.ArrayList;
import java.util.Comparator;

public abstract class Generator<T extends Generable> {

	protected ArrayList<T> entries;
	
	public Generator() {
		entries = new ArrayList<T>();
	}
	protected void sortEntries() {
		entries.sort(new Comparator<T>() {
			@Override
			public int compare(T o1, T o2) {
				if(o1.getProb() > o2.getProb())
					return 1;
				else if(o1.getProb() < o2.getProb())
					return -1;
				else
					return 0;
			}
		});
	}
}
