package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInformationPage {
	//Declaration
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement contactHeaderText;
	
	//initialization
	public ContactInformationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//utilization
	public WebElement getContactHeaderText() {
		return contactHeaderText;
	}

	//Business Libraries
	/**
	 * This method will get header text of contact
	 * @return
	 */
	public String getContactHeader()
	{
		return contactHeaderText.getText();
	}
	
	
}
