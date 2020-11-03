package model.utils;

import java.util.ArrayList;
import java.util.Comparator;

public abstract class Generator<T extends Generable> {

	protected ArrayList<T> entries;
	
	public Generator() {
		entries = new ArrayList<T>();
	}

}
