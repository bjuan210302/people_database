package model.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
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
		initializeArray(NAMES_PATH, names);
		initializeArray(SURNAMES_PATH, surnames);
		countryGenerator = new CountryGenerator(POPULATION_DISTRIBUTION_PATH);
		ageGenerator = new AgeGenerator(POPULATION_AGE_PATH);
	}
	
	public ArrayList<Person> generate(int amount, Trie<Person> trie) throws MalformedURLException, IOException{
		ArrayList<Person> list = new ArrayList<Person>();
		String[] compoundName = generateName(trie);
		
		for(int i = 0; i < amount; i++) {
			Person person = new Person(
					ID_GENERATOR.nextLong(), compoundName[0], compoundName[1],
					ageGenerator.generateRandom(), countryGenerator.generateRandom());
			
			list.add(person);
		}
		
		return list;
	}
	
	private String[] generateName(Trie<Person> trie) {
		String name = "";
		String surname = "";
		String compoundName = "";
		boolean compNameExist = false;
		TrieNode<Person> result = null;
		
		do {
			//Generating name
			name = names.get(new Random().nextInt(namesSize));
			surname = surnames.get(new Random().nextInt(surnamesSize));
			compoundName = name + " " + surname;
			
			//Checking if it already exist
			result = trie.getNode(compoundName);
			
			compNameExist = (result != null && result.hasData());
		}while(compNameExist);
		
		return new String[] {name, surname};
	}
	private void initializeArray(String path, ArrayList<String> list) throws IOException {
		list = new ArrayList<String>();
		BufferedReader br = Files.newBufferedReader(Paths.get(path));
		
		String line = br.readLine();
		
		while(line != null) {
			list.add(line);
			line = br.readLine();
		}
		
		br.close();
	}
}
