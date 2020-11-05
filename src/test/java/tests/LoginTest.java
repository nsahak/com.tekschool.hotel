package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import core.Base;
import pageObj.HotelLoginPageObj;
import utilities.DriverUtility;

public class LoginTest extends Base {

	HotelLoginPageObj hotelLoginPageObj;

	@BeforeMethod
	public void beforeMethod() {

		Base.initializeDriver();
		logger.info("Browser opened successfully");
		DriverUtility.wait(5000);

	}

	@Test
	@Parameters({ "userName", "password" })
	public void loginToHotel(String userName, String password) {
		hotelLoginPageObj = new HotelLoginPageObj();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(hotelLoginPageObj.SignInIsPresent());
		softAssert.assertTrue(hotelLoginPageObj.passwordIsPresent());
		softAssert.assertTrue(hotelLoginPageObj.signInButtonIsPresent());
		hotelLoginPageObj.enterUserAndPassword(userName, password);
		DriverUtility.wait(10000);

	}

	@AfterMethod
	public void afterMethod() {
		Base.tearDown();
		logger.info("Browser Closed");

	}

}
