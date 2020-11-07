package model.structures;

import java.util.List;

public interface BSTInterface<K extends Comparable<K>,T> {
	public TreeNode<K, T> add(K key, T data);
	public TreeNode<K, T> delete(K key);
	public List<TreeNode<K, T>> search(K key);
	

}
