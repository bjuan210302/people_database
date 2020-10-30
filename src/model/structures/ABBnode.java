package model.structures;

public class ABBnode<K extends Comparable<K>,T> {
	
	private K key;
	private T data;
	private ABBnode<K, T> parent;
	private ABBnode<K, T> left;
	private ABBnode<K, T> right;
	
	public ABBnode(K key, T data) {
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

	public ABBnode<K, T> getParent() {
		return parent;
	}

	public void setParent(ABBnode<K, T> parent) {
		this.parent = parent;
	}

	public ABBnode<K, T> getLeft() {
		return left;
	}

	public void setLeft(ABBnode<K, T> left) {
		this.left = left;
	}

	public ABBnode<K, T> getRight() {
		return right;
	}

	public void setRight(ABBnode<K, T> right) {
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
