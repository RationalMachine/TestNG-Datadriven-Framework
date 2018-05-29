import PageObjects.CommercePage;
import PageObjects.LoginPage;
import PageObjects.MainPage;
import dataprovider.ExcelDataProviderObject;
import org.junit.Assert;
import org.testng.annotations.Test;
import util.InitWebDriver;
import util.ObjectRepo;
import util.PropertyReader;

import java.util.Arrays;
import java.util.List;


public class PriceLookupQuoteTest extends InitWebDriver {

    @Test(dataProviderClass = dataprovider.ExcelDataProvider.class,dataProvider="priceLookUpQuote")
    public void priceLookUpQuote(ExcelDataProviderObject DPObj){
        LoginPage lpage;
        lpage = new LoginPage(Driver);
        ObjectRepo.waitForLoad(10000);
        lpage.username.sendKeys(PropertyReader.fileRead("ProjectData.properties","Username"));
        lpage.password.sendKeys(PropertyReader.fileRead("ProjectData.properties","Password"));
        ObjectRepo.waitForLoad(5000);
        MainPage mainPage = lpage.clickLogin();
        ObjectRepo.waitForLoad(3000);
        CommercePage commercePage =
                mainPage.navigateToPriceLookupQuote(DPObj.DataArray.get(0), DPObj.DataArray.get(1),
                DPObj.DataArray.get(2),
                DPObj.DataArray.get(3), DPObj.DataArray.get(4), DPObj.DataArray.get(5));
        ObjectRepo.waitForLoad(17000);
        Assert.assertTrue(commercePage.quoteID.isDisplayed());
        Assert.assertTrue(commercePage.defaultQuoteType());
        Assert.assertEquals(commercePage.getHTMLtext(commercePage.quoteStatus),"Created");
    }
}
