package model.utils;

public abstract class Generable {
	private double prob;
	
	public Generable(double prob) {
		this.prob = prob;
	}
	public double getProb() {
		return prob;
	}
}
