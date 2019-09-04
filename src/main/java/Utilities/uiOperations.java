package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class uiOperations {
    private WebDriver driver;
    public uiOperations(WebDriver driver) {
        this.driver=driver;
    }

    public String performAction(String action, By locator, String controlName, String text) {
        ExplicitWait please = new ExplicitWait(driver, 50);
        WebElement control = please.waitforPresenceOfElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String getText = null;
        switch (action) {
            case "click":
                control.click();
                ExtentTestManager.getTest().info(controlName + " is clicked");
                break;
            case "scroll":
                js.executeScript("arguments[0].scrollIntoView(true);", control);
                please.waitForVisibilityOfControl(getElement(locator));
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

    public String performAction(String action, String locator, String type, String controlName, String text) {
        ExplicitWait please = new ExplicitWait(driver, 50);
        WebElement control = getElement(locator,type);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String getText = null;
        switch (action) {
            case "click":
                control.click();
                ExtentTestManager.getTest().info(controlName + " is clicked");
                break;
            case "scroll":
                js.executeScript("arguments[0].scrollIntoView(true);", control);
                please.waitForVisibilityOfControl(locator, type);
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

    public String performAction(String action, WebElement control, String controlName, String text) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String getText = null;
        switch (action) {
            case "click":
                control.click();
                ExtentTestManager.getTest().info(controlName + " is clicked");
                break;
            case "scroll":
                js.executeScript("arguments[0].scrollIntoView(true);", control);
                new ExplicitWait(driver, 50).waitForVisibilityOfControl(control);
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

    public WebElement getElement(By locator) {
                return this.driver.findElement(locator);
    }

    public WebElement getElement(String locator, String type) {
        type = type.toLowerCase();
        switch (type) {
            case "id":
                System.out.println("Element found with id: " + locator);
                return this.driver.findElement(By.id(locator));
            case "name":
                System.out.println("Element found with name: " + locator);
                return this.driver.findElement(By.name(locator));
            case "xpath":
                System.out.println("Element found with xpath: " + locator);
                return this.driver.findElement(By.xpath(locator));
            case "css":
                System.out.println("Element found with css: " + locator);
                return this.driver.findElement(By.cssSelector(locator));
            case "classname":
                System.out.println("Element found with classname: " + locator);
                return this.driver.findElement(By.className(locator));
            case "tagname":
                System.out.println("Element found with tagname: " + locator);
                return this.driver.findElement(By.tagName(locator));
            case "linktext":
                System.out.println("Element found with link text: " + locator);
                return this.driver.findElement(By.linkText(locator));
            case "partiallinktext":
                System.out.println("Element found with partial link text: " + locator);
                return this.driver.findElement(By.partialLinkText(locator));
            default:
                System.out.println("Locator type not supported");
                return null;
        }
    }

    public List<WebElement> getElementList(String locator, String type) {
        type = type.toLowerCase();
        List<WebElement> elementList = new ArrayList<>();
        switch (type) {
            case "id":
                elementList = this.driver.findElements(By.id(locator));
                break;
            case "name":
                elementList = this.driver.findElements(By.name(locator));
                break;
            case "xpath":
                elementList = this.driver.findElements(By.xpath(locator));
                break;
            case "css":
                elementList = this.driver.findElements(By.cssSelector(locator));
                break;
            case "classname":
                elementList = this.driver.findElements(By.className(locator));
                break;
            case "tagname":
                elementList = this.driver.findElements(By.tagName(locator));
                break;
            case "linktext":
                elementList = this.driver.findElements(By.linkText(locator));
                break;
            case "partiallinktext":
                elementList = this.driver.findElements(By.partialLinkText(locator));
                break;
            default:
                System.out.println("Locator type not supported");
                break;
        }
        if (elementList.isEmpty()) {
            System.out.println("Element not found with " + type +": " + locator);

        } else {
            System.out.println("Element found with " + type +": " + locator);
        }
        return elementList;
    }

    public boolean isElementPresent(String locator, String type) {
        List<WebElement> elementList = getElementList(locator, type);

        int size = elementList.size();

        return size > 0;
    }

    public int getSizeOfElements(String locator, String type) {
        List<WebElement> elementList = getElementList(locator, type);

        return elementList.size();
    }

    public int convertStringToInteger(String text)
    {
        return Integer.parseInt(text);
    }

    public String getSubString(String text, String cutoff)
    {
        String subText = text.substring(text.lastIndexOf(cutoff) + 1);
        ExtentTestManager.getTest().info("Sub text of " + text + " is " + subText);
        return subText;
    }

    public String getVisibleText(String locator, String xpath) {
        return getElement(locator,xpath).getText();
    }
}
