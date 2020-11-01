package model.utils;

public class Country extends Generable{

	private String country;
	
	public Country(String country, double prob) {
		super(prob);
		this.country = country;
	}
	
	public String getCountry() {
		return country;
	}
}
