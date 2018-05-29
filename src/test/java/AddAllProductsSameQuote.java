import PageObjects.CommercePage;
import PageObjects.LoginPage;
import PageObjects.MainPage;
import PageObjects.ProductPage;
import dataprovider.ExcelDataProviderObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import util.InitWebDriver;
import util.ObjectRepo;
import util.PropertyReader;

public class AddAllProductsSameQuote extends InitWebDriver{

    private String quoteID;

    @Test
    public void addAllProductsSameQuote(){
        LoginPage lpage;
        lpage = new LoginPage(Driver);
        ObjectRepo.waitForLoad(10000);
        lpage.username.sendKeys(PropertyReader.fileRead("ProjectData.properties","Username"));
        lpage.password.sendKeys(PropertyReader.fileRead("ProjectData.properties","Password"));
        ObjectRepo.waitForLoad(10000);
        MainPage mainPage = lpage.clickLogin();
        CommercePage commercePage = mainPage.getCPQPage("AdityaNew", "New");
        ObjectRepo.waitForLoad(5000);
        ProductPage productPage = commercePage.addProduct();
        //Add Hub
        Actions actions = new Actions(Driver);
        actions.moveToElement(productPage.ethernet).moveToElement(productPage.ethernetHub).click().perform();
        ObjectRepo.waitForLoad(5000);
        productPage.addressEntry.sendKeys("3, Julius-Tandler-Platz, Wien, Austria, Wien");
        ObjectRepo.waitForLoad(10000);
        productPage.addressEntry.sendKeys(Keys.DOWN);
        productPage.addressEntry.sendKeys(Keys.ENTER);
        ObjectRepo.waitForLoad(10000);
        JavascriptExecutor executor = (JavascriptExecutor)Driver;
        executor.executeScript("arguments[0].click()", productPage.checkConnectivity);
        ObjectRepo.waitForLoad(10000);
        executor.executeScript("arguments[0].click()", productPage.siteDetails);
        ObjectRepo.waitForLoad(5000);
        Select dropdown = new Select(productPage.serviceDropDown);
        dropdown.selectByValue("1 Gbps");
        ObjectRepo.waitForLoad(5000);
        Select dropdownRes = new Select(productPage.resiliencyDrop);
        dropdownRes.selectByValue("Protected");
        ObjectRepo.waitForLoad(3000);
        productPage.addAllHubFeatures("no");
        executor.executeScript("arguments[0].click()", productPage.saveToQuote);
        ObjectRepo.waitForLoad(3000);
        commercePage.saveButton.click();
        this.quoteID = commercePage.getHTMLtext(commercePage.quoteID);
        System.out.println(quoteID);
        ObjectRepo.waitForLoad(3000);
    }

    @Test(dependsOnMethods = "addAllProductsSameQuote",dataProviderClass = dataprovider.ExcelDataProvider.class,dataProvider="getProductData")
    public void addRestOfTheProducts(ExcelDataProviderObject DPObj){
        LoginPage lpage;
        lpage = new LoginPage(Driver);
        ObjectRepo.waitForLoad(10000);
        lpage.username.sendKeys(PropertyReader.fileRead("ProjectData.properties","Username"));
        lpage.password.sendKeys(PropertyReader.fileRead("ProjectData.properties","Password"));
        ObjectRepo.waitForLoad(10000);
        MainPage mainPage = lpage.clickLogin();
        ObjectRepo.waitForLoad(3000);
        CommercePage commercePage = mainPage.openQuote(quoteID);
        ObjectRepo.waitForLoad(10000);
        ProductPage productPage = commercePage.addProduct();
        productPage.addProductPageProduct(DPObj.DataArray.get(0), DPObj.DataArray.get(1), DPObj.DataArray.get(2),
                DPObj.DataArray.get(3),
                DPObj.DataArray.get(4),
                DPObj.DataArray.get(7)
        );
        ObjectRepo.waitForLoad(3000);
        commercePage.saveButton.click();
    }
}
