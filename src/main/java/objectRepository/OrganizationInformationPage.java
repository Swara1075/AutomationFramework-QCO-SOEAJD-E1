package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInformationPage { //Declaration
	
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement orgHeaderText;
	
	//initialization
	public OrganizationInformationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilization

	public WebElement getOrgHeaderText() {
		return orgHeaderText;
	}

	//Business Library
	/**
	 * This method will get organization header text
	 * @return
	 */
	public String getOrgHeader()
	{
		return orgHeaderText.getText();
	}
		
}
