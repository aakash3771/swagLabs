package TestClasses;

import BaseTest.BaseTest;
import Utilities.ExtentTestManager;
import org.dom4j.DocumentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.loginPage;

import java.lang.reflect.Method;

public class LoginTests extends BaseTest {

    @Test(priority = 2, dataProvider = "ValidateLockedOutUser", groups = {"positive"})
    public void ValidateLockedOutUser(String username, String password, String errorMessage, Method method) throws DocumentException {
        ExtentTestManager.getTest().assignCategory(LoginTests.class.getName());
        loginPage login = new loginPage(driver);
        login.login(username, password);
        Assert.assertEquals(login.getErrorMessageLocator(), errorMessage);
    }

    @Test(priority = 1, dataProvider = "ValidLoginTests", groups = {"positive"})
    public void ValidateStandardUser(String username, String password, Method method) throws DocumentException {
        ExtentTestManager.getTest().assignCategory(LoginTests.class.getName());
        loginPage login = new loginPage(driver);
        login.login(username, password);
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        login.clickLeftMenu();
        login.clickLogOut();
    }

    @Test(priority = 3, dataProvider = "ValidateLockedOutUser", groups = {"negative"})
    public void InvalidScenario(String username, String password, Method method) throws DocumentException {
        ExtentTestManager.getTest().assignCategory(LoginTests.class.getName());
        loginPage login = new loginPage(driver);
        login.login(username, password);
        Assert.assertTrue(false);
    }

    @DataProvider(name ="ValidateLockedOutUser")
    public Object[][] ValidateLockedOutUser()
    {
        return new Object[][]{{"locked_out_user", "secret_sauce","Epic sadface: Sorry, this user has been locked out."}};
    }

    @DataProvider(name ="ValidLoginTests")
    public Object[][] ValidLoginTests()
    {
        return new Object[][]{{"standard_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"}};
    }
}
