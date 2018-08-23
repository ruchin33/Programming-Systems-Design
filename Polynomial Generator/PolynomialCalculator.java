import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;

public class PolynomialCalculator{
	
	/**
	 	COMMAND_LENGTHS : An array that contains the lengths of each command which can later be used for calculation
	 	ARGUMENTS       : An array of what each command will have as an argument 
	 	
	 */
	private static final String CREATE = "create";
	private static final String PRINT = "print";
	private static final String EVAL = "eval";
	private static final String ADD = "add";
	private static final String QUIT = "quit";
	private static final String HELP = "help";
	private static final String[] COMMANDS = {CREATE,PRINT,EVAL,ADD,QUIT,HELP};
	private static final int COMMAND_LENGTH[] = {CREATE.length(),PRINT.length(),EVAL.length(),ADD.length(),QUIT.length(),HELP.length()};
	private static final int[] ARGUMENTS = {1,1,1,3,0,0};
	
	/**
	 * 
	 * s1 		: String variable that contains the actual text that we type on the screen
	 * cmd 		: String variable that contains the actual command that will be used to initiate a particular function
	 */
	public static void main(String[] args){
		
		Polynomial poly[] = new Polynomial[10];
		for(int i=0;i<10;i++){
			poly[i] = new Polynomial();
		}
		
		Scanner in = new Scanner(System.in);
		String s1="",cmd="",check_number="";

		System.out.println("Enter command 'help' to get information regarding the commands");
		while(true){
			cmd="NO";
			while(cmd.equals("NO") || (check_number == "NO")){
				System.out.print("cmd> ");
				s1 = in.nextLine();
				cmd = check_command(s1,COMMANDS);
				s1 = cmd+getNumber(s1,cmd,COMMANDS,ARGUMENTS);
				
				if(cmd.equals("NO") || check_number == "NO"){
					System.out.println("ERROR: Illegal command. Type 'help' for command options");
				}
			
			}
			if(cmd.equals("quit")){
				break;
			}
			
			executeCommand(s1,cmd,poly);
			
		}
	}
	
	/**
	 *  This method executes the command that we type on the computer screen
	 *  
	 * @param s    : This is the actual string that we have written on the computer screen
	 * @param cmd  : This is a string variable that contains the true checked value of the command that we enter.
	 * @param poly : This is an array of Polynomial objects which we will be using for calculation
	 * 
	 */
	
	private static void executeCommand(String s,String cmd,Polynomial[] poly){
		
		switch(cmd){
			case CREATE: doCreate(s,COMMAND_LENGTH[0],poly);
						 break;
				 
			case PRINT: doPrint(s,COMMAND_LENGTH[1],poly);
						break;
	 			
			case EVAL: doEval(s,COMMAND_LENGTH[2],poly);
						break;
	 		   
			case ADD: doAdd(s,COMMAND_LENGTH[3],poly);
						break;
		
			case HELP: doHelp();
						break;
	 		  
			default: System.out.println("ERROR: Illegal command. Type 'help' for command options");
		}
		
	}
	
	/**
	 * This method gives us the arguments or the numbers following our command. for example
	 * if we type : create 4 -> this method return 1
	 * 
	 * @param s       : This is the actual string that we have written on the computer screen
	 * @param cmd     : This is a string variable that contains the true checked value of the command that we enter.
	 * @param command : This is an array of the 6 actual commands that we will be using
	 * @param args    : It is an integer array that contains individual number of arguments that each command is permitted
	 * @return		  : returns a string of containing numbers which are arguments to our typed commands. 
	 * 					It will be appended to our checked command
	 */
	private static String getNumber(String s,String cmd,String[] command,int[] args){
		
		String number = "";
		int count = 0;
		Scanner ln = new Scanner(s);
		for(int i = 0;i<command.length;i++){
			count =0;
			if(cmd.equals(command[i])){
				ln.next();
				for(int j=0;j<args[i];j++){
					int temp = ln.nextInt();
					
					if(temp > 9 || temp < 0){
						System.out.println("ERROR: illegal index for a poly.  must be between 0 and 9, inclusive");
						return "NO";
					}
					number = number+" "+temp;
					count ++;
				}
			}
			if(count == args[i]){
				return number;
			}
		}
		return number;
	}
	
