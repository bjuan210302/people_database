package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import javafx.beans.property.DoublePropertyBase;

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
		DoublePropertyBase amount = new DoublePropertyBase() {
			@Override
			public String getName() {
				return "thisDoesNotMatter";
			}
			
			@Override
			public Object getBean() {
				return null;
			}
		};
		amount.set(200);
		
		db.generateTempList(amount);
		
		assertTrue(db.mergeTempList(amount));
		//Testing trees
		assertEquals(amount.get(), db.getPeoplePerName().count());
		assertEquals(amount.get(), db.getPeoplePerSurname().count());
		assertEquals(amount.get(), db.getPeoplePerCode().count());
		//Testing available suggestions
		assertEquals(amount.get(), db.getNameSuggestions().countTotalSuggestions());
		assertEquals(amount.get(), db.getSurnameSuggestions().countTotalSuggestions());
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
		
		assertEquals(1, db.searchPersonByCode(1).size());
		assertEquals(compound.toLowerCase(), db.getNameSuggestions(name).get(0));
		
		assertEquals(name, db.searchPersonByCode(1).get(0).getName()); 
		assertEquals(surname, db.searchPersonByCode(1).get(0).getSurname());
		
		db.deletePerson(p); // Is not in the database anymore
		
		assertEquals(null, db.getNameSuggestions(name)); 
		assertEquals(null, db.getSurnameSuggestions(surname));
		
		assertEquals(null, db.searchPersonByCode(1));
		
	}
	
}
