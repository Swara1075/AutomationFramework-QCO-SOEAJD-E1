package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {//Rule 1: create class
	
	//Rule 2:identify elements using annotations
	
	@FindBy(xpath = "//img[@alt='Create Contact...']")
	private WebElement createContactsLookUpImage;
	
	//Rule 3:Create constructor to initialize
	public ContactsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Rule 4:generate getters to access
	public WebElement getcreateContactsLookUpImage() {
		return createContactsLookUpImage;
	}
	
	//Business Libraries
	/**
	 * This method will click on create Contacts look up image
	 */
	public void clickOnContactsLookUpImage()
	{
		createContactsLookUpImage.click();
	}

	


}
