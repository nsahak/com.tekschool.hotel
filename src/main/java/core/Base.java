package core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

	// In this class, we define property of below Classes and will use them to child
	// classes
	// WebDriver
	// Logger
	// Properties Class

	public static WebDriver driver;
	public static Properties properties;
	public static Logger logger;

	private String projectPropertyFilePath = ".\\src\\test\\resources\\properties\\ProjectProperty.properties";

	private String log4JFilePath = ".\\src\\test\\resources\\properties\\log4j.properties";

	// We are creating a constructor here
	public Base() {

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(projectPropertyFilePath));
			properties = new Properties();
			properties.load(reader);
			reader.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger = logger.getLogger("logger_file");
		PropertyConfigurator.configure(log4JFilePath);

	}

	/**
	 * This method will return value of browser name such as Chrome, FireFox, IE,
	 * and Headless browser
	 * 
	 * @return
	 */
	public static String getBrowserName() {
		String browserName = properties.getProperty("browser");
		return browserName;

	}

	/**
	 * This method will return URL of application we use for this Framework
	 * 
	 * @return
	 */

	public static String getURL() {
		String url = properties.getProperty("url");
		return url;
	}

	/**
	 * This method will return implicitly wait time and parse it to long data type
	 * as Implicitly wait method in selenium accepts long dataType
	 * 
	 * @return
	 */
	public static Long getImpWait() {
		String impWait = properties.getProperty("implicitlyWait");
		return Long.parseLong(impWait);
	}

	/**
	 * This method will return pageLoadTime Out wait time and parse it to long data
	 * type as pageLoadTime wait method in selenium accepts long dataType
	 * 
	 * @return
	 */
	public static Long getPageLoadTimeOut() {
		String pageLoadTimeOut = properties.getProperty("pageLoadTimeOut");
		return Long.parseLong(pageLoadTimeOut);

	}

	/**
	 * This method will initialize the WebDriver whenever we call it.
	 */
	public static void initializeDriver() {

		if (Base.getBrowserName().equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (Base.getBrowserName().equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		} else if (Base.getBrowserName().equalsIgnoreCase("ff")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Base.getPageLoadTimeOut(), TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Base.getImpWait(), TimeUnit.SECONDS);

		driver.get(getURL());
	}

	/**
	 * This method will Close and Quit all windows after each execution.
	 */
	public static void tearDown() {
		driver.close();
		driver.quit();
	}

}
