package model.structures;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class TrieTest {

	private Trie trie;
	
	private void setupEmpty() {
		trie = new Trie();
	}
	
	private void setupPopulatedTrie() { //populated with 9 strings
		trie = new Trie();
		trie.add("black");
		trie.add("block");
		trie.add("blonde");
		trie.add("wine");
		trie.add("win");
		trie.add("war");
		trie.add("dislike");
		trie.add("disliking");
		trie.add("bourbon");
	}
	
	@Test
	void addTest() {
		setupEmpty();
		
		trie.add("black");
		assertNotEquals(null, trie.getNode("black")); //Was added, should not be null if searched
		assertTrue(trie.getNode("black").isWord()); 
		assertEquals(1, trie.getNode("black").getMultiplicity()); //Was added one time, multiplicity is 1
		//Number of children and chars should be always the same
		assertEquals(trie.getRoot().getChars().size(), trie.getRoot().getChildren().size());
		//Add it again and check multiplicity
		trie.add("black");
		assertEquals(2, trie.getNode("black").getMultiplicity()); //Was added one time, multiplicity is 1
		
		//adding another word starting with b should not increase the size of the roots children list
		trie.add("blonde");
		assertTrue(trie.getNode("blonde").isWord()); 
		assertEquals(1, trie.getRoot().getChildren().size());
		assertEquals(trie.getRoot().getChars().size(), trie.getRoot().getChildren().size());
		
		//Testing multiplicities, idk how to spell that
		trie.add("bourbon");
		assertEquals(1, trie.getNode("bourbon").getMultiplicity()); //Was added one time, multiplicity is 1
		trie.add("bourbon"); //Add it again
		assertEquals(2, trie.getNode("bourbon").getMultiplicity()); //Multiplicity should go up by 1
		trie.add("bourbon"); //Again
		assertEquals(3, trie.getNode("bourbon").getMultiplicity()); //Multiplicity should go up by 1
	}

	@Test
	void removeTest() {
		setupPopulatedTrie();
		
		// We have dislike and disliking in the tree. Both have prefix 'dislik', if we delete disliking (the longer one)
		// dislike should remain intact
		
		assertTrue(trie.remove("disliking")); //The word exist, should return true
		assertEquals(null, trie.getNode("disliking")); // is removed
		assertNotEquals(null, trie.getNode("dislike")); //This one is not.
		
		//As disliking was not the only word starting with d, the size of the roots children list remains the same
		assertEquals(3, trie.getRoot().getChildren().size());
		assertEquals(3, trie.getRoot().getChars().size());
		
		//However, if we remove dislike, the size should go down by one
		trie.remove("dislike");
		assertEquals(2, trie.getRoot().getChildren().size());
		assertEquals(2, trie.getRoot().getChars().size());
		
		//Multis
		trie.add("bourbon"); //Add it again, multiplicity is now 2
		assertEquals(2, trie.getNode("bourbon").getMultiplicity());
		trie.remove("bourbon"); //Multiplicity should go down by 1
		assertEquals(1, trie.getNode("bourbon").getMultiplicity());
		trie.remove("bourbon"); //Multiplicity should go down by 1 and be deleted
		assertEquals(null, trie.getNode("bourbon")); // And now is null
		
		//Removing a no leaf node
		//If we remove a node that is not leaf (and its multiplicity is 1), isLeaf should be set do false
		//but the node should not be deleted as there are other strigns that depend on it.
		
		trie.remove("win"); //Not leaf and multiplicity is 1
		assertEquals(1, trie.getNode("wine").getMultiplicity()); //Wine was not affected
		assertFalse(trie.getNode("win").isWord()); // win is no longer considered a word
	}
	
	@Test
	void getNodeTest() {
		setupPopulatedTrie();
		
		// should be able to get 'block' or 'wine' and isWord = true
		// but if we try to get 'blo' or 'wi' we'll find isWord = false, these strings exist in the trie, but aren't words
		assertTrue(trie.getNode("black").isWord());
		assertTrue(trie.getNode("wine").isWord());
		assertTrue(trie.getNode("win").isWord()); // This node is not leaf, but was added as a word, therefore it is a word
		
		assertFalse(trie.getNode("wi").isWord());
		assertFalse(trie.getNode("blo").isWord());
	}
	
	@Test
	void getSuggestionsTest() {
		setupPopulatedTrie();
		//Expected arrays
		String[] bl = new String[] {"black", "block", "blonde"};
		String[] b = new String[] {"black", "block", "blonde", "bourbon"};
		String[] win = new String[] {"wine"};
		String[] w = new String[] {"war", "win", "wine"};
		String[] dis = new String[] {"dislike", "disliking"};
		
		//Collecting all the TextSuggestion objects and extracting the text to a list
		List<String> _bl = trie.getSuggestions("bl");
		List<String> _b = trie.getSuggestions("b");
		List<String> _win = trie.getSuggestions("win");
		List<String> _w = trie.getSuggestions("w");
		List<String> _dis = trie.getSuggestions("dis");
		
		//Removing all the objects of the simple arrays from the lists; if the final length is zero, the objects
		//inside were all the same, hence, the list and the corresponding array were identical
		_bl.removeAll(Arrays.asList(bl));
		_b.removeAll(Arrays.asList(b));
		_win.removeAll(Arrays.asList(win));
		_w.removeAll(Arrays.asList(w));
		_dis.removeAll(Arrays.asList(dis));
		
		//Evaluate
		assertEquals(0, _bl.size());
		assertEquals(0, _b.size());
		assertEquals(0, _win.size());
		assertEquals(0, _w.size());
		assertEquals(0, _dis.size());
	}
	
	@Test 
	void countSuggestionsTest(){
		setupPopulatedTrie();
		assertEquals(3, trie.countSuggestions("bl"));
		assertEquals(4, trie.countSuggestions("b"));
		assertEquals(1, trie.countSuggestions("win"));
		assertEquals(3, trie.countSuggestions("w"));
		assertEquals(2, trie.countSuggestions("dis"));
		assertEquals(0, trie.countSuggestions("z")); // No word starting with 'z'
		
		//Add some words and test again
		trie.add("blanco");
		trie.add("bronce");
		trie.add("winnie");
		trie.add("zombie");
		
		assertEquals(4, trie.countSuggestions("bl"));
		assertEquals(6, trie.countSuggestions("b"));
		assertEquals(2, trie.countSuggestions("win"));
		assertEquals(4, trie.countSuggestions("w"));
		assertEquals(2, trie.countSuggestions("dis"));
		assertEquals(1, trie.countSuggestions("z")); // No word starting with 'z'
	}
	
	void countTotalSuggestionsTest(){
		setupPopulatedTrie(); // Populated with 9
		assertEquals(9, trie.countTotalSuggestions());
		
		//Add some words and test again
		trie.add("blanco");
		trie.add("bronce");
		trie.add("winnie");
		trie.add("zombie");
		
		assertEquals(15, trie.countTotalSuggestions());
	}
}
