package model.structures;

public class BSTNode<K extends Comparable<K>,T> {
	
	private K key;
	private T data;
	private BSTNode<K, T> parent;
	private BSTNode<K, T> left;
	private BSTNode<K, T> right;
	
	public BSTNode(K key, T data) {
		this.key = key;
		this.data = data;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public BSTNode<K, T> getParent() {
		return parent;
	}

	public void setParent(BSTNode<K, T> parent) {
		this.parent = parent;
	}

	public BSTNode<K, T> getLeft() {
		return left;
	}

	public void setLeft(BSTNode<K, T> left) {
		this.left = left;
	}

	public BSTNode<K, T> getRight() {
		return right;
	}

	public void setRight(BSTNode<K, T> right) {
		this.right = right;
	}
	
	public boolean isEmpty() {
		return false;
	}
	
	public boolean isLeaf() {
		return (left.isEmpty() && right.isEmpty());
	}
	
	public int getBalanceFactor() {
		int bfRight = (right == null) ? 0 : right.getHeight();
		int bfLeft = (left == null) ? 0 : left.getHeight();
		return bfLeft - bfRight;
	}
	public int getHeight() {
		int hRight = (right == null) ? 0 : right.getHeight();
		int hLeft = (left == null) ? 0 : left.getHeight();
		
		return (hRight > hLeft) ? hRight+1 : hLeft+1;
	}
	
	public String toString() {
		return   "(" + getKey().toString() + ")";
	}
	

}
