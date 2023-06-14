package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OpportunitiesPage { //Declaration
	
	@FindBy(xpath = "//img[@alt='Create Opportunity...']")
	private WebElement opportunitiesLookUpImg;
	
	//initialization
	public OpportunitiesPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Declaration
	public WebElement getOpportunitiesLookUpImg() {
		return opportunitiesLookUpImg;
	}

	//Business Library
	/**
	 * This method will click on Opportunities LookUpImg
	 */
	public void clickOnOpportunitiesLookUpImage()
	{
		opportunitiesLookUpImg.click();
	}
}
