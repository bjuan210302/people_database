package model.structures;

public class BinarySearchTree<K extends Comparable<K>, T> {
	
	protected BSTNode<K, T> root;
	
	public BinarySearchTree() {
		this.root = null;
	}
	
	public BSTNode<K, T> getRoot(){
		return root;
	}
	
	public BSTNode<K, T> search(K key) {
		return search(key, root);
	}

	private BSTNode<K, T> search(K key, BSTNode<K, T> node){
		if(node == null)
			return null;
		
		int compa = key.compareTo(node.getKey()); 

		if(compa == 0) {
			return node;
		}
		else if(compa > 0) {
			return search(key, node.getRight());
		}
		else {
			return search(key, node.getLeft());
		}

	}

	public BSTNode<K, T> add(K key, T data) {
		BSTNode<K, T> node = new BSTNode<>(key, data);

		return add(root, node);
	}
	
	protected BSTNode<K, T> add(BSTNode<K, T> parent, BSTNode<K, T> child) {
		if(root == null) {
			root = child;
			return child;
		}

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
		else {
//			System.out.println("non accepted");
			return null;
		}
		
	}
	
	public BSTNode<K, T> delete(K key){
		BSTNode<K, T> node = search(key);
		
		if(node != null) {
			delete(node);
		}
		
		return node;
	}
	
	private void delete(BSTNode<K, T> z){
		BSTNode<K, T> y;
		BSTNode<K, T> x;
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
	
	private BSTNode<K, T> min(BSTNode<K, T> node){
		while(node.getLeft() != null) {
			node = node.getLeft();
			
		}
		return node;
	}
	
	private BSTNode<K, T> successor(BSTNode<K, T> x){
		if(x.getRight() != null) {
			return min(x.getRight());
			
		}
		BSTNode<K, T> y = x.getParent();
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
