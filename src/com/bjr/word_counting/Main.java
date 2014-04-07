package com.bjr.word_counting;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletContext;

import com.google.gson.Gson;

public class Main {
	
	public static double distance = -1;

	public static void main(String[] args) {

//		displayTokenArrayContent(generateWordVectorFromFile("war\\WEB-INF\\agb_templates\\AGB_Muster.txt", true));
		
//		System.out.println("\nEuclidean distance Dropbox - YouTube: " + calculateEuclideanDistance(generateWordVectorFromFile("AGB_Dropbox.txt", true), generateWordVectorFromFile("Nutzungsbedingungen_YouTube.txt", true)));
//		System.out.println("\nEuclidean distance Dropbox - Vodafone: " + calculateEuclideanDistance(generateWordVectorFromFile("AGB_Dropbox.txt", true), generateWordVectorFromFile("AGB_Vodafone.txt", true)));
//		System.out.println("\nEuclidean distance Dropbox - Dropbox_changed: " + calculateEuclideanDistance(generateWordVectorFromFile("AGB_Dropbox.txt", true), generateWordVectorFromFile("AGB_Dropbox_changed.txt", true)));
//		System.out.println(getWordVectorComparisonAsCSV("Um die Services zu nutzen abd a3f dfdf", "Hallo dies ist ein Test dfdf a3f dfdf"));
//		System.out.println(getWordVectorComparisonWithTemplateAsCSV("Um die Services zu nutzen abd a3f dfdf"));
	}
	
	public static String getWordVectorComparisonAsCSV(String agb_text1, String agb_text2) {
		
		StringBuffer sb = new StringBuffer();
		
		ArrayList<String> supersetArray = new ArrayList<String>();
		ArrayList<Token> supersetTokenArray1 = new ArrayList<Token>();
		ArrayList<Token> supersetTokenArray2 = new ArrayList<Token>();
		
		ArrayList<Token> tokenArray1 = generateWordVectorFromString(agb_text1);
		ArrayList<Token> tokenArray2 = generateWordVectorFromString(agb_text2);

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
		
		sb.append("Word,AGB 1,AGB 2\n");
		
		// Calculate euclidean distance
		
		double sum = 0;
		
		for (int i = 0; i < supersetArray.size(); i++) {
			
			if (i < 26) {
				sb.append(supersetTokenArray1.get(i).getWord() + "," + supersetTokenArray1.get(i).getCount() + "," + supersetTokenArray2.get(i).getCount() + "\n");
				sum = sum + Math.pow( (supersetTokenArray1.get(i).getCount() - supersetTokenArray2.get(i).getCount()), 2);
			} else {
				break;
			}
		}
		
		distance = Math.sqrt(sum);
		
		return sb.toString();
	}
	
