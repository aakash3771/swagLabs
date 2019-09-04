package pageObjects;

import Utilities.ExplicitWait;
import Utilities.XMLReader;
import Utilities.uiOperations;
import io.qameta.allure.Step;
import org.dom4j.DocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class basePage {
    WebDriver driver;
    uiOperations ops;
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
        please = new ExplicitWait(driver, 50);
        please.waitUntilPageLoadIsComplete();
    }

    /**
     * Clicks All Items link
     */
    public void clickAllItems()
    {
        ops.performAction("click", allItems, "All Items", "");
    }

    public void clickCloseLeftMenu()
    {
        ops.performAction("click", closeLeftMenu, "Left Menu close icon", "");
    }

    public void clickLogOut()
    {
        ops.performAction("click", logOut, "Logout link", "");
    }

    /**
     * clicks About link
     */
    public void clickAbout()
    {
        ops.performAction("click", about, "About link", null);
    }

    /**
     * @return cart page
     */
    @Step("Cart icon is clicked.")
    public cartPage clickCartIcon() throws DocumentException {
        ops.performAction("click", cartIcon, "Cart icon", null);
        return new cartPage(driver);
    }

    @Step("Left menu is opened.")
    public void clickLeftMenu()
    {
        ops.performAction("click", leftMenu, "Left menu", null);
    }

    public String getNumberOfItemsInCart()
    {
        return ops.performAction("getText", numberOfItems, "Cart icon", null);
    }
}
