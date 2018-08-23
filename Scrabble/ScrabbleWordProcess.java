// Name: Ruchin Patel	
// USC NetID: ruchinpa
// CS 455 PA3
// Spring 2017


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * It takes the task of processing and generating Scrabble words and finally printing the words grouped and sorted 
 * as per their scores. This class is specifically made to process the word on the rack, calculate score of each of 
 * the subset  and then print them in a sorted manner grouped according to the scores.So the task carried out are:
 * 1.) Considering only the characters from the rack and ignoring everything else
 * 2.) Calculates scores of every valid anagram of the word on rack and sorting all the words according to their scores
 * 	   in decreasing order.
 * Also depends on AnagramDictionary class and ScoreTable class
 */

public class ScrabbleWordProcess{
	
	/**
	 * ascending     : The rack string sorted alphabetically
	 * appended_list : The ArrayList variable that contains the valid anagrams of all the subsets of word on a rack 
	 */
	private String ascending;
	private Rack subsets;
	private ArrayList<String> appended_list;
	
	
	/**
	 * This method cleans the rack of all the special characters and generated an alphabetically sorted word 
	 * 
	 * @param messy_rack : The messed up rack that is actually typed by the user
	 * @return           : Returns the cleaned word from the rack in alphabetical order
	 */
	public String sortCleanRack(String messy_rack){
		
		String cleaned_rack = "";  
		for(int i =0;i<messy_rack.length();i++){
			if(Character.isLetter(messy_rack.charAt(i))){
				cleaned_rack = cleaned_rack + messy_rack.substring(i, i+1);
			}
		}
		char[] sorted_rack_arr = cleaned_rack.toLowerCase().toCharArray();
		Arrays.sort(sorted_rack_arr);  
		ascending = new String(sorted_rack_arr);
		return ascending;
		  
	}
	
	
	
	
	/**
	 * This method generates a string of unique words making the sorted_cleaned_rack word, the multiplicity
	 * or occurrence of each of the letter in the unique string in sorted_cleaned_rack word.Finally it calls the 
	 * allSubsets method of class Rack.
	 * 
	 * @param sorted_cleaned_rack : The string on the rack cleaned and sorted by sortCleanRack function  
	 * @return                    : Return an ArrayList consisting of all the subsets of the sorted and 
	 * 								cleaned word on the rack.
	 */
	
	public ArrayList<String> unique_multiplicity(String sorted_cleaned_rack){
		char[] rack_char = sorted_cleaned_rack.toLowerCase().toCharArray();
		Arrays.sort(rack_char);
		String rack_string = new String(rack_char);
	
		Map<String,Integer> letter_count = new HashMap<String,Integer>();
		String unique = "";
		for(int i =0;i<rack_string.length();i++){
			String s1 = rack_string.substring(i, i+1);
			if(letter_count.get(s1) == null){
				letter_count.put(s1, 1);
				unique = unique + s1;
			}
			else{
				letter_count.put(s1, letter_count.get(s1)+1);
			}
		}
		int[] multiplicity = new int[letter_count.size()];
		
		for(int i =0;i<letter_count.size();i++){
			multiplicity[i] = letter_count.get(unique.substring(i, i+1));
		}
		
		subsets = new Rack();
		
		return subsets.getSubsets(unique, multiplicity, 0);
		
	}
	
	
	
	/**
	 * The method takes valid anagrams of all the subsets of rack word, calculates
	 * the score of each word and groups the words according to the scores 
	 * in decreasing order of scores.
	 * @param scrabble_words : The ArrayList containing valid anagrams of all the 
	 * 						   subsets of the word on rack. 
	 */
	
	public void calculateScore(ArrayList<String> scrabble_words){
		
		ScoreTable table = new ScoreTable();
		
		Map<Integer, ArrayList<String>> scored_words = new HashMap<Integer, ArrayList<String>>();
		
		scored_words = table.wordScore(scrabble_words);
		
		Map<Integer, ArrayList<String>> newMap = new TreeMap(Collections.reverseOrder());
		
        newMap.putAll(scored_words);
        
        printScrabbleWords(newMap);
        
	}
	
	
	
	/**
	 * This method finds all valid anagram of a given subset of a word on the rack and generates and appends
	 * the valid anagrams of all the subsets in a new ArrayList. 
	 * @param subsets : All the subsets of a cleaned and sorted word on a rack
	 * @param anagram : The AnagramDictionary object.
	 * @return returns an ArrayList of all the valid anagrams of a whatever is on the rack.
	 */
	
	public ArrayList<String> appendAnagramsubsets(ArrayList<String> subsets,AnagramDictionary anagram){
		
		appended_list = new ArrayList<String>();
		for(int i=0;i<subsets.size();i++){
			  String sub = subsets.get(i);
			  if(anagram.getAnagramsOf(sub) != null){
				  appended_list.addAll(anagram.getAnagramsOf(sub));
			  }
			  
		}
		
		return appended_list;
		
	}
	
	////////////////////////////private method part starts here////////////////////////////////
	
	/**
	 * This method prints the grouped, scored and sorted valid anagrams in the format that is needed
	 * @param sortedNewMap : the sorted map with keys in decreasing order.
	 */
	
	private static void printScrabbleWords(Map<Integer, ArrayList<String>> sortedNewMap){
		if(sortedNewMap.size()>0){
			System.out.println("All of the words with their scores (sorted by score):");
			Set<Integer> keySet = sortedNewMap.keySet();
			for(Integer key : keySet){
				for(int i=0;i<sortedNewMap.get(key).size();i++){
					System.out.println(key+": "+sortedNewMap.get(key).get(i));
				}
			}
		}
	}
}