	/**
	 * 
	 * @param s   : This is the actual string that we have written on the computer screen
	 * @param cmd : This is an array of the 6 actual commands that we will be using
	 * @return    : If the string entered is a correct command that correct command is returned as a string
	 * 				else it returns a "NO"
	 */
	
	private static String check_command(String s,String[] cmd){
		Scanner ln = new Scanner(s);
		String given_command = ln.next();
		for(int i=0;i<cmd.length;i++){
			boolean c = given_command.equalsIgnoreCase(cmd[i]);
			if(c){
				return cmd[i];
			}
		}
		return "NO";
	}
	
	/**
	 *  This method displays the function of each command
	 */
	private static void doHelp(){
		
		System.out.println("Hi Welcome to Help: \n The commands are to be implemented in the follawing way\n");
		System.out.println("1.) create i: this command creates ith polynomial\n After you type create enter space seperated integer values");
		System.out.println("     -> c1 e1 c2 e2 c3 e3 .....\n        Here ci = coeffecient and ei = exponent\n  \n");
		System.out.println("2.) print i: this command prints the ith polynomial from the array\n \n");
		System.out.println("3.) eval i: evaluates the ith polynomial for a given floating value of x\n \n");
		System.out.println("4.) add i j k: this command adds jth and kth polynomial and stores its value in the ith polynomial\n \n");
		System.out.println("5.) quit i: this command quits the polynomial calculator");
		
	}
	
	/**
	 *  This is method invokes the create command. It is a complex method and is further subdivided in other child methods. 
	 *  the method heirarchy for create command is as follows
	 *  doCreate->generatePoly->createPoly
	 *  The doCreate method basically identifies which polynomial is to be created
	 * @param s            : This is the actual string that we have written on the computer screen
	 * @param create_size  : This variable holds the size of the create command
	 * @param poly		   : this is an array of polynomial objects
	 */
	private static void doCreate(String s, int create_size, Polynomial[] poly){
		
		int i = create_size;
		String s2 = s.substring(i,s.length());
		Scanner ln = new Scanner(s2);
		int which_polynomial = 0;
		while(ln.hasNextInt()){
			which_polynomial = ln.nextInt();
			break;
		}
		System.out.println("Creating polynomial number "+which_polynomial+" :");
		generatePoly(which_polynomial,poly);
		
		
	}
	
	/**
	 *  This ,ethod prints the particular polynomial which we have attributed to be printed
	 *  This method prints the polynomial that is is specified in the argument. It has only one argument and anything after that first number will be ignored
	 * @param s          : This is the actual string that we have written on the computer screen
	 * @param print_size : This the the size of the print method
	 * @param poly_array : This is the array of polynomial objects
	 */
	
	private static void doPrint(String s, int print_size,Polynomial[] poly_array){
		
		int i = print_size;
		String s2 = s.substring(i,s.length());
		Scanner ln = new Scanner(s2);
		int which_polynomial = 0;
		while(ln.hasNextInt()){
			which_polynomial = ln.nextInt();
			break;
		}
		
	
		System.out.println("Printing polynomial number "+which_polynomial+" :");
		System.out.println(poly_array[which_polynomial].toFormattedString());
	}
	
