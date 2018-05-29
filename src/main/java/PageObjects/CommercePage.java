package PageObjects;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.ObjectRepo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CommercePage extends PageBase{

    private WebDriver driver;
    private String approvalMsgText;
    private String approvalMsgTextDealPricing;
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

    @FindBy(xpath = "((//tr[@class='general_fields'])[2]/td)[2]/div")
    public WebElement approvalMsgDealPricing;

    @FindBy(xpath = "//a[@name='submit_to_approval']")
    public WebElement submitToApproval;

    @FindBy(xpath = "(((//div[@id='reasons_popup']/div)[3]/table/tbody/tr)[1]/td)[2]/a")
    public WebElement submitPopupOk;

    @FindBy(xpath = "//select[contains(@name,'quoteType_t')]")
    public WebElement quoteType;

    //TO DO - create getter for innerHTML of this element(Deal Pricing Team Member [colttest1]-Deal Pricing)
    @FindBy(xpath = "//div[@id='attr_wrapper_1_approval_status_submit_t']/div/div/div/ul")
    public WebElement pendingApprovalMsg;

    @FindBy(xpath = "((//div[@class='nav-links'])[2]/a/img)[1]")
    public WebElement commerceSettingsButton;

    @FindBy(xpath = "//div[@id='field_wrapper_1_pricingSegment_t']/div/span")
    public WebElement pricingSegment;

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

    @FindBy(css = "#upload_key_financials")
    public WebElement uploadMarginButtonDealPriceUser;

    @FindBy(xpath = "(//tr[@class='line-item child-line-item line-item-show']/td)[3]/div/div/span")
    public WebElement quoteLineItem;

    @FindBy(id = "readonly_1_status_t")
    public WebElement quoteStatus;

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

    @FindBy(css = "#dealBackgroundTextArea_t")
    public WebElement dealBackText;

    @FindBy(xpath = "//a[contains(@class,'approve-reason show-loading-dialog')]")
    public WebElement submitDealApproval;

    @FindBy(css = "#technicalSolutionTextArea_t")
    public WebElement techSolText;

    @FindBy(css = "#competitorsTextArea_t")
    public WebElement competText;

    @FindBy(css = "#pricePositioningTextArea_t")
    public WebElement pricePositText;

    @FindBy(css = "#commercialRiskTextArea_t")
    public WebElement commerceRiskText;

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

    public String[] getApprovalMsgDealPricing(){
        String[] arr = new String[2];
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//tr[@class='general_fields']/td)[2]/div")));
        arr[0] = approvalMsg.getAttribute("innerHTML");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("((//tr[@class='general_fields'])[2]/td)[2]/div")));
        arr[1] = approvalMsgDealPricing.getAttribute("innerHTML");
        return arr;
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

    public void modifyCSVwithNewPrices(String acvRaw, String tcvRaw, String arrRaw, String igmadRaw){
        try {
            String path = System.getProperty("user.dir") + "\\Deal Pricing CSV upload-New.csv";
            Reader reader = Files.newBufferedReader(Paths.get(path));
            CSVReader csvReader = new CSVReader(reader);
            List<String[]> csvBody = csvReader.readAll();
            csvBody.get(13)[1] = acvRaw;
            csvBody.get(14)[1] = tcvRaw;
            csvBody.get(15)[1] = arrRaw;
            csvBody.get(22)[1] = igmadRaw;
            csvReader.close();
            //CSVWriter writer = new CSVWriter(new FileWriter(path));
            CSVWriter writer = new CSVWriter(new FileWriter(path),
                    CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.RFC4180_LINE_END);
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
        String csvPath = System.getProperty("user.dir") + "\\Deal Pricing CSV upload-New.csv";
        uploadCSVbutton.sendKeys(csvPath);
        ObjectRepo.waitForLoad(3000);
        //saveButton.click();
        //ObjectRepo.waitForLoad(5000);
        fillFields();
        ObjectRepo.waitForLoad(3000);
        uploadMarginButtonDealPriceUser.click();
        ObjectRepo.waitForLoad(3000);
    }

    public void fillFields(){
        dealBackText.sendKeys("aaaaa");
        ObjectRepo.waitForLoad(3000);
        technicalSolutionTextAreaDealUser.sendKeys("sdsds");
        ObjectRepo.waitForLoad(3000);
        competitorsTextAreaDealUser.sendKeys("fdfd");
        ObjectRepo.waitForLoad(3000);
        pricePositionTextAreaDealUser.sendKeys("sdssds");
        ObjectRepo.waitForLoad(3000);
        commercialRiskTextAreaDealUser.sendKeys("fdfdfdfdfd");
        ObjectRepo.waitForLoad(3000);
    }

    public boolean isENTSorWHSS(WebElement element){
        if (getHTMLtext(element).equalsIgnoreCase("ENTS")){
            return true;
        }else if (getHTMLtext(element).equalsIgnoreCase("WHSS")){
            return true;
        }else return false;
    }

    public boolean defaultQuoteType(){
        Select quoteDropDown = new Select(quoteType);
        String type = quoteDropDown.getFirstSelectedOption().getText();
        return type.equalsIgnoreCase("Price look-up");
    }
}
