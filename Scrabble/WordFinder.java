// Name: Ruchin Patel	
// USC NetID: ruchinpa
// CS 455 PA3
// Spring 2017

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

/**
   This is a class that basically has the main method in it. It takes care of the following things:
   1.) Processing the file.That is it takes care of taking input from command the dictionary to be 
  	   used and the exception handling if the file specified is not there.
   2.) It takes care of taking the rack input from the user on command line and printing the output 
  	   of Scrabble words wit their score.
   Assumption about WordFinder class:
  	-- The dictionary file is in the same working directory as the the other classes of this Assignment.
 	-- The default file sowpods.txt is also supposed to be in the same directory.
 	-- The sowpods Dictionary has words that are of the length 2 or more.
 	-- In the sowpods Dictionary, a plural of a word is also considered as a separate unique word.
 	-- Verb conjunction etc are considered as different words in sowpods.txt dictionary.
 
 */

public class WordFinder{
	
	
	public static void main(String[] args){
		
		String fileName = "";  // The file that is initialized as an empty string
		
		boolean file_present = false;  //The variable that gives us information if file is present or not.
		   
		  if(args.length<=0 ||args[0].equals("")){
			  fileName = "sowpods.txt";
			  file_present = true;
		  }
		  else{
			  try {

				  fileName = args[0];
	            
				  file_present = readProcessFile(fileName);
				  

			  }
			  catch (FileNotFoundException exc) {
				  System.out.println("File not found: " + fileName);
			  }
			  catch (IOException exc) {
				  exc.printStackTrace();
			  }
		  }
		  
		  processFile(file_present,fileName);
		  
		  
	}
	
	//////////////////////////////////private methods start here/////////////////////////////////////////////////////
	
	/**
	 * This method processes the file, which in this case is the dictionary, it also takes care of taking
	 * the rack word from the user and processing it with the help of methods of ScrabbleWordProcess
	 * class and finally printing all the valid anagrams of the word on the rack. However the printing of
	 * the words is taken care by a private method in ScrabbleWordProcess class.
	 * 
	 * @param file_present : The boolean variable which tells us whether the file is present or not.
	 * @param fileName     : The name of the file that we entered on the command line. If nothing is
	 * 						 entered the filename is sowpods.txt by default.
	 */
	private static void processFile(boolean file_present,String fileName){
		if(file_present){
			  
			AnagramDictionary anagram;
			  try{
				  anagram = new AnagramDictionary(fileName);
				  Scanner rc = new Scanner(System.in);
				  System.out.println("Type . to quit.");
				  
				  while(true){
					  
					  System.out.print("Rack? ");
					  
					  String rack_line = rc.nextLine();
					  Scanner sc = new Scanner(rack_line);
					  String rack = sc.next();
					  if(rack.equals(".")){
						  break;
					  } 
					  
					  
					  ScrabbleWordProcess s1 = new ScrabbleWordProcess();
					  rack = s1.sortCleanRack(rack);
					  ArrayList<String> subsets =  s1.unique_multiplicity(rack);
					  ArrayList<String> scrabble_words = new ArrayList<String>();
					  
					  scrabble_words = s1.appendAnagramsubsets(subsets,anagram);
					  
					  System.out.println("We can make "+scrabble_words.size()+" words from \""+rack+"\"");
					  
					  Collections.sort(scrabble_words);
					  
					  s1.calculateScore(scrabble_words);
					  
				  }
			  }
			  catch (FileNotFoundException exc) {
				  System.out.println("File not found: " + fileName);
			  }
		  }
	}
	
	
	/**
	 * This method is generic and is used just to check whether the file specified by the user is present
	 * in the directory
	 * 
	 * @param fileName     : the fileName of dictionary entered by the user on command line,if nothing is 
	 * 						 entered , the default fileName sowpods.txt is passed
	 * @return			   : Always returns true if the fileName specified is present in the directory.
	 * 						 However if it is not present, it returns nothing and throws an exception.
	 * @throws IOException
	 */
	private static boolean readProcessFile(String fileName) throws IOException {
		File inFile = new File(fileName);
		Scanner in = new Scanner(inFile);
		return true;
	}
	
	
	
}



