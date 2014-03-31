package com.bjr.word_counting;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void main(String[] args) {

		StringBuffer content = new StringBuffer();
		String pattern = "(\\s+)|(\\.)|(\\,)|(\\()|(\\))|(\\-)|(\\„)|(\\“)|(\\r|\\n)";
		String[] splitString;
		ArrayList<Token> tokenArray = new ArrayList<Token>();
		StopwordArray stopwords = new StopwordArray();
		ArrayList<Token> resultArray = new ArrayList<Token>();

		String line;
		String lineFeed = System.getProperty("line.separator");
		try {
			Reader r = new InputStreamReader(new FileInputStream("AGB_Dropbox.txt"), "UTF8");
		 // Reader r = new InputStreamReader(newFileInputStream("AGB_Vodafone.txt"));
			BufferedReader in = new BufferedReader(r);
			while ((line = in.readLine()) != null) {
				content.append(line).append(lineFeed);
				// content.append(line);
			}
			in.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		splitString = content.toString().split(pattern);

		for (String word : splitString) {
			if (fillArray(word, tokenArray)) {
				tokenArray.add(new Token(word));
			}
		}

		// Remove stopwords
		for (Token token : tokenArray) {
			if (!containsCaseInsensitive(token.getWord(), stopwords.getArray())) {
				resultArray.add(token);
			}
		}

		Collections.sort(resultArray, new SortCount());

		// Remove first "blank" token 
		 resultArray.remove(0);

		for (Token token : resultArray) {
			System.out.println(token.getWord() + " : " + token.getCount());
		}
	}

	private static boolean fillArray(String word, ArrayList<Token> tokenArray) {

		for (Token token : tokenArray) {
			if (token.getWord().equalsIgnoreCase(word)) {
				token.increaseCount();
				return false;
			}
		}
		return true;
	}

	private static boolean containsCaseInsensitive(String s,
			ArrayList<String> arrayList) {
		for (String token : arrayList) {
			if (token.equalsIgnoreCase(s)) {
				return true;
			}
		}
		return false;
	}

}
