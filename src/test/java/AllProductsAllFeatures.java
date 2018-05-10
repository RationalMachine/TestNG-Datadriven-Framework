import PageObjects.CommercePage;
import PageObjects.LoginPage;
import PageObjects.MainPage;
import PageObjects.ProductPage;
import dataprovider.ExcelDataProvider;
import dataprovider.ExcelDataProviderObject;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import util.InitWebDriver;
import util.ObjectRepo;
import util.PropertyReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;

public class AllProductsAllFeatures extends InitWebDriver{

    @Test(dataProviderClass = dataprovider.ExcelDataProvider.class,dataProvider="getProductData")
    public void allProductsAddingAndQuoteGeneration(ExcelDataProviderObject DPObj) throws IOException {
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
        productPage.addProductPageProduct(DPObj.DataArray.get(0), DPObj.DataArray.get(1), DPObj.DataArray.get(2),
                DPObj.DataArray.get(3),
                DPObj.DataArray.get(4),
                DPObj.DataArray.get(6));
        ObjectRepo.waitForLoad(3000);
        JavascriptExecutor executor = (JavascriptExecutor)Driver;
        executor.executeScript("arguments[0].click()", commercePage.saveButton);
        ObjectRepo.waitForLoad(3000);
        String content = commercePage.getHTMLtext(commercePage.quoteID);
        String path = System.getProperty("user.dir") + "\\quoteID.txt";
        File file = new File(path);
        FileWriter fw = new FileWriter(file,true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(content);
        bw.close();
    }
}
