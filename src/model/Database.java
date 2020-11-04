package model;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javafx.beans.property.DoublePropertyBase;
import model.structures.AVLTree;
import model.structures.TreeNode;
import model.structures.Trie;
import model.utils.PeopleManager;

public class Database {

	public static final int MAX_PEOPLE = 1100399846; // Max number unique names generables with the given datasets
	
	private AVLTree<String, Person> peoplePerName; //Name
	private AVLTree<String, Person> peoplePerSurname; //Surname
	private AVLTree<Long, Person> peoplePerCode;
	
	private Trie nameSuggestions; //Name
	private Trie surnameSuggestions; //Surname
	private Trie compoundNameSuggestions; //Compound name: Name + " " + Surname
	
	private PeopleManager peopleManager;
	
	public Database() throws IOException {
		peoplePerName = new AVLTree<String, Person>();
		peoplePerSurname = new AVLTree<String, Person>();
		peoplePerCode = new AVLTree<Long, Person>();
		nameSuggestions = new Trie();
		surnameSuggestions = new Trie();
		peopleManager = new PeopleManager();
	}
	
	//AUTO GENERATE
	public void generateTempList(DoublePropertyBase amount) {
		peopleManager.generateList(amount);
	}
	public void cleanTempList() {
//		tempList = null;
	}
	public boolean mergeTempList(DoublePropertyBase amount) {
		return peopleManager.mergeTempList(this, amount);
	}
	
	//ADD, DELETE, AND SEARCH PEOPLE.
	
		//ADD
	public void addPerson(String name, String surname, String gender, String birthdate, double height, String nationality) {
		Person p = new Person(peopleManager.generateRandomId(), name, surname, gender, birthdate, height, nationality);
		addPerson(p);
	}
	public void addPerson(Person p) {
		peoplePerName.add(p.getName().toLowerCase(), p);
		peoplePerSurname.add(p.getSurname().toLowerCase(), p);
		peoplePerCode.add(p.getCode(), p);
		
		nameSuggestions.add(p.getName().toLowerCase());
		surnameSuggestions.add(p.getSurname().toLowerCase());
		compoundNameSuggestions.add(p.getCompoundName().toLowerCase());
	}
		//SEARCH
	public List<Person> searchPersonByName(String name){
		List<TreeNode<String, Person>> list = peoplePerName.search(name);
		if(list != null)
			return list.stream().map(TreeNode<String, Person>::getData).collect(Collectors.toList());
		return null;
	}
	public List<Person> searchPersonBySurname(String surname){
		List<TreeNode<String, Person>> list = peoplePerSurname.search(surname);
		if(list != null)
			return list.stream().map(TreeNode<String, Person>::getData).collect(Collectors.toList());
		return null;
	}
	public List<Person> searchPersonByCode(long code){
		List<TreeNode<Long, Person>> list = peoplePerCode.search(code);
		if(list != null)
			return list.stream().map(TreeNode<Long, Person>::getData).collect(Collectors.toList());
		return null;
	}
	
	//TEXT SUGGESTIONS
	public List<String> getNameSuggestions(String prefix){
		prefix = prefix.toLowerCase();
		return nameSuggestions.getSuggestions(prefix);
	}
	public List<String> getSurnameSuggestions(String prefix){
		return surnameSuggestions.getSuggestions(prefix);
	}
	
	//GETTERS
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
