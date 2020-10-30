package model;

import model.structures.AVLTree;

public class Database {

	
	private AVLTree<String, Person> peoplePerName;
	private AVLTree<String, Person> peoplePerSurname;
	private AVLTree<String, Person> peoplePerCompoundName;
	private AVLTree<Long, Person> peoplePerCode;
	
}
