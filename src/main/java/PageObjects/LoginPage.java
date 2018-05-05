package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.ObjectRepo;

public class LoginPage extends PageBase{

    private WebDriver driver;

    @FindBy(xpath = "//input[contains(@id,'username')]")
    public WebElement username;

    @FindBy(xpath = "//input[contains(@id,'password')]")
    public WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//span[text()='No']")
    private WebElement popupButtonNo;

    public LoginPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public MainPage clickLogin(){
        loginButton.click();
        ObjectRepo.waitForLoad(10000);
        try{
            popupButtonNo.click();
        }catch (Exception e){
            e.printStackTrace();
        }
        ObjectRepo.waitForLoad(3000);
        return new MainPage(driver);
    }
}
