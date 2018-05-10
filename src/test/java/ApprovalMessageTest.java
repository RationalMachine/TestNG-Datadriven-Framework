import PageObjects.CommercePage;
import PageObjects.LoginPage;
import PageObjects.MainPage;
import PageObjects.ProductPage;
import dataprovider.ExcelDataProviderObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.InitWebDriver;
import util.ObjectRepo;
import util.PropertyReader;

//import util.InitWebDriver.Driver;

public class ApprovalMessageTest extends InitWebDriver {

    @Test(dataProviderClass = dataprovider.ExcelDataProvider.class,dataProvider="getProductData")
    public void approvalMessageTest(ExcelDataProviderObject DPObj){

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
                DPObj.DataArray.get(6)
                );
        ObjectRepo.waitForLoad(3000);
        commercePage.quoteTab.click();
        ObjectRepo.waitForLoad(3000);
        Select dropdownRes = new Select(commercePage.quoteTypeDropDown);
        dropdownRes.selectByValue(DPObj.DataArray.get(6));
        ObjectRepo.waitForLoad(3000);
        commercePage.nrcDiscountEntry.clear();
        ObjectRepo.waitForLoad(3000);
        commercePage.nrcDiscountEntry.sendKeys(DPObj.DataArray.get(5));
        commercePage.mrcDiscountEntry.clear();
        ObjectRepo.waitForLoad(2000);
        commercePage.mrcDiscountEntry.sendKeys(DPObj.DataArray.get(5));
        ObjectRepo.waitForLoad(3000);
        //commercePage.mrcDiscountEntry.clear();
        //commercePage.mrcDiscountEntry.sendKeys(DPObj.DataArray.get(5));
        //ObjectRepo.waitForLoad(3000);
        JavascriptExecutor executor = (JavascriptExecutor)Driver;
        executor.executeScript("arguments[0].click()", commercePage.calculateDiscount);
        ObjectRepo.waitForLoad(12000);
        commercePage.approvals.click();
        ObjectRepo.waitForLoad(8000);
        int discount = Integer.parseInt(DPObj.DataArray.get(5));
        String innerTextItem = commercePage.getQuoteLineItem();
        System.out.println(discount + innerTextItem);
        if(discount<5){
            String approvalMessage1 = commercePage.getApprovalMsgText();
            Assert.assertEquals(approvalMessage1,
                    "This quote only requires Self-Approval. " +
                            "Please click on \"Submit to Approval\" button " +
                            "below to proceed.");
        }else
        if(discount>5 && discount<10){
            String approvalMessage2 = commercePage.getApprovalMsgText();
            Assert.assertEquals(approvalMessage2,"VP Sales - 2 approval required from " +
                    "Faraz anwar as total TCV discount  given on quote is in between 5.0% and 9.99%.");
        }else
            if(discount>10 && discount<15){
            String approvalMessage3 = commercePage.getApprovalMsgText();
            Assert.assertEquals(approvalMessage3, "VP Sales - 1 approval required from Paula Cogan as total " +
                    "TCV discount  given on quote is in between 10.0% and 15.0%.");
            }else
                if(discount>15){
                String approvalMessage4 = commercePage.getApprovalMsgText();
                Assert.assertEquals(approvalMessage4, "Deal Pricing Review will be triggered as " +
                        "TCV discount given on Base Price under line # " +
                        commercePage.getQuoteLineItem() +
                        " is greater than 15.0%.");
                }
    }
}
