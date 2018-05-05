package util;

import PageObjects.LoginPage;
import PageObjects.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class InitWebDriver {

    protected static WebDriver Driver = null;
    protected static PropertyReader reader = null;

    public InitWebDriver() {
        reader = new PropertyReader();
    }
    public InitWebDriver(String filename) {
        reader = new PropertyReader();
    }

    @BeforeTest
    public void setUp(){

        System.setProperty("webdriver.chrome.driver", reader.getChromeDriverPath());
        Driver = new ChromeDriver();
        //Driver.manage().timeouts().pageLoadTimeout(reader.getPageLoadTimeOut(), TimeUnit.SECONDS);
        //Driver.manage().timeouts().implicitlyWait(reader.getImplicitTimeOut(), TimeUnit.SECONDS);
        Driver.manage().window().maximize();
        Driver.get(reader.getWebsite());
    }

    public WebDriver getDefaultDriver(){
        return Driver;
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {

        if(Driver != null){
            Driver.quit();
            Driver = null;
        }
    }

}
