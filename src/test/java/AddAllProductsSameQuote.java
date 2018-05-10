import PageObjects.CommercePage;
import PageObjects.LoginPage;
import PageObjects.MainPage;
import PageObjects.ProductPage;
import dataprovider.ExcelDataProviderObject;
import org.testng.annotations.Test;
import util.InitWebDriver;
import util.ObjectRepo;
import util.PropertyReader;

public class AddAllProductsSameQuote extends InitWebDriver{

    @Test(dataProviderClass = dataprovider.ExcelDataProvider.class,dataProvider="getProductData")
    public void addAllProductsSameQuote(ExcelDataProviderObject DPObj){

    }
}
