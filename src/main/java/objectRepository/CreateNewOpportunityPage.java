package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class CreateNewOpportunityPage extends WebDriverUtility { //Declaration
	
	@FindBy(name="potentialname")
	private WebElement opportunityNameEdt;
	
	@FindBy(id="related_to_type")
	private WebElement relatedToTypeDropDown;
	
	@FindBy(xpath = "//input[@id='related_to_display']/following-sibling::img[@title='Select']")
	private WebElement selectLookUpImage;
	
	@FindBy(name="search_text")
	private WebElement contactsSearchEdt;
	
	@FindBy(name="search")
	private WebElement searchBtn;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	//initialization
	public CreateNewOpportunityPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//utilization

	public WebElement getOpportunityNameEdt() {
		return opportunityNameEdt;
	}

	public WebElement getRelatedToTypeDropDown() {
		return relatedToTypeDropDown;
	}

	public WebElement getContactsSearchEdt() {
		return contactsSearchEdt;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}

	public WebElement getSelectLookUpImage() {
		return selectLookUpImage;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	//Business Library
	/**
	 * This method is used to create Opportunity
	 * @param opportunityName
	 */
	public void createNewOpportunity(String opportunityName)
	{
		opportunityNameEdt.sendKeys(opportunityName);
		saveBtn.click();
	}
	
	/**
	 * This method is used to create opportunity with contact
	 * @param driver
	 * @param opportunityName
	 * @param RELATEDTO
	 * @param LASTNAME
	 */
	public void createNewOpportunity(WebDriver driver,String opportunityName,String RELATEDTO,String LASTNAME)
	{
		opportunityNameEdt.sendKeys(opportunityName);
		handleDropDown(relatedToTypeDropDown, RELATEDTO);
		selectLookUpImage.click();
		switchToWindow(driver, "Contacts");
		contactsSearchEdt.sendKeys(LASTNAME);
		searchBtn.click();
		driver.findElement(By.xpath("//a[.=' "+LASTNAME+"']")).click();
		switchToWindow(driver, "Potentials");
		saveBtn.click();
	}

}
