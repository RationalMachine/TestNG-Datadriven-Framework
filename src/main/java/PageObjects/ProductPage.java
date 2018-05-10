package PageObjects;

import cucumber.api.java.eo.Se;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import util.ObjectRepo;

import static org.testng.Assert.*;

public class ProductPage extends PageBase {

    private WebDriver driver;
    private String approvalMsgText;

    public ProductPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(xpath = "//li[@id='dataVoice-ethernet']")
    public WebElement ethernet;

    @FindBy(xpath = "//li[@id='dataVoice-optical']")
    public WebElement opticalWave;

    @FindBy(xpath = "//a[contains(text(),'Ethernet Hub')]")
    public WebElement ethernetHub;

    @FindBy(xpath = "//a[contains(text(),'Ethernet Line')]")
    public WebElement ethernetLine;

    @FindBy(xpath = "//a[contains(text(),'Wave')]")
    public WebElement wave;

    @FindBy(xpath = "//a[contains(text(),'Ethernet Spoke')]")
    public WebElement spoke;

    @FindBy(id = "hubType")
    public WebElement hubType;

    @FindBy(id = "hubListForSpoke")
    public WebElement hubListForSpoke;

    @FindBy(xpath = "(//select)[3]/option[contains(text(),'Hub1')]")
    public WebElement hub1;

    @FindBy(id = "hubReferenceID")
    public WebElement hubReferenceField;

    @FindBy(id = "siteAddressHubExisting")
    public WebElement existingHubAddress;

    @FindBy(xpath = "//input[@id='siteAddressAEnd']")
    public WebElement addressEntry;

    @FindBy(xpath = "//input[@id='siteAddressBEnd']")
    public WebElement addressEntryBend;

    @FindBy(xpath = "//td[contains(text(),'Check Connectivity')]")
    public WebElement checkConnectivity;

    @FindBy(xpath = "(//span[contains(text(),'Site Details')])[2]")
    public WebElement siteDetails;

    @FindBy(xpath = "//select[contains(@id,'serviceBandwidth')]")
    public WebElement serviceDropDown;

    @FindBy(xpath = "//select[@id='interfaceWavePage2']")
    public WebElement waveInterface;

    @FindBy(xpath = "//select[contains(@id,'resiliencyServiceLevel')]")
    public WebElement resiliencyDrop;

    @FindBy(xpath = "(//span[contains(text(),'Features')])[1]")
    public WebElement featureTab;

    @FindBy(xpath = "//input[@name='fasttrackAddon']")
    public WebElement fastTrackBox;

    @FindBy(xpath = "//input[@name='performanceReportingAddon']")
    public WebElement performanceReporting;

    @FindBy(xpath = "//input[@name='diversity']")
    public WebElement diversityBox;

    @FindBy(xpath = "//select[@id='diversityTypeAEnd']")
    public WebElement diversityAend;

    @FindBy(xpath = "//select[@id='diversityTypeBEnd']")
    public WebElement diversityBend;

    @FindBy(xpath = "//input[@id='outsideBusinessHoursInstallationAEnd_true']")
    public WebElement obhA;

    @FindBy(xpath = "//input[@id='outsideBusinessHoursInstallationBEnd_true']")
    public WebElement obhB;

    @FindBy(id = "dualEntryAEnd_true")
    public WebElement dualEA;

    @FindBy(id = "dualEntryBEnd_true")
    public WebElement dualBA;

    @FindBy(id = "longLiningAEnd_true")
    public WebElement longLineingA;

    @FindBy(id = "longLiningBEnd_true")
    public WebElement longLineingB;

    @FindBy(id = "internalCablingAEnd_true")
    public WebElement internalCablingA;

    @FindBy(id = "internalCablingBEnd_true")
    public WebElement internalCablingB;

    @FindBy(id = "selectFloor_AEndPrim")
    public WebElement internalCablingDropA;

    @FindBy(id = "selectFloor_BEndPrim")
    public WebElement internalCablingDropB;

    @FindBy(id = "linkAggregationLAGAEnd_true")
    public WebElement linkAggregA;

    @FindBy(id = "linkAggregationLAGBEnd_true")
    public WebElement linkAggregB;

    @FindBy(id = "diversity_true")
    public WebElement diversity;

    @FindBy(id = "diversityTypeAEnd")
    public WebElement diversityADrop;

    @FindBy(id = "diversityTypeBEnd")
    public WebElement diversityBDrop;

    @FindBy(id = "classOfServiceAddon_true")
    public WebElement classOfService;

    @FindBy(id = "proactiveManagementAddon_true")
    public WebElement proActive;

