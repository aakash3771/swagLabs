package pageObjects;

import Utilities.XMLReader;
import org.dom4j.DocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class cartPage extends basePage{
    private final By continueShopping = By.xpath(XMLReader.readPropertiesFile("//cartPage/continueShopping"));
    private final By checkOut = By.xpath(XMLReader.readPropertiesFile("//cartPage/checkOut"));

    public cartPage(WebDriver driver) throws DocumentException {
        super(driver);
    }

    public productsPage clickContinueShopping() throws DocumentException {
        ops.performAction(driver,"click", continueShopping, null);
        return new productsPage(driver);
    }

    public checkOutPage clickCheckOut() throws DocumentException {
        ops.performAction(driver,"click", checkOut, null);
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