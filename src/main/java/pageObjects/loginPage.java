package pageObjects;

import Utilities.XMLReader;
import org.dom4j.DocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class loginPage extends basePage{

    private final By username = By.id(XMLReader.readPropertiesFile("//loginPage/username"));
    private final By password = By.id(XMLReader.readPropertiesFile("//loginPage/password"));
    private final By loginButton = By.xpath(XMLReader.readPropertiesFile("//loginPage/loginButton"));
    private final By errorMessage = By.xpath(XMLReader.readPropertiesFile("//loginPage/errorMessage"));

    public loginPage(WebDriver driver) throws DocumentException {
        super(driver);
    }

    public void setUserName(String userName) {
        ops.performAction(driver,"type", username, userName);
    }

    public void setPassword(String passwordText) {
        ops.performAction(driver,"type", password, passwordText);
    }

    public productsPage clickLoginButton() throws DocumentException {
        ops.performAction(driver,"click", loginButton, null);
        return new productsPage(driver);
    }

    public String getErrorMessage()
    {
        return ops.performAction(driver,"getText", errorMessage, null);
    }

    public void login(String username, String passwordText) throws DocumentException {
        setUserName(username);
        setPassword(passwordText);
        clickLoginButton();
    }
}