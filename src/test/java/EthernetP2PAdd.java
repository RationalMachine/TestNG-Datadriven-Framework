import PageObjects.CommercePage;
import PageObjects.LoginPage;
import PageObjects.MainPage;
import PageObjects.ProductPage;
import dataprovider.ExcelDataProviderObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import util.ObjectRepo;
import util.PropertyReader;
import util.InitWebDriver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EthernetP2PAdd extends InitWebDriver{

    @Test(dataProviderClass = dataprovider.ExcelDataProvider.class,dataProvider="getProductData")
    public void ethernetAdditionScript(ExcelDataProviderObject DPObj) throws IOException {
        LoginPage lpage;
        lpage = new LoginPage(Driver);
        ObjectRepo.waitForLoad(10000);
        lpage.username.sendKeys(PropertyReader.fileRead("ProjectData.properties","Username"));
        lpage.password.sendKeys(PropertyReader.fileRead("ProjectData.properties","Password"));
        ObjectRepo.waitForLoad(10000);
        MainPage mainPage = lpage.clickLogin();
        CommercePage commercePage = mainPage.getCPQPage("AdityaNew", "New");
        ObjectRepo.waitForLoad(5000);

        String path = System.getProperty("user.dir") + "\\text.txt";

        for(int i=1; i<51; i++) {
            long startTime = System.currentTimeMillis();
            ProductPage productPage = commercePage.addProduct();
            System.out.println(DPObj.DataArray.get(0));
            productPage.addProductPageProduct(DPObj.DataArray.get(0).replaceAll("\\s", ""), DPObj.DataArray.get(1).replaceAll("\\s", ""), DPObj.DataArray.get(2), DPObj.DataArray.get(3), DPObj.DataArray.get(4));
            long endTime = System.currentTimeMillis();
            long totalLong = endTime - startTime;
            String total = String.valueOf(totalLong);
            String deviceIDValue = commercePage.getHTMLtext(commercePage.quoteID);
            String content = deviceIDValue + "-" + total + "\n";
            File file = new File(path);
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
            ObjectRepo.waitForLoad(3000);
            commercePage.saveButton.click();
            ObjectRepo.waitForLoad(3000);
        }
    }
}
