package pageObjects;

import io.qameta.allure.Step;
import org.dom4j.DocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import Utilities.*;

public class basePage {
    WebDriver driver;
    uiOperations ops;
    GenericMethods method;
    ExplicitWait please;

    private final By cartIcon = By.xpath(XMLReader.readPropertiesFile("//commonControls/cartIcon"));
    private final By numberOfItems = By.xpath(XMLReader.readPropertiesFile("//commonControls/numberOfItems"));
    private final By leftMenu = By.xpath(XMLReader.readPropertiesFile("//commonControls/leftMenu"));
    private final By logOut = By.id(XMLReader.readPropertiesFile("//commonControls/logOut"));
    private final By about = By.id(XMLReader.readPropertiesFile("//commonControls/about"));
    private final By allItems = By.id(XMLReader.readPropertiesFile("//commonControls/allItems"));
    private final By closeLeftMenu = By.xpath(XMLReader.readPropertiesFile("//commonControls/closeLeftMenu"));

    basePage(WebDriver driver) throws DocumentException {
        this.driver = driver;
        ops = new uiOperations(driver);
        method = new GenericMethods(driver);
        please = new ExplicitWait(driver, 50);
    }

    /**
     * Clicks All Items link
     */
    public void clickAllItems()
    {
        ops.performAction(driver,"click", allItems, null);
    }

    public void clickCloseLeftMenu()
    {
        ops.performAction(driver,"click", closeLeftMenu, null);
    }

    @Step("Log out link is clicked.")
    public void clickLogOut()
    {
        ops.performAction(driver,"click", logOut, null);
    }

    /**
     * clicks About link
     */
    public void clickAbout()
    {
        ops.performAction(driver,"click", about, null);
    }

    /**
     * @return cart page
     */
    @Step("Cart icon is clicked.")
    public cartPage clickCartIcon() throws DocumentException {
        ops.performAction(driver,"click", cartIcon, null);
        return new cartPage(driver);
    }

    @Step("Left menu is opened.")
    public void clickLeftMenu()
    {
        ops.performAction(driver,"click", leftMenu, null);
    }

    public String getNumberOfItemsInCart()
    {
        return ops.performAction(driver,"getText", numberOfItems, null);
    }
}
