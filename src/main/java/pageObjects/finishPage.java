package pageObjects;

import Utilities.XMLReader;
import org.dom4j.DocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class finishPage extends basePage{
    private final By successMessage = By.xpath(XMLReader.readPropertiesFile("//finishPage/successMessage"));

    finishPage(WebDriver driver) throws DocumentException {
        super(driver);
        By pageTitle = By.xpath(XMLReader.readPropertiesFile("//finishPage/pageTitle"));
        please.waitForVisibilityOfControl(driver.findElement(pageTitle));
    }

    public String getSuccessMessage()
    {
        return ops.performAction(driver, "getText", successMessage, "Success message", null);
    }
}