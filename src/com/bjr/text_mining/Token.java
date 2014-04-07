package com.bjr.text_mining;


public class Token {
	
	private String word;
	private int count;
	
	public Token(String word, int initalValue) {
		this.word = word;
		this.count = initalValue;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
	
	public void setCount(int count) {
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	public void increaseCount() {
		this.count = count + 1;
	}
	
	

}
