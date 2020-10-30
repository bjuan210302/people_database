package model.structures;

public class ABBTree<K extends Comparable<K>, T> {
	
	protected ABBnode<K, T> root;
	
	public ABBTree() {
		this.root = null;
	}
	
	public ABBnode<K, T> getRoot(){
		return root;
	}
	
	public ABBnode<K, T> search(K key) {
		return search(key, root);
	}
	
	private ABBnode<K, T> search(K key, ABBnode<K, T> node){
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
	
	public ABBnode<K, T> add(K key, T data) {
		ABBnode<K, T> node = new ABBnode<>(key, data);
		
		return add(root, node);
	}
	
	protected ABBnode<K, T> add(ABBnode<K, T> parent, ABBnode<K, T> child) {
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
	

	public ABBnode<K, T> delete(K key){
		ABBnode<K, T> node = search(key);
		if(node != null) {
			return delete(node);
		}
		else {
			return null;
		}
	}
	
	private ABBnode<K, T> delete(ABBnode<K, T> z){
		ABBnode<K, T> y;
		ABBnode<K, T> x;
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
	

	
	public ABBnode<K, T> min(ABBnode<K, T> node){
		while(node.getLeft() != null) {
			node = node.getLeft();
			
		}
		return node;
	}
	
	public ABBnode<K, T> max(ABBnode<K, T> node){
		while(node.getRight() != null) {
			node = node.getRight();
		}
		return node;
	}
	
	public ABBnode<K, T> successor(ABBnode<K, T> x){
		if(x.getRight() != null) {
			return min(x.getRight());
			
		}
		ABBnode<K, T> y = x.getParent();
		while(y != null && x.equals(y.getRight()) ) {
			x = y;
			y = y.getParent();
		}
		return y;
	}
	
	public ABBnode<K, T> predeccessor(ABBnode<K, T> x){
		if(x.getLeft() != null) {
			return max(x.getLeft());
		}
		ABBnode<K, T> y = x.getParent();
		while(y != null && x == y.getLeft()) {
			x = y;
			y = y.getParent();
		}
		return y; 
	}
	
	public String toString() {
		return toString(root, "", true);
	}
	
	private String toString(ABBnode<K, T> node, String indent, boolean last){
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
