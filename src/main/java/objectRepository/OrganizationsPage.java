package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage { //Rule 1: create class
	
	//Rule 2:identify elements using annotations
	
	@FindBy(xpath = "//img[@alt='Create Organization...']")
	private WebElement createOrgLookUpImage;
	
	//Rule 3:Create constructor to initialize
	public OrganizationsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Rule 4:generate getters to access

	public WebElement getcreateOrgLookUpImage() {
		return createOrgLookUpImage;
	}
	
	//Business Libraries
	/**
	 * This method will click on organization look up image
	 */
	public void clickOnOrgLookUpImage()
	{
		createOrgLookUpImage.click();
	}

}