	/**
	 * This command evaluated the given polynomial for a given double value of x
	 * 
	 * @param s          : This is the actual string that we have written on the computer screen
	 * @param eval_size  : This the actual size of EVAL command
	 * @param poly_array : The array of object of polynomials 
	 */
	private static void doEval(String s, int eval_size,Polynomial[] poly_array){
		
		int i = eval_size;
		double result=0,x=0;
		Scanner in = new Scanner(System.in);
		System.out.println("Enter a floating point value for x: ");
		x = in.nextDouble();
		String s2 = s.substring(i,s.length());
		Scanner ln = new Scanner(s2);
		int which_polynomial = 0;
		
		while(ln.hasNextInt()){
			which_polynomial = ln.nextInt();
			break;
		}
		
		result = poly_array[which_polynomial].eval(x);
		System.out.println(result);
	}
	
	/**
	 *  This command add two polynomial. This command takes care of everything, i.e if we have entered
	 *  two terms with the same exponent then we get add them
	 * @param s          : This is the actual string that we have written on the computer screen
	 * @param add_size   : This the actual size of Add command
	 * @param poly_array : The array of object of polynomials
	 */
	private static void doAdd(String s, int add_size,Polynomial[] poly_array){
		
		int i = add_size,count = 0;
		String s2 = s.substring(i,s.length());
		Scanner ln = new Scanner(s2);
		int[] operands = new int[3];
		
		while(ln.hasNextInt()){
			operands[count] = ln.nextInt();
			count++;
			if(count == 3){
				break;
			}
		}
		poly_array[operands[0]] = poly_array[operands[1]].add(poly_array[operands[2]]); 
	}
	
	/**
	 *  This is the sub function of the doCreate method. This method asks to input the space seperated list of numbers
	 *  It also seprates the coefficient and exponent in 2 different array lists, after this both the
	 *  array lists are passed to the createPoly method which finally creates the polynomial as needed 
	 * 	If any character comes between the sequenced numbers we only take the numbers before that character
	 * 
	 * @param poly_number : this is the variable which hold the creation number of polynomial
	 * @param poly		  : This is the array of objects of type Polynomial
	 */
	private static void generatePoly(int poly_number,Polynomial[] poly){
		
		ArrayList<Double> polynomial_coeff = new ArrayList<Double>();
		ArrayList<Integer> polynomial_expon = new ArrayList<Integer>();
		System.out.println("Enter a space-separated sequence of coeff-power pairs terminated by <nl>");
		Scanner in = new Scanner(System.in);
		String line;
		int count = 0;
		line = in.nextLine();
		Scanner ln = new Scanner(line);
		
		while(ln.hasNextInt() || ln.hasNextDouble()){   
			double temp = ln.nextDouble();
			if((temp < 0) && (count%2==1)){
				System.out.println("WARNING: you entered a negative exponent and we are convering it to positive");
				temp = Math.abs(temp);
			}
			
			if(count%2 == 1){
				polynomial_expon.add((int)temp);
			}
			else if(count%2 ==0){
				polynomial_coeff.add(temp); 
			}
			count ++;
		}
		createPoly(poly_number,polynomial_coeff,polynomial_expon,poly);
		
	}
	
	/**
	 *  This is the final node of the doCreate method, this method creates the final polynomial with
	 *  all the required invariants.
	 * @param which_polynomial : which number of polynomial is to be created
	 * @param polynomial_coeff : This is the array list that contains the coefficient of the sequenced list
	 * @param polynomial_expon : This is the array list that contains the exponents of the sequenced list
	 * @param poly_array       : The array of 10 Polynomial objects
	 */
	private static void createPoly(int which_polynomial,ArrayList<Double> polynomial_coeff,ArrayList<Integer> polynomial_expon,Polynomial[] poly_array){
		
		
		int total_poly = polynomial_expon.size();
		
		poly_array[which_polynomial] = new Polynomial(new Term(polynomial_coeff.get(0), polynomial_expon.get(0)));
		
		for(int i=1;i<total_poly;i++){
			
			poly_array[which_polynomial] = poly_array[which_polynomial].add(new Polynomial(new Term(polynomial_coeff.get(i), polynomial_expon.get((i)))));
		}
		
	}
}