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
		DoublePropertyBase amount = GeneratePeopleThread.intAsObservable(200);
		
		db.generateTempList(amount);
		
		assertTrue(db.mergeTempList(amount));
//		//Testing trees
//		assertEquals(amount, db.getPeoplePerName().count());
//		assertEquals(amount, db.getPeoplePerSurname().count());
//		assertEquals(amount, db.getPeoplePerCode().count());
//		//Testing available suggestions
//		assertEquals(amount, db.getNameSuggestions().countTotalSuggestions());
//		assertEquals(amount, db.getSurnameSuggestions().countTotalSuggestions());
	}

}
