package model.structures;

public class TextSuggestion<E> {
	
	private String textKey;
	private E data;
	
	public TextSuggestion(String textKey, E data) {
		this.textKey = textKey;
		this.data = data;
	}
	
	public String getText() {
		return this.textKey;
	}
	public E getData() {
		return this.data;
	}
}
