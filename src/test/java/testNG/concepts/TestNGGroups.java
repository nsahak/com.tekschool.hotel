package testNG.concepts;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNGGroups {

	// What is TestNG Groups
	// TestNG Groups allows us to group certain tests and run them from TestNG.xml
	// (Test Suite)
	// Why we use them?
	// We can group our test cases such as Smoke, Regression, and run them from
	// suite regardless of their class location.

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("This method executes before each Test");
	}

	@Test(groups = { "smoke" })
	public void testSmoke() {
		System.out.println("smoke one");
	}

	@Test(groups = { "smoke" })
	public void testSmoke_two() {
		System.out.println("smoke two");
	}

	@Test(groups = { "smoke" })
	public void testSmoke_three() {
		System.out.println("smoke three");
	}

	@Test(groups = { "regression" })
	public void testRegression() {

	}

	@Test(groups = { "regression" })
	public void testRegression_two() {

	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("This method executes after each Test");
	}

}
