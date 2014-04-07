package com.bjr.text_mining;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletContext;

import com.google.gson.Gson;

public class WordMiner {
	
	public static double distance = -1;

	public static void main(String[] args) {

		displayTokenArrayContent(generateWordVectorFromFile("war\\WEB-INF\\agb_templates\\Nutzungsbedingungen_YouTube.txt", false));
		
//		System.out.println("\nEuclidean distance Dropbox - YouTube: " + calculateEuclideanDistance(generateWordVectorFromFile("AGB_Dropbox.txt", true), generateWordVectorFromFile("Nutzungsbedingungen_YouTube.txt", true)));
//		System.out.println("\nEuclidean distance Dropbox - Vodafone: " + calculateEuclideanDistance(generateWordVectorFromFile("AGB_Dropbox.txt", true), generateWordVectorFromFile("AGB_Vodafone.txt", true)));
//		System.out.println("\nEuclidean distance Dropbox - Dropbox_changed: " + calculateEuclideanDistance(generateWordVectorFromFile("AGB_Dropbox.txt", true), generateWordVectorFromFile("AGB_Dropbox_changed.txt", true)));
//		System.out.println(getWordVectorComparisonAsCSV("Um die Services zu nutzen abd a3f dfdf", "Hallo dies ist ein Test dfdf a3f dfdf"));
//		System.out.println(getWordVectorComparisonWithTemplateAsCSV("Um die Services zu nutzen abd a3f dfdf"));
	}
	
	public String getWordVectorComparisonAsCSV(String agb_text1, String agb_text2) {
		
		StringBuffer sb = new StringBuffer();
		ArrayList<Token> tokenArray1 = generateWordVectorFromString(agb_text1);
		ArrayList<Token> tokenArray2 = generateWordVectorFromString(agb_text2);
		
		ArrayList<String> supersetArray = new ArrayList<String>();
		ArrayList<Token> supersetTokenArray1 = new ArrayList<Token>();
		ArrayList<Token> supersetTokenArray2 = new ArrayList<Token>();
		
		ArrayList<TokenTwoCounts> supersetTokenArray = new ArrayList<TokenTwoCounts>();
		
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
		
		// Copy the two arrays to one common array with two counters
		
		for (int i = 0; i < supersetArray.size(); i++) {
			supersetTokenArray.add(new TokenTwoCounts(supersetTokenArray1.get(i).getWord(),supersetTokenArray1.get(i).getCount(),supersetTokenArray2.get(i).getCount()));
		}
		
		
		// Sort common array for highest count sum of both tokens
		
		Collections.sort(supersetTokenArray, new SortTwoCounts());
		
		sb.append("Word,AGB 1,AGB 2\n");
		
		// Add the first 26 entries to chart
		
		for (int i = 0; i < supersetTokenArray.size(); i++) {

			if (i < 26) {
				sb.append(supersetTokenArray.get(i).getWord() + "," + supersetTokenArray.get(i).getCount1() + "," + supersetTokenArray.get(i).getCount2() + "\n");
			} else {
				break;
			}
			
		}
		
		// Calculate euclidean distance for all entries

		double sum = 0;
		
		for (int i = 0; i < supersetTokenArray.size(); i++) {

				sum = sum + Math.pow( (supersetTokenArray.get(i).getCount1() - supersetTokenArray.get(i).getCount2()), 2);
		}
		
		distance = Math.sqrt(sum);
		
		return sb.toString();
	}
	
	public String getWordVectorComparisonWithTemplateAsCSV(String agb_text, ServletContext servletContext) {
		
		StringBuffer sb = new StringBuffer();
		
		ArrayList<String> supersetArray = new ArrayList<String>();
		ArrayList<Token> supersetTokenArray1 = new ArrayList<Token>();
		ArrayList<Token> supersetTokenArray2 = new ArrayList<Token>();
	
		ArrayList<TokenTwoCounts> supersetTokenArray = new ArrayList<TokenTwoCounts>();
		
		ArrayList<Token> tokenArray1 = generateWordVectorFromString(agb_text);
		
		StringBuffer content = new StringBuffer();
		Reader reader = null;
		String line;
		String lineFeed = System.getProperty("line.separator");
		
		try {
			reader = new InputStreamReader(servletContext.getResourceAsStream("/WEB-INF/agb_templates/AGB_Muster.txt"),"UTF-8");
			BufferedReader bReader = new BufferedReader(reader);
			while ((line = bReader.readLine()) != null) {
				content.append(line).append(lineFeed);
			}
			bReader.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
		
		// Copy the two arrays to one common array with two counters
		
		for (int i = 0; i < supersetArray.size(); i++) {
			supersetTokenArray.add(new TokenTwoCounts(supersetTokenArray1.get(i).getWord(),supersetTokenArray1.get(i).getCount(),supersetTokenArray2.get(i).getCount()));
		}
		
		
		// Sort common array for highest count sum of both tokens
		
		Collections.sort(supersetTokenArray, new SortTwoCounts());
		
		sb.append("Word,Eingegebene AGB,Muster AGB\n");
		
		// Add the first 26 entries to chart
		
		for (int i = 0; i < supersetTokenArray.size(); i++) {

			if (i < 26) {
				sb.append(supersetTokenArray.get(i).getWord() + "," + supersetTokenArray.get(i).getCount1() + "," + supersetTokenArray.get(i).getCount2() + "\n");
			} else {
				break;
			}
			
		}
		
		// Calculate euclidean distance for all entries

		double sum = 0;
		
		for (int i = 0; i < supersetTokenArray.size(); i++) {

				sum = sum + Math.pow( (supersetTokenArray.get(i).getCount1() - supersetTokenArray.get(i).getCount2()), 2);
		}
		
		distance = Math.sqrt(sum);
		
		return sb.toString();
	}
	
	public String getWordVectorAsJSON(String agb_text) {
		
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
	
private ArrayList<Token> generateWordVectorFromString(String agb_text) {
	
	String pattern = "(\\s+)|(\\.)|(\\!)|(\\,)|(\\;)|(\\()|(\\))|(\\„)|(\\“)|(\\-)|(\\–)|(\\r)|(\\n)";
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

//		 Remove first -blank- token
//		 resultArray.remove(0);

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
	
	
	private static ArrayList<Token> generateWordVectorFromFile(String filename, boolean useUTF8) {
	
	String pattern = "(\\s+)|(\\.)|(\\!)|(\\,)|(\\;)|(\\()|(\\))|(\\„)|(\\“)|(\\-)|(\\r)|(\\n)";
	String[] splitString;
	ArrayList<Token> tokenArray = new ArrayList<Token>();
	StopwordArray stopwords = new StopwordArray();
	ArrayList<Token> resultArray = new ArrayList<Token>();
	
	StringBuffer content = new StringBuffer();
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

//	 Remove first -blank- token
//	 resultArray.remove(0);

	return resultArray;
}	
	

}