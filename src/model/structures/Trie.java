package model.structures;

import java.util.HashMap;
import java.util.List;

public class Trie<E> {
	
	public TrieNode<E> root;
	 
    public Trie() {
        root = new TrieNode<E>();
    }

    //Returns false if the key word already exist
    public boolean add(String word, E data) {
        return add(word, root, data);
    }
    private boolean add(String string, TrieNode<E> parent, E data) {
    	if(string == null)
    		return false;
    	
    	HashMap<Character, TrieNode<E>> children = parent.getChildren();
    	char key = string.charAt(0);
    	String substring = (string.length() > 1) ? string.substring(1) : null;
    	
    	if(children.containsKey(key)){
        	return add(substring, children.get(key), data);
        	
        }else{
        	
        	TrieNode<E> child = new TrieNode<E>(parent, key);
        	parent.getChildren().put(key, child);
        	
        	if(substring == null) {
        		child.attachData(data);
        		return true;
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
        
        for(int i = 0; i < string.length(); i++){
            char key = string.charAt(i);
            
            if(children.containsKey(key)){
                aux = children.get(key);
                children = aux.getChildren();
            }else{
                break;
            }
        }
 
        return aux;
    }
    
    public List<TextSuggestion<E>> getSuggestions(String prefix){
    	return getNode(prefix).getSuggestions(prefix);
    }
    
    public int countSuggestions(String prefix){
    	return getNode(prefix).countSuggestions(prefix);
    }
}