    @FindBy(xpath = "((//div[@class='buttons']/table)[3]/tbody/tr/td)[2]/div/a")
    public WebElement saveToQuote;

    @FindBy(xpath = "(//a[contains(text(),'Manager')])[1]")
    public WebElement quoteManager;

    @FindBy(id = "dualCustomerPowerSourceAEnd_true")
    public WebElement dualCustPowerA;

    @FindBy(id = "dualCustomerPowerSourceBEnd_true")
    public WebElement dualCustPowerB;

    @FindBy(xpath = "\"//a[contains(text(),\" + \"'\" + QT-20180508-031897-01 + \"')]\"")
    public WebElement quoteClick;

    public void addProductPageProduct(String productName, String arg0, String s, String s1, String s2, String poa) {
        switch (productName) {
            case "hub":
                Actions actions = new Actions(driver);
                actions.moveToElement(ethernet).moveToElement(ethernetHub).click().perform();
                ObjectRepo.waitForLoad(5000);
                addressEntry.sendKeys(arg0);
                ObjectRepo.waitForLoad(10000);
                addressEntry.sendKeys(Keys.DOWN);
                addressEntry.sendKeys(Keys.ENTER);
                ObjectRepo.waitForLoad(10000);
                JavascriptExecutor executor = (JavascriptExecutor)driver;
                executor.executeScript("arguments[0].click()", checkConnectivity);
                ObjectRepo.waitForLoad(10000);
                executor.executeScript("arguments[0].click()", siteDetails);
                ObjectRepo.waitForLoad(5000);
                Select dropdown = new Select(serviceDropDown);
                dropdown.selectByValue(s1);
                ObjectRepo.waitForLoad(5000);
                Select dropdownRes = new Select(resiliencyDrop);
                dropdownRes.selectByValue(s2);
                ObjectRepo.waitForLoad(3000);
                addAllHubFeatures(poa);
                executor.executeScript("arguments[0].click()", saveToQuote);
                break;
            case "line":
                Actions actions1 = new Actions(driver);
                actions1.moveToElement(ethernet).moveToElement(ethernetLine).click().perform();
                ObjectRepo.waitForLoad(2000);
                addressEntry.sendKeys(arg0);
                ObjectRepo.waitForLoad(10000);
                addressEntry.sendKeys(Keys.DOWN);
                addressEntry.sendKeys(Keys.ENTER);
                ObjectRepo.waitForLoad(10000);
                //addressEntryBend.click();
                addressEntryBend.sendKeys(s);
                addressEntryBend.sendKeys(Keys.DOWN);
                addressEntryBend.sendKeys(Keys.ENTER);
                ObjectRepo.waitForLoad(15000);
                JavascriptExecutor executor1 = (JavascriptExecutor)driver;
                //checkConnectivity.click();
                executor1.executeScript("arguments[0].click()", checkConnectivity);
                ObjectRepo.waitForLoad(10000);
                executor1.executeScript("arguments[0].click()", siteDetails);
                ObjectRepo.waitForLoad(5000);
                Select dropdown1 = new Select(serviceDropDown);
                dropdown1.selectByValue(s1);
                ObjectRepo.waitForLoad(5000);
                Select dropdownRes1 = new Select(resiliencyDrop);
                dropdownRes1.selectByValue(s2);
                ObjectRepo.waitForLoad(3000);
                executor1.executeScript("arguments[0].click()", featureTab);
                ObjectRepo.waitForLoad(3000);
                addFeatures(poa);
                ObjectRepo.waitForLoad(3000);
                executor1.executeScript("arguments[0].click()", saveToQuote);
                break;
            case "wave":
                Actions actions2 = new Actions(driver);
                actions2.moveToElement(opticalWave).moveToElement(wave).click().perform();
                ObjectRepo.waitForLoad(2000);
                addressEntry.sendKeys(arg0);
                ObjectRepo.waitForLoad(10000);
                addressEntry.sendKeys(Keys.DOWN);
                addressEntry.sendKeys(Keys.ENTER);
                ObjectRepo.waitForLoad(10000);
                addressEntryBend.click();
                addressEntryBend.sendKeys(s);
                ObjectRepo.waitForLoad(10000);
                addressEntryBend.sendKeys(Keys.DOWN);
                addressEntryBend.sendKeys(Keys.ENTER);
                ObjectRepo.waitForLoad(10000);
                JavascriptExecutor executor3 = (JavascriptExecutor)driver;
                executor3.executeScript("arguments[0].click()", checkConnectivity);
                ObjectRepo.waitForLoad(10000);
                executor3.executeScript("arguments[0].click()", siteDetails);
                ObjectRepo.waitForLoad(5000);
                Select dropdown2 = new Select(waveInterface);
                waveInterface.click();
                dropdown2.selectByValue("Ethernet");
                ObjectRepo.waitForLoad(3000);
                Select dropdown3 = new Select(serviceDropDown);
                dropdown3.selectByValue(s1);
                Select dropdown4 = new Select(resiliencyDrop);
                dropdown4.selectByValue(s2);
                ObjectRepo.waitForLoad(3000);
                executor3.executeScript("arguments[0].click()", featureTab);
                ObjectRepo.waitForLoad(3000);
                addFeatures(poa);
                ObjectRepo.waitForLoad(3000);
                executor3.executeScript("arguments[0].click()", saveToQuote);
                break;
            case "spoke":
                Actions actions3 = new Actions(driver);
                actions3.moveToElement(ethernet).moveToElement(spoke).click().perform();
                ObjectRepo.waitForLoad(2000);
                Select hubDrop = new Select(hubListForSpoke);
                try {
                    hubDrop.selectByValue("Hub1");
                    ObjectRepo.waitForLoad(3000);
                    addressEntry.sendKeys(arg0);
                    ObjectRepo.waitForLoad(3000);
                    addressEntry.sendKeys(Keys.DOWN);
                    addressEntry.sendKeys(Keys.ENTER);
                    ObjectRepo.waitForLoad(5000);
                    JavascriptExecutor executor5 = (JavascriptExecutor)driver;
                    executor5.executeScript("arguments[0].click()", checkConnectivity);
                    ObjectRepo.waitForLoad(3000);
                    executor5.executeScript("arguments[0].click()", siteDetails);
                    ObjectRepo.waitForLoad(5000);
                    Select serviceDropDownList = new Select(serviceDropDown);
                    serviceDropDownList.selectByValue(s1);
                    ObjectRepo.waitForLoad(3000);
                    Select resDrop = new Select(resiliencyDrop);
                    resDrop.selectByValue(s2);
                    ObjectRepo.waitForLoad(5000);
                    addAllSpokeFeatures(poa);
                    executor5.executeScript("arguments[0].click()", saveToQuote);
                }catch (NoSuchElementException e){
                    Select hubTypeDrop = new Select(hubType);
                    hubTypeDrop.selectByValue("Existing");
                    ObjectRepo.waitForLoad(3000);
                    hubReferenceField.sendKeys("1234");
                    ObjectRepo.waitForLoad(2000);
                    existingHubAddress.sendKeys(arg0);
                    ObjectRepo.waitForLoad(3000);
                    existingHubAddress.sendKeys(Keys.DOWN);
                    existingHubAddress.sendKeys(Keys.ENTER);
                    ObjectRepo.waitForLoad(3000);
                    addressEntry.sendKeys(arg0);
                    ObjectRepo.waitForLoad(3000);
                    addressEntry.sendKeys(Keys.DOWN);
                    addressEntry.sendKeys(Keys.ENTER);
                    JavascriptExecutor executor4 = (JavascriptExecutor)driver;
                    executor4.executeScript("arguments[0].click()", checkConnectivity);
                    ObjectRepo.waitForLoad(10000);
                    executor4.executeScript("arguments[0].click()", siteDetails);
                    ObjectRepo.waitForLoad(5000);
                    Select serviceDropDownList = new Select(serviceDropDown);
                    serviceDropDownList.selectByValue(s1);
                    ObjectRepo.waitForLoad(3000);
                    Select resDrop = new Select(resiliencyDrop);
                    resDrop.selectByValue(s2);
                    ObjectRepo.waitForLoad(5000);
                    addAllSpokeFeatures(poa);
                    executor4.executeScript("arguments[0].click()", saveToQuote);
                }
                break;
        }
    }

