package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class uiOperations {
    private GenericMethods methods;
    public uiOperations(WebDriver driver) {
        methods = new GenericMethods(driver);
    }

    public String performAction(WebDriver driver, String action, By locator, String controlName, String text) {
        ExplicitWait please = new ExplicitWait(driver, 50);
        WebElement control = please.waitforPresenceOfElement(driver, locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String getText = null;
        switch (action) {
            case "click":
                control.click();
                ExtentTestManager.getTest().info(controlName + " is clicked");
                break;
            case "scroll":
                js.executeScript("arguments[0].scrollIntoView(true);", control);
                please.waitForVisibilityOfControl(control);
                ExtentTestManager.getTest().info("Scrolling to " + controlName);
                break;
            case "type":
                control.clear();
                control.click();
                control.sendKeys(text);
                ExtentTestManager.getTest().info(text + " is set to " + controlName);
                break;
            case "clear":
                control.clear();
                ExtentTestManager.getTest().info(controlName + " has been cleared");
                break;
            case "getText":
                getText = control.getText();
                ExtentTestManager.getTest().info("Visible text from " + controlName + " is " + getText);
                break;
            default:
                System.out.println("Action" + action + "cannot be performed.");
        }
        return getText;
    }
}
