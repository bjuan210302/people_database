package model;

import model.structures.AVLTree;
import model.structures.Trie;

public class Database {

	
	private AVLTree<String, Person> peoplePerName;
	private AVLTree<String, Person> peoplePerSurname;
	private AVLTree<String, Person> peoplePerCompoundName;
	private AVLTree<Long, Person> peoplePerCode;
	
	private Trie nameSuggestions; //Name - Surname
	private Trie surnameSuggestions; //Surname - name
	private Trie codeSuggestions;
	
	
}
