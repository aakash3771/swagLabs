package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class uiOperations {
    GenericMethods methods;
    public uiOperations(WebDriver driver) {
        methods = new GenericMethods(driver);
    }

    public String performAction(WebDriver driver, String action, By locator, String text) {
        ExplicitWait please = new ExplicitWait(driver, 50);
        WebElement control = please.waitforPresenceOfElement(driver, locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String getText = null;
        switch (action) {
            case "click":
                control.click();
                break;
            case "scroll":
                js.executeScript("arguments[0].scrollIntoView(true);", control);
                please.waitForVisibilityOfControl(control);
                break;
            case "type":
                control.clear();
                control.click();
                control.sendKeys(text);
                break;
            case "clear":
                control.clear();
                break;
            case "getText":
                getText = control.getText();
                break;
            default:
                System.out.println("Action" + action + "cannot be performed.");
        }
        return getText;
    }
}