    public void clickXpathHelper(String xpathValue){
        driver.findElement(By.xpath(xpathValue)).click();
    }

    public void addFeatures(String poa){
        obhA.click();
        ObjectRepo.waitForLoad(5000);
        obhB.click();
        ObjectRepo.waitForLoad(5000);
        if(poa == "yes") {
            dualBA.click();
            ObjectRepo.waitForLoad(5000);
            dualEA.click();
            ObjectRepo.waitForLoad(5000);
            longLineingA.click();
            ObjectRepo.waitForLoad(5000);
            longLineingB.click();
        }
        ObjectRepo.waitForLoad(5000);
        internalCablingA.click();
        ObjectRepo.waitForLoad(5000);
        internalCablingB.click();
        ObjectRepo.waitForLoad(5000);
        linkAggregA.click();
        ObjectRepo.waitForLoad(5000);
        linkAggregB.click();
        ObjectRepo.waitForLoad(5000);
        diversity.click();
        ObjectRepo.waitForLoad(5000);
        classOfService.click();
        ObjectRepo.waitForLoad(5000);
        performanceReporting.click();
        ObjectRepo.waitForLoad(5000);
        proActive.click();
        ObjectRepo.waitForLoad(5000);

        Select dropFloorA = new Select(internalCablingDropA);
        dropFloorA.selectByValue("1");
        ObjectRepo.waitForLoad(5000);
        Select dropFloorB = new Select(internalCablingDropB);
        ObjectRepo.waitForLoad(5000);
        dropFloorB.selectByValue("1");
        ObjectRepo.waitForLoad(5000);

        Select diversityDropBy = new Select(diversityADrop);
        diversityDropBy.selectByValue("Type 1");
        ObjectRepo.waitForLoad(5000);
        Select diversityDropByB = new Select(diversityBDrop);
        diversityDropByB.selectByValue("Type 1");
        ObjectRepo.waitForLoad(5000);

        dualCustPowerA.click();
        ObjectRepo.waitForLoad(5000);
        dualCustPowerB.click();
        ObjectRepo.waitForLoad(5000);
    }

