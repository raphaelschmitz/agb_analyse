package com.bjr.word_counting;

import java.util.Comparator;


public class SortCount implements Comparator<Token> {

	@Override
	public int compare(Token token1, Token token2) {
		return token2.getCount() - token1.getCount();
	}

}
