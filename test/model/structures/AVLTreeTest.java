package model.structures;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AVLTreeTest {

	AVLTree<Integer, String> avl;
	
	private void setupEmpty() {
		avl = new AVLTree<Integer, String>();
	}
	
	private void setupPopulatedTree() {
		setupEmpty();
		avl.add(5, "brown");
		avl.add(1, "blue");
		avl.add(2, "black");
		avl.add(3, "red");
		avl.add(4, "beige");
		avl.add(6, "pink");
		avl.add(7, "white");
	}
	
	@Test
	void testAdd() {
		setupEmpty();

		assertTrue(avl.add(3, "red").getData().equals("red")); //Add returns the added values as a node
		assertTrue(avl.root.getData().equals("red")); //The first node added is root

		String toLeft = "blue";
		String toRight = "black";

		avl.add(1, toLeft); //This should be root's left child
		avl.add(5, toRight); //This should be root's right child

		TreeNode<Integer, String> left = avl.root.getLeft();
		TreeNode<Integer, String> right = avl.root.getRight();

		assertTrue(left.getData().equals(toLeft));
		assertTrue(right.getData().equals(toRight));

		assertEquals(1, avl.add(1, "white").getKey()); //Is already in the tree, but can be added again
		
		//add some to the right nodes to test balance
		avl.add(4, "beige");
		avl.add(6, "pink");
		avl.add(7, "white");
		
		assertTrue(Math.abs(avl.getRoot().getBalanceFactor()) < 2);
	}

	@Test
	void testDelete() {
		setupPopulatedTree();
		int id = 5;
		
		assertEquals("brown", avl.search(id).get(0).getData()); //Make sure the node exists
		assertEquals("brown", avl.delete(id).getData()); //Delete the node, should return the removed node
		assertEquals(null, avl.search(id)); //The node doesn't exist anymore
		assertEquals(null, avl.delete(id)); //Can't delete it again
		
		//delete some nodes to test balance
		avl.delete(5);
		avl.delete(7);
		avl.delete(6);
		assertTrue(Math.abs(avl.getRoot().getBalanceFactor()) < 2);
	}
	
}
