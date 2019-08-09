package Utilities;

import drivers.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ExplicitWait {

	private WebDriver driver;
	private WebDriverWait wait;
	private FluentWait<WebDriver> fluentWait;

	public ExplicitWait(WebDriver driver, long timeOut) {
		this.driver = driver;
		fluentWait = new FluentWait<>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofMillis(600))
				.ignoring(NoSuchElementException.class);
		wait = new WebDriverWait(driver, timeOut);
	}

	public WebElement waitforPresenceOfElement(WebDriver driver, By locator) {
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement waitforElementToBeClickable(WebDriver driver, By locator) {
		if(wait == null)
			initializeWait(driver);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public boolean waitforTextToAppear(WebDriver driver, By locator, String text) {
		if(wait == null)
			initializeWait(driver);
		return wait.until(ExpectedConditions.textToBe(locator, text));
	}

	private void initializeWait(WebDriver driver)
	{
		long timeOut = 20;
		wait = new WebDriverWait(driver, timeOut);
	}

	public void waitForInvisibilityOfControl(WebElement control)
	{
		fluentWait.until(ExpectedConditions.invisibilityOf(control));
	}

	public void waitForVisibilityOfControl(WebElement control)
	{
		fluentWait.until(ExpectedConditions.visibilityOf(control));
	}
}
