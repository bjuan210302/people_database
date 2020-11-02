package model.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AgeGenerator extends Generator<Range>{
	
	public AgeGenerator(String path) throws IOException {
		super();
		BufferedReader br = Files.newBufferedReader(Paths.get(path));
		
		String line = br.readLine();
		
		while(line != null) {
			String[] args = line.split(",");
			Range range = new Range(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Double.parseDouble(args[2]));
			entries.add(range);
			line = br.readLine();
		}
		
		br.close();
		
		sortEntries();
	}
	
	public int generateRandom() {
		double x = Math.random();
		int age = 0;
		for(Range range: entries) {
			if(x < range.getProb()) {
				age = range.generateAgeInRage();
				break;
			}
		}
		return age;
	}
}
