package model.structures;

import java.util.ArrayList;
import java.util.List;

public class TrieNode {

	private char key;
	private TrieNode parent;
    private ArrayList<TrieNode> children;
    private ArrayList<Character> chars;
    private int wordMultiplicity;
    private boolean isWord;
    
    public TrieNode() {
    	children = new ArrayList<TrieNode>();
    	chars = new ArrayList<Character>();
    	isWord = false;
    }
    public TrieNode(TrieNode parent, char key) {
    	this();
    	this.parent = parent;
    	this.key = key;
    }
    
    public List<String> getSuggestions(String previousCharacters){
    	ArrayList<String> suggestions = new ArrayList<String>();
    	
    	for(TrieNode child: children) {
    		if(child.isWord) {
    			String sugText = previousCharacters + String.valueOf(child.key);
    			suggestions.add(sugText);
    		}
    		suggestions.addAll(child.getSuggestions(previousCharacters + child.key));
    	}
    	
    	return suggestions;
    }
    
    public int countSuggestions(String previousCharacters){
    	int counter = 0;
    	
    	for(TrieNode child: children) {
    		if(child.isWord) {
    			counter++;
    		}
    		counter += child.countSuggestions(previousCharacters + child.key);
    	}
    	
    	return counter;
    }
    
    public int countTotalSuggestions(){
    	int counter = 0;
    	
    	for(TrieNode child: children) {
    		if(child.isWord) {
    			counter++;
    		}
    		counter += child.countTotalSuggestions();
    	}
    	
    	return counter;
    }
    
    public TrieNode getChildWithChar(char c){
    	//This for will do max 26 iterations
    	for(TrieNode child: children) {
    		if(child.key == c)
    			return child;
    	}
    	return null;
    }
    public boolean containsChar(char c) {
    	boolean result = false;
    	if(chars.size() >= 1)
    	for(Character local: chars) {
    		if(local == c) {
    			result = true;
    			break;
    		}
    	}
    	return result;
    }
    public void addChar(Character c) {
    	this.chars.add(c);
    }
    public void addChild(TrieNode t) {
    	this.children.add(t);
    }
    public ArrayList<TrieNode> getChildren() {
		return children;
	}
	public ArrayList<Character> getChars() {
		return chars;
	}
	public void addMultiplicity(int num) {
    	wordMultiplicity += num;
    	isWord = (wordMultiplicity > 0);
    }
    
    public boolean isWord() {
    	return this.isWord;
    }
    
    public boolean isLeaf() {
    	return this.children.isEmpty();
    }
    
    public TrieNode getParent(){
    	return this.parent;
    }
    
    public char getKey(){
    	return this.key;
    }
    
    //for tests
    
    public int getMultiplicity() {
    	return this.wordMultiplicity;
    }
}
