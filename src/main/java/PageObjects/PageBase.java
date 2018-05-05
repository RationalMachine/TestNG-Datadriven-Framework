package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class PageBase {

    private WebDriver driver;

    public void waitForElement(WebElement element, int timeOutInSeconds){

    }

    public PageBase(WebDriver driver){
        if(driver == null)
            throw new IllegalArgumentException("Driver object is null");
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}
