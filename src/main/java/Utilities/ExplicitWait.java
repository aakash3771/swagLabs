package Utilities;

import drivers.driverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ExplicitWait {

	private WebDriver driver;
	private WebDriverWait wait;
	private FluentWait<WebDriver> fluentWait;
	uiOperations ops;

	public ExplicitWait(WebDriver driver, long timeOut) {
		this.driver = driver;
		fluentWait = new FluentWait<>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofMillis(600))
				.ignoring(NoSuchElementException.class);
		wait = new WebDriverWait(driver, timeOut);
		ops = new uiOperations(driver);
	}

	public WebElement waitforPresenceOfElement(By locator) {
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement waitforElementToBeClickable(By locator) {
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public boolean waitforTextToAppear(By locator, String text) {
		return wait.until(ExpectedConditions.textToBe(locator, text));
	}

	public void waitForInvisibilityOfControl(WebElement control)
	{
		fluentWait.until(ExpectedConditions.invisibilityOf(control));
	}

	public void waitForVisibilityOfControl(String locator, String type)
	{
		fluentWait.until(ExpectedConditions.visibilityOf(ops.getElement(locator, type)));
	}

	public void waitForVisibilityOfControl(By locator)
	{
		fluentWait.until(ExpectedConditions.visibilityOf(ops.getElement(locator)));
	}

	public void waitForVisibilityOfControl(WebElement control)
	{
		fluentWait.until(ExpectedConditions.visibilityOf(control));
	}

	public boolean waitForStalenessOfControl(WebElement control)
	{
		return fluentWait.until(ExpectedConditions.stalenessOf(control));
	}

	public void waitUntilPageLoadIsComplete(){
		wait.until(webDriver -> ((JavascriptExecutor)webDriver).
				executeScript("return document.readyState").equals("complete"));
	}
}
