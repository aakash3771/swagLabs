package TestClasses;

import BaseTest.BaseTest;
import org.dom4j.DocumentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.loginPage;

public class LoginTests extends BaseTest {

    @Test(dataProvider = "ValidateLockedOutUser")
    public void ValidateLockedOutUser(String username, String password, String errorMessage) throws DocumentException {
        loginPage login = new loginPage(driver);
        login.login(username, password);
        Assert.assertEquals(login.getErrorMessage(), errorMessage);
    }

    @Test(dataProvider = "ValidLoginTests")
    public void ValidateStandardUser(String username, String password) throws DocumentException {
        loginPage login = new loginPage(driver);
        login.login(username, password);
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        login.clickLeftMenu();
        login.clickLogOut();
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
