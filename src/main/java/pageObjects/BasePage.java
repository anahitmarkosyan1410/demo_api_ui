package pageObjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage{
    protected WebDriver webDriver;
    private WebDriverWait webDriverWait;

    public BasePage(WebDriver driver){
        this.webDriver = driver;
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public void waitForVisitable(WebElement element){
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }

    public void clickElement(WebElement element){
        waitForVisitable(element);
        element.click();
    }

    public String getText(WebElement element){
        waitForVisitable(element);
        return element.getText();
    }

    public void enterText(WebElement element, String text){
        waitForVisitable(element);
        element.clear();
        element.sendKeys(text);
    }

    public void navigateTo(String url){
        webDriver.get(url);
    }

    Alert  alert = webDriver.switchTo().alert();
}
