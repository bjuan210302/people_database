package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.jupiter.api.Test;

import javafx.beans.property.DoublePropertyBase;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import threads.GeneratePeopleThread;

class DatabaseTest {

	private Database db;
	private String name;
	private String surname;
	private Person p;
	
	private void setupEmpty()  {
		try {
			db = new Database();
		} catch (IOException e) {
			fail("unexpected exception: " + e.getMessage());
		}
	}
	
	private void setupOnePerson() {
		setupEmpty();
		name = "Test";
		surname = "McTester";
		
		p = new Person(1, name, surname, "Male", "2/02/20", 1.4, "Colombia");
		
		db.addPerson(p);
	}
	
	@Test
	void testGenerateAndMerge() {
		setupEmpty();
		DoublePropertyBase amount = GeneratePeopleThread.intAsObservable(200);
		
		db.generateTempList(amount);
		
		assertTrue(db.mergeTempList(amount));
		//Testing trees
		assertEquals(amount, db.getPeoplePerName().count());
		assertEquals(amount, db.getPeoplePerSurname().count());
		assertEquals(amount, db.getPeoplePerCode().count());
		//Testing available suggestions
		assertEquals(amount, db.getNameSuggestions().countTotalSuggestions());
		assertEquals(amount, db.getSurnameSuggestions().countTotalSuggestions());
	}
	
	@Test
	void testAdd() {
		setupEmpty();
		
		String name = "Test";
		String surname = "McTester";
		
		Person p = new Person(1, name, surname, "Male", "2/02/20", 1.4, "Colombia");
		
		db.addPerson(p);
		
		assertEquals(1, db.searchPersonByCode(1).size()); //Database should find just one person with that code
		assertEquals(name, db.searchPersonByCode(1).get(0).getName()); 
		assertEquals(surname, db.searchPersonByCode(1).get(0).getSurname());
	}
	
	@Test
	void testSearchDelete() {
		setupOnePerson();
		
		String compound = name + " " + surname;
		String inverseCompound = surname + " " + name;
		
		assertEquals(1, db.searchPersonByCode(1).size());
		assertEquals(compound, db.getNameSuggestions(name).get(0)); 
		assertEquals(inverseCompound, db.getSurnameSuggestions(surname).get(0));
		
		assertEquals(name, db.searchPersonByCode(1).get(0).getName()); 
		assertEquals(surname, db.searchPersonByCode(1).get(0).getSurname());
		
		db.deletePerson(p); // Is not in the database anymore
		
		assertEquals(0, db.searchPersonByCode(1).size());
		assertEquals(null, db.getNameSuggestions(name)); 
		assertEquals(null, db.getSurnameSuggestions(surname));
		
		assertEquals(null, db.searchPersonByCode(1)); 
		assertEquals(null, db.searchPersonByCode(1));
		
	}
	
}
