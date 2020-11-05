package testNG.concepts;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNGPractice {

	WebDriver driver;
	SoftAssert softAssert = new SoftAssert();

	@BeforeTest
	public void beforeMethod() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();

	}

	@Test
	@Parameters({"url"})
	public void navigateToURL(String url) {
		driver.get(url);
		String actualTitle = driver.getTitle();
		System.out.println(actualTitle);
		String expectedTitle = "TEK SCHOOL";
		
		softAssert.assertEquals(actualTitle, expectedTitle);
		softAssert.assertAll();

	}
	
	@Test (dependsOnMethods = "navigateToURL")
	public void verifyMyAccount() throws InterruptedException {
	
		WebElement myAccount = driver.findElement(By.xpath("//span[contains(text(),'My Account')]"));
		softAssert.assertTrue(myAccount.isDisplayed());
		Thread.sleep(5000);
		myAccount.click();
		softAssert.assertAll();
	}
	
	@Test
	@Parameters({"userName", "password"})
	public void loginToMyAccount(String userName, String password) {
		WebElement logIn = driver.findElement(By.xpath("//body/nav[@id='top']/div[1]/div[2]/ul[1]/li[2]/ul[1]/li[2]/a[1]"));
		logIn.click();
		
		WebElement emailField = driver.findElement(By.xpath("//input[@id='input-email']"));
		emailField.sendKeys(userName);
		
		WebElement passwordField = driver.findElement(By.xpath("//input[@id='input-password']"));
		passwordField.sendKeys(password);
	}


	@AfterTest
	public void afterMethod() {
		
		driver.close();

	}

}
