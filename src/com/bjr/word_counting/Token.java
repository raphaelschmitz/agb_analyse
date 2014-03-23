package com.bjr.word_counting;

public class Token {
	
	private String word;
	private int count;
	
	public Token(String word) {
		this.word = word;
		this.count = 1;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getCount() {
		return count;
	}

	public void increaseCount() {
		this.count = count + 1;
	}
	
	

}
