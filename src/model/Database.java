package model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import model.structures.AVLTree;
import model.structures.TextSuggestion;
import model.structures.Trie;
import model.structures.TrieNode;
import model.utils.PeopleGenerator;

public class Database {

	public static final int MAX_PEOPLE = 1022831978;
	
	private AVLTree<String, Person> peoplePerName; //Compound name: Name Surname
	private AVLTree<String, Person> peoplePerSurname; //Inverted compound name: Surname name
	private AVLTree<Long, Person> peoplePerCode;
	
	private Trie<Person> nameSuggestions; //Compound name: Name Surname
	private Trie<Person> surnameSuggestions; //Inverted compound name: Surname name
	
	private PeopleGenerator peopleGenerator;
	
	public Database() throws IOException {
		peoplePerName = new AVLTree<String, Person>();
		peoplePerSurname = new AVLTree<String, Person>();
		peoplePerCode = new AVLTree<Long, Person>();
		nameSuggestions = new Trie<Person>();
		surnameSuggestions = new Trie<Person>();
		peopleGenerator = new PeopleGenerator();
	}
	
	public void generate(int amount) throws MalformedURLException, IOException {
		List<Person> list = peopleGenerator.generate(amount, nameSuggestions);
		
		for(Person p: list) {
			peoplePerName.add(p.getCompoundName(), p);
			peoplePerSurname.add(p.getInvertedCompoundName(), p);
			peoplePerCode.add(p.getCode(), p);
			
			nameSuggestions.add(p.getCompoundName(), p);
			surnameSuggestions.add(p.getInvertedCompoundName(), p);
		}
	}
	
	//TEXT SUGGESTIONS
	public List<TextSuggestion<Person>> getNameSuggestions(String prefix){
		return nameSuggestions.getSuggestions(prefix);
	}
	public List<TextSuggestion<Person>> getSurnameSuggestions(String prefix){
		return surnameSuggestions.getSuggestions(prefix);
	}
}
