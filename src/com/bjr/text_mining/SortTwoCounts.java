package com.bjr.text_mining;

import java.util.Comparator;


public class SortTwoCounts implements Comparator<TokenTwoCounts> {

	@Override
	public int compare(TokenTwoCounts token1, TokenTwoCounts token2) {
		return (token2.getCount1() + token2.getCount2()) - (token1.getCount1() + token1.getCount2());
	}

}
