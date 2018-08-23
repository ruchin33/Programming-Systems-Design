import java.util.Scanner;

/**
 	This class asks us for 2 choices:
 	-> Press 1 if you want to toss a coin
 		If you choose to toss the coin you will be asked, how many time do you wish to toss that coin
 		and you will be given the total number of 2 heads, 2 tails and head_tail counts.
 	-> Press 2 if you wish to reset:
 		If you choose to reset, all the values for tossing the coin will become zero
 * @author ruchinpatel
 */

public class CoinTossSimulatorTester{
	public static void main(String[] args){
		
		/**
		 * trials    : variable for asking the user to input total number of tosses
		 * choice    : variable that asks the user whether he/she wants to toss coin or reset the coin simulator
		 * temp      : variable for exiting the loop if the simulator is chosen to reset
		 * temp_trial: variable to choose the progressive increase in no of trials
		 * run       : variable to indicate which number of run we are in
		 */
		int trials=0,choice =0,temp=0,temp_trial=0,run=1;
		
		Scanner in = new Scanner(System.in);
		
		CoinTossSimulator R1 = new CoinTossSimulator();
		System.out.println("After Constructor: ");
		System.out.println("Number of Trials  [exp:0] :"+R1.getNumTrials());
		System.out.println("Two Heads tosses: "+R1.getTwoHeads());
		System.out.println("Two Tail tosses: "+R1.getTwoTails());
		System.out.println("One Heads, One Tail tosses: "+R1.getHeadTails());
		if(R1.getNumTrials()==0){
			System.out.println("Tosses add up correctly?: true \n \n");
		}
		else{
			System.out.println("Tosses add up correctly?: false \n \n");
		}
		
		/**
		 *  This while loop would be exit only when the reset command is called i.e when we select choice 2,
		 	the control goes to the second if statement where the temp variable is updated to value 1 and as a result of this the 
		 	while loop is exited.
		 */
		while(temp == 0){
			System.out.println("Enter your choice \n Enter 1 if you want to TOSS \n Enter 2 if you want to reset: \n");
			choice = in.nextInt();
			
			/**
			 	this if statement checks if the the choice made is 1(i.e if you want to simulate the tossing of coins
			 */
			if (choice == 1){
				System.out.println("Enter number of a TRIALS: ");
				trials = in.nextInt();
				temp_trial = temp_trial + trials;
				R1.run(trials);
				System.out.println("After run("+run+"):");
				System.out.println("Number of Trials  [exp:"+trials+"]: "+R1.getNumTrials());
				System.out.println("Two Heads tosses: "+R1.getTwoHeads());
				System.out.println("Two Tail tosses: "+R1.getTwoTails());
				System.out.println("One Heads, One Tail tosses: "+R1.getHeadTails());
				if(R1.getNumTrials()==temp_trial){
					System.out.println("Tosses add up correctly?: true \n \n");
				}
				else{
					System.out.println("Tosses add up correctly?: false \n \n");
				}
				temp =0;
				run++;
			}
			
			/**
			 	This if statement checks if the choice made is 2(i.e if you want to reset the simulator
			 */
			else if(choice == 2){
				R1.reset();
				temp_trial=0;
				System.out.println("After run("+run+"):");
				System.out.println("Number of Trials  [exp:"+trials+"]: "+R1.getNumTrials());
				System.out.println("Two Heads tosses: "+R1.getTwoHeads());
				System.out.println("Two Tail tosses: "+R1.getTwoTails());
				System.out.println("One Heads, One Tail tosses: "+R1.getHeadTails());
				if(R1.getNumTrials()==temp_trial){
					System.out.println("Tosses add up correctly?: true \n \n");
				}
				else{
					System.out.println("Tosses add up correctly?: false \n \n");
				}
				temp=1;
			}
		}
		
	}
}