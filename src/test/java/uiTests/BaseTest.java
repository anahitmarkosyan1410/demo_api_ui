package uiTests;

import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver webDriver;

    @BeforeMethod
    public void setUp(){
        webDriver = DriverFactory.getDriver();
    }

    @AfterMethod
    public void tearDown(){
        DriverFactory.quiteDriver();
    }
}
