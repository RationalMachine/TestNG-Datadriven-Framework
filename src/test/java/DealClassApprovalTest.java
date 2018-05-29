import PageObjects.*;
import dataprovider.ExcelDataProviderObject;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import util.InitWebDriver;
import util.ObjectRepo;
import util.PropertyReader;

/************
 *@author Aditya
 *@apiNote This test case will validate if the correct approval message
 * has been triggered by the given discount. The test case reads
 * data from an Excel Sheet using the ExcelDataProvider class.
 * The ExcelDataProvider class returns an Array Object with its elements
 * as the data in the Excel cells.
 */

public class DealClassApprovalTest extends InitWebDriver{

    @Test(dataProviderClass = dataprovider.ExcelDataProvider.class,dataProvider="getDealPriceData")
    public void dealClassApprovalFlow(ExcelDataProviderObject DPObj){
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
        System.out.println(DPObj.DataArray.get(0));
        productPage.addProductPageProduct(DPObj.DataArray.get(0).replaceAll("\\s",""), DPObj.DataArray.get(1).replaceAll("\\s",""),
                DPObj.DataArray.get(2),
                DPObj.DataArray.get(3),
                DPObj.DataArray.get(4),
                DPObj.DataArray.get(6));
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
        ObjectRepo.waitForLoad(2000);

        String tcvValue = commercePage.getTCVValue();
        String acvValue = commercePage.getACVValue();
        String arrValue = commercePage.getARRValue();
        String igmadVal = DPObj.DataArray.get(8);

        commercePage.modifyCSVwithNewPrices(acvValue,tcvValue,arrValue,igmadVal);

        String quoteIDvalue = commercePage.getHTMLtext(commercePage.quoteID);

        commercePage.approvals.click();
        ObjectRepo.waitForLoad(3000);
        commercePage.submitToApproval.click();
        ObjectRepo.waitForLoad(3000);
        commercePage.submitPopupOk.click();
        ObjectRepo.waitForLoad(10000);
        AdminPage adminPage = commercePage.clickSettings();
        ObjectRepo.waitForLoad(3000);
        adminPage.usersButton.click();
        ObjectRepo.waitForLoad(3000);
        adminPage.clickOnUser.click();
        ObjectRepo.waitForLoad(3000);
        adminPage.groups.click();
        ObjectRepo.waitForLoad(3000);
        String optionValueTxt = adminPage.getInnerHtml(adminPage.optionValue);
        System.out.println(optionValueTxt);
        if(!"Deal&nbsp;Pricing&nbsp;Team&nbsp;Member".equalsIgnoreCase(optionValueTxt)){
            Select drop = new Select(adminPage.allGroupsTable);
            drop.selectByValue("Deal&nbsp;Pricing&nbsp;Team&nbsp;Member");
            adminPage.pushGroupFromTable.click();
            adminPage.applyButton.click();
            adminPage.backButton.click();
        }else if("Deal&nbsp;Pricing&nbsp;Team&nbsp;Member".equalsIgnoreCase(optionValueTxt)){
            adminPage.backButton.click();
        }

        adminPage.userProxyLogin.click();
        ObjectRepo.waitForLoad(3000);
        productPage.quoteManager.click();
        ObjectRepo.waitForLoad(3000);
        //int discount = Integer.parseInt(DPObj.DataArray.get(5));
        int IGMAD = Integer.parseInt(DPObj.DataArray.get(8));

        String xpathValue = "//a[contains(text()," + "'" + quoteIDvalue + "')]";

        ObjectRepo.waitForLoad(3000);
        productPage.clickXpathHelper(xpathValue);
        ObjectRepo.waitForLoad(3000);

        commercePage.pAndLDealPrice.click();
        ObjectRepo.waitForLoad(3000);

        Select dropDeal = new Select(commercePage.selectDealPriceUserDropDown);
        dropDeal.selectByValue("Sandesh Magdum");

        ObjectRepo.waitForLoad(3000);
        commercePage.assignQuoteDealPriceUser.click();
        ObjectRepo.waitForLoad(3000);

        commercePage.uploadCSV();
        ObjectRepo.waitForLoad(3000);

        commercePage.saveButton.click();
        ObjectRepo.waitForLoad(3000);

        executor.executeScript("arguments[0].click()", commercePage.approvals);

        String[] arr = commercePage.getApprovalMsgDealPricing();

        ObjectRepo.waitForLoad(3000);

        if(IGMAD<25){
            Assert.assertEquals(arr[0],"VP Sales approval required from " +
                    "Mohit Khetrapal as IGMAD% given on quote is less than 25.0%");
            Assert.assertEquals(arr[1],"VP Finance approval required from " +
                    "Ben Thomas as IGMAD% given on quote is less than 25.0%.");
            commercePage.submitDealApproval.click();
        }

        if(IGMAD>=25 && IGMAD<30){
            Assert.assertEquals(arr[0],"VP Sales - 1 approval required from " +
                    "Mohit Khetrapal as IGMAD% given on quote is in between 24.99% and 30.0%");
            Assert.assertEquals(arr[1],"VP Finance - 1 approval required from " +
                    "Ben Thomas as IGMAD% given on quote is in between 24.99% and 30.0%.");
            commercePage.submitDealApproval.click();
        }

        //Assert.assertTrue(commercePage.approvals.isDisplayed());
    }
}
