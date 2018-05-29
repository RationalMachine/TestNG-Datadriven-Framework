import PageObjects.LoginPage;
import PageObjects.MainPage;
import dataprovider.ExcelDataProviderObject;
import org.testng.annotations.Test;
import util.InitWebDriver;
import util.ObjectRepo;
import util.PropertyReader;

public class AccountCreationC4CScript extends InitWebDriver{

    @Test(dataProviderClass = dataprovider.ExcelDataProvider.class,dataProvider="accountCreation")
    public void accountCreationC4C(ExcelDataProviderObject DPObj){
        LoginPage lpage;
        lpage = new LoginPage(Driver);
        ObjectRepo.waitForLoad(10000);
        lpage.username.sendKeys(PropertyReader.fileRead("ProjectData.properties","Username"));
        lpage.password.sendKeys(PropertyReader.fileRead("ProjectData.properties","Password"));
        ObjectRepo.waitForLoad(5000);
        MainPage mainPage = lpage.clickLogin();
        ObjectRepo.waitForLoad(3000);
        mainPage.accountCreation(DPObj.DataArray.get(0),
                DPObj.DataArray.get(1),
                DPObj.DataArray.get(2),
                DPObj.DataArray.get(3),
                DPObj.DataArray.get(4),
                DPObj.DataArray.get(5),
                DPObj.DataArray.get(6),
                DPObj.DataArray.get(7));
        ObjectRepo.waitForLoad(30000);
    }
}
