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

//		displayTokenArrayContent(generateWordVector("AGB_Dropbox.txt", true));
//		displayTokenArrayContent(generateWordVector("AGB_Vodafone.txt", false));
//		displayTokenArrayContent(generateWordVector("Nutzungsbedingungen_YouTube.txt", false));
		
		System.out.println("\nEuclidean distance Dropbox - YouTube: " + calculateEuclideanDistance(generateWordVector("AGB_Dropbox.txt", true), generateWordVector("Nutzungsbedingungen_YouTube.txt", true)));
		System.out.println("\nEuclidean distance Dropbox - Vodafone: " + calculateEuclideanDistance(generateWordVector("AGB_Dropbox.txt", true), generateWordVector("AGB_Vodafone.txt", true)));
		System.out.println("\nEuclidean distance Dropbox - Dropbox_changed: " + calculateEuclideanDistance(generateWordVector("AGB_Dropbox.txt", true), generateWordVector("AGB_Dropbox_changed.txt", true)));

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

	private static boolean containsCaseInsensitive(String s, ArrayList<String> arrayList) {
		for (String token : arrayList) {
			if (token.equalsIgnoreCase(s)) {
				return true;
			}
		}
		return false;
	}
	
//	private static boolean containsTokenCaseInsensitive(String s, ArrayList<Token> arrayList) {
//		for (Token token : arrayList) {
//			if (token.getWord().equalsIgnoreCase(s)) {
//				return true;
//			}
//		}
//		return false;
//	}
	
	private static ArrayList<Token> generateWordVector(String filename, boolean useUTF8) {
		
		StringBuffer content = new StringBuffer();
		String pattern = "(\\s+)|(\\.)|(\\!)|(\\,)|(\\;)|(\\()|(\\))|(\\–)|(\\„)|(\\“)|(\\-)|(\\r)|(\\n)";
		String[] splitString;
		ArrayList<Token> tokenArray = new ArrayList<Token>();
		StopwordArray stopwords = new StopwordArray();
		ArrayList<Token> resultArray = new ArrayList<Token>();
		Reader reader = null;

		String line;
		String lineFeed = System.getProperty("line.separator");
		try {
			if (useUTF8) {
				reader = new InputStreamReader(new FileInputStream(filename), "UTF8");
			} else {
				reader = new InputStreamReader(new FileInputStream(filename));
			}
			
			BufferedReader bReader = new BufferedReader(reader);
			while ((line = bReader.readLine()) != null) {
				content.append(line).append(lineFeed);
				// content.append(line);
			}
			bReader.close();
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
				tokenArray.add(new Token(word.toLowerCase(), 1));
			}
		}

		// Remove stopwords
		for (Token token : tokenArray) {
			if (!containsCaseInsensitive(token.getWord(), stopwords.getArray())) {
				resultArray.add(token);
			}
		}

		Collections.sort(resultArray, new SortCount());

		// Remove first -blank- token
		 resultArray.remove(0);

		return resultArray;
	}
	
	private static void displayTokenArrayContent(ArrayList<Token> tokenArray) {
		
		int numberOfTokens = 0;
		
		// Print word vector content
		for (Token token : tokenArray) {
			numberOfTokens++;
			System.out.println(token.getWord() + " --> " + token.getCount());
		}
		
		System.out.println("Total number of tokens: " + numberOfTokens);
	}
		
//	private static ArrayList<Token> calculateEuclideanDistance(ArrayList<Token> tokenArray1, ArrayList<Token> tokenArray2) {
	private static double calculateEuclideanDistance(ArrayList<Token> tokenArray1, ArrayList<Token> tokenArray2) {
		
	
		ArrayList<String> supersetArray = new ArrayList<String>();
		ArrayList<Token> supersetTokenArray1 = new ArrayList<Token>();
		ArrayList<Token> supersetTokenArray2 = new ArrayList<Token>();

//		 Generate a superset of the dimensions of both tokenArrays
		
//		 Copy entire first array
		for (Token token : tokenArray1) {
			supersetArray.add(token.getWord());
		}
		
//		 Copy tokens from second array which are not already present
		for (Token token : tokenArray2) {
			if (!containsCaseInsensitive(token.getWord(), supersetArray)) {
				supersetArray.add(token.getWord());
			}
		}
		
		
//		 Map the input arrays to the superset
		
		for (String word : supersetArray) {
			supersetTokenArray1.add(new Token(word.toLowerCase(), 0));
		}
		
		for (Token token : tokenArray1) {
			for(Token supersetToken : supersetTokenArray1) {
				if (token.getWord().equalsIgnoreCase(supersetToken.getWord())) {
					supersetToken.setCount(token.getCount());
				}
			}
		}
		
		for (String word : supersetArray) {
			supersetTokenArray2.add(new Token(word.toLowerCase(), 0));
		}
		
		for (Token token : tokenArray2) {
			for(Token supersetToken : supersetTokenArray2) {
				if (token.getWord().equalsIgnoreCase(supersetToken.getWord())) {
					supersetToken.setCount(token.getCount());
				}
			}
		}
		
		
	// Perform euclidean distance calculation
		
		double sum = 0;
		
		for (int i = 0; i < supersetArray.size(); i++) {
//			System.out.println("Array1: " + supersetTokenArray1.get(i).getWord() + " --> " + supersetTokenArray1.get(i).getCount() + "    Array2: " + supersetTokenArray2.get(i).getWord() + " --> " + supersetTokenArray2.get(i).getCount());
			sum = sum + Math.pow( (supersetTokenArray1.get(i).getCount() - supersetTokenArray2.get(i).getCount()), 2);
		}
		
		double distance = Math.sqrt(sum); 
					
		return distance;
	}

}
