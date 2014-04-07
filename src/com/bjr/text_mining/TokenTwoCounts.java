package com.bjr.text_mining;


public class TokenTwoCounts {
	
	private String word;
	private int count1;
	private int count2;
	
	public TokenTwoCounts(String word, int initalValueCount1, int initalValueCount2) {
		this.word = word;
		this.count1 = initalValueCount1;
		this.count2 = initalValueCount2;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
	
	public void setCount1(int count) {
		this.count1 = count;
	}

	public int getCount1() {
		return count1;
	}

	public void setCount2(int count) {
		this.count2 = count;
	}

	public int getCount2() {
		return count2;
	}
	
	

}
