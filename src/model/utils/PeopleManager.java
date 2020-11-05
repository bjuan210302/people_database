package model.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;

import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.property.DoublePropertyBase;
import java.nio.file.*;

import model.Database;
import model.Person;

public class PeopleManager {
	
	private Random idGenerator = new Random();
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

	private ArrayList<Person> tempList;
	
	public PeopleManager() throws IOException {
		names = initializeArray(NAMES_PATH);
		surnames = initializeArray(SURNAMES_PATH);
		countryGenerator = new CountryGenerator(POPULATION_DISTRIBUTION_PATH);
		ageGenerator = new AgeGenerator(POPULATION_AGE_PATH);
		
		namesSize = names.size();
		surnamesSize = surnames.size();
		
		tempList = new ArrayList<Person>();
	}

	public boolean mergeTempList(Database db, DoublePropertyBase amount) {
		if (tempList == null || amount.get() > tempList.size())
			return false;
		
		trackeableCycle(amount, new Consumer<Integer>() {
			
			@Override
			public void accept(Integer i) {
				Person p = tempList.get(i);
				db.addPerson(p);
			}
		});
		return true;
	}
	
	public void generateList(DoublePropertyBase amount) {
		trackeableCycle(amount, new Consumer<Integer>() {
			
			@Override
			public void accept(Integer t) {
				String[] compoundName = generateName();
				Person person = new Person(
						generateRandomId(), compoundName[0], compoundName[1],
						ageGenerator.generateRandom(), countryGenerator.generateRandom());
				tempList.add(person);
			}
		});
	}
	
	public void trackeableCycle(DoublePropertyBase times, Consumer<Integer> function) {
		double totalIterations = times.get();
		
		double onePercentOfIterations = totalIterations*0.01;
		int iterations = 0;
		double percentDone = 0;
		BooleanPropertyBase notify = getBolPropertyBase();
		
		counterDown(1000, notify).start();
		
		for(int i = 0; i < totalIterations; i++) {
			
			function.accept(i);
			
			iterations++;
			percentDone = (i+1)/totalIterations;
			if(notify.get() && (iterations >= onePercentOfIterations)) { 
				times.set(percentDone);
				iterations = 0;
			}
		}
	}
	
	private Thread counterDown(long milis, BooleanPropertyBase notify) {
		return new Thread(() -> {
			try {
				Thread.sleep(milis);
				notify.set(true);
			} catch (InterruptedException e) {
			}
		});
	}

	private BooleanPropertyBase getBolPropertyBase() {
		return new BooleanPropertyBase() {
			@Override
			public String getName() {
				return "Ah";
			}
			
			@Override
			public Object getBean() {
				return this;
			}
		};
	}
	private String[] generateName() {
		String name = "";
		String surname = "";

		name = names.get(new Random().nextInt(namesSize));
		surname = surnames.get(new Random().nextInt(surnamesSize));

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
	
	public long generateRandomId() {
		return this.idGenerator.nextLong();
	}
	
}
