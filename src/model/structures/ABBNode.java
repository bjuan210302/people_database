package model.structures;

public class ABBNode<K extends Comparable<K>,T> {
	
	private K key;
	private T data;
	private ABBNode<K, T> parent;
	private ABBNode<K, T> left;
	private ABBNode<K, T> right;
	
	public ABBNode(K key, T data) {
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

	public ABBNode<K, T> getParent() {
		return parent;
	}

	public void setParent(ABBNode<K, T> parent) {
		this.parent = parent;
	}

	public ABBNode<K, T> getLeft() {
		return left;
	}

	public void setLeft(ABBNode<K, T> left) {
		this.left = left;
	}

	public ABBNode<K, T> getRight() {
		return right;
	}

	public void setRight(ABBNode<K, T> right) {
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
