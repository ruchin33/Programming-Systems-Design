// Name:Ruchin Patel
// USC loginid: ruchinpa@usc.edu
// CS 455 PA2
// Spring 2017


import java.util.ArrayList;

/**
   A polynomial. Polynomials can be added together, evaluated, and
   converted to a string form for printing.
*/
public class Polynomial {
	
	/**
	 *  @param poly : it is the polynomial list made of Term class which will store coefficients and exponents of different classes
	 *  The representation Invariants of the polynomial objects are:
	 *  1.) The coefficient should not be zero, if it is zero don't display it
	 *  2.) The exponent should not be less than zero, in Polynomial Calculator class, if the exponent are negative change it to positive
	 *  3.) The terms of the polynomial should be in decreasing order of the exponents
	 */
	private ArrayList<Term>  poly = new ArrayList<Term>();
	private double coeffecient = 0;
	private int exponent = 0;
	
    /**
       Creates the 0 polynomial
    */
    public Polynomial() {
    	
    	poly.clear();
    	Term t1 = new Term(0,0);
    	poly.add(t1);
    }


    /**
       Creates polynomial with single term given
     */
    public Polynomial(Term term) {
    	
    	poly.clear();
    	poly.add(term);
    }


    /**
       Returns the Polynomial that is the sum of this polynomial and b
       (neither poly is modified)
       coef_local: this is the value of coefficent that is used locally
       this_poly : this the variable that describes the counter used for the polynomial that calls this add method
       b_poly    : this the variable that describes the counter used for the polynomial that is passed as argument
       p1        : Object which holds the addition of two polynomial
     */
    public Polynomial add(Polynomial b) {
    	
    	assert isValidPolynomial();
    	assert b.isValidPolynomial();
    	Polynomial p1 = new Polynomial();
    	p1.poly.remove(0);
    	double coef_local =0;
    	int this_poly =0,b_poly=0;
    	while((this_poly < this.poly.size()) && (b_poly <b.poly.size())){
    		
 
    		if((this.poly.get(this_poly).getExpon()) >= (b.poly.get(b_poly).getExpon())){
    			
    			if((this.poly.get(this_poly).getExpon()) == (b.poly.get(b_poly).getExpon())){
    				coef_local = this.poly.get(this_poly).getCoeff() + b.poly.get(b_poly).getCoeff();
    		
    				if(coef_local != 0.0){
    					p1.poly.add(new Term(coef_local,poly.get(this_poly).getExpon()));
    				
    					this_poly++;
    					b_poly++;
    				
    				}
    				else{
    					this_poly++;
    					b_poly++;
    				}
    				
    			}
    			else{
    				p1.poly.add(this.poly.get(this_poly));
    				
    				this_poly++;
    			}
    			
    		}
    		else{
    			p1.poly.add(b.poly.get(b_poly));
    			
    			b_poly++;
    			
    		}
    	}
    	
    	while(this_poly < this.poly.size()){
			p1.poly.add(this.poly.get(this_poly));
			this_poly++;
		}
		while(b_poly < b.poly.size()){
			p1.poly.add(b.poly.get(b_poly));
			b_poly++;
		}
		assert p1.isValidPolynomial();
	return p1;  // dummy code.  just to get stub to compile
    }


    /**
       Returns the value of the poly at a given value of x.
       result : This is the variable that holds the result of the evaluation
     */
    public double eval(double x) {
    	double result = 0;
  
    	for(int i=0;i<poly.size();i++){
    		result = result + poly.get(i).getCoeff()*Math.pow(x, poly.get(i).getExpon());
    	}
	return result;         // dummy code.  just to get stub to compile
    }


    /**
       Return a String version of the polynomial with the 
       following format, shown by example:
       zero poly:   "0.0"
       1-term poly: "3.2x^2"
       4-term poly: "3.0x^5 + -x^2 + x + -7.9"

       Polynomial is in a simplified form (only one term for any exponent),
       with no zero-coefficient terms, and terms are shown in
       decreasing order by exponent.
    */
    public String toFormattedString() {
    	
    	String polynomial = "";
    		
    	if(poly.size() >=1 && (poly.get(0).getCoeff() != 0.0)){
    		polynomial = " "+(poly.get(0).getCoeff())+"x^"+(poly.get(0).getExpon());
    		for(int i=1;i<poly.size();i++){
    		
    			if(poly.get(i).getExpon() == 0){
    				polynomial = polynomial+" + " +(poly.get(i).getCoeff()); 
    			}
    			else if(poly.get(i).getCoeff() == 0){
    				polynomial = polynomial+"";
    			}
    			else{
    				polynomial = polynomial+" + " +(poly.get(i).getCoeff())+"x^"+(poly.get(i).getExpon());
    			}
    		}
    	}
    	
	return polynomial;        // dummy code.  just to get stub to compile
    }


    // **************************************************************
    //  PRIVATE METHOD(S)

    /**
     * This is the method that checks the representation invariants
     * 1.) The coefficient should not be 0
     * 2.) The exponents should be displayed in decreasing order
     * 3.) The exponent should not be negative
       Returns true iff the poly data is in a valid state.
       temp = This is the variable that tells us if the the polynomial is valid or not
    */
    private boolean isValidPolynomial() {
    	int temp = 0;
    	if(poly.size()>1){
    		for(int i=0; i< this.poly.size()-1;i++){
    			if(this.poly.get(i).getExpon() > this.poly.get(i+1).getExpon()){
    				temp++;
    			}
    			else if(this.poly.get(i).getExpon()<0){  // negative exponent
    				temp = -1;
    			}
    			else if(this.poly.get(i).getCoeff() == 0){  // 0 coeffecient
    				temp = -1;
    			}
    			else if(this.poly.get(i).getExpon() <= this.poly.get(i+1).getExpon()){  //decreasing exponent 
    				temp = -1;
    			}
    		}
    	}
    	
    	if(temp < 0){
    		return false;
    	}
    	else{
    		return true;    
    	}
    }

}