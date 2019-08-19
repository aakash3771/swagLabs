package pageObjects;

import Utilities.XMLReader;
import io.qameta.allure.Step;
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

    @Step("Username is typed in with {0}.")
    public void setUserName(String userName) {
        ops.performAction(driver,"type", username, userName);
    }
    @Step("Password is typed in with {0}.")
    public void setPassword(String passwordText) {
        ops.performAction(driver,"type", password, passwordText);
    }
    @Step("Login button is clicked.")
    public productsPage clickLoginButton() throws DocumentException {
        ops.performAction(driver,"click", loginButton, null);
        return new productsPage(driver);
    }
    @Step("Error message is returned.")
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
