package drivers;

import Utilities.PropertyReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import java.net.MalformedURLException;
import org.openqa.selenium.remote.RemoteWebDriver;

public class driverFactory {
	private static final Logger log = LogManager.getLogger(driverFactory.class.getName());

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
			log.info("Driver selected is chrome");
			//System.out.println("Driver selected is chrome");
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			//System.out.println("Driver selected is Firefox");
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

	private void initializeRemoteDriver(String platform, String browser, String nodeURL) throws MalformedURLException {
		DesiredCapabilities caps = new DesiredCapabilities();

		// Platforms
		if (platform.equalsIgnoreCase("Windows")) {
			caps.setPlatform(Platform.WINDOWS);
		}
		if (platform.equalsIgnoreCase("MAC")) {
			caps.setPlatform(Platform.MAC);
		}
		// Browsers
		if (browser.equalsIgnoreCase("chrome")) {
			caps = DesiredCapabilities.chrome();
		}
		if (browser.equalsIgnoreCase("firefox")) {
			caps = DesiredCapabilities.firefox();
		}
		// Version
		//caps.setVersion(version);
		driver = new RemoteWebDriver(new URL(nodeURL), caps);
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

	public WebDriver getRemoteDriver(String platform, String browser, String nodeURL) throws MalformedURLException {
		if(driver != null)
			return driver;
		initializeRemoteDriver(platform, browser, nodeURL);
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
