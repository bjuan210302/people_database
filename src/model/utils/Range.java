package model.utils;

public class Range extends Generable{

	private int min;
	private int max;
	
	public Range(int min, int max, double prob) {
		super(prob);
		this.min = min;
		this.max = max;
	}
	
	public int generateAgeInRage() {
		 return (int)(Math.random() * (max - min + 1) + min); //generates a num between min and max
	}
}