	public static String getWordVectorComparisonWithTemplateAsCSV(String agb_text, ServletContext servletContext) {
		StringBuffer sb = new StringBuffer();
		
		ArrayList<String> supersetArray = new ArrayList<String>();
		ArrayList<Token> supersetTokenArray1 = new ArrayList<Token>();
		ArrayList<Token> supersetTokenArray2 = new ArrayList<Token>();
		
		ArrayList<Token> tokenArray1 = generateWordVectorFromString(agb_text);
		
		StringBuffer content = new StringBuffer();
		Reader reader = null;
		String line;
		String lineFeed = System.getProperty("line.separator");
		
		try {
			reader = new InputStreamReader(servletContext.getResourceAsStream("/WEB-INF/agb_templates/AGB_Muster.txt"));
			BufferedReader bReader = new BufferedReader(reader);
			while ((line = bReader.readLine()) != null) {
				content.append(line).append(lineFeed);
//				 content.append(line);
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
		
		ArrayList<Token> tokenArray2 = generateWordVectorFromString(content.toString());
		
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
		
		sb.append("Word,Eingegebene AGB,Muster AGB\n");
		
		// Calculate euclidean distance

		double sum = 0;
		
		for (int i = 0; i < supersetArray.size(); i++) {

			if (i < 26) {
				sb.append(supersetTokenArray1.get(i).getWord() + "," + supersetTokenArray1.get(i).getCount() + "," + supersetTokenArray2.get(i).getCount() + "\n");
				sum = sum + Math.pow( (supersetTokenArray1.get(i).getCount() - supersetTokenArray2.get(i).getCount()), 2);
			} else {
				break;
			}
			
		}
		
		distance = Math.sqrt(sum);
		
//		String s = sb.toString();
//		byte[] b = null;
//		try {
//			b = s.getBytes("UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String st = null;
//		try {
//			st = new String(b, "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return st;
		
		return sb.toString();
	}
	
	public static String getWordVectorAsJSON(String agb_text) {
		
		Gson gson = new Gson();
		String result = null;
		
		ArrayList<Token> arrayList = generateWordVectorFromString(agb_text);
		
		result = "{\"children\":" + gson.toJson(arrayList) + "}"; 
		
		return result;
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
	
//	private static ArrayList<Token> generateWordVectorFromFile(String filename, boolean useUTF8) {
//		
//		String pattern = "(\\s+)|(\\.)|(\\!)|(\\,)|(\\;)|(\\()|(\\))|(\\„)|(\\“)|(\\-)|(\\r)|(\\n)";
//		String[] splitString;
//		ArrayList<Token> tokenArray = new ArrayList<Token>();
//		StopwordArray stopwords = new StopwordArray();
//		ArrayList<Token> resultArray = new ArrayList<Token>();
//		
//		StringBuffer content = new StringBuffer();
//		Reader reader = null;
//		String line;
//		String lineFeed = System.getProperty("line.separator");
//		try {
//			if (useUTF8) {
//				reader = new InputStreamReader(new FileInputStream(filename), "UTF8");
//			} else {
//				reader = new InputStreamReader(new FileInputStream(filename));
//			}
//			
//			BufferedReader bReader = new BufferedReader(reader);
//			while ((line = bReader.readLine()) != null) {
//				content.append(line).append(lineFeed);
//				// content.append(line);
//			}
//			bReader.close();
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		splitString = content.toString().split(pattern);
//
//		for (String word : splitString) {
//			if (fillArray(word, tokenArray)) {
//				tokenArray.add(new Token(word.toLowerCase(), 1));
//			}
//		}
//
//		// Remove stopwords
//		for (Token token : tokenArray) {
//			if (!containsCaseInsensitive(token.getWord(), stopwords.getArray())) {
//				resultArray.add(token);
//			}
//		}
//
//		Collections.sort(resultArray, new SortCount());
//
//		// Remove first -blank- token
//		 resultArray.remove(0);
//
//		return resultArray;
//	}
	
	
private static ArrayList<Token> generateWordVectorFromString(String agb_text) {
	
	String pattern = "(\\s+)|(\\.)|(\\!)|(\\,)|(\\;)|(\\()|(\\))|(\\„)|(\\“)|(\\-)|(\\r)|(\\n)";
	String[] splitString;
	ArrayList<Token> tokenArray = new ArrayList<Token>();
	StopwordArray stopwords = new StopwordArray();
	ArrayList<Token> resultArray = new ArrayList<Token>();
		
		splitString = agb_text.toString().split(pattern);

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
//	private static double calculateEuclideanDistance(ArrayList<Token> tokenArray1, ArrayList<Token> tokenArray2) {
//		
//	
//		ArrayList<String> supersetArray = new ArrayList<String>();
//		ArrayList<Token> supersetTokenArray1 = new ArrayList<Token>();
//		ArrayList<Token> supersetTokenArray2 = new ArrayList<Token>();
//
////		 Generate a superset of the dimensions of both tokenArrays
//		
////		 Copy entire first array
//		for (Token token : tokenArray1) {
//			supersetArray.add(token.getWord());
//		}
//		
////		 Copy tokens from second array which are not already present
//		for (Token token : tokenArray2) {
//			if (!containsCaseInsensitive(token.getWord(), supersetArray)) {
//				supersetArray.add(token.getWord());
//			}
//		}
//		
//		
////		 Map the input arrays to the superset
//		
//		for (String word : supersetArray) {
//			supersetTokenArray1.add(new Token(word.toLowerCase(), 0));
//		}
//		
//		for (Token token : tokenArray1) {
//			for(Token supersetToken : supersetTokenArray1) {
//				if (token.getWord().equalsIgnoreCase(supersetToken.getWord())) {
//					supersetToken.setCount(token.getCount());
//				}
//			}
//		}
//		
//		for (String word : supersetArray) {
//			supersetTokenArray2.add(new Token(word.toLowerCase(), 0));
//		}
//		
//		for (Token token : tokenArray2) {
//			for(Token supersetToken : supersetTokenArray2) {
//				if (token.getWord().equalsIgnoreCase(supersetToken.getWord())) {
//					supersetToken.setCount(token.getCount());
//				}
//			}
//		}
//		
//		
//	// Perform euclidean distance calculation
//		
//		double sum = 0;
//		
//		for (int i = 0; i < supersetArray.size(); i++) {
////			System.out.println("Array1: " + supersetTokenArray1.get(i).getWord() + " --> " + supersetTokenArray1.get(i).getCount() + "    Array2: " + supersetTokenArray2.get(i).getWord() + " --> " + supersetTokenArray2.get(i).getCount());
//			sum = sum + Math.pow( (supersetTokenArray1.get(i).getCount() - supersetTokenArray2.get(i).getCount()), 2);
//		}
//		
//		double distance = Math.sqrt(sum); 
//					
//		return distance;
//	}

}