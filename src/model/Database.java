package model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javafx.beans.property.DoublePropertyBase;
import model.structures.AVLTree;
import model.structures.Trie;
import model.utils.PeopleManager;

public class Database {

	public static final int MAX_PEOPLE = 1100399846; // Max number unique names generables with the given datasets
	
	private AVLTree<String, Person> peoplePerName; //Compound name: Name Surname
	private AVLTree<String, Person> peoplePerSurname; //Inverted compound name: Surname name
	private AVLTree<Long, Person> peoplePerCode;
	
	private Trie nameSuggestions; //Compound name: Name Surname
	private Trie surnameSuggestions; //Inverted compound name: Surname name
	
	private PeopleManager peopleManager;
	
	public Database() throws IOException {
		peoplePerName = new AVLTree<String, Person>();
		peoplePerSurname = new AVLTree<String, Person>();
		peoplePerCode = new AVLTree<Long, Person>();
		nameSuggestions = new Trie();
		surnameSuggestions = new Trie();
		peopleManager = new PeopleManager();
	}
	
	public void generateTempList(DoublePropertyBase amount) {
		peopleManager.generateList(amount);
	}
	public void cleanTempList() {
//		tempList = null;
	}
	
	public boolean mergeTempList(DoublePropertyBase amount) {
		return peopleManager.mergeTempList(this, amount);
	}
	//TEXT SUGGESTIONS
	public List<String> getNameSuggestions(String prefix){
		prefix = prefix.toLowerCase();
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
