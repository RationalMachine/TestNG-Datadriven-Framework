import PageObjects.LoginPage;
import PageObjects.MainPage;
import org.testng.annotations.Test;
import util.InitWebDriver;
import util.ObjectRepo;
import util.PropertyReader;

//import util.InitWebDriver.Driver;

public class ApprovalMessageTest extends InitWebDriver {

    @Test
    public void approvalMessageTest(){

        LoginPage lpage;
        lpage = new LoginPage(Driver);
        ObjectRepo.waitForLoad(10000);
        lpage.username.sendKeys(PropertyReader.fileRead("ProjectData.properties","Username"));
        lpage.password.sendKeys(PropertyReader.fileRead("ProjectData.properties","Password"));
        ObjectRepo.waitForLoad(10000);
        MainPage mainPage = lpage.clickLogin();
        mainPage.getCPQPage("AdityaNew", "New");
        //CommercePage commercePage = mainPage.getCPQPage("Account Name");
        //commercePage.addProductBtn.click();
    }
}
