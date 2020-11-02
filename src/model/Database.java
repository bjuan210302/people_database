package model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;

import model.structures.AVLTree;
import model.structures.Trie;
import model.utils.PeopleGenerator;

public class Database {

	public static final int MAX_PEOPLE = 1100399846; // Max number unique names generables with the given datasets
	
	private AVLTree<String, Person> peoplePerName; //Compound name: Name Surname
	private AVLTree<String, Person> peoplePerSurname; //Inverted compound name: Surname name
	private AVLTree<Long, Person> peoplePerCode;
	
	private Trie nameSuggestions; //Compound name: Name Surname
	private Trie surnameSuggestions; //Inverted compound name: Surname name
	
	private PeopleGenerator peopleGenerator;
	private HashMap<String, Integer> existingNamesInDB;
	private List<Person> tempList;
	
	public Database() throws IOException {
		peoplePerName = new AVLTree<String, Person>();
		peoplePerSurname = new AVLTree<String, Person>();
		peoplePerCode = new AVLTree<Long, Person>();
		nameSuggestions = new Trie();
		surnameSuggestions = new Trie();
		peopleGenerator = new PeopleGenerator();
		
		existingNamesInDB = new HashMap<String, Integer>();
	}
	
	public void generateTempList(int amount) throws MalformedURLException, IOException {
		tempList = peopleGenerator.generateList(amount, existingNamesInDB);
	}
	public void cleanTempList() {
		tempList = null;
	}
	
	public boolean mergeTempList() {
		if (tempList == null)
			return false;
		
		long trietime = 0;
		long avltime = 0;
		long initial = 0;
		for(Person p: tempList) {
//			initial = System.currentTimeMillis();
//			peoplePerName.add(p.getCompoundName(), p);
//			peoplePerSurname.add(p.getInvertedCompoundName(), p);
//			peoplePerCode.add(p.getCode(), p);
//			avltime += System.currentTimeMillis() - initial;
			
//			initial = System.currentTimeMillis();
			nameSuggestions.add(p.getCompoundName().toLowerCase());
			surnameSuggestions.add(p.getInvertedCompoundName().toLowerCase());
//			trietime += System.currentTimeMillis() - initial;
		}
		
//		System.out.println("avltime: " + avltime);
//		System.out.println("trietime: " + trietime);
		return true;
	}
	//TEXT SUGGESTIONS
	public List<String> getNameSuggestions(String prefix){
		return nameSuggestions.getSuggestions(prefix);
	}
	public List<String> getSurnameSuggestions(String prefix){
		return surnameSuggestions.getSuggestions(prefix);
	}
	
	//For tests
	
	public AVLTree<String, Person> getPeoplePerName() {
		return peoplePerName;
	}

	public AVLTree<String, Person> getPeoplePerSurname() {
		return peoplePerSurname;
	}

	public AVLTree<Long, Person> getPeoplePerCode() {
		return peoplePerCode;
	}

	public Trie getNameSuggestions() {
		return nameSuggestions;
	}

	public Trie getSurnameSuggestions() {
		return surnameSuggestions;
	}
}
