package testNG.concepts;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNGAnnotations {

	// @Test: This annotation will be used to run actual test method.
	// @BeforeMethod: This annotation will be executed before each test method
	// @AfterMethod: This annotation will be executed after each test method
	// @BeforeTest: This annotation will be executed before all test methods
	// @AfterTest: This annotation will be executed after all test methods
	// @BeforeClass: This annotation will be executed before Class if we have test
	// cases in TestNG. This annotation will execute before each class.
	// @AfterClass: This annotation will be executed after class if we have multiple
	// test cases in TestNG. This annotation will executed after each Class.
	// @BeforeGroup and @AfterGroup: These annotations will be executed before and
	// after set of test cases which are grouped by specific names.

	// in Real World we use mainly @Test for each Test case. and @BeforeMethod and
	// @AfterMethod to setup properties and Tear Down after each Execution.
	// @BeforeMethod and @AfterMethod are similar to BeforeHooks and AfterHooks of
	// Cucumber.

	@BeforeTest
	public void beforeTest() {
		System.out.println("This method executes before first Test");
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("This method executes before each Test");

	}

	@Test (priority = 1)
	public void test_one() {
		System.out.println("This is Test One");

	}

	@Test (priority = 1)
	public void test_two() {
		System.out.println("This is Test Two");

	}

	@Test (priority = 1)
	public void test_three() {
		System.out.println("This is Test Three");

	}
	
	@Test
	public void test_without_priority() {
		System.out.println("This test will be executed before all cases with priorities");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("This method executes after each Test");

	}

	@AfterTest
	public void afterTest() {
		System.out.println("This method executes after all Tests are executed");
	}

}
