package model.structures;

public class AVLTree<K extends Comparable<K>, T> extends ABBTree<K, T>{
	
	@Override
	public ABBnode<K, T> add(K key, T data) {
		ABBnode<K, T> ref = super.add(key, data);
		balance(ref.getParent());
		return ref;
	}
	
	@Override
	public ABBnode<K, T> delete(K key) {
		ABBnode<K, T> ref = super.delete(key);
		balance(ref.getParent());
		return ref;
	}
	
	//Doesn't admit node = null
	private void balance(ABBnode<K, T> parent) {

		while(parent != null) {

			if(parent.getBalanceFactor() == 2) {//Big tree is left-heavy
				if(parent.getLeft().getBalanceFactor() >= 0) {//left subtree is left-heavy
					rightRotate(parent);
				}
				else {//left subtree is right-heavy
					leftRotate(parent.getLeft());
					rightRotate(parent);
				}
			}
			else if(parent.getBalanceFactor() == -2){//Big tree is right-heavy
				if(parent.getRight().getBalanceFactor() <= 0) {//right subtree is right-heavy
					leftRotate(parent);
				}
				else {//right subtree is left-heavy
					rightRotate(parent.getRight());
					leftRotate(parent);
				}
			}
			
			parent = parent.getParent();
		}
	}
	
	private void leftRotate(ABBnode<K, T> node) {
		
		ABBnode<K, T> rNode = node.getRight();
		ABBnode<K, T> grandpa = node.getParent();
		
		if(grandpa == null) {
			root = rNode;
			rNode.setParent(null);
		}
		else if(node == grandpa.getLeft()) {
			grandpa.setLeft(rNode);
			rNode.setParent(grandpa);
		}
		else if(node == grandpa.getRight()){
			grandpa.setRight(rNode);
			rNode.setParent(grandpa);
		}
		
		
		node.setRight(rNode.getLeft());
		if(node.getRight() != null)
			node.getRight().setParent(node);
		
		rNode.setLeft(node);
		node.setParent(rNode);
	}
	
	private void rightRotate(ABBnode<K, T> node) {
		ABBnode<K, T> lNode = node.getLeft();
		ABBnode<K, T> grandpa = node.getParent();
		
		if(grandpa == null) {
			root = lNode;
			lNode.setParent(null);
		}
		else if(node == grandpa.getLeft()) {
			grandpa.setLeft(lNode);
			lNode.setParent(grandpa);
		}
		else if(node == grandpa.getRight()){
			grandpa.setRight(lNode);
			lNode.setParent(grandpa);
		}
		
		node.setLeft(lNode.getRight());
		if(node.getLeft() != null)
			node.getLeft().setParent(node);
		
		lNode.setRight(node);
		node.setParent(lNode);
	}
}
