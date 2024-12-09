package lab12;

/*
 * Class to calculate the sum of the digits of an integer
 * Base case 0, return 0
 * Case Minimum Integer Value, return 47 (sum of the number of -2147483648)
 */
public class RecursiveDigitSum {
	/**
	 * @param number the signed number whose digits to sum
	 * @return the sum of the digits of the number
	 */
	
	public static int computeSum(int number) {
		// Base Case for minimum integer value to avoid overflow
		if (number == Integer.MIN_VALUE) {
            return 47;
        }
		return recursiveDigitSum(Math.abs(number));
	}
	/**
	 * @param number the positive number whose digits to sum
	 * @return the sum of the digits of the number
	 */
	private static int recursiveDigitSum(int number) {
		if (number==0) return 0;
		return number%10 + recursiveDigitSum(number/10);
	}
}
