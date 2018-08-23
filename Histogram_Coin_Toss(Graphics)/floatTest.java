import java.math.BigDecimal;
import java.math.RoundingMode;

public class floatTest{
	public static void main(String[] args){
		double i=20,j=7,value=0;
		value = i/j;
		System.out.println("value:"+value);
		double value1 = BigDecimal.valueOf(value).setScale(2,RoundingMode.HALF_DOWN).doubleValue();
		value = value1;
		System.out.println("value:"+value);
	}
}