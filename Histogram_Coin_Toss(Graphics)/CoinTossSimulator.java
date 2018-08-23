// Name: Ruchin Patel
// USC NetID: ruchinpa
// CS 455 PA1
// Spring 2017
/**
 	Importing the random Class
*/

import java.util.Random;
	
/**
 * class CoinTossSimulator
 * 
 * Simulates trials of tossing two coins and allows the user to access the
 * cumulative results.
 * 
 * NOTE: we have provided the public interface for this class.  Do not change
 * the public interface.  You can add private instance variables, constants, 
 * and private methods to the class.  You will also be completing the 
 * implementation of the methods given. 
 * 
 * Invariant: getNumTrials() = getTwoHeads() + getTwoTails() + getHeadTails()
 * 
 */
public class CoinTossSimulator {

	private Random gen1; //random generator for coin1
	private Random gen2; //random generator for coin2
	/**
	 * trials    : Stores the total number of trials  
	 * coin1     : Stores the value of face in first coin
	 * coin2     : Stores the value of the face of coin2
	 * head_head : Stores the number of times both coins show heads
	 * tail_tail : Stores the number of times both coins show tail
	 * head_tail : Stores the number of times when one coin shows heads and the other shows tail	
	*/
	private int trials, coin1, coin2, head_head, tail_tail, head_tail;
		

   /**
      Creates a coin toss simulator with no trials done yet.
   */
   public CoinTossSimulator() {
	   trials = 0;
	   head_head=0;
	   head_tail=0;
	   tail_tail=0;
	   gen1 = new Random();
	   gen2 = new Random();
   }


   /**
      Runs the simulation for numTrials more trials. Multiple calls to this
      without a reset() between them add these trials to the simulation
      already completed.
      
      @param numTrials  number of trials to for simulation; must be >= 1
    */
   public void run(int numTrials) {
	   
	   for(int i =1;i<=numTrials;i++){
			coin1 = gen1.nextInt(2);
			coin2 = gen2.nextInt(2);
			
			if((coin1 == 0) && (coin2==0)){
				tail_tail++;
			}
			else if(((coin1==0)&&(coin2==1)) || ((coin1==1)&&(coin2==0))){
				head_tail++;
			}
			else if((coin1==1) && (coin2==1)){
				head_head++;
			}
			
			trials++;
		}
   }


   /**
      Get number of trials performed since last reset.
   */
   public int getNumTrials() {
	 
	   return head_head+head_tail+tail_tail;
   }


   /**
      Get number of trials that came up two heads since last reset.
   */
   public int getTwoHeads() {
       return head_head; 
   }


   /**
     Get number of trials that came up two tails since last reset.
   */  
   public int getTwoTails() {
       return tail_tail; 
   }


   /**
     Get number of trials that came up one head and one tail since last reset.
   */
   public int getHeadTails() {
	   return head_tail; 
   }


   /**
      Resets the simulation, so that subsequent runs start from 0 trials done.
    */
   public void reset() {
	   trials = 0;
	   head_head=0;
	   head_tail=0;
	   tail_tail=0;
	   gen1 = new Random();
	   gen2 = new Random();
   }

}