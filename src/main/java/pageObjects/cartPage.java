package pageObjects;

import Utilities.XMLReader;
import drivers.driverFactory;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.DocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class cartPage extends basePage{
    private static final Logger log = LogManager.getLogger(cartPage.class.getName());
    private final By continueShopping = By.xpath(XMLReader.readPropertiesFile("//cartPage/continueShopping"));
    private final By checkOut = By.xpath(XMLReader.readPropertiesFile("//cartPage/checkOut"));

    public cartPage(WebDriver driver) throws DocumentException {
        super(driver);
    }

    public productsPage clickContinueShopping() throws DocumentException {
        ops.performAction(driver,"click", continueShopping, null);
        return new productsPage(driver);
    }

    @Step("Checkout button is cliked on cart page.")
    public checkOutPage clickCheckOut() throws DocumentException {
        ops.performAction(driver,"click", checkOut, null);
        return new checkOutPage(driver);
    }

    public int verifyNumberOfUniqueItemsInCart()
    {
        return method.getSizeOfElements("//div[@class='cart_item']", "xpath");
    }

    @Step("Quantity of product {0} is returned.")
    public String checkQuantityOfProduct(String productName)
    {
        String locator = "//div[text()='"+productName+"']/parent::a/parent::div/preceding-sibling::div";
        log.info("Quantity of product" + method.getVisibleText(locator,"xpath") + "is returned.");
        return method.getVisibleText(locator,"xpath");
    }

    @Step("Verified that product {0} is in the cart.")
    public boolean isProductInCart(String productName)
    {
        return method.isElementPresent("//div[text()='"+productName+"']","xpath");
    }

}
