package model.structures;

import java.util.List;

public class BinarySearchTree<K extends Comparable<K>, T> {
	
	protected TreeNode<K, T> root;
	
	public BinarySearchTree() {
		this.root = null;
	}
	
	public TreeNode<K, T> getRoot(){
		return root;
	}
	
	public List<TreeNode<K, T>> search(K key) {
		return search(key, root);
	}

	private List<TreeNode<K, T>> search(K key, TreeNode<K, T> node){
		if(node == null)
			return null;
		
		int compa = key.compareTo(node.getKey()); 

		if(compa == 0) {
			return node.getSiblings();
		}
		else if(compa > 0) {
			return search(key, node.getRight());
		}
		else {
			return search(key, node.getLeft());
		}

	}

	public TreeNode<K, T> add(K key, T data) {
		TreeNode<K, T> child = new TreeNode<>(key, data);
		
		if(root == null) {
			root = child;
			return child;
		}

		return add(root, child);
	}
	
	protected TreeNode<K, T> add(TreeNode<K, T> parent, TreeNode<K, T> child) {

		int compa = child.getKey().compareTo(parent.getKey()); 
		if(compa > 0) {
			if(parent.getRight() == null) {
				parent.setRight(child);
				child.setParent(parent);
				return child;
			}
			
			return add(parent.getRight(), child);
		}
		else if (compa < 0){
			if(parent.getLeft() == null) {
				parent.setLeft(child);
				child.setParent(parent);
				return child;
			}
			return add(parent.getLeft(), child);
		}
		else { //Add a "duplicate"
			parent.addSibling(child);
			return child;
		}
		
	}
	
	public TreeNode<K, T> delete(K key){
		List<TreeNode<K, T>> list = search(key);
		TreeNode<K, T> node = null;
		
		if(list != null)
			node = list.get(0);
		
		if(node != null && node.hasSiblings()) {
			node.replaceWithSibling();
		}
		else if(node != null) {
			delete(node);
		}
		
		return node;
	}
	
	private void delete(TreeNode<K, T> z){
		TreeNode<K, T> y;
		TreeNode<K, T> x;
		if(z.getLeft() == null || z.getRight() == null) {
			y = z;
		}
		else {
			y = successor(z);
		}
		if(y.getLeft() != null) {
			x = y.getLeft();
		}
		else {
			x = y.getRight();
		}
		if(x != null) {
			x.setParent(y.getParent());
		}
		if(y.getParent() == null) {
			root = x;
		}
		else if(y == y.getParent().getLeft()) {
			y.getParent().setLeft(x);
		}
		else {
			y.getParent().setRight(x);
		}
		
		if(y != z) {
			z.setKey(y.getKey());
			z.setData(y.getData());
		}
		
	}
	
	private TreeNode<K, T> min(TreeNode<K, T> node){
		while(node.getLeft() != null) {
			node = node.getLeft();
			
		}
		return node;
	}
	
	private TreeNode<K, T> successor(TreeNode<K, T> x){
		if(x.getRight() != null) {
			return min(x.getRight());
			
		}
		TreeNode<K, T> y = x.getParent();
		while(y != null && x.equals(y.getRight()) ) {
			x = y;
			y = y.getParent();
		}
		return y;
	}

	public int count() {
		return (root != null) ? root.count() : 0;
	}
}
