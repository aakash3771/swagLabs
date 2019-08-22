package pageObjects;

import org.dom4j.DocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class productsPage extends basePage{

    productsPage(WebDriver driver) throws DocumentException {
        super(driver);
    }

    public void addProductToCart(String productName) {
        By locator = By.xpath("//div[text()='" + productName + "']/parent::a/parent::div/following-sibling::div/button");
        ops.performAction(driver, "scroll", locator, productName, null);
        ops.performAction(driver, "click", locator, "Add to cart button", null);
    }

    public String getAmount(String productName) {
        By locator = By.xpath("//div[text()='" + productName + "']/parent::a/parent::div/following-sibling::div/div");
        ops.performAction(driver, "scroll", locator, productName, null);
        return ops.performAction(driver, "getText", locator, "Product amount", null);
    }
}
