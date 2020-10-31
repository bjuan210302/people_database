package model.structures;

import java.util.HashMap;
import java.util.List;

public class Trie<E> {
	
	private TrieNode<E> root;
	 
    public Trie() {
        root = new TrieNode<E>();
    }

    //Returns false if the key word already exist
    public boolean add(String word, E data) {
        return add(word, root, data);
    }
    private boolean add(String string, TrieNode<E> node, E data) {
    	HashMap<Character, TrieNode<E>> children = node.getChildren();
    	char key = string.charAt(0);
    	String substring = (string.length() > 1) ? string.substring(1) : null;
    	
    	if(children.containsKey(key)){
    		if(substring == null) {
    			return node.getChildren().get(key).attachData(data);
        	}else {
        		return add(substring, children.get(key), data);
        	}
        	
        }else{
        	
        	TrieNode<E> child = new TrieNode<E>(node, key);
        	node.getChildren().put(key, child);
        	if(substring == null) {
        		return child.attachData(data);
        	}else {
        		return add(substring, child, data);
        	}
        }
    }
    
    public void remove(String word) {
    	TrieNode<E> aux = getNode(word);
    	
    	if(aux.hasData())
    		aux.detachData();
    	
    	TrieNode<E> parent = aux.getParent();
    	
    	while(parent != null && aux.isLeaf()) {
    		parent.getChildren().remove(aux.getKey());
    		
    		aux = parent;
    		parent = parent.getParent();
    	}
    		
    }
    
    public TrieNode<E> getNode(String string){
    	HashMap<Character, TrieNode<E>> children = root.getChildren(); 
        TrieNode<E> aux = null;
        boolean longerThanMaxStored = false;
        for(int i = 0; i < string.length(); i++){
        	
            char key = string.charAt(i);

            if(children.containsKey(key)){
                aux = children.get(key);
                children = aux.getChildren();
            }else{
            	longerThanMaxStored = true;
                break;
            }
        }
        return (longerThanMaxStored) ? null : aux;
    }
    
    public List<TextSuggestion<E>> getSuggestions(String prefix){
    	return getNode(prefix).getSuggestions(prefix);
    }
    
    public int countSuggestions(String prefix){
    	TrieNode<E> aux = getNode(prefix);
    	return (aux != null) ? aux.countSuggestions(prefix) : 0;
    }
    
    //for test
    public TrieNode<E> getRoot(){
    	return this.root;
    }
}
