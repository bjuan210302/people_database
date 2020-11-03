package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;

class DatabaseTest {

	private Database db;
	
	private void setupEmpty()  {
		try {
			db = new Database();
		} catch (IOException e) {
			fail("unexpected exception: " + e.getMessage());
		}
	}
	
	@Test
	void testGenerate() {
		setupEmpty();
		ObservableValue<Integer> amount = new SimpleIntegerProperty(20000).asObject();
		
		try {
			long time = System.currentTimeMillis();
			db.generateTempList(amount);
			System.out.println("generate: " + (System.currentTimeMillis() - time));
		} catch (MalformedURLException e) {
			fail("unexpected exception: " + e.getMessage());
		} catch (IOException e) {
			fail("unexpected exception: " + e.getMessage());
		}
		
		assertTrue(db.mergeTempList());
//		//Testing trees
//		assertEquals(amount, db.getPeoplePerName().count());
//		assertEquals(amount, db.getPeoplePerSurname().count());
//		assertEquals(amount, db.getPeoplePerCode().count());
//		//Testing available suggestions
//		assertEquals(amount, db.getNameSuggestions().countTotalSuggestions());
//		assertEquals(amount, db.getSurnameSuggestions().countTotalSuggestions());
	}

}
