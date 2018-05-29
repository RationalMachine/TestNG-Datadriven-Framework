package PageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import util.ObjectRepo;
import util.PropertyReader;

import java.security.cert.X509Certificate;

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

    @FindBy(xpath = "(//span[contains(@role,'presentation')])[13]")
    public WebElement plusSignAccountCreation;

    @FindBy(xpath = "(//input[contains(@id,'inputfield')])[1]")
    public WebElement nameField;

    @FindBy(xpath = "(//input[contains(@id,'dropdownlist')])[2]")
    public WebElement salesChannelAccountCreation;

    @FindBy(xpath = "(//input[contains(@id,'dropdownlist')])[3]")
    public WebElement businessSectorAccountCreation;

    @FindBy(xpath = "(//input[contains(@id,'inputfield')])[5]")
    public WebElement streetAccountCreation;

    @FindBy(xpath = "(//input[contains(@id,'inputfield')])[6]")
    public WebElement cityAccountCreation;

    @FindBy(xpath = "(//input[contains(@id,'inputfield')])[8]")
    public WebElement postalCodeAccountCreation;

    @FindBy(xpath = "//span[contains(text(),'Save')]")
    public WebElement saveButtonAccountCreation;

    @FindBy(xpath = "//span[contains(@id,'button-content')]")
    public WebElement saveButtonPostWarningAcctCreate;

    @FindBy(xpath = "(//span[contains(text(),'Potential duplicate accounts were found. To continue, choose Save.')])[2]")
    public WebElement saveButtonAcctHeaderMsg;

    @FindBy(xpath = "(//span[contains(@id,'qcbuttonmenu')])[1]")
    public WebElement plusSignOppo;

    @FindBy(xpath = "//span[contains(@aria-label,'tools-opportunity')]")
    public WebElement oppoHover;

    @FindBy(xpath = "(//input[contains(@id,'inputfield')])[1]")
    public WebElement oppoNameEntryAcctCreate;

    @FindBy(xpath = "(//input[contains(@id,'dropdownlist')])[1]")
    public WebElement offerTypeDropAcctCreate;

    @FindBy(xpath = "(//h1[contains(text(),'Sales')])[2]")
    public WebElement salesDropButton;

    @FindBy(xpath = "(//a[contains(text(),'Quotes')])[1]")
    public WebElement allQuotesButton;

    @FindBy(xpath = "//span[contains(@id,'searchButton-img')]")
    public WebElement searchBtn;

    @FindBy(xpath = "//input[contains(@id,'searchField')]")
    public WebElement searchBtnField;

    @FindBy(xpath = "//a[contains(@href,'QuoteID')]")
    public WebElement quoteIDLink;

    @FindBy(xpath = "//span[contains(text(),'Action')]")
    public WebElement action;

    @FindBy(xpath = "//span[contains(text(),'Edit')]")
    public WebElement edit;

    @FindBy(xpath = "((//div[contains(@role,'toolbar')])[4]/div)[2]/button")
    public WebElement searchBtnFinal;

    @FindBy(xpath = "//a[contains(text(),'AdityaNew')]")
    public WebElement accountLink;

    @FindBy(xpath = "(//div[contains(@id,'tabstrip-tabs')]/div)[4]/span")
    public WebElement opportunityTab;

    @FindBy(xpath = "//div[contains(@id,'bottomRow')]/button/span/span")
    public WebElement searchBtnForOppo;

    @FindBy(xpath = "(//input[contains(@id,'searchField-I')])[2]")
    public WebElement oppoSearchField;

    @FindBy(xpath = "(//tbody/tr/td)[5]/div/a")
    public WebElement opportunity;

    //@FindBy(xpath = "(//div[contains(@id,'tabstrip')]/div)[2]/div/div[4]")
    @FindBy(xpath = "(//div[contains(@class,'StripItem')])[4]")
    public WebElement quotesTab;

    @FindBy(xpath = "(//div[contains(@id,'bottomRow')]/button)[3]")
    public WebElement addLink;

    @FindBy(xpath = "//span[contains(text(),'Add')]")
    public WebElement addLinkPriceLookUp;

    @FindBy(xpath = "(//input[contains(@id,'dropdownlist')])[1]")
    public WebElement coltOrgCountry;

    @FindBy(xpath = "(//input[contains(@id,'dropdownlist')])[3]")
    public WebElement countryCurrency;

    @FindBy(xpath = "(//input[contains(@id,'dropdownlist')])[2]")
    public WebElement salesChannelPriceLookUp;

    @FindBy(xpath = "(//input[contains(@id,'dropdownlist')])[4]")
    public WebElement legalComplexityPriceLookUp;

    @FindBy(xpath = "(//input[contains(@id,'dropdownlist')])[5]")
    public WebElement technicalComplexityPriceLookUp;

    @FindBy(xpath = "//span[contains(text(),'Proceed')]")
    public WebElement proceedPriceLookUp;

    @FindBy(xpath = "(//div[contains(@id,'checkbox')])[2]")
    public WebElement newLogoCheckbox;

    public CommercePage getCPQPage(String s, String o){
        customers.click();
        accounts.click();
        ObjectRepo.waitForLoad(10000);
        searchBtn.click();
        ObjectRepo.waitForLoad(2000);
        searchBtnField.sendKeys(s);
        ObjectRepo.waitForLoad(2000);
        searchBtnField.sendKeys(Keys.ENTER);
        ObjectRepo.waitForLoad(3000);
        accountLink.click();
        ObjectRepo.waitForLoad(6000);
        opportunityTab.click();
        ObjectRepo.waitForLoad(6000);
        Actions actions = new Actions(driver);
        actions.moveToElement(searchBtnForOppo).click().build().perform();
        //searchBtnForOppo.click();
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        /*
        executor.executeScript("arguments[0].click()", searchBtnForOppo);
        searchBtnForOppo.click();
        */
        ObjectRepo.waitForLoad(3000);
        oppoSearchField.sendKeys(o);
        oppoSearchField.sendKeys(Keys.ENTER);
        ObjectRepo.waitForLoad(3000);
        opportunity.click();
        ObjectRepo.waitForLoad(6000);
        //quotesTab.click();
        executor.executeScript("arguments[0].click()", quotesTab);
        ObjectRepo.waitForLoad(5000);
        quotesTab.click();
        ObjectRepo.waitForLoad(12000);
        addLink.click();
        ObjectRepo.waitForLoad(3000);
        return new CommercePage(driver);
    }

    public CommercePage openQuote(String quoteID){
        salesDropButton.click();
        ObjectRepo.waitForLoad(7000);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", allQuotesButton);
        ObjectRepo.waitForLoad(3000);
        searchBtn.click();
        ObjectRepo.waitForLoad(2000);
        searchBtnField.sendKeys(quoteID);
        ObjectRepo.waitForLoad(2000);
        searchBtnField.sendKeys(Keys.ENTER);
        ObjectRepo.waitForLoad(3000);
        quoteIDLink.click();
        ObjectRepo.waitForLoad(3000);
        action.click();
        ObjectRepo.waitForLoad(3000);
        edit.click();
        return new CommercePage(driver);
    }

    public CommercePage navigateToPriceLookupQuote(String aCountry, String aCurrency,
                                                   String aSalesChannel,
                                                   String aLegalComplexity,
                                                   String aTechnicalComplexity,
                                                   String aNewLogo){
        salesDropButton.click();
        allQuotesButton.click();
        ObjectRepo.waitForLoad(8000);
        addLinkPriceLookUp.click();
        ObjectRepo.waitForLoad(3000);
        coltOrgCountry.click();
        ObjectRepo.waitForLoad(3000);
        coltOrgCountry.sendKeys(aCountry);
        ObjectRepo.waitForLoad(3000);
        coltOrgCountry.sendKeys(Keys.DOWN);
        coltOrgCountry.sendKeys(Keys.ENTER);
        ObjectRepo.waitForLoad(3000);
        countryCurrency.click();
        countryCurrency.sendKeys(aCurrency);
        ObjectRepo.waitForLoad(3000);
        countryCurrency.sendKeys(Keys.DOWN);
        countryCurrency.sendKeys(Keys.ENTER);
        ObjectRepo.waitForLoad(5000);
        legalComplexityPriceLookUp.click();
        ObjectRepo.waitForLoad(5000);
        legalComplexityPriceLookUp.sendKeys(aLegalComplexity);
        ObjectRepo.waitForLoad(3000);
        legalComplexityPriceLookUp.sendKeys(Keys.DOWN);
        legalComplexityPriceLookUp.sendKeys(Keys.ENTER);
        ObjectRepo.waitForLoad(5000);
        salesChannelPriceLookUp.click();
        ObjectRepo.waitForLoad(3000);
        salesChannelPriceLookUp.sendKeys(aSalesChannel);
        ObjectRepo.waitForLoad(5000);
        salesChannelPriceLookUp.sendKeys(Keys.DOWN);
        salesChannelPriceLookUp.sendKeys(Keys.ENTER);
        ObjectRepo.waitForLoad(3000);
        technicalComplexityPriceLookUp.click();
        technicalComplexityPriceLookUp.sendKeys(aTechnicalComplexity);
        ObjectRepo.waitForLoad(5000);
        technicalComplexityPriceLookUp.sendKeys(Keys.DOWN);
        technicalComplexityPriceLookUp.sendKeys(Keys.ENTER);
        ObjectRepo.waitForLoad(3000);
        if(aNewLogo.equalsIgnoreCase("Y")){
            newLogoCheckbox.click();
            ObjectRepo.waitForLoad(2000);
        }
        proceedPriceLookUp.click();
        return new CommercePage(driver);
    }

    public void accountCreation(String aName, String aCountry, String aSalesChannel,
                                String aBusinessSect, String aStreet,
                                String aCity, String aPostalCode, String aOppoName) {
        customers.click();
        ObjectRepo.waitForLoad(2000);
        accounts.click();
        ObjectRepo.waitForLoad(6000);
        ObjectRepo.clickWhenClickable(driver,plusSignAccountCreation);
        ObjectRepo.waitForLoad(6000);
        nameField.click();
        nameField.sendKeys(aName);
        ObjectRepo.waitForLoad(6000);
        coltOrgCountry.click();
        coltOrgCountry.sendKeys(aCountry);
        ObjectRepo.waitForLoad(3000);
        coltOrgCountry.sendKeys(Keys.DOWN);
        coltOrgCountry.sendKeys(Keys.ENTER);
        ObjectRepo.waitForLoad(3000);
        salesChannelAccountCreation.click();
        salesChannelAccountCreation.sendKeys(aSalesChannel);
        ObjectRepo.waitForLoad(4000);
        salesChannelAccountCreation.sendKeys(Keys.DOWN);
        salesChannelAccountCreation.sendKeys(Keys.ENTER);
        ObjectRepo.waitForLoad(4000);
        businessSectorAccountCreation.click();
        businessSectorAccountCreation.sendKeys(aBusinessSect);
        ObjectRepo.waitForLoad(4000);
        businessSectorAccountCreation.sendKeys(Keys.DOWN);
        businessSectorAccountCreation.sendKeys(Keys.ENTER);
        ObjectRepo.waitForLoad(4000);
        streetAccountCreation.sendKeys(aStreet);
        ObjectRepo.waitForLoad(2000);
        cityAccountCreation.sendKeys(aCity);
        ObjectRepo.waitForLoad(2000);
        postalCodeAccountCreation.sendKeys(aPostalCode);
        ObjectRepo.waitForLoad(2000);
        saveButtonAccountCreation.click();
        ObjectRepo.waitForLoad(3000);
        if(saveButtonAcctHeaderMsg.isDisplayed()){
            ObjectRepo.clickWhenClickable(driver, saveButtonPostWarningAcctCreate);
            System.out.println("We have verified the warning message");
        }
        ObjectRepo.waitForLoad(3000);
        fillOppoData(aName,aOppoName);
    }

    private void fillOppoData(String aName, String aOppoName) {
        ObjectRepo.clickWhenClickable(driver,searchBtn);
        ObjectRepo.waitForLoad(3000);
        searchBtnField.sendKeys(aName);
        searchBtnField.sendKeys(Keys.ENTER);
        ObjectRepo.waitForLoad(3000);
        String partName = "//a[@title=" + "'" + aName + "'" + "]";
        WebElement ele = driver.findElement(By.xpath(partName));
        ObjectRepo.clickWhenClickable(driver,ele);
        ObjectRepo.waitForLoad(8000);
        ObjectRepo.clickWhenClickable(driver,plusSignOppo);
        ObjectRepo.waitForLoad(8000);
        ObjectRepo.clickWhenClickable(driver,oppoHover);
        ObjectRepo.waitForLoad(20000);
        oppoNameEntryAcctCreate.sendKeys(aOppoName);
        ObjectRepo.waitForLoad(3000);
        offerTypeDropAcctCreate.sendKeys();
    }

}
