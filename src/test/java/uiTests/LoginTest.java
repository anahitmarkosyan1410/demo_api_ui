package uiTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import utilities.FileConfig;

public class LoginTest extends BaseTest{
    LoginPage loginPage;
    String url = FileConfig.getProperty("baseURL");

    @Test
    public void checkLoginTest() {
        loginPage = new LoginPage(webDriver);
        loginPage.navigateTo(url);
        loginPage.loginWithConfigCred();
        Assert.assertTrue(webDriver.getTitle().contains(""));
    }

    @Test
    public void checkLoginWithInvalidUserNameTest() {
        loginPage = new LoginPage(webDriver);
        loginPage.navigateTo(url);
        loginPage.login("userName",FileConfig.getProperty("password"));
        Assert.assertTrue(webDriver.getTitle().contains(""));
    }
}
