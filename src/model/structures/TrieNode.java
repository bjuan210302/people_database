package model.structures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrieNode<E> {

	private char key;
	private TrieNode<E> parent;
    private HashMap<Character, TrieNode<E>> children;
    private E data;
    private boolean hasData;
    
    public TrieNode() {
    	children = new HashMap<Character, TrieNode<E>>();
    	hasData = false;
    }
    public TrieNode(TrieNode<E> parent, char key) {
    	this.parent = parent;
    	children = new HashMap<Character, TrieNode<E>>();
    	hasData = false;
    	this.key = key;
    }
    
    public List<TextSuggestion<E>> getSuggestions(String previousCharacters){
    	ArrayList<TextSuggestion<E>> suggestions = new ArrayList<TextSuggestion<E>>();
    	
    	
    	for(Map.Entry<Character, TrieNode<E>> childEntry: children.entrySet()) {
    		TrieNode<E> child = childEntry.getValue();
    		
    		if(child.hasData) {
    			String sugText = previousCharacters + String.valueOf(child.getKey());
    			TextSuggestion<E> sug = new TextSuggestion<E>(sugText, child.data);
    			suggestions.add(sug);
    		}
    		
    		suggestions.addAll(child.getSuggestions(previousCharacters + child.key));
    	}
    	
    	return suggestions;
    }
    
    public int countSuggestions(String previousCharacters){
    	int counter = 0;
    	
    	for(Map.Entry<Character, TrieNode<E>> childEntry: children.entrySet()) {
    		TrieNode<E> child = childEntry.getValue();
    		
    		if(child.hasData) {
    			counter++;
    		}
    		
    		counter += child.countSuggestions(previousCharacters + child.key);
    	}
    	
    	return counter;
    }
    
    public HashMap<Character, TrieNode<E>> getChildren(){
    	return this.children;
    }
    
    public boolean attachData(E data) {
    	if(hasData)
    		return false;
    	else {
    		this.data = data;
    		this.hasData = true;
    		return true;
    	}
    }
    public void detachData() {
    	this.hasData = false;
    	this.data = null;
    }
    
    public boolean hasData() {
    	return this.hasData;
    }
    public E getData() {
    	return this.data;
    }
    
    public boolean isLeaf() {
    	return this.children.isEmpty();
    }
    
    public TrieNode<E> getParent(){
    	return this.parent;
    }
    
    public char getKey() {
    	return this.key;
    }
}
