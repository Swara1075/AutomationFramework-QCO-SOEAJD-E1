package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CamapignInformationPage { //Declaration
	
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement campaignHeaderText;
	
	//initialization
	public CamapignInformationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilization
	public WebElement getCampaignHeaderText() {
		return campaignHeaderText;
	}
	
	//Business Library
	/**
	 * This method is used to get campaign header
	 * @return
	 */
	public String getCampaignHeader()
	{
		return campaignHeaderText.getText();
	}
}
