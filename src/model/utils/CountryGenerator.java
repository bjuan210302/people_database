package model.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CountryGenerator extends Generator<Country>{

	public CountryGenerator(String path) throws IOException {
		
		BufferedReader br = Files.newBufferedReader(Paths.get(path));
		
		String line = br.readLine();
		
		while(line != null) {
			String[] args = line.split(",");
			Country country = new Country(args[0], Double.parseDouble(args[2]));
			entries.add(country);
			line = br.readLine();
		}
		
		br.close();
		
		sortEntries();
	}
	
	public String generateRandom() {
		double x = Math.random();
		String name = null;
		for(Country country: entries) {
			if(x < country.getProb()) {
				name = country.getCountry();
				break;
			}
		}
		
		return (name != null) ? name : generateRandom();
	}
}
