package model.structures;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BinarySearchTreeTest {

	private BinarySearchTree<Integer, String> bst;
	
	private void setupEmpty() {
		bst = new BinarySearchTree<Integer, String>();
	}
	
	private void setupPopulatedTree() {
		setupEmpty();
		bst.add(5, "brown");
		bst.add(1, "blue");
		bst.add(2, "black");
		bst.add(3, "red");
		bst.add(4, "beige");
		bst.add(6, "pink");
		bst.add(7, "white");
	}
	
	@Test
	void addTest() {
		setupEmpty();
		
		assertTrue(bst.add(3, "red").getData().equals("red")); //Add returns the added values as a node
		assertTrue(bst.root.getData().equals("red")); //The first node added is root
		
		String toLeft = "blue";
		String toRight = "black";
		
		bst.add(1, toLeft); //This should be root's left child
		bst.add(5, toRight); //This should be root's right child
		
		TreeNode<Integer, String> left = bst.root.getLeft();
		TreeNode<Integer, String> right = bst.root.getRight();
		
		assertTrue(left.getData().equals(toLeft));
		assertTrue(right.getData().equals(toRight));
		
		assertEquals(1, bst.add(1, "white").getKey()); //The same key can be added several times, it will be added as a sibling
		assertEquals(2, bst.search(1).size()); //The same key can be added several times, it will be added as a sibling
	}

	@Test
	void searchTest() {
		setupPopulatedTree();
		int id = 5;
		
		assertEquals("brown", bst.search(id).get(0).getData()); //Returns the node when exists
		bst.delete(id).getData(); //Delete the node
		assertEquals(null, bst.search(id)); //The node is no longer in the tree
		
		bst.add(id, "beige"); //Adding the node (key) again with different data
		assertEquals("beige", bst.search(id).get(0).getData());
		
		//If we add the node (key) again and eliminate the first one, this should not be affected
		bst.add(id, "gray");
		assertEquals("beige", bst.search(id).get(0).getData());
		bst.delete(id);
		assertEquals("gray", bst.search(id).get(0).getData());
	}
	
	@Test
	void deleteTest() {
		setupPopulatedTree();
		int id = 5;
		
		assertEquals("brown", bst.search(id).get(0).getData()); //Make sure the node exists
		bst.delete(id); //Delete the node
		assertEquals(null, bst.search(id)); //The node doesn't exist anymore
		assertEquals(null, bst.delete(id)); //Can't delete it again
	}
	
	@Test
	void countTest() {
		setupEmpty();
		assertEquals(0, bst.count());
		
		setupPopulatedTree(); //Populated with 7 nodes
		assertEquals(7, bst.count());
	}
}
