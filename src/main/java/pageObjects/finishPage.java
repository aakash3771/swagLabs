package pageObjects;

import Utilities.XMLReader;
import Utilities.constants;
import org.dom4j.DocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class finishPage extends basePage{
    private final String successMessage = XMLReader.readPropertiesFile("//finishPage/successMessage");
    private final String pageTitle = XMLReader.readPropertiesFile("//finishPage/pageTitle");
    finishPage(WebDriver driver) throws DocumentException {
        super(driver);
        please.waitForVisibilityOfControl(pageTitle, constants.xpath);
    }

    public String getSuccessMessage()
    {
        return ops.performAction(constants.getText, successMessage, constants.xpath, "Success message", null);
    }
}