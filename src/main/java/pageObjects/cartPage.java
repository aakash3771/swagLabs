package pageObjects;

import Utilities.XMLReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.DocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class cartPage extends basePage{
    private static final Logger log = LogManager.getLogger(cartPage.class.getName());
    private final By continueShopping = By.xpath(XMLReader.readPropertiesFile("//cartPage/continueShopping"));
    private final By checkOut = By.xpath(XMLReader.readPropertiesFile("//cartPage/checkOut"));

    cartPage(WebDriver driver) throws DocumentException {
        super(driver);
    }

    public productsPage clickContinueShopping() throws DocumentException {
        ops.performAction(driver, "click", continueShopping, "Continue button", null);
        return new productsPage(driver);
    }

    public checkOutPage clickCheckOut() throws DocumentException {
        ops.performAction(driver, "click", checkOut, "Checkout button", null);
        return new checkOutPage(driver);
    }

    public int verifyNumberOfUniqueItemsInCart()
    {
        return method.getSizeOfElements("//div[@class='cart_item']", "xpath");
    }

    public String checkQuantityOfProduct(String productName)
    {
        String locator = "//div[text()='"+productName+"']/parent::a/parent::div/preceding-sibling::div";
        return method.getVisibleText(locator,"xpath");
    }

    public boolean isProductInCart(String productName)
    {
        return method.isElementPresent("//div[text()='"+productName+"']","xpath");
    }

}
