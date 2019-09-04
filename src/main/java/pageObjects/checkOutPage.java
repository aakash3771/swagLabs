package pageObjects;

import Utilities.XMLReader;
import Utilities.constants;
import org.dom4j.DocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class checkOutPage extends basePage{
    private final By firstName = By.id(XMLReader.readPropertiesFile("//checkOutPage/firstName"));
    private final By lastName = By.id(XMLReader.readPropertiesFile("//checkOutPage/lastName"));
    private final By postalCode = By.id(XMLReader.readPropertiesFile("//checkOutPage/postalCode"));
    private final By continueButton = By.xpath(XMLReader.readPropertiesFile("//checkOutPage/continueButton"));

    checkOutPage(WebDriver driver) throws DocumentException {
        super(driver);
    }

    public void setFirstName(String firstNameText) {
        ops.performAction(constants.type, firstName, "First name field", firstNameText);
    }

    public void setLastName(String lastNameText) {
        ops.performAction(constants.type, lastName, "Last name field", lastNameText);
    }

    public void setPostalCode(String postalCodeText) {
        ops.performAction(constants.type, postalCode, "Postal code field", postalCodeText);
    }

    public checkoutOverviewPage clickContinue() throws DocumentException {
        ops.performAction(constants.click, continueButton, "Contnue button", null);
        return new checkoutOverviewPage(driver);
    }
}
