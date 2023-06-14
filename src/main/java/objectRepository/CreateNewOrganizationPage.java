package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class CreateNewOrganizationPage extends WebDriverUtility {//Declaration
	
	@FindBy(name="accountname")
	private WebElement orgNameEdt;
	
	@FindBy(name="industry")
	private WebElement industryDropDown;
	
	@FindBy(name="accounttype")
	private WebElement typeDropDown;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	//initialization
	public CreateNewOrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilization
	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}

	public WebElement getIndustryDropDown() {
		return industryDropDown;
	}

	public WebElement getTypeDropDown() {
		return typeDropDown;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	//Business Library
	/**
	 * This method is used to create new organization
	 * @param ORGNAME
	 */
	public void createNewOrganization(String ORGNAME)
	{
		orgNameEdt.sendKeys(ORGNAME);
		saveBtn.click();
	}
	
	/**
	 * This method is used to create organization with industry dropdown
	 * @param ORGNAME
	 * @param VALUE
	 */
	public void createNewOrganization(String ORGNAME,String INDUSTRY) //Method overloading
	{
		orgNameEdt.sendKeys(ORGNAME);
		handleDropDown(industryDropDown, INDUSTRY);
		saveBtn.click();
	}
	
	/**
	 * This method will create organization with industry and type dropdown
	 * @param ORGNAME
	 * @param INDUSTRY
	 * @param TYPE
	 */
	public void createNewOrganization(String ORGNAME,String INDUSTRY,String TYPE)
	{
		orgNameEdt.sendKeys(ORGNAME);
		handleDropDown(industryDropDown, INDUSTRY);
		handleDropDown(typeDropDown, TYPE);
		saveBtn.click();
	}

}
