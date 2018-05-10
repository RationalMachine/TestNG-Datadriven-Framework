package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminPage extends PageBase {

    private WebDriver driver;

    public AdminPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(xpath = "//img[@title='Users']")
    public WebElement usersButton;

    @FindBy(xpath = "(//a[contains(@href,'36909318')])[1]")
    public WebElement clickOnUser;

    @FindBy(xpath = "(//a[contains(@href,'36909318')])[2]")
    public WebElement userProxyLogin;

    @FindBy(xpath = "//span[text()='Groups']")
    public WebElement groups;

    @FindBy(xpath = "//select[contains(@name,'currentGroups')]")
    public WebElement groupsTable;

    @FindBy(xpath = "(//select[contains(@name,'currentGroups')]/option)[1]")
    public WebElement optionValue;

    @FindBy(xpath = "//select[contains(@name,'allGroups')]")
    public WebElement allGroupsTable;

    @FindBy(xpath = "//a[@id='>']")
    public WebElement pushGroupFromTable;

    @FindBy(xpath = "//a[@id='apply']")
    public WebElement applyButton;

    @FindBy(xpath = "//a[@id='back']")
    public WebElement backButton;

    public String getInnerHtml(WebElement element){
        return element.getAttribute("innerHTML");
    }

}
