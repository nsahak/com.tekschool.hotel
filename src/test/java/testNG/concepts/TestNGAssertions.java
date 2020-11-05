package testNG.concepts;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestNGAssertions {

	// Assertions are used to validate expected result against actual result
	// TestNG provides two types of Assertions
	// Hard Assertions: if Expected is not equal to Actual, execution will stop at
	// this point
	// Soft Assertions: if Expected is not equal to Actual, execution will continue
	// but test case will fail

	// Syntax for hard Assertion is: Assert.MethodName(Actual, Expected)
	// SoftAssertion class is provided by TestNG. we need to create Object of
	// SoftAssertion class Then we can use methods provided by SoftAssertion class.
	// at the end of assertion we need to call assertAll method to confirm
	// assertions,
	// otherwise it will not provide test status.

	@Test(enabled = false) // enabled=false will ignore the test case from execution
	public void hardAssertion() {
		int actualValue = 4;
		int expectedValue = 4;

		Assert.assertEquals(actualValue, expectedValue);
		System.out.println("This line of code will be executed if assertion passed");

	}

	@Test
	public void softAssertion() {
		int a = 10;
		int b = 15;

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(a, b);
		System.out.println("This line of code will be executed regardless of assertion pass or fail");

		softAssert.assertTrue(false);
		System.out.println("This is after section execution");

		softAssert.assertAll();
	}

}
