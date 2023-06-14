package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class CreateNewCampaignPage extends WebDriverUtility{ //Declaration
	
	@FindBy(name="campaignname")
	private WebElement campaignEdt;
	
	@FindBy(name="campaigntype")
	private WebElement campaignTypeDrpDown;
	
	@FindBy(name="campaignstatus")
	private WebElement campaignStatusDropDown;
	
	@FindBy(xpath = "//img[@alt='Select']")
	private WebElement selectProductLookUpImg;
	
	@FindBy(name="search_text")
	private WebElement productSearchEdt;
	
	@FindBy(xpath = "//input[@name='search']")
	private WebElement searchBtn;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	//initialization
	public CreateNewCampaignPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilization
	public WebElement getCampaignEdt() {
		return campaignEdt;
	}

	public WebElement getCampaignTypeDrpDown() {
		return campaignTypeDrpDown;
	}

	public WebElement getCampaignStatusDropDown() {
		return campaignStatusDropDown;
	}

	public WebElement getSelectProductLookUpImg() {
		return selectProductLookUpImg;
	}

	public WebElement getProductSearchEdt() {
		return productSearchEdt;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	//Business Library
	/**
	 * This method will create new campaign
	 * @param CAMPAIGNNAME
	 */
	public void createNewCamapign(String CAMPAIGNNAME)
	{
		campaignEdt.sendKeys(CAMPAIGNNAME);
		saveBtn.click();
	}

	public void createNewCamapign(WebDriver driver,String CAMPAIGNNAME,String PRODUCTNAME,String TYPE,String STATUS)
	{
		campaignEdt.sendKeys(CAMPAIGNNAME);
		handleDropDown(campaignTypeDrpDown, TYPE);
		handleDropDown(campaignStatusDropDown, STATUS);
		selectProductLookUpImg.click();
		switchToWindow(driver, "Products");
		productSearchEdt.sendKeys(PRODUCTNAME);
		searchBtn.click();
		driver.findElement(By.xpath("//a[.='"+PRODUCTNAME+"']")).click();
		switchToWindow(driver, "Campaigns");
	    saveBtn.click();
	}

}
