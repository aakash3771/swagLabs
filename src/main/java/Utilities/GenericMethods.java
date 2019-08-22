package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class GenericMethods {
    private WebDriver driver;

    public GenericMethods(WebDriver driver) {
        this.driver = driver;
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

    public String getAttribute(String locator, String type, String attribute)
    {
        return getElement(locator,type).getAttribute(attribute);
    }

    public int getSizeOfElements(String locator, String type)
    {
        List<WebElement> elementList = getElementList(locator, type);
        int size = elementList.size();
        ExtentTestManager.getTest().info("Number of elements is " + size);
        return size;
    }

    public String getVisibleText(String locator, String type)
    {
        String text = getElement(locator, type).getText();
        ExtentTestManager.getTest().info("Visible text from control is " + text);
        return text;
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
}