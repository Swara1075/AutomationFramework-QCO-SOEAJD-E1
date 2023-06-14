package vtiger.Practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class PagePractice extends WebDriverUtility {
	
	@FindBy(linkText = "More")
	private WebElement moreLink;
	
	@FindBy(linkText = "Campaigns")
	private WebElement campaignsLink;
	
	//con
	public PagePractice(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getMoreLink() {
		return moreLink;
	}

	public WebElement getCampaignsLink() {
		return campaignsLink;
	}

	//bl
	public void clickOnCampaigns(WebDriver driver)
	{
		mouseHoverAction(driver, moreLink);
		campaignsLink.click();
	}
}
