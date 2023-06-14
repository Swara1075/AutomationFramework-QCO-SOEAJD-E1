package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignsPage { //Declaration
	
	@FindBy(xpath = "//img[@alt='Create Campaign...']")
	private WebElement campaignLookUpImg;
	
	//initialization
	public CampaignsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//utilization
	public WebElement getCampaignLookUpImg() {
		return campaignLookUpImg;
	}

	//Business Library
	/**
	 * This method will click on campaign look up image
	 */
	public void clickOnCampaignLookUpImage()
	{
		campaignLookUpImg.click();
	}
}
