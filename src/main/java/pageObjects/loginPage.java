package pageObjects;

import Utilities.XMLReader;
import Utilities.constants;
import org.dom4j.DocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class loginPage extends basePage{

    private final By usernameLocator = By.id(XMLReader.readPropertiesFile("//loginPage/username"));
    private final By passwordLocator = By.id(XMLReader.readPropertiesFile("//loginPage/password"));
    private final By loginButtonLocator = By.xpath(XMLReader.readPropertiesFile("//loginPage/loginButton"));
    private final By errorMessageLocator = By.xpath(XMLReader.readPropertiesFile("//loginPage/errorMessage"));

    public loginPage(WebDriver driver) throws DocumentException {
        super(driver);
    }

    public void setUserName(String userName) {
        ops.performAction(constants.type, usernameLocator, "username", userName);
    }

    public void setPasswordLocator(String passwordText) {
        ops.performAction(constants.type, passwordLocator, "password", passwordText);
    }

    public productsPage clickLoginButton() throws DocumentException {
        ops.performAction(constants.click, loginButtonLocator, "Login button", "");
        return new productsPage(driver);
    }

    public String getErrorMessageLocator()
    {
        return ops.performAction(constants.getText, errorMessageLocator, "", "");
    }

    public void login(String username, String passwordText) throws DocumentException {
        setUserName(username);
        setPasswordLocator(passwordText);
        clickLoginButton();
    }
}
