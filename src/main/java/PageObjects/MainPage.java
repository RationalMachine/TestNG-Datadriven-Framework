package PageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import util.ObjectRepo;
import util.PropertyReader;

public class MainPage extends PageBase{

    private WebDriver driver;

    public MainPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(xpath = "//span[contains(@id,'CollapsedImg')]")
    public WebElement customers;

    @FindBy(xpath = "//a[contains(text(),'Accounts')]")
    public WebElement accounts;

    @FindBy(xpath = "//span[contains(@id,'searchButton-img')]")
    public WebElement searchBtn;

    @FindBy(xpath = "//input[contains(@id,'searchField')]")
    public WebElement searchBtnField;

    @FindBy(xpath = "((//div[contains(@role,'toolbar')])[4]/div)[2]/button")
    public WebElement searchBtnFinal;

    @FindBy(xpath = "//a[contains(text(),'AdityaNew')]")
    public WebElement accountLink;

    @FindBy(xpath = "(//div[contains(@id,'tabstrip-tabs')]/div)[4]/span")
    public WebElement opportunityTab;

    @FindBy(xpath = "//div[contains(@id,'bottomRow')]/button/span")
    public WebElement searchBtnForOppo;

    @FindBy(xpath = "(//input[contains(@id,'searchField-I')])[2]")
    public WebElement oppoSearchField;

    @FindBy(xpath = "(//tbody/tr/td)[5]/div/a")
    public WebElement opportunity;

    //@FindBy(xpath = "(//div[contains(@id,'tabstrip')]/div)[2]/div/div[4]")
    @FindBy(xpath = "(//div[contains(@class,'StripItem')])[4]")
    public WebElement quotesTab;

    @FindBy(xpath = "(//div[contains(@id,'bottomRow')]/button)[3]")
    public static WebElement addLink;

    public CommercePage getCPQPage(String s, String o){
        customers.click();
        accounts.click();
        ObjectRepo.waitForLoad(5000);
        searchBtn.click();
        ObjectRepo.waitForLoad(2000);
        searchBtnField.sendKeys(s);
        ObjectRepo.waitForLoad(2000);
        searchBtnField.sendKeys(Keys.ENTER);
        ObjectRepo.waitForLoad(3000);
        accountLink.click();
        ObjectRepo.waitForLoad(6000);
        opportunityTab.click();
        ObjectRepo.waitForLoad(3000);
        searchBtnForOppo.click();
        ObjectRepo.waitForLoad(3000);
        oppoSearchField.sendKeys(o);
        oppoSearchField.sendKeys(Keys.ENTER);
        ObjectRepo.waitForLoad(3000);
        opportunity.click();
        ObjectRepo.waitForLoad(3000);
        //quotesTab.click();
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", quotesTab);
        ObjectRepo.waitForLoad(5000);
        quotesTab.click();
        ObjectRepo.waitForLoad(12000);
        addLink.click();
        ObjectRepo.waitForLoad(3000);
        return new CommercePage(driver);
    }
}
