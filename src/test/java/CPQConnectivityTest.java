import PageObjects.CommercePage;
import PageObjects.LoginPage;
import PageObjects.MainPage;
import PageObjects.ProductPage;
import org.junit.Assert;
import org.testng.annotations.Test;
import util.InitWebDriver;
import util.ObjectRepo;
import util.PropertyReader;

public class CPQConnectivityTest extends InitWebDriver {

    @Test
    public void commercePageValidations(){
        LoginPage lpage;
        lpage = new LoginPage(Driver);
        ObjectRepo.waitForLoad(10000);
        lpage.username.sendKeys(PropertyReader.fileRead("ProjectData.properties","Username"));
        lpage.password.sendKeys(PropertyReader.fileRead("ProjectData.properties","Password"));
        ObjectRepo.waitForLoad(10000);
        MainPage mainPage = lpage.clickLogin();
        CommercePage commercePage = mainPage.getCPQPage("AdityaNew", "New");
        ObjectRepo.waitForLoad(10000);
        Assert.assertTrue(commercePage.quoteID.isDisplayed());
    }
}
