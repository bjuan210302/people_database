package model.structures;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class TrieTest {

	private Trie<Integer> trie;
	
	private void setupEmpty() {
		trie = new Trie<Integer>();
	}
	
	private void setupPopulatedTrie() {
		setupEmpty();
		trie.add("black", 1);
		trie.add("block", 2);
		trie.add("blonde", 3);
		trie.add("wine", 4);
		trie.add("win", 5);
		trie.add("war", 6);
		trie.add("dislike", 7);
		trie.add("disliking", 8);
		trie.add("bourbon", 9);
	}
	
	@Test
	void addTest() {
		setupEmpty();
		
		assertTrue(trie.add("black", 1)); //First to be added, should return true
		assertTrue(trie.getNode("black").getData() == 1); //the key and value are now in the trie
		assertTrue(!trie.add("black", 2)); //Can't add it again, should return false
		
		//adding another word starting with b should not increase the size of the roots children list
		assertTrue(trie.add("blonde", 1));
		assertEquals(1, trie.getRoot().getChildren().size());
	}

	@Test
	void removeTest() {
		setupPopulatedTrie();
		
		// We have dislike and disliking in the tree. Both have prefix 'dislik', if we delete disliking (the longer one)
		// dislike should remain intact
		
		trie.remove("disliking");
		assertEquals(null, trie.getNode("disliking")); // is removed
		assertEquals(7, trie.getNode("dislike").getData()); //This one is not.
		
		//As disliking was not the only word starting with d, the size of the roots children list remains the same
		assertEquals(3, trie.getRoot().getChildren().size());
		
		//However, if we remove dislike, the size should go down by one
		trie.remove("dislike");
		assertEquals(2, trie.getRoot().getChildren().size());
	}
	
	@Test
	void getNodeTest() {
		setupPopulatedTrie();
		
		// should be able to get 'block' or 'wine' and their data
		// but if we try to get 'blo' or 'wi' we'll find data = null, these strings exist in the trie, but have no attached value
		assertEquals(1, trie.getNode("black").getData());
		assertEquals(4, trie.getNode("wine").getData());
		assertEquals(5, trie.getNode("win").getData());
		
		assertEquals(null, trie.getNode("wi").getData());
		assertEquals(null, trie.getNode("blo").getData());
	}
	
	@Test
	void getSuggestionsTest() {
		setupPopulatedTrie();
		//As getNode, only return whole words with data
		//Note if you type a whole word it won't be a suggestion, why would you need it if you already typed it
		
		//Expected arrays
		String[] bl = new String[] {"black", "block", "blonde"};
		String[] b = new String[] {"black", "block", "blonde", "bourbon"};
		String[] win = new String[] {"wine"};
		String[] w = new String[] {"war", "win", "wine"};
		String[] dis = new String[] {"dislike", "disliking"};
		
		//Collecting all the TextSuggestion objects and extracting the text to a list
		List<String> _bl = trie.getSuggestions("bl").stream().map(TextSuggestion::getText).collect(Collectors.toList());
		List<String> _b = trie.getSuggestions("b").stream().map(TextSuggestion::getText).collect(Collectors.toList());
		List<String> _win = trie.getSuggestions("win").stream().map(TextSuggestion::getText).collect(Collectors.toList());
		List<String> _w = trie.getSuggestions("w").stream().map(TextSuggestion::getText).collect(Collectors.toList());
		List<String> _dis = trie.getSuggestions("dis").stream().map(TextSuggestion::getText).collect(Collectors.toList());
		
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
		trie.add("blanco", 800);
		trie.add("bronce", 802);
		trie.add("winnie", 803);
		trie.add("zombie", 804);
		
		assertEquals(4, trie.countSuggestions("bl"));
		assertEquals(6, trie.countSuggestions("b"));
		assertEquals(2, trie.countSuggestions("win"));
		assertEquals(4, trie.countSuggestions("w"));
		assertEquals(2, trie.countSuggestions("dis"));
		assertEquals(1, trie.countSuggestions("z")); // No word starting with 'z'
	}
}
