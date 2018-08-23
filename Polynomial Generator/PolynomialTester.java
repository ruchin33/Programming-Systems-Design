public class PolynomialTester{
	
	public static void main(String[] args){
		
		Polynomial p1 = new Polynomial(new Term(5,2));
		Polynomial p2 = new Polynomial(new Term(6, 3));
		Polynomial p4 = new Polynomial(new Term(6, 2)).add(new Polynomial(new Term(5, 3)));
		Polynomial p9 = p4.add(new Polynomial(new Term(-5, 3)));
		double result1=p4.eval(2);
		Polynomial p5 = p4.add(new Polynomial(new Term(-1, 1)));
		Polynomial p6 = p5.add(new Polynomial(new Term(-9.5, 0)));
		Polynomial p7 = p6.add(p5);
		Polynomial p8 = new Polynomial(new Term(9, 0));
		Polynomial p10 = p7.add(p8);
		Polynomial p11 = new Polynomial();
		double result2=p5.eval(2.5);
		double result3=p6.eval(2);
		double result4=p7.eval(2);
		double result5=p10.eval(2);
		System.out.println(p1.toFormattedString());
		System.out.println(p2.toFormattedString());
		System.out.println(p4.toFormattedString());
		System.out.println(p9.toFormattedString());
		System.out.println(result1);
		System.out.println(p5.toFormattedString());
		System.out.println(result2);
		System.out.println(p6.toFormattedString());
		System.out.println(result3);
		System.out.println(p7.toFormattedString());
		System.out.println(result4);
		System.out.println(p10.toFormattedString());
		System.out.println(result5);
		System.out.println(p11.toFormattedString());

	}
	
}