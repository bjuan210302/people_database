package model.structures;

public class ABBTree<K extends Comparable<K>, T> {
	
	protected ABBNode<K, T> root;
	
	public ABBTree() {
		this.root = null;
	}
	
	public ABBNode<K, T> getRoot(){
		return root;
	}
	
	public ABBNode<K, T> search(K key) {
		return search(key, root);
	}
	
	private ABBNode<K, T> search(K key, ABBNode<K, T> node){
		if(node!=null) {
			
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
		else
			return null;
		
		
		
	}
	
	public ABBNode<K, T> add(K key, T data) {
		ABBNode<K, T> node = new ABBNode<>(key, data);
		
		return add(root, node);
	}
	
	protected ABBNode<K, T> add(ABBNode<K, T> parent, ABBNode<K, T> child) {
		if(root == null) {
			root = child;
			return child;
		}

		int compa = child.getKey().compareTo(parent.getKey()); 
		if(compa >= 0) {
			if(parent.getRight() == null) {
				parent.setRight(child);
				child.setParent(parent);
				return child;
			}
			
			return add(parent.getRight(), child);
		}
		else {
			if(parent.getLeft() == null) {
				parent.setLeft(child);
				child.setParent(parent);
				return child;
			}
			return add(parent.getLeft(), child);
		}
		
	}
	

	public ABBNode<K, T> delete(K key){
		ABBNode<K, T> node = search(key);
		if(node != null) {
			return delete(node);
		}
		else {
			return null;
		}
	}
	
	private ABBNode<K, T> delete(ABBNode<K, T> z){
		ABBNode<K, T> y;
		ABBNode<K, T> x;
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
		return y;
		
	}
	

	
	public ABBNode<K, T> min(ABBNode<K, T> node){
		while(node.getLeft() != null) {
			node = node.getLeft();
			
		}
		return node;
	}
	
	public ABBNode<K, T> max(ABBNode<K, T> node){
		while(node.getRight() != null) {
			node = node.getRight();
		}
		return node;
	}
	
	public ABBNode<K, T> successor(ABBNode<K, T> x){
		if(x.getRight() != null) {
			return min(x.getRight());
			
		}
		ABBNode<K, T> y = x.getParent();
		while(y != null && x.equals(y.getRight()) ) {
			x = y;
			y = y.getParent();
		}
		return y;
	}
	
	public ABBNode<K, T> predeccessor(ABBNode<K, T> x){
		if(x.getLeft() != null) {
			return max(x.getLeft());
		}
		ABBNode<K, T> y = x.getParent();
		while(y != null && x == y.getLeft()) {
			x = y;
			y = y.getParent();
		}
		return y; 
	}
	
	public String toString() {
		return toString(root, "", true);
	}
	
	private String toString(ABBNode<K, T> node, String indent, boolean last){
		String treeString = "";
	    treeString = indent + "+- " + node +"\n";
	    indent += last ? "   " : "|  ";

	    if(node.getLeft()!=null) {
	    	treeString += toString(node.getLeft(), indent, node.getRight()==null);
	    }else if(node.getRight()!=null) {
	    	treeString += indent+"|\n";
	    }
	    
	    if(node.getRight()!=null) {
	    	treeString += toString(node.getRight(), indent, true);
	    }
	    
	    return treeString;
	    
	}

}
