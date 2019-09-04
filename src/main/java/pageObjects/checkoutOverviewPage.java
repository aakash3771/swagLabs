package pageObjects;

import Utilities.XMLReader;
import Utilities.constants;
import org.dom4j.DocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class checkoutOverviewPage extends basePage{
    private final By cancel = By.xpath(XMLReader.readPropertiesFile("//checkoutOverviewPage/cancel"));
    private final By finish = By.xpath(XMLReader.readPropertiesFile("//checkoutOverviewPage/finish"));
    private final By paymentInfo = By.xpath(XMLReader.readPropertiesFile("//checkoutOverviewPage/paymentInfo"));
    private final By shippingInfo = By.xpath(XMLReader.readPropertiesFile("//checkoutOverviewPage/shippingInfo"));
    private final By itemTotalInfo = By.xpath(XMLReader.readPropertiesFile("//checkoutOverviewPage/itemTotalInfo"));
    private final By taxInfo = By.xpath(XMLReader.readPropertiesFile("//checkoutOverviewPage/taxInfo"));
    private final By totalInfo = By.xpath(XMLReader.readPropertiesFile("//checkoutOverviewPage/totalInfo"));

    public checkoutOverviewPage(WebDriver driver) throws DocumentException {
        super(driver);
    }

    public productsPage clickCancel() throws DocumentException {
        ops.performAction(constants.click, cancel, "Cancel button", null);
        return new productsPage(driver);
    }

    public finishPage clickFinish() throws DocumentException {
        ops.performAction(constants.click, finish, "Finish button", null);
        return new finishPage(driver);
    }

    /**
     * @param productName is the name of the product.
     * @return return true if product is present in cart.
     */
    public boolean isProductInCart(String productName)
    {
        return ops.isElementPresent("//div[text()='"+productName+"']","xpath");
    }

    public String getPaymentInfo()
    {
        return ops.performAction(constants.getText, paymentInfo, "Payment section", null);
    }

    public String getShippingInfo()
    {
        return ops.performAction(constants.getText, shippingInfo, "Shipping section", null);
    }

    public String getItemTotalInfo()
    {
        return ops.getSubString(ops.performAction(constants.getText, itemTotalInfo, "Item Total", null), "$");
    }

    public String getTaxInfo()
    {
        return ops.getSubString(ops.performAction(constants.getText, taxInfo, "Tax info", null), "$");
    }

    public String getTotalInfo()
    {
        return ops.getSubString(ops.performAction(constants.getText, totalInfo, "Total Amount", null), "$");
    }

}
