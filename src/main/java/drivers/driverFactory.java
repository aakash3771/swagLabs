package drivers;

import Utilities.PropertyReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class driverFactory {

	/**
	 * this is the driver that will be used throughout the project
	 */
	private WebDriver driver;

	/**
	 * driver is initiliazed. Which browser and URL has to be used is mentioned in Application.Properties file.
	 *
	 */
	private void initializeDriver() {
		switch (PropertyReader.readApplicationFile("browser")) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-infobars");
			driver = new ChromeDriver(options);
			System.out.println("Driver selected is chrome");
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			System.out.println("Driver selected is Firefox");
			break;
		case "IE":
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			System.out.println("Driver selected is IE");
			break;
		default:
			System.out.println("Incorrect driver setup");
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(PropertyReader.readApplicationFile("url"));
	}

	/**
	 * @return the browser
	 */
	public WebDriver getDriver()
	{
		if(driver != null)
		return driver;
		initializeDriver();
		return driver;
	}

	public void quitDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

	/**
	 * @return the current url of the page.
	 */
	public String returnCurrentUrl()
	{
		return driver.getCurrentUrl();
	}
}
