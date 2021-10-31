import java.math.BigInteger;
import java.util.*;

/**
 * This program takes gives the nTh Fibonacci number using Matrix
 * 
 * @author Chirag Yadav
 *
 */
public class FibonacciDigit {
	static BigInteger one = new BigInteger("1");
	static BigInteger zero = new BigInteger("0");
	static BigInteger[][] ideal = {{one,one}, {one,zero}};
	public static Map<Integer, BigInteger[][]> store = new HashMap();
	public static String bin = "";
	
	
	/**
	 * Matrix multiplication
	 * 
	 * @param a first Matrix
	 * @param b Second Matrix
	 * @return the product of a and b
	 */
	private static BigInteger[][] mulitply(BigInteger[][] a, BigInteger[][] b) {
		BigInteger[][] temp = {{BigInteger.ZERO, BigInteger.ZERO},{BigInteger.ZERO, BigInteger.ZERO}};
		for (int row = 0; row < a.length; row++) {
			for (int column = 0; column < b.length; column++) {
				for(int i = 0; i < 2; i++){
					temp[row][column] = (temp[row][column]).add(a[row][i].multiply(b[i][column]));;
				}
			}
		}
		return temp;
	}
	
	/**
	 * This calculate the Fibonacci number
	 *  
	 * @param n the nTh position you want to find
	 * @return the Fibonacci number
	 */
	private static BigInteger getFibonacciDigitLong(long n) {
		if(n <= 1) {
			return new BigInteger("" + n);
		}
		if(n == 2) {
			return new BigInteger("" + 1);
		}
		n = n-1;
		bin = Integer.toBinaryString((int) n);
		store.put(0, ideal);
		BigInteger[][] temp = new BigInteger[2][2];
		BigInteger holder;
		for(int index = 1; index < bin.length(); index++) {
			temp = mulitply(store.get(index-1) ,store.get(index-1));
			store.put(index, temp);
		}
		BigInteger[][] temp2 = {{BigInteger.ZERO, BigInteger.ZERO},{BigInteger.ZERO, BigInteger.ZERO}};
		int bit = bin.length()-1;
		for(int index = 0; index < bin.length(); index++, bit--) {
			char a = bin.charAt(index); 
			if(Character.getNumericValue(a) == 1) {
				if(temp2[0][0].equals(BigInteger.ZERO)) {
					temp2 = store.get(bit);
				}else {
					BigInteger[][] temp11 = store.get(bit);
					temp2 = mulitply(temp11, temp2);
				}
			}
		}
		holder = (temp2[1][0].add(temp2[1][1]));
		temp2 = (mulitply(temp2, ideal));
		
		return holder;
	}
    
	
	//driver code
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        BigInteger c = getFibonacciDigitLong(n);
        System.out.println(c);
    }
}

