package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OpportunityInformationPage { //Declaration
	
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement opportunityHeaderText;
	
	//initialization
	public OpportunityInformationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilization
	public WebElement getOpportunityHeaderText() {
		return opportunityHeaderText;
	}
	
	//Business Library
	/**
	 * This method will get header of opportunity
	 * @return
	 */
	public String getOpportunityHeader()
	{
		return opportunityHeaderText.getText();
	}

	
}
