package model.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.nio.file.*;

import model.Person;
import model.structures.Trie;
import model.structures.TrieNode;

public class PeopleGenerator {
	
	public Random ID_GENERATOR = new Random();
	public static final String NAMES_PATH = "data/csv/names.csv";
	public static final String SURNAMES_PATH = "data/csv/surnames.csv";
	public static final String POPULATION_DISTRIBUTION_PATH = "data/csv/american_countries_dist.csv";
	public static final String POPULATION_AGE_PATH = "data/csv/american_ages_dist.csv";
	
	private ArrayList<String> names;
	private ArrayList<String> surnames;
	private int namesSize; //Just not to calculate this each time
	private int surnamesSize;
	private AgeGenerator ageGenerator;
	private CountryGenerator countryGenerator;
	
	public PeopleGenerator() throws IOException {
		names = initializeArray(NAMES_PATH);
		surnames = initializeArray(SURNAMES_PATH);
		countryGenerator = new CountryGenerator(POPULATION_DISTRIBUTION_PATH);
		ageGenerator = new AgeGenerator(POPULATION_AGE_PATH);
		
		namesSize = names.size();
		surnamesSize = surnames.size();
	}
	
	public ArrayList<Person> generateList(int amount, HashMap<String, Integer> existingNamesInDB) throws MalformedURLException, IOException{
		HashMap<String, Integer> generatedNames = new HashMap<String, Integer>();
		ArrayList<Person> list = new ArrayList<Person>();
		
		
		for(int i = 0; i < amount; i++) {
			String[] compoundName = generateNonExsitingName(existingNamesInDB, generatedNames);
			Person person = new Person(
					ID_GENERATOR.nextLong(), compoundName[0], compoundName[1],
					ageGenerator.generateRandom(), countryGenerator.generateRandom());
			
			list.add(person);
		}
		return list;
	}
	
	private String[] generateNonExsitingName(HashMap<String, Integer> existingNamesInDB, HashMap<String, Integer> namesMap) {
		String name = "";
		String surname = "";
		String compoundName = "";
		boolean compNameExist = false;

		do {
			//Generating name
			name = names.get(new Random().nextInt(namesSize));
			surname = surnames.get(new Random().nextInt(surnamesSize));
			compoundName = name + " " + surname;
			//Checking if it already exist

			compNameExist = existingNamesInDB.containsKey(compoundName);
		}while(compNameExist);

		namesMap.put(compoundName, 0); //The Integer value doesn't really matter, the map is just for fast-search names
		return new String[] {name, surname};
	}
	private ArrayList<String> initializeArray(String path) throws IOException {
		ArrayList<String> list = new ArrayList<String>();
		BufferedReader br = Files.newBufferedReader(Paths.get(path));

		String line = br.readLine();
		String item = null;
		while(line != null) {
			item = line.split(",")[0];
			list.add(item);
			line = br.readLine();
		}

		br.close();

		return list;
	}
}
