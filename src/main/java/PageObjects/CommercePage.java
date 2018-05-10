package PageObjects;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import dataprovider.ExcelDataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.ObjectRepo;

import java.io.FileWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.cert.X509Certificate;
import java.util.List;

public class CommercePage extends PageBase{

    private WebDriver driver;
    private String approvalMsgText;
    private String quoteLineItemID;

    public CommercePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(xpath = "//a[contains(@id,'add_product')]")
    public WebElement addProduct;

    @FindBy(xpath = "(//span[contains(text(),'Approval')])[1]")
    public WebElement approvals;

    @FindBy(xpath = "(//span[contains(text(),'Quote')])[1]")
    public WebElement quoteTab;

    @FindBy(xpath = "//span[@id='readonly_1_quoteID_t']")
    public WebElement quoteID;

    @FindBy(xpath = "(//select[contains(@name,'nRCDiscountType_t')])[1]")
    public WebElement quoteTypeDropDown;

    @FindBy(xpath = "//input[contains(@name,'discountNRCPerc_t')]")
    public WebElement nrcDiscountEntry;

    @FindBy(xpath = "//input[contains(@name,'discountMRCPerc_t')]")
    public WebElement mrcDiscountEntry;

    @FindBy(xpath = "(//a[contains(@id,'calculate_discount')])[1]")
    public WebElement calculateDiscount;

    @FindBy(xpath = "(//tr[@class='general_fields']/td)[2]/div")
    public WebElement approvalMsg;

    @FindBy(xpath = "//a[@name='submit_to_approval']")
    public WebElement submitToApproval;

    @FindBy(xpath = "(((//div[@id='reasons_popup']/div)[3]/table/tbody/tr)[1]/td)[2]/a")
    public WebElement submitPopupOk;

    //TO DO - create getter for innerHTML of this element(Deal Pricing Team Member [colttest1]-Deal Pricing)
    @FindBy(xpath = "//div[@id='attr_wrapper_1_approval_status_submit_t']/div/div/div/ul")
    public WebElement pendingApprovalMsg;

    @FindBy(xpath = "((//div[@class='nav-links'])[2]/a/img)[1]")
    public WebElement commerceSettingsButton;

    /*Below elements belong to Administrative Page
    *Make separate Page Object for that page
    * Copy the below elements to that page
     */



    //

    @FindBy(xpath = "")
    public WebElement quoteManager;

    @FindBy(xpath = "//a[contains(text(),'QT-20180507-031782-01')]")
    public WebElement quoteidDealPrice;

    @FindBy(xpath = "//span[contains(text(),'P& L')]")
    public WebElement pAndLDealPrice;

    @FindBy(xpath = "//select[@name='dealPricingTeamMembers_t']")
    public WebElement selectDealPriceUserDropDown;

    @FindBy(xpath = "//a[@id='assign_quote']")
    public WebElement assignQuoteDealPriceUser;

    @FindBy(xpath = "//a[@class='approve-reason show-loading-dialog']")
    public WebElement approveDealPriceUser;

    @FindBy(xpath = "//textarea[@id='dealBackgroundTextArea_t']")
    public WebElement backgroundTextAreaDealPriceUser;

    @FindBy(xpath = "//textarea[@id='competitorsTextArea_t']")
    public WebElement competitorsTextAreaDealUser;

    @FindBy(xpath = "//textarea[@id='pricePositioningTextArea_t']")
    public WebElement pricePositionTextAreaDealUser;

    @FindBy(xpath = "//textarea[@id='commercialRiskTextArea_t']")
    public WebElement commercialRiskTextAreaDealUser;

    @FindBy(xpath = "//textarea[@id='technicalSolutionTextArea_t']")
    public WebElement technicalSolutionTextAreaDealUser;

    @FindBy(xpath = "//a[@id='save']")
    public WebElement saveButton;

    @FindBy(id = "_file_uploadFile_t")
    public WebElement uploadCSVbutton;

