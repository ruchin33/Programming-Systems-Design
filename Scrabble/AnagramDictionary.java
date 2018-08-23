// Name: Ruchin Patel	
// USC NetID: ruchinpa
// CS 455 PA3
// Spring 2017

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.util.Scanner;


/**
 * A dictionary of all anagram sets. 
 * Note: the processing is case-sensitive; so if the dictionary has all lower
 * case words, you will likely want any string you test to have all lower case
 * letters too, and likewise if the dictionary words are all upper case.
 */

public class AnagramDictionary {
   
	Map<String,ArrayList<String>> anagram_dict = new HashMap<String,ArrayList<String>>();

   /**
    * Create an anagram dictionary from the list of words given in the file
    * indicated by fileName.  
    * PRE: The strings in the file are unique.
    * @param fileName  the name of the file to read from
    * @throws FileNotFoundException  if the file is not found
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException {
	   
	   File dictionary = new File(fileName);
	   Scanner sc = new Scanner(dictionary);
	   while(sc.hasNext()){
		   
		   String original_from_dictionary = sc.next();
		   String s1 = original_from_dictionary.toLowerCase();  
		   char[] sorted_char = s1.toCharArray();
		   Arrays.sort(sorted_char);
		   String sorted_string = new String(sorted_char);
		   if(anagram_dict.get(sorted_string) == null){
			   anagram_dict.put(sorted_string,new ArrayList<String>());
			   anagram_dict.get(sorted_string).add(original_from_dictionary);
		   }
		   else{
			   anagram_dict.get(sorted_string).add(original_from_dictionary);
		   }
	   }
	   
   }
   

   /**
    * Get all anagrams of the given string. This method is case-sensitive.
    * E.g. "CARE" and "race" would not be recognized as anagrams.
    * @param s string to process
    * @return a list of the anagrams of s
    * 
    */
   public ArrayList<String> getAnagramsOf(String s) {
       char[] key_char = s.toLowerCase().toCharArray();
       Arrays.sort(key_char);
       String key_string = new String(key_char);
       return anagram_dict.get(key_string);  
   }
   
   
}