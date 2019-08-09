package pageObjects;

import Utilities.XMLReader;
import org.dom4j.DocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class checkOutPage extends basePage{
    private final By firstName = By.id(XMLReader.readPropertiesFile("//checkOutPage/firstName"));
    private final By lastName = By.id(XMLReader.readPropertiesFile("//checkOutPage/lastName"));
    private final By postalCode = By.id(XMLReader.readPropertiesFile("//checkOutPage/postalCode"));
    private final By continueButton = By.xpath(XMLReader.readPropertiesFile("//checkOutPage/continueButton"));

    public checkOutPage(WebDriver driver) throws DocumentException {
        super(driver);
    }

    public void setFirstName(String firstNameText) {
        ops.performAction(driver,"type", firstName, firstNameText);
    }

    public void setLastName(String lastNameText) {
        ops.performAction(driver,"type", lastName, lastNameText);
    }

    public void setPostalCode(String postalCodeText) {
        ops.performAction(driver,"type", postalCode, postalCodeText);
    }

    public checkoutOverviewPage clickContinue() throws DocumentException {
        ops.performAction(driver,"click", continueButton, null);
        return new checkoutOverviewPage(driver);
    }
}
