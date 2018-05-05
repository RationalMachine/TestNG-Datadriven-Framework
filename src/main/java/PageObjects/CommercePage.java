package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CommercePage extends PageBase{

    private WebDriver driver;

    public CommercePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(xpath = "")
    public WebElement addProductBtn;

    protected void add(String product) {
    }

    protected void giveQuoteLevelDiscount(String s) {
    }
}
