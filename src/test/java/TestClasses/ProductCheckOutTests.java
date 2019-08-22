package TestClasses;

import BaseTest.BaseTest;
import Utilities.ExtentTestManager;
import org.dom4j.DocumentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.*;

public class ProductCheckOutTests extends BaseTest {

    @Test(priority = 1, dataProvider = "productSearch")
    public void ProductSearch(String username, String password, String productName, String quantityOfProduct,
                              String firstName, String lastName, String zipCode, String itemTotal, String totalInfo,
                              String taxInfo, String shippingInfo, String paymentInfo, String successMessage) throws DocumentException {
        ExtentTestManager.getTest().assignCategory(ProductCheckOutTests.class.getName());
        loginPage login = new loginPage(driver);
        login.setUserName(username);
        login.setPasswordLocator(password);
        productsPage product = login.clickLoginButton();
        product.addProductToCart(productName);
        Assert.assertEquals(product.getNumberOfItemsInCart(), "1");
        cartPage cart = product.clickCartIcon();
        Assert.assertTrue(cart.isProductInCart(productName));
        Assert.assertEquals(cart.checkQuantityOfProduct(productName), quantityOfProduct);
        checkOutPage checkOut = cart.clickCheckOut();
        checkOut.setFirstName(firstName);
        checkOut.setLastName(lastName);
        checkOut.setPostalCode(zipCode);
        checkoutOverviewPage overview = checkOut.clickContinue();
        Assert.assertEquals(overview.getPaymentInfo(), paymentInfo);
        Assert.assertEquals(overview.getShippingInfo(), shippingInfo);
        Assert.assertEquals(overview.getItemTotalInfo(), itemTotal);
        Assert.assertEquals(overview.getTaxInfo(), taxInfo);
        Assert.assertEquals(overview.getTotalInfo(), totalInfo);
        finishPage finish = overview.clickFinish();
        Assert.assertEquals(finish.getSuccessMessage(), successMessage);
        finish.clickLeftMenu();
        finish.clickLogOut();
    }

    @DataProvider(name = "productSearch")
    public Object[][] returnTestData()
    {
        return new Object[][]{{"standard_user", "secret_sauce", "Sauce Labs Backpack", "1","Divya", "Gupta","110026",
                "29.99","32.39","2.40","FREE PONY EXPRESS DELIVERY!","SauceCard #31337","THANK YOU FOR YOUR ORDER"}};
    }
}
