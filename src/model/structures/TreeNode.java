package model.structures;

import java.util.ArrayList;
public class TreeNode<K extends Comparable<K>,T> {
	
	private K key;
	private T data;
	private TreeNode<K, T> parent;
	private TreeNode<K, T> left;
	private TreeNode<K, T> right;
	private ArrayList<TreeNode<K, T>> siblings;
	
	public TreeNode(K key, T data) {
		this.key = key;
		this.data = data;
		this.siblings = new ArrayList<TreeNode<K,T>>();
		this.siblings.add(this);
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

	public TreeNode<K, T> getParent() {
		return parent;
	}

	public void setParent(TreeNode<K, T> parent) {
		this.parent = parent;
	}

	public TreeNode<K, T> getLeft() {
		return left;
	}

	public void setLeft(TreeNode<K, T> left) {
		this.left = left;
	}

	public TreeNode<K, T> getRight() {
		return right;
	}

	public void setRight(TreeNode<K, T> right) {
		this.right = right;
	}
	
	public void addSibling(TreeNode<K, T> sibling) {
		this.siblings.add(sibling);
	}
	public boolean hasSiblings() {
		return this.siblings.size() > 1;
	}
	public ArrayList<TreeNode<K, T> > getSiblings(){
		return this.siblings;
	}
	
	public void replaceWithSibling() {
		TreeNode<K, T> replacement = siblings.get(1);
		this.data = replacement.data;
		siblings.remove(0);
		replacement.siblings = siblings;
	}
	public void deleteSibling(int i) {
		siblings.remove(i);
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

	public int count() {
		int sum = siblings.size();
		
		if(left != null)
			sum += left.count();
		
		if(right != null)
			sum += right.count();
		
		return sum;
	}
}
