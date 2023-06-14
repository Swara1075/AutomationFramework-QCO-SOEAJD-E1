package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class CreateNewContactPage extends WebDriverUtility{ //Declaration
	
	@FindBy(name="lastname")
	private WebElement lastNameEdt;
	
	@FindBy(xpath = "//input[@name='account_name']/following-sibling::img[@alt='Select']")
	private WebElement selectOrgLookUpImg;
	
	@FindBy(name = "search_text")
	private WebElement orgSearchEdt;
	
	@FindBy(name="search")
	private WebElement searchBtn;
	
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	//initialization
	public CreateNewContactPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilization
	public WebElement getLastNameEdt() {
		return lastNameEdt;
	}

	public WebElement getSelectOrgLookUpImg() {
		return selectOrgLookUpImg;
	}
	
	public WebElement getOrgSearchEdt() {
		return orgSearchEdt;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	//Business Library
	/**
	 * This method will create contact
	 * @param LASTNAME
	 */
	public void createNewContact(String LASTNAME)
	{
		lastNameEdt.sendKeys(LASTNAME);
		saveBtn.click();
	}
	
	public void createNewContact(WebDriver driver,String LASTNAME,String ORGNAME)
	{
		lastNameEdt.sendKeys(LASTNAME);
		selectOrgLookUpImg.click();
		switchToWindow(driver, "Accounts");
		orgSearchEdt.sendKeys(ORGNAME);
		searchBtn.click();
		driver.findElement(By.xpath("//a[.='"+ORGNAME+"']")).click();
		switchToWindow(driver, "Contacts");
		saveBtn.click();
		}
}