    public void addAllSpokeFeatures(String poa){
        JavascriptExecutor executor6 = (JavascriptExecutor)driver;
        executor6.executeScript("arguments[0].click()", featureTab);
        ObjectRepo.waitForLoad(3000);
        obhA.click();
        ObjectRepo.waitForLoad(3000);
        if(poa == "yes") {
            dualEA.click();
            ObjectRepo.waitForLoad(3000);
            longLineingA.click();
            ObjectRepo.waitForLoad(3000);
        }
        internalCablingA.click();
        ObjectRepo.waitForLoad(3000);
        linkAggregA.click();
        ObjectRepo.waitForLoad(3000);
        diversity.click();
        ObjectRepo.waitForLoad(3000);
        performanceReporting.click();
        ObjectRepo.waitForLoad(3000);
        proActive.click();
        ObjectRepo.waitForLoad(3000);
        fastTrackBox.click();
        ObjectRepo.waitForLoad(3000);
        Select icablingDrop = new Select(internalCablingDropA);
        icablingDrop.selectByValue("1");
        ObjectRepo.waitForLoad(3000);
        Select iDiversityDrop = new Select(diversityADrop);
        ObjectRepo.waitForLoad(3000);
        iDiversityDrop.selectByValue("Type 1");
        ObjectRepo.waitForLoad(3000);
        dualCustPowerA.click();
    }

    public void addAllHubFeatures(String poa){
        JavascriptExecutor executor7 = (JavascriptExecutor)driver;
        executor7.executeScript("arguments[0].click()", featureTab);
        ObjectRepo.waitForLoad(3000);
        obhA.click();
        ObjectRepo.waitForLoad(3000);
        if(poa=="yes") {
            dualEA.click();
            ObjectRepo.waitForLoad(3000);
            longLineingA.click();
            ObjectRepo.waitForLoad(3000);
        }
        internalCablingA.click();
        Select iCablingDropHub = new Select(internalCablingDropA);
        iCablingDropHub.selectByValue("1");
        ObjectRepo.waitForLoad(3000);
        linkAggregA.click();
        ObjectRepo.waitForLoad(3000);
        fastTrackBox.click();
        ObjectRepo.waitForLoad(3000);
    }

    public void addAllWaveFeatures(){
        JavascriptExecutor executor8 = (JavascriptExecutor)driver;
        executor8.executeScript("arguments[0].click()", featureTab);
        ObjectRepo.waitForLoad(3000);
        obhA.click();
        ObjectRepo.waitForLoad(3000);
        dualEA.click();
        ObjectRepo.waitForLoad(3000);
        longLineingA.click();
        ObjectRepo.waitForLoad(3000);
        internalCablingA.click();
        ObjectRepo.waitForLoad(3000);
        linkAggregA.click();
        ObjectRepo.waitForLoad(3000);
        fastTrackBox.click();
        ObjectRepo.waitForLoad(3000);
        proActive.click();
        ObjectRepo.waitForLoad(3000);
        Select iCablingDropper = new Select(internalCablingDropA);
        iCablingDropper.selectByValue("1");
        ObjectRepo.waitForLoad(3000);
        Select iCablingDropperB = new Select(internalCablingDropB);
        iCablingDropperB.selectByValue("1");
        ObjectRepo.waitForLoad(3000);
    }

}
