package pageObjects;

import Utilities.XMLReader;
import io.qameta.allure.Step;
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

    @Step("First name ({0}) is typed in.")
    public void setFirstName(String firstNameText) {
        ops.performAction(driver,"type", firstName, firstNameText);
    }

    @Step("Last name ({0}) is typed in.")
    public void setLastName(String lastNameText) {
        ops.performAction(driver,"type", lastName, lastNameText);
    }

    @Step("Postal code ({0}) is typed in.")
    public void setPostalCode(String postalCodeText) {
        ops.performAction(driver,"type", postalCode, postalCodeText);
    }

    @Step("Continue button is clicked.")
    public checkoutOverviewPage clickContinue() throws DocumentException {
        ops.performAction(driver,"click", continueButton, null);
        return new checkoutOverviewPage(driver);
    }
}
