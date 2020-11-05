package pageObj;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import core.Base;

public class HotelLoginPageObj extends Base {
	// we are following Page Obj Model Design Pattern with implementing PageFactory
	// Class
	// to implement Page Factory Class we need to create a constructor and use
	// initElements method to initialize thhis class variables
	public HotelLoginPageObj() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Sign in')]")
	private WebElement SignIn;
	@FindBy(id = "email")
	private WebElement emailAddress;
	@FindBy(id = "passwd")
	private WebElement password;
	@FindBy(xpath = "//button[@id='SubmitLogin']/span")
	private WebElement signInButton;
	@FindBy(tagName = "a")
	private List<WebElement> allLinks;

	public boolean SignInIsPresent() {
		return SignIn.isDisplayed();
	}

	public boolean emailAddressIsPresent() {
		return emailAddress.isDisplayed();
	}

	public boolean passwordIsPresent() {
		return password.isDisplayed();
	}

	public boolean signInButtonIsPresent() {
		return signInButton.isDisplayed();
	}

	public void clickOnSignIn() {
		SignIn.click();
	}

	public void enterUserAndPassword(String user, String pass) {
		emailAddress.sendKeys(user);
		password.sendKeys(pass);
		signInButton.click();
	}

	public List<WebElement> PrintoutAllLinks() {
		return allLinks;

	}
}