    @FindBy(xpath = "//a[@id='upload_margin']")
    public WebElement uploadMarginButtonDealPriceUser;

    @FindBy(xpath = "(//tr[@class='line-item child-line-item line-item-show']/td)[3]/div/div/span")
    public WebElement quoteLineItem;

    @FindBy(xpath = "//input[@id='showColumnsMenu_t0']")
    public WebElement discountCheckbox;

    @FindBy(xpath = "((//tbody[@class='line-item-grid-body']/tr)[2]/td)[14]/div/div/div/input")
    public WebElement baseNRCdiscountBox;

    @FindBy(xpath = "((//tbody[@class='line-item-grid-body']/tr)[2]/td)[17]/div/div/div/input")
    public WebElement baseMRCdiscountBox;

    @FindBy(xpath = "((//tbody[@class='line-item-grid-body']/tr)[4]/td)[14]/div/div/div/input")
    public WebElement fastTrackDiscountBox;

    @FindBy(xpath = "//span[@id='readonly_1_aCVAnnualContractValue_t']")
    public WebElement acv;

    @FindBy(xpath = "//span[@id='readonly_1_tCVTotalContractValue_t']")
    public WebElement tcv;

    @FindBy(xpath = "//span[@id='readonly_1_aRRAnnualRecurringRevenue_t']")
    public WebElement arr;

    public ProductPage addProduct() {
        addProduct.click();
        return new ProductPage(driver);
    }

    protected void giveQuoteLevelDiscount(String s) {
    }

    public String getApprovalMsgText(){
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//tr[@class='general_fields']/td)[2]/div")));
        this.approvalMsgText = approvalMsg.getAttribute("innerHTML");
        return approvalMsgText;
    }

    public String getQuoteLineItem() {
        this.quoteLineItemID = quoteLineItem.getAttribute("innerHTML");
        return quoteLineItemID;
    }

    public String getTCVValue() {
        String tcvValue = tcv.getAttribute("innerHTML");
        String strippedValueTCV = stripper(tcvValue);
        return strippedValueTCV;
    }

    public String getACVValue() {
        String acvValue = acv.getAttribute("innerHTML");
        String strippedValueACV = stripper(acvValue);
        return strippedValueACV;
    }

    public String getARRValue() {
        String arrValue = arr.getAttribute("innerHTML");
        String strippedValueARR = stripper(arrValue);
        return strippedValueARR;
    }

    private String stripper(String Stripped){
        Stripped = Stripped.replace("€","");
        Stripped = Stripped.replace(".","");
        Stripped = Stripped.replace(",",".");
        return Stripped;
    }

    public void modifyCSVwithNewPrices(String acvRaw, String tcvRaw, String arrRaw ){
        try {
            String path = System.getProperty("user.dir") + "\\DealPricingCSVupload-New_Seema.csv";
            Reader reader = Files.newBufferedReader(Paths.get(path));
            CSVReader csvReader = new CSVReader(reader);
            List<String[]> csvBody = csvReader.readAll();
            csvBody.get(13)[1] = acvRaw;
            csvBody.get(14)[1] = tcvRaw;
            csvBody.get(15)[1] = arrRaw;
            csvReader.close();
            CSVWriter writer = new CSVWriter(new FileWriter(path));
            writer.writeAll(csvBody);
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public AdminPage clickSettings(){
        commerceSettingsButton.click();
        return new AdminPage(driver);
    }

    public String getHTMLtext(WebElement element){
        return element.getAttribute("innerHTML");
    }

    public void uploadCSV(){
        String csvPath = System.getProperty("user.dir") + "\\DealPricingCSVupload-New_Seema.csv";
        uploadCSVbutton.sendKeys(csvPath);
        ObjectRepo.waitForLoad(3000);
        saveButton.click();
        ObjectRepo.waitForLoad(5000);
        uploadMarginButtonDealPriceUser.click();
        ObjectRepo.waitForLoad(3000);
    }
}
