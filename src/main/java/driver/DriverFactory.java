package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import utilities.FileConfig;


public class DriverFactory {
    private static Logger logger = LogManager.getLogger(DriverFactory.class);
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver driverInitializer(){
        String browser = FileConfig.getProperty("browser").toLowerCase();
        logger.info("Initializing browser " + browser);
        switch (browser){
            case "chrome" :
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                driver.set(new ChromeDriver(options));
                break;
            case  "firefox" :
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--remote-allow-origins=*");
                driver.set(new FirefoxDriver(firefoxOptions));
                break;
            default:
                logger.info("Invalid browser type");
                throw new IllegalArgumentException("Invalid browser");
        }
        driver.get().manage().window().maximize();
        return driver.get();
    }

    public static WebDriver getDriver(){
        if (driver.get() == null){
            driverInitializer();
        }
        return driver.get();
    }

    public static void quiteDriver(){
        if (driver.get() != null){
            driver.get().quit();
            driver.remove();
            logger.info("Driver has been closed.");
        }
    }
}
