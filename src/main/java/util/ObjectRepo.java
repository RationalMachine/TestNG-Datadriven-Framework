package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ObjectRepo {
    private static int i;
    public static void waitForLoad(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void clickWhenClickable(WebDriver driver, WebElement ele){
        try{
            WebDriverWait wait = new WebDriverWait(driver, 11);
            wait.until(ExpectedConditions.elementToBeClickable(ele));
            ele.click();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
