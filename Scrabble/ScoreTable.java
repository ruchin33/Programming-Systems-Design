// Name: Ruchin Patel	
// USC NetID: ruchinpa
// CS 455 PA3
// Spring 2017

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

/**
  	This class has the information regarding the score alloted to every letter from English language.
  	It is also alloted the function of calculating the score of every anagram of the Scrabbble rack.
  	Assumptions about class ScoreTable:
  	--  The letters and points array are predefined.
 */

public class ScoreTable{
	
	/**
	 
	 *  points       : The integer array that has the respective points corresponding to the letters array.
	 *  		       for example: points[0] -> points corresponding to letters[0] which is a;
	 *  				       	    points[1] -> points corresponding to letters[1] which is b;
	 *  
	 *  scored_words : A Map variable with key as the score of the word and value as all the words having the 
	 *  			   same score.
	 */
	
	private int[] points =   {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
	private Map<Integer,ArrayList<String>> scored_words = new HashMap<Integer,ArrayList<String>>();
	
	
	/**
	 * VALID ANAGRAMS: The anagrams that are present in the dictionary selected by us or the standard sowpods dictionary
	 * 
	 * This method groups all the valid anagrams of all subsets of a word on rack according to their score after
	 * calculating it.
	 * 
	 * @param scrabble_words : The valid anagrams of all the subsets of a word on rack,selected from the dictionary 
	 * @return               : Return a Map with key as the score of a particular word and value as an ArrayList
	 * 						   of all the valid anagrams of a subset of word on rack. 
	 * 						   Here the final returned map would contain all the valid anagrams grouped according 
	 * 						   to their scores of all subsets from a word on rack.
	 */
	public Map<Integer,ArrayList<String>> wordScore(ArrayList<String> scrabble_words){
		
		
		for(int i=0;i<scrabble_words.size();i++){
			String original = scrabble_words.get(i);
			String word_to_compare = original.toLowerCase(); 
			int score = 0;
			for(int j =0;j<word_to_compare.length();j++){
				
				score = score + points[word_to_compare.charAt(j)-'a'];
			}
			if(scored_words.get(score) == null){
				scored_words.put(score, new ArrayList<String>());
				scored_words.get(score).add(original);
			}
			else{
				scored_words.get(score).add(original);
			}
		}
		return scored_words;
	}
}