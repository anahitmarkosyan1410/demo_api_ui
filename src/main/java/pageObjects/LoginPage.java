package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.FileConfig;

public class LoginPage extends BasePage{

    protected String userNameFromConfig = FileConfig.getProperty("username");
    protected String passwordFromConfig = FileConfig.getProperty("password");

    @FindBy(id = "")
    private WebElement userNameField;

    @FindBy(id = "")
    private WebElement passwordField;

    @FindBy(id = "")
    private WebElement loginButton;

    public LoginPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public void enterUserName(String userName){
        enterText(userNameField,userName);
    }

    public void enterPassword(String password){
        enterText(passwordField,password);
    }

    public void clickLoginButton(){
        clickElement(loginButton);
    }

    public void login(String userName, String password){
        enterUserName(userName);
        enterPassword(password);
        clickLoginButton();
    }

    public void loginWithConfigCred(){
        login(userNameFromConfig,passwordFromConfig);
    }
}
