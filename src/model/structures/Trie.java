package model.structures;

import java.util.List;

public class Trie {
	
	private TrieNode root;
	 
    public Trie() {
        root = new TrieNode();
    }

    //Returns false if the key word already exist
    public void add(String word) {
        add(word, root);
    }
    private void add(String string, TrieNode node) {
    	char currentChar = string.charAt(0);
    	String substring = (string.length() > 1) ? string.substring(1) : null;
    	
    	if(node.containsChar(currentChar)){
    		if(substring == null) { //It's last character
    			node.getChildWithChar(currentChar).addMultiplicity(1);
        	}else {
        		add(substring, node.getChildWithChar(currentChar));
        	}
        	
        }else{// The character doesn't exist
        	TrieNode child = new TrieNode(node, currentChar);
        	node.addChild(child);
        	node.addChar(currentChar);
        	if(substring == null) {
        		child.addMultiplicity(1);
        	}else {
        		add(substring, child);
        	}
        }
    }
    
    public boolean remove(String word) {
    	TrieNode aux = getNode(word);
    	if(aux == null)
    		return false;
    	
    	if(aux.isWord())
    		aux.addMultiplicity(-1);
    	
    	TrieNode parent = aux.getParent();
    	
    	while(parent != null && aux.isLeaf() && !aux.isWord()) {
    		parent.getChildren().remove(aux);
    		parent.getChars().remove((Character)aux.getKey());
    		
    		aux = parent;
    		parent = parent.getParent();
    	}
    		
    	return true;
    }

    public TrieNode getNode(String string){
    	return getNode(string, root);
    }
    
    private TrieNode getNode(String string, TrieNode node){
    	String substring = (string.length() > 1) ? string.substring(1) : null;
    	char currentChar = string.charAt(0);
    	if(node.containsChar(currentChar)){
    		if(substring == null)
    			return node.getChildWithChar(currentChar);
    		else
    			return getNode(substring, node.getChildWithChar(currentChar));
    	}else{
    		return null;
    	}
    }

    public List<String> getSuggestions(String prefix){
    	TrieNode aux = getNode(prefix);
    	return (aux != null) ? aux.getSuggestions(prefix) : null;
    }
    
    public int countSuggestions(String prefix){
    	TrieNode aux = getNode(prefix);
    	return (aux != null) ? aux.countSuggestions(prefix) : 0;
    }
    
    public int countTotalSuggestions(){
    	return root.countTotalSuggestions();
    }
    
    //for test
    public TrieNode getRoot(){
    	return this.root;
    }
}
