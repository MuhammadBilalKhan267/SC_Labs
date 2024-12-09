package lab12;

import static org.junit.Assert.*;
import org.junit.Test;

/** * Tests for the static methods of Commands. */ 
public class RecursiveDigitSumTest { 
	// Testing strategy
	// - Test with zero
	// - Test with Maximum Integer value (2147483647)
	// - Test with Minimum Integer Value (-2147483648)
	// - Test with single digit
	// - Test with multiple digits
	// - Test single digit negative number
	// - Test negative number with multiple digits
	
	@Test(expected=AssertionError.class) 
	public void testAssertionsEnabled() { 
		assert false; // make sure assertions are enabled with VM argument: -ea } // TODO tests for Commands.differentiate() and Commands.simplify() }
	}
	@Test
	public void testZero() {
		assertEquals(RecursiveDigitSum.computeSum(0), 0);
	}
	@Test
	public void TestMaxInt() {
		assertEquals(RecursiveDigitSum.computeSum(2147483647), 46);
	}
	@Test
	public void TestMinInt() {
		assertEquals(RecursiveDigitSum.computeSum(-2147483648), 47);
	}
	@Test
	public void TestSingleDigit() {
		assertEquals(RecursiveDigitSum.computeSum(6), 6);
	}
	@Test
	public void TestMultipleDigits() {
		assertEquals(RecursiveDigitSum.computeSum(48), 12);
		assertEquals(RecursiveDigitSum.computeSum(436), 13);
		assertEquals(RecursiveDigitSum.computeSum(6482), 20);
		assertEquals(RecursiveDigitSum.computeSum(99999), 45);
		assertEquals(RecursiveDigitSum.computeSum(32452), 16);
	}
	@Test
	public void TestMultipleDigitsNegative() {
		assertEquals(RecursiveDigitSum.computeSum(-56), 11);
		assertEquals(RecursiveDigitSum.computeSum(-278), 17);
		assertEquals(RecursiveDigitSum.computeSum(-5187), 21);
		assertEquals(RecursiveDigitSum.computeSum(-45367), 25);
		assertEquals(RecursiveDigitSum.computeSum(-787921), 34);
	}